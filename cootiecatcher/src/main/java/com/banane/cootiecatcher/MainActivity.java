package com.banane.cootiecatcher;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.opengl.Visibility;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by annabillstrom on 11/3/15.
 */
public class MainActivity extends Activity {
    private static final String TAG = "COOTIE";
    int state = 0; // 0 closed, 1 open-wide, 2 open-tall
    int turns = 0;
    int TURN_LIMIT = 2;
    int[][] numbers = { {2,5,1,6}, { 3,4,7,8}};
    LinearLayout gameLayout;
    TextView revealFortune;
    String[] colors = {"yellow","blue","red","green"};
    Button againBtn;
    ImageView gameImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main_activity);
        Button btn = (Button)findViewById(R.id.settings_btn);
        againBtn = (Button)findViewById(R.id.again_btn);
        Typeface face= Typeface.createFromAsset(getAssets(),
                "fonts/WalterTurncoat.ttf");

        btn.setTypeface(face);
        againBtn.setTypeface(face);
        gameLayout = (LinearLayout)findViewById(R.id.game_layout);
        revealFortune = (TextView)findViewById(R.id.reveal_fortune);
        revealFortune.setTypeface(face);
        gameImage = (ImageView)findViewById(R.id.game_image);
    }

    public void viewSettings(View v){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    private void click(int quadrant){
        if (state ==0){
            animate(colors[quadrant].length());
            turns += 1;
        } else if(turns < TURN_LIMIT) {
            animate(numbers[state-1][quadrant]);
            turns += 1;
        } else {
            turns = 0;
            revealFortune(quadrant, state);
            state = 0;
        }
    }

    private void animate(int count){
        Log.d(TAG, "doing animation, pretend!" + count);
        gameImage.setVisibility(View.VISIBLE);
        //see if end state is positive or negative
        int[] images = { R.drawable.wide, R.drawable.tall};

        Drawable[] layers = new Drawable[count];
        int startingBit = state;
        if(state > 0) {
            startingBit = state - 1;
        }
        for(int i=0;i<count;i++){
            layers[i] = ContextCompat.getDrawable(this, images[startingBit]);
            startingBit ^= 1;
        }
        TransitionDrawable transition = new TransitionDrawable(layers);
        gameImage.setImageDrawable(transition);
        transition.startTransition(500);
        state = startingBit + 1;
    }

    public void again(View v){
        state = 0;
        turns = 0;
        gameImage.setImageResource(R.drawable.closed);
        gameImage.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        revealFortune.setVisibility(View.INVISIBLE);
        againBtn.setVisibility(View.INVISIBLE);
    }

    private void changeState(int newState){
        state = newState;
        if(newState == 1) {
            gameImage.setImageResource(R.drawable.wide); // 1
        } else {
            gameImage.setImageResource(R.drawable.tall);  // 2
        }
    }

    private void revealFortune(int theQuadrant, int theState){
        Log.d(TAG, "qu: " + theQuadrant + " st: " +  theState);

        int position = numbers[theState-1][theQuadrant];
        CootieApp app = (CootieApp)getApplicationContext();
        String fortune = app.values[position];
        gameLayout.setVisibility(View.INVISIBLE);
        gameImage.setVisibility(View.INVISIBLE);
        Log.d(TAG, "fortune: " + fortune );
        revealFortune.setText(fortune);
        revealFortune.setVisibility(View.VISIBLE);
        againBtn.setVisibility(View.VISIBLE);

    }

    public void clickTopLeft(View v){
        click(0);
    }
    public void clickTopRight(View v){
        click(1);
    }
    public void clickBottomLeft(View v){
        click(2);
    }
    public void clickBottomRight(View v){
        click(3);
    }
}
