package com.banane.cootiecatcher;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import java.lang.reflect.AccessibleObject;

/**
 * Created by annabillstrom on 11/3/15.
 */
public class FortuneDetailActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fortune_detail);

        getIntent();
    }


    public void clearFortune(View v){

    }

    public void saveFortune(View v){

    }
}
