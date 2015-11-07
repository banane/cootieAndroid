package com.banane.cootiecatcher;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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
    @Override
    public View getView(int position, View v, ViewGroup parent)
    {
        View mView = v ;
        if(mView == null){
            LayoutInflater vi = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = vi.inflate(layoutResourceId, null);
        }

        TextView text = (TextView) mView.findViewById(android.R.id.text1);

        if(data[position] != null )
        {
/*            text.setTextColor(Color.WHITE);
            text.setText(items.get(position));
            text.setBackgroundColor(Color.RED);
            int color = Color.argb( 200, 255, 64, 64 );
            text.setBackgroundColor( color );*/
            text.setTypeface(tf);
            text.setTextColor(Color.WHITE);
            text.setText(data[position]);

        }

        return mView;
    }

}
