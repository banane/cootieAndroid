package com.banane.cootiecatcher;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.ArrayAdapter;

/**
 * Created by annabillstrom on 11/4/15.
 */
public class FortuneAdapter extends ArrayAdapter {

    Context context;
    int layoutResourceId;
    CharSequence data[] = null;
    Typeface tf;

    public FortuneAdapter(Context context, int layoutResourceId, CharSequence[] data, String FONT ) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
        tf = Typeface.createFromAsset(context.getAssets(), FONT);
    }
}
