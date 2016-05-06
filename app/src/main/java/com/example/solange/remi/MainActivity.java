package com.example.solange.remi;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    Random r = new Random();
    int y = r.nextInt(20);
    int x = r.nextInt(20 - 10) + 10;
    RelativeLayout.LayoutParams parms;
    LinearLayout.LayoutParams par;
    float dx=0,dy=0,x2=0,y2=0;
    int vel = r.nextInt(6);
    ArrayList<MainFood> foods = new ArrayList<>();
    ImageView menubg, remiAccessory_imgView,animation, brush, hand, shine;
    Drawable imageBurger, imagePizza, imageCookie;
    Accessory remisaccessory;
    Remi remi;
    boolean menuOff = true;
    boolean hit;
    boolean options = true;
    AnimationDrawable frameAnimation;
    Button scoreButton;
    int intScore;
    Score score ;
    ImageButton food_button1,food_button2,food_button3, food_button4, remiButton,menub1, menub2, menub3, menub4, menub5, menub6, menub7,menub8, menub9;
    ImageButton loveButton, feedButton, brushButton;
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
        loveButton= (ImageButton) findViewById(R.id.loveButton);
        feedButton= (ImageButton) findViewById(R.id.feedButton);
        brushButton= (ImageButton) findViewById(R.id.brushButton);
        brush=(ImageView) findViewById(R.id.brush);
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

        drawFood();
        score.drawScore();
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.woof);
        remi.draw();
        remisaccessory.draw();
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

        loveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                optionsOnOff();
                loveStarts();
            }
        });
        
        feedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                optionsOnOff();
                foodStarts();
            }
        });brushButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                optionsOnOff();
                brushStarts();
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
public void menuOnOff(final boolean boolMenuOff){
    if (boolMenuOff){
        menuFrame.setVisibility(View.GONE);
        menub1.setVisibility(View.GONE);
        menub2.setVisibility(View.GONE);
        menub3.setVisibility(View.GONE);
        menub4.setVisibility(View.GONE);
        menub5.setVisibility(View.GONE);
        menub6.setVisibility(View.GONE);
        menub7.setVisibility(View.GONE);
        menub8.setVisibility(View.GONE);
        menub9.setVisibility(View.GONE);
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
            currFood.move();
            currFood.draw();
            currFood.fall();
        }
    }
    /*
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN :
            {
                parms = (RelativeLayout.LayoutParams) brush.getLayoutParams();
                par = (LinearLayout.LayoutParams) getWindow().findViewById(Window.ID_ANDROID_CONTENT).getLayoutParams();
                dx = event.getRawX() - parms.leftMargin;
                dy = event.getRawY() - parms.topMargin;
            }
            break;
            case MotionEvent.ACTION_MOVE :
            {
                x2 = event.getRawX();
                y2 = event.getRawY();
                parms.leftMargin = (int) (x-dx);
                parms.topMargin = (int) (y - dy);
                brush.setLayoutParams(parms);
            }
            break;
            case MotionEvent.ACTION_UP :
            {

            }
            break;
        }
        return true;
    }
*/

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


    public void optionsOnOff(){
        loveButton.setVisibility(View.GONE);
        feedButton.setVisibility(View.GONE);
        brushButton.setVisibility(View.GONE);
    }
    private float xb, yb;
    private int mx, my;

    public void brushStarts(){
    brush.setVisibility(View.VISIBLE);
    brush.setOnTouchListener(new View.OnTouchListener()
    {
        public boolean onTouch(View v, MotionEvent event)
        {
                switch(event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        xb = event.getX();
                        yb = event.getY();
                    case MotionEvent.ACTION_MOVE:
                        mx = (int)(event.getRawX() - xb);
                        my = (int)(event.getRawY() -200 - yb);
                        brush.setX(mx);
                        brush.setY(my);

                        break;
                }
                return true;
            }});
    if (((Math.abs(remi.xPos - brush.getX()) < (int) ((brush.getWidth()/ 2) + remiButton.getWidth())) &&
                (Math.abs(remi.yPos - brush.getY()) < (int) ((brush.getHeight()/ 2)+remiButton.getWidth())))){

       // scoreButton.setBackgroundColor(222);
    }
    }

    public void foodStarts(){
        createFood();
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
    public void loveStarts(){
        hand.setVisibility(View.VISIBLE);
        hand.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                switch(event.getAction())
                {
                    case MotionEvent.ACTION_DOWN :
                    {
                        parms = (RelativeLayout.LayoutParams) brush.getLayoutParams();
                        par = (LinearLayout.LayoutParams) getWindow().findViewById(Window.ID_ANDROID_CONTENT).getLayoutParams();
                        dx = event.getRawX() - parms.leftMargin;
                        dy = event.getRawY() - parms.topMargin;
                    }
                    break;
                    case MotionEvent.ACTION_MOVE :
                    {
                        x2 = event.getRawX();
                        y2 = event.getRawY();
                        parms.leftMargin = (int) (x-dx);
                        parms.topMargin = (int) (y - dy);
                        brush.setLayoutParams(parms);
                    }
                    break;
                    case MotionEvent.ACTION_UP :
                    {

                    }
                    break;
                }
                return true;
            }
        });
    }
    public void shine(){

    }



}
