package com.banane.cootiecatcher;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

/**
 * Created by annabillstrom on 11/3/15.
 */
public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main_activity);
        Button btn = (Button)findViewById(R.id.settings_btn);
        Typeface face= Typeface.createFromAsset(getAssets(),
                "fonts/WalterTurncoat.ttf");

        btn.setTypeface(face);
    }

    public void viewSettings(View v){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
}
