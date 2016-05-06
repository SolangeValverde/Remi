package com.example.solange.remi;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.drawable.Drawable;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;

import java.util.ArrayList;
//import com.badlogic.androidgames.framework.Sound;

/**
 * Created by Solange on 25/04/2016.
 */
public class MainFood {
    public int xPos, yPos, xFinal, yFinal, width, height;
    public float currXPos, currYPos;
    public ImageButton food_button;
    public Drawable image;

    public MainFood(int xPos, int yPos, int xFinal, ImageButton food_button, Drawable image) {
        //super();
        this.xPos = xPos;
        this.yPos = yPos;
        this.xFinal = xFinal;
        this.yFinal = 0;
        this.food_button = food_button;
        this.image = image;
        this.height = 100;
        this.width = 100;
    }

    public void update() {
       // xPos += xVel;
     //   yPos += yVel;
    }

    public void removeFood(ArrayList foods, int i){
        foods.remove(foods.get(i));
    }
/*
    public void move2() {
        TranslateAnimation animation = new TranslateAnimation(xPos, xFinal,
                yPos, yPos);          //  new TranslateAnimation(xFrom,xTo, yFrom,yTo)
        animation.setDuration(7000);  // animatranslateYAnimationtion duration
        animation.setRepeatMode(1);   // repeat animation (left to right, right to left )
       // if (xPos < -21 || xPos > 900) {
            animation.setFillAfter(true);
              //  }
        food_button.startAnimation(animation);  // start animation
    }
*/
    public void move(){
        ObjectAnimator translateXAnimation= ObjectAnimator.ofFloat(food_button, "translationX", xPos, xFinal);
        ObjectAnimator translateYAnimation= ObjectAnimator.ofFloat(food_button, "translationY", yPos, yPos);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(7000);
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
                currYPos = (Float)animation.getAnimatedValue();
            }
        });

    }

    public void fall(){
        yFinal = 700;
        ObjectAnimator translateXAnimation= ObjectAnimator.ofFloat(food_button, "translationX", currXPos, currXPos);
        ObjectAnimator translateYAnimation= ObjectAnimator.ofFloat(food_button, "translationY", yPos, yFinal);

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
/*
    public void fall2() {
        yFinal = 700;
        TranslateAnimation animation2 = new TranslateAnimation(currXPos, currXPos,
                yPos, yFinal);
        animation2.setDuration(9000);  // animation duration
       // if (yPos > 600) {
            animation2.setFillAfter(true);
        //}
        food_button.startAnimation(animation2);  // start animation
        //yPos = yFinal;
    }
*/


    public void draw() {
        food_button.setBackgroundDrawable(image);
        food_button.setX(xPos);
        food_button.setY(yPos);
    }
}
