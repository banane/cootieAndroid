package com.banane.cootiecatcher;

import android.app.Application;

/**
 * Created by annabillstrom on 11/4/15.
 */
public class CootieApp extends Application {
    public String[] values = new String[8];
    public String[] resetValues;


    @Override
    public void onCreate() {
        super.onCreate();

        setupResetValues();
        //todo read from local disk

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

}
