package com.banane.cootiecatcher;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.uservoice.uservoicesdk.Config;
import com.uservoice.uservoicesdk.UserVoice;

import java.lang.reflect.Field;

/**
 * Created by annabillstrom on 11/3/15.
 */
public class SettingsActivity extends Activity {
    Typeface face;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.settings);
        face= Typeface.createFromAsset(getAssets(),
                "fonts/WalterTurncoat.ttf");
        setupButtons();

        Config config = new Config("banane.uservoice.com");
        config.setForumId(328458);
        UserVoice.init(config, this);

    }


    public void contactUs(View v){
        UserVoice.launchContactUs(this);
    }

    public void postIdea(View v){
        UserVoice.launchPostIdea(this);
    }

    public void getForums(View v){
        UserVoice.launchForum(this);
    }

    public void viewAbout(View v){
        AlertDialog dialog = new AlertDialog.Builder(this).setMessage(R.string.about_us_msg)
                .setPositiveButton(R.string.about_us_yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!
                    }
                }).show();
        TextView textView = (TextView) dialog.findViewById(android.R.id.message);
        Button button1 = (Button)dialog.findViewById(android.R.id.button1);
        textView.setTypeface(face);
        button1.setTypeface(face);

    }

    public void viewFortunes(View v){
        Intent intent = new Intent(this, FortunesActivity.class);
        startActivity(intent);
    }

    private void setupButtons(){

        Button btnc = (Button)findViewById(R.id.contact_us_btn);
        Button btna = (Button)findViewById(R.id.about_us_btn);
        Button btnf = (Button)findViewById(R.id.forums_btn);
        Button btnp = (Button)findViewById(R.id.post_idea_btn);
        Button btnft = (Button)findViewById(R.id.fortunes_btn);

        btnc.setTypeface(face);
        btna.setTypeface(face);
        btnf.setTypeface(face);
        btnp.setTypeface(face);
        btnft.setTypeface(face);


    }

}
