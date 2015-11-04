package com.banane.cootiecatcher;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewConfiguration;

import com.uservoice.uservoicesdk.Config;
import com.uservoice.uservoicesdk.UserVoice;

import java.lang.reflect.Field;

/**
 * Created by annabillstrom on 11/3/15.
 */
public class SettingsActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

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
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.about_us_msg)
                .setPositiveButton(R.string.about_us_yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!
                    }
                });

        // Create the AlertDialog object and return it
        builder.create();
        builder.show();

    }

    public void viewFortunes(View v){
        Intent intent = new Intent(this, FortunesActivity.class);
        startActivity(intent);
    }

}
