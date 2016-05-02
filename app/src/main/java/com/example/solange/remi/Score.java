package com.example.solange.remi;

import android.app.Activity;
import android.widget.Button;

/**
 * Created by Solange on 28/04/2016.
 */
public class Score {
    int score;
    Button scoreButton;
    public Activity activity;

    public Score(int score, Activity _activity){
        this.score=score;
        this.activity = _activity;
        this.scoreButton = (Button) this.activity.findViewById(R.id.scoreButton);
    }

    public void drawScore(){
        scoreButton.setText(Integer.toString(score));
    }
    public void updateScore(int NewHit){
        score = score + NewHit;

                    }
}
