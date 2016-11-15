package com.github.onlynight.rippleeverywhere;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

/**
 * Created by lion on 2016/11/11.
 * <p>
 * RippleImageView use the {@link Path#addCircle} function
 * to draw the view when {@link RippleImageView#onDraw} called.
 * <p>
 * When you call {@link View#invalidate()} function,then the
 * {@link View#onDraw(Canvas)} will be called. In that way you
 * can use {@link Path#addCircle} to draw every frame, you will
 * see the ripple animation.
 */

public class RippleImageView extends ImageView {

    // view center x
    private int centerX = 0;
    // view center y
    private int centerY = 0;
    // ripple animation current radius
    private float radius = 0;
    // the max radius that ripple animation need
    private float maxRadius = 0;
    // record the ripple animation is running
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

        // initial the animator, when animValue change,
        // radiusAnimator will call {@link this#setAnimValue} method.
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

    /**
     * Calculate the max ripple animation radius.
     *
     * @param left   view left
     * @param top    view top
     * @param right  view right
     * @param bottom view bottom
     * @return
     */
    private float maxRadius(int left, int top, int right, int bottom) {
        return (float) Math.sqrt(Math.pow(right - left, 2) + Math.pow(bottom - top, 2) / 2);
    }

    /**
     * This method will be called by {@link this#radiusAnimator}
     * reflection calls.
     *
     * @param value animation current value
     */
    public void setAnimValue(float value) {
        this.radius = value * maxRadius;
        System.out.println("radius = " + this.radius);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (running) {
            // get canvas current state
            final int state = canvas.save();
            // add circle to path to crate ripple animation
            // attention: you must reset the path first,
            // otherwise the animation will run wrong way.
            ripplePath.reset();
            ripplePath.addCircle(centerX, centerY, radius, Path.Direction.CW);
            canvas.clipPath(ripplePath);

            // the {@link View#onDraw} method must be called before
            // {@link Canvas#restoreToCount}, or the change will not appear.
            super.onDraw(canvas);
            canvas.restoreToCount(state);
            return;
        }

        // in a normal condition, you should call the
        // super.onDraw the draw the normal situation.
        super.onDraw(canvas);
    }

    /**
     * call the {@link Animator#start()} function to start the animation.
     */
    public void startAnimation() {
        if (radiusAnimator.isRunning()) {
            radiusAnimator.cancel();
        }

        radiusAnimator.start();
    }
}
