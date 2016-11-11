package com.github.onlynight.rippleeverywhere;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

/**
 * Created by lion on 2016/11/11.
 */

public class RippleImageView extends ImageView {

    private int centerX = 0;
    private int centerY = 0;
    private float radius = 0;
    private float maxRadius = 0;
    private boolean running = false;

    private ObjectAnimator radiusAnimator;
    private Path ripplePath;

    public RippleImageView(Context context) {
        super(context);
        init();
    }

    public RippleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RippleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(21)
    public RippleImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        ripplePath = new Path();

        radiusAnimator = ObjectAnimator.ofFloat(this, "animValue", 0, 1);
        radiusAnimator.setDuration(1000);
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
        super.onLayout(changed, left, top, right, bottom);
        centerX = (right - left) / 2;
        centerY = (bottom - top) / 2;
        maxRadius = maxRadius(left, top, right, bottom);
    }

    private float maxRadius(int left, int top, int rigt, int bottom) {
        return (float) Math.sqrt(Math.pow(rigt - left, 2) + Math.pow(bottom - top, 2) / 2);
    }

    public void setAnimValue(float radius) {
        this.radius = radius * maxRadius;
        System.out.println("radius = " + this.radius);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (running) {
            final int state = canvas.save();
            ripplePath.reset();
            ripplePath.addCircle(centerX, centerY, radius, Path.Direction.CW);
            canvas.clipPath(ripplePath);
            super.onDraw(canvas);
            canvas.restoreToCount(state);
            return;
        }
        super.onDraw(canvas);
    }

    public void startAnimation() {
        if (radiusAnimator.isRunning()) {
            radiusAnimator.cancel();
        }

        radiusAnimator.start();
    }
}
