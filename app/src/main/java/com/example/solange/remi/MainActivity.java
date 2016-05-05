package com.example.solange.remi;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    Random r = new Random();
    int y = r.nextInt(20);
    int x = r.nextInt(20 - 10) + 10;
    int vel = r.nextInt(6);
    ArrayList<MainFood> foods = new ArrayList<>();
    ImageView menubg, remiAccessory_imgView,animation;
    Drawable imageBurger, imagePizza, imageCookie;
    Accessory remisaccessory;
    Remi remi;
    boolean menuOff = true;
    boolean hit;
    AnimationDrawable frameAnimation;
    Button scoreButton;
    int intScore;
    Score score ;
    ImageButton food_button1,food_button2,food_button3, food_button4, remiButton,menub1, menub2, menub3, menub4, menub5, menub6, menub7,menub8, menub9;
    FrameLayout menuFrame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        ImageButton menuButton = (ImageButton) findViewById(R.id.menu_button);
        menuButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (menuOff) {
                    menuOff = false;
                    menuOnOff(menuOff);
                } else {
                    menuOff = true;
                    menuOnOff(menuOff);
                }
            }
        });



        food_button1 = (ImageButton) findViewById(R.id.btn_burger);
        food_button2 = (ImageButton) findViewById(R.id.btn_burger2);
        food_button3 = (ImageButton) findViewById(R.id.btn_burger3);
        food_button4 = (ImageButton) findViewById(R.id.btn_burger4);
        imageBurger = (Drawable)getResources().getDrawable(R.drawable.burger);
        imagePizza = (Drawable)getResources().getDrawable(R.drawable.pizza);
        imageCookie = (Drawable)getResources().getDrawable(R.drawable.cookie);
        menuFrame = (FrameLayout) findViewById(R.id.frameLayout2);
        menub1 = (ImageButton) findViewById(R.id.menu_button1);
        menub2 = (ImageButton) findViewById(R.id.menu_button2);
        menub3 = (ImageButton) findViewById(R.id.menu_button3);
        menub4 = (ImageButton) findViewById(R.id.menu_button4);
        menub5 = (ImageButton) findViewById(R.id.menu_button5);
        menub6 = (ImageButton) findViewById(R.id.menu_button6);
        menub7 = (ImageButton) findViewById(R.id.menu_button7);
        menub8 = (ImageButton) findViewById(R.id.menu_button8);
        menub9 = (ImageButton) findViewById(R.id.menu_button9);
        scoreButton = (Button)findViewById(R.id.scoreButton);
        score = new Score(intScore,this);
        remiButton = (ImageButton) findViewById(R.id.img_Remi);
        remiButton.setBackgroundResource(R.drawable.remi1);
        animation = (ImageView) findViewById(R.id.animation);
        animation.setBackgroundResource(R.drawable.animation);
        //remiAccessory_imgView= (ImageView) findViewById(R.id.accessory);
        frameAnimation = (AnimationDrawable) animation.getBackground();
        remi = new Remi(0, 0, 0, this);
        remisaccessory = new Accessory(0, 0, 0, this);
        setSupportActionBar(toolbar);
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.woof);
        remi.draw();
        remisaccessory.draw();
        createFood();
        drawFood();
        score.drawScore();
        remiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();
            }
        });

        menub1.setOnClickListener(this);
        menub2.setOnClickListener(this);
        menub3.setOnClickListener(this);
        menub4.setOnClickListener(this);
        menub5.setOnClickListener(this);
        menub6.setOnClickListener(this);
        menub7.setOnClickListener(this);
        menub8.setOnClickListener(this);
        menub9.setOnClickListener(this);



        for (int i = 0; i<foods.size(); i++) {
            final MainFood currFood = foods.get(i);
            final int j = i;
            currFood.move();
            currFood.food_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    currFood.fall();
                    hit = remi.collision(currFood);
                    if (hit){
                        score.updateScore(10);
                        score.drawScore();
                        currFood.removeFood(foods,j);

                    }
                    if (currFood.currYPos> 699){
                        currFood.removeFood(foods,j);
                    }
                }
            });

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
public void menuOnOff(final boolean boolMenuOff){
    if (boolMenuOff){
        menuFrame.setVisibility(View.INVISIBLE);
        menub1.setVisibility(View.INVISIBLE);
        menub2.setVisibility(View.INVISIBLE);
        menub3.setVisibility(View.INVISIBLE);
        menub4.setVisibility(View.INVISIBLE);
        menub5.setVisibility(View.INVISIBLE);
        menub6.setVisibility(View.INVISIBLE);
        menub7.setVisibility(View.INVISIBLE);
        menub8.setVisibility(View.INVISIBLE);
        menub9.setVisibility(View.INVISIBLE);
    } else{
        menuFrame.setVisibility(View.VISIBLE);
        menub1.setVisibility(View.VISIBLE);
        menub2.setVisibility(View.VISIBLE);
        menub3.setVisibility(View.VISIBLE);
        menub4.setVisibility(View.VISIBLE);
        menub5.setVisibility(View.VISIBLE);
        menub6.setVisibility(View.VISIBLE);
        menub7.setVisibility(View.VISIBLE);
        menub8.setVisibility(View.VISIBLE);
        menub9.setVisibility(View.VISIBLE);
    }
}

    // Called when Activity becomes visible or invisible to the user
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            // Starting the animation when in Focus
            frameAnimation.start();
        } else {
            // Stoping the animation when not in Focus
            frameAnimation.stop();
        }
    }

    public boolean addFood(MainFood newFood) {
        foods.add(newFood);
        return true;
    }

    public void createFood() {
        //    MainFood f  = new MainFood( xPos  , yPos,xFinal, food_button, image);
        final MainFood f1 = new MainFood((-1 * x), 0,   990, food_button1, imagePizza);
        final MainFood f2 = new MainFood(500,    -100, -520, food_button2, imageBurger);
        final MainFood f3 = new MainFood((-1 * x), 20,  990, food_button3, imageBurger);
        final MainFood f4 = new MainFood(500,     150, -520, food_button4, imageCookie);
        addFood(f1);
        addFood(f2);
        addFood(f3);
        addFood(f4);
    }

    public void drawFood() {
        for (int i = 0; i < foods.size(); i++) {
            MainFood currFood = foods.get(i);
            currFood.update();
            currFood.draw();
            currFood.fall();
        }
    }
    public void collision(Remi rem){
        for (int i = 0; i < foods.size(); i++) {
            MainFood currFood = foods.get(i);
        if (rem.collision(currFood) == true) {
        }}
//        asdfasdf
    }

    @Override
    public void onClick(View v) {
       switch(v.getId()) {
           case R.id.menu_button1:
               remisaccessory.setImage(0);
               menuOff = true;
               menuOnOff(menuOff);
               break;
           case R.id.menu_button2:
               remisaccessory.setImage(R.drawable.remicollar3);
               menuOff = true;
               menuOnOff(menuOff);
               break;
           case R.id.menu_button3:
               remisaccessory.setImage(R.drawable.remicollar4);
               menuOff = true;
               menuOnOff(menuOff);
               break;
           case R.id.menu_button4:
               remisaccessory.setImage(R.drawable.remicollar2);
               menuOff = true;
               menuOnOff(menuOff);
               break;
           case R.id.menu_button5:
               remisaccessory.setImage(R.drawable.remicollar1);
               menuOff = true;
               menuOnOff(menuOff);
               break;
           case R.id.menu_button6:
               remisaccessory.setImage(R.drawable.remibandana4);
               menuOff = true;
               menuOnOff(menuOff);
               break;
           case R.id.menu_button7:
               remisaccessory.setImage(R.drawable.remibandana2);
               menuOff = true;
               menuOnOff(menuOff);
               break;
           case R.id.menu_button8:
               remisaccessory.setImage(R.drawable.remibandana1);
               menuOff = true;
               menuOnOff(menuOff);
               break;
           case R.id.menu_button9:
               remisaccessory.setImage(R.drawable.remibandana3);
               menuOff = true;
               menuOnOff(menuOff);
               break;
           default:
               break;

       }
    }
}
