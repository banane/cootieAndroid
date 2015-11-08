package com.banane.cootiecatcher;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
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
    int[][] numbers = { {3,4,8,7},{2,5,1,6}};
    LinearLayout gameLayout;
    TextView revealFortune;
    String[] colors = {"yellow","blue","red","green"};
    Button againBtn;
    ImageView gameImage;
    MediaPlayer mp;
    AnimationDrawable mBackgroundAnimation;
    boolean animationIsRunning = false;
    int startingBit;
    AnimationDrawable animation;

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
        revealFortune.setTextSize(24.0f);

        gameImage = (ImageView)findViewById(R.id.game_image);

        mp = MediaPlayer.create(this, R.raw.cootie);
    }

    public void viewSettings(View v){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    private void click(int quadrant){
        Log.d(TAG,"----");
        Log.d(TAG,"I'm on turn: " + turns);
        Log.d(TAG, "quadrant: " + quadrant + " state: " + state);
        if(state == 1) {
            Log.d(TAG, "should be wide");
        }
        if(state == 2){
            Log.d(TAG,"should be tall");
        }
        if (state ==0 || turns == 0){
            Log.d(TAG,"i'm on color: " + colors[quadrant]);
            animate(colors[quadrant].length());
            turns += 1;
        } else if(turns < TURN_LIMIT) {
            Log.d(TAG,"i'm on number: " + numbers[state-1][quadrant] + " index:");
            animate(numbers[state-1][quadrant]);
            turns += 1;
        } else {
            revealFortune(quadrant, state);
            turns = 0;
        }
    }

    private void animate(int count){
        Log.d(TAG, "doing animation, pretend!" + count);
        gameImage.setVisibility(View.VISIBLE);
        //see if end state is positive or negative
        int[] images = { R.drawable.wide, R.drawable.tall}; // keep 0 and 1 in order

        Drawable[] layers = new Drawable[count];
        if(state == 0){
            startingBit = 0;
        } else {
            startingBit = state - 1;
            startingBit ^=1; // switch it up
        }

        final int interval = 750;
        int duration = interval * count;
        animation = new AnimationDrawable();
        animation.setOneShot(true);

        for(int i=0;i<count;i++){
            animation.addFrame(getResources().getDrawable(images[startingBit]),interval);
            startingBit ^= 1;
        }
        startingBit ^=1; // switch it back

        state = startingBit +1; // pop it back into state

        gameImage.setImageDrawable(animation);

        gameImage.post(new Starter());
        Log.d(TAG, " count: " + count);

        mp.start();

        CountDownTimer timer = new CountDownTimer(duration, 320) {
            @Override
            public void onTick(long millisUntilFinished) {
                // Nothing to do
                if (mp.isPlaying()) {
                } else {
                    mp.start();
                }
            }

            @Override
            public void onFinish() {
                if (mp.isPlaying()) {
                    mp.stop();
                }
            }
        };
        timer.start();

    }
    class Starter implements Runnable {

        public void run() {
            animation.start();
        }


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

        int number = numbers[theState-1][theQuadrant];
        Log.d(TAG, "number: " + number);
        int position = number -1;
        Log.d(TAG, "position: " + position);
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
