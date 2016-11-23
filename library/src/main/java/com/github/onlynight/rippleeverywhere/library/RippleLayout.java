package com.github.onlynight.rippleeverywhere.library;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.LinearLayout;

/**
 * Created by lion on 2016/11/11.
 */

public class RippleLayout extends LinearLayout {

    private static final int RIPPLE_CENTER_ALIGN_TOP_LEFT = 0;
    private static final int RIPPLE_CENTER_ALIGN_TOP_RIGHT = 1;
    private static final int RIPPLE_CENTER_ALIGN_BOTTOM_LEFT = 2;
    private static final int RIPPLE_CENTER_ALIGN_BOTTOM_RIGHT = 3;

    private int centerX = -1;
    private int centerY = -1;
    private float radius = 0;
    private float maxRadius = 0;
    private boolean running = false;
    private float startValue = 0;
    private float endValue = 1;
    private int duration = 300;
    private int rippleCenterAlign = RIPPLE_CENTER_ALIGN_TOP_LEFT;
    private int width = 0;
    private int height = 0;

    private float startX = 0;
    private float startY = 0;

    private ObjectAnimator radiusAnimator;
    private Path ripplePath;

    public RippleLayout(Context context) {
        super(context);
        init(null);
    }

    public RippleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.
                obtainStyledAttributes(attrs, R.styleable.RippleLayout);
        init(array);
    }

    public RippleLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.
                obtainStyledAttributes(
                        attrs, R.styleable.RippleLayout, defStyleAttr, 0);
        init(array);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public RippleLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        TypedArray array = context.
                obtainStyledAttributes(attrs, R.styleable.RippleLayout,
                        defStyleAttr, defStyleRes);
        init(array);
    }

    private void init(TypedArray array) {
        ripplePath = new Path();

        if (array != null) {
            startX = (int) array.getDimension(
                    R.styleable.RippleLayout_ripple_center_x, -1);
            startY = (int) array.getDimension(
                    R.styleable.RippleLayout_ripple_center_y, -1);
            duration = array.getInteger(
                    R.styleable.RippleLayout_ripple_anim_time, duration);
            startValue = array.getFloat(
                    R.styleable.RippleLayout_ripple_start_value, startValue);
            endValue = array.getFloat(
                    R.styleable.RippleLayout_ripple_end_value, endValue);
            rippleCenterAlign = array.getInt(
                    R.styleable.RippleLayout_ripple_center_align,
                    -1);

            centerX = (int) startX;
            centerY = (int) startY;
        }

        radiusAnimator = ObjectAnimator.ofFloat(this, "animValue", startValue, endValue);
        radiusAnimator.setDuration(duration);
        radiusAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        radiusAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                running = true;
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                running = false;
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        width = right - left;
        height = bottom - top;
        initCenter(left, top, right, bottom);
        maxRadius = maxRadius(left, top, right, bottom);
        super.onLayout(changed, left, top, right, bottom);
    }

    private void initCenter(int left, int top, int right, int bottom) {
        if (centerX == -1 && centerY == -1) {
            centerX = (right - left) / 2;
            centerY = (bottom - top) / 2;
        } else {
            switch (rippleCenterAlign) {
                case RIPPLE_CENTER_ALIGN_TOP_LEFT: {
                    centerX = (int) startX;
                    centerY = (int) startY;
                }
                break;
                case RIPPLE_CENTER_ALIGN_TOP_RIGHT: {
                    centerX = (int) (width - startX);
                    centerY = (int) startY;
                }
                break;
                case RIPPLE_CENTER_ALIGN_BOTTOM_LEFT: {
                    centerX = (int) startX;
                    centerY = (int) (height - startY);
                }
                break;
                case RIPPLE_CENTER_ALIGN_BOTTOM_RIGHT: {
                    centerX = (int) (width - startX);
                    centerY = (int) (height - startY);
                }
                break;
            }
        }
    }

    private float maxRadius(int left, int top, int right, int bottom) {
        float x = Math.max(Math.abs(centerX - left), Math.abs(centerX - right));
        float y = Math.max(Math.abs(centerY - top), Math.abs(centerY - bottom));
        return (float) Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    public void setAnimValue(float radius) {
        this.radius = radius * maxRadius;
        invalidate();
    }

    @Override
    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
        if (running) {
            final int state = canvas.save();
            ripplePath.reset();
            ripplePath.addCircle(centerX, centerY, radius, Path.Direction.CW);
            canvas.clipPath(ripplePath);
            boolean invalidated = super.drawChild(canvas, child, drawingTime);
            canvas.restoreToCount(state);
            return invalidated;
        }
        return super.drawChild(canvas, child, drawingTime);
    }

    /**
     * get the radius change animator,
     * so that you can listen the animator status.
     *
     * @return animator
     */
    public ObjectAnimator getRadiusAnimator() {
        return radiusAnimator;
    }
}
