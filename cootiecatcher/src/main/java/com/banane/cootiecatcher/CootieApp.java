package com.banane.cootiecatcher;

import android.app.Application;
import android.content.SharedPreferences;

/**
 * Created by annabillstrom on 11/4/15.
 */
public class CootieApp extends Application {
    private static final String PREFERENCES_NAME = "CootieCatcherPrefs";
    public String[] values = new String[8];
    public String[] resetValues;


    @Override
    public void onCreate() {
        super.onCreate();

        setupResetValues();
        readFortunesFromPreferences();

        if(shouldResetValues()) {
            System.arraycopy(resetValues, 0, values, 0, resetValues.length);
        }
    }

    private void setupResetValues(){
        resetValues = new String[] {  "You will be president.","Giraffes are neat.","Someone will steal your pencil.",   "Give someone a dollar.",
  "Nobody gets you, like I get you.", "Your secret is safe.",  "You will make an amazing pie.", "Your wish will come true."};
    }

    private boolean shouldResetValues(){
        for(String fortune: values){
            if(fortune == null || (fortune.length() == 0)){
                return true;
            }
        }
        return false;
    }

    public void writeFortunesToPreferences(String[] fortunes){
        SharedPreferences preferences = getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE);
        SharedPreferences.Editor edit= preferences.edit();
        for(int i=0;i<8;i++){
            String key = "fortune_" + i;
            edit.putString(key, fortunes[i]);
        }

        edit.commit();
    }

    public void readFortunesFromPreferences(){
        SharedPreferences settings = getSharedPreferences(PREFERENCES_NAME, 0);
        for(int i=0;i<8;i++) {
            String key = "fortune_" + i;
            values[i] = settings.getString(key,"");
        }
    }

}
