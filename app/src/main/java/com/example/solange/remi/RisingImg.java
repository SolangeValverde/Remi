package com.example.solange.remi;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

/**
 * Created by miroslav on 05/05/16.
 */
public class RisingImg {
    public int xPos, yPos, xFinal, yFinal, width, height;
    public float currXPos, currYPos;

    public ImageView rising_img, rising_img2;
    public Drawable image;
    public Activity activity;

    public RisingImg(int xPos, int yPos, Activity _activity,ImageView rising_img){
        this.xPos = xPos;
        this.yPos = yPos;
        this.yFinal = 0;
        //this.image = image;
        this.activity = _activity;
        this.height = 100;
        this.width = 100;
        this.rising_img=rising_img;
        //rising_img = (ImageView) this.activity.findViewById(R.id.risible);
    }

    public void setRisingImage(int imageId){
        rising_img.setBackgroundResource(imageId);
       // rising_img2.setBackgroundResource(imageId);
    }

    public void rise(){
        yFinal = 0;
        ObjectAnimator translateXAnimation= ObjectAnimator.ofFloat(rising_img, "translationX", xPos, xPos);
        ObjectAnimator translateYAnimation= ObjectAnimator.ofFloat(rising_img, "translationY", yPos, yFinal);

        AnimatorSet set = new AnimatorSet();
        set.setDuration(9000);
        set.playTogether(translateXAnimation, translateYAnimation);
        set.start();

        translateXAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currXPos = (Float)animation.getAnimatedValue();
            }
        });

        translateYAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currXPos = (Float)animation.getAnimatedValue();
            }
        });

    }

    public void fadeOut(final ImageView img)
    {
        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setDuration(1000);

        fadeOut.setAnimationListener(new Animation.AnimationListener()
        {
            public void onAnimationEnd(Animation animation)
            {
                img.setVisibility(View.GONE);
            }
            public void onAnimationRepeat(Animation animation) {}
            public void onAnimationStart(Animation animation) {}
        });

        img.startAnimation(fadeOut);
    }

    public void draw() {
       // rising_img.setBackgroundResource(image);
        rising_img.setX(xPos);
        rising_img.setY(yPos);
    }


}
