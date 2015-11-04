package com.banane.cootiecatcher;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import java.lang.reflect.AccessibleObject;

/**
 * Created by annabillstrom on 11/3/15.
 */
public class FortuneDetailActivity extends Activity {
    EditText ed;
    String fortune;
    int position;
    private static int RESULT_OK = 1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.fortune_detail);
        ed = (EditText)findViewById(R.id.fortune_edittext);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        if(b.containsKey("position") && b.containsKey("fortune")){
            ed.setText(b.getString("fortune"));
            position = b.getInt("position");
        }
        setupButtons();
    }


    public void clearFortune(View v){
        ed.setText("");
    }

    public void saveFortune(View v){
        Intent returnIntent = new Intent();
        // you can use this to pass your stuff to the Requesting activity
        returnIntent.putExtra("position",position);
        returnIntent.putExtra("fortune", ed.getText().toString());
        setResult(RESULT_OK, returnIntent);
        finish();
    }

    private void setupButtons(){
        Typeface face= Typeface.createFromAsset(getAssets(),
                "fonts/WalterTurncoat.ttf");
        Button btns = (Button)findViewById(R.id.save_fortune_btn);
        btns.setTypeface(face);
        Button btnc = (Button)findViewById(R.id.clear_fortune_btn);
        btnc.setTypeface(face);

        ed.setTypeface(face);
    }
}
