package com.example.solange.remi;

import android.app.Activity;
import android.widget.ImageButton;

/**
 * Created by Solange on 25/04/2016.
 */
public class Remi {
    public int xPos, yPos, xVel, yVel;
    public ImageButton remi_button;
    public Accessory accessory;
    public Activity activity;

    public Remi(int xPos, int yPos, int xVel, Activity _activity) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.xVel = xVel;
        this.activity = _activity;
        remi_button = (ImageButton) this.activity.findViewById(R.id.img_Remi);
    }

    boolean collision(MainFood other) {
        return ((Math.abs(xPos - other.currXPos) < (int) ((other.food_button.getWidth()/ 2))) &&
                (Math.abs(yPos - other.currYPos) < (int) ((other.food_button.getHeight()/ 2))));
    }

    public void draw() {
        remi_button.setX(xPos);
        remi_button.setY(yPos);
    }

}
