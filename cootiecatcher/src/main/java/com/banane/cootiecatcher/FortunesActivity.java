package com.banane.cootiecatcher;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by annabillstrom on 11/3/15.
 */
public class FortunesActivity extends ListActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fortunes_activity);

        String values[] = new String[] { "a","b"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, values);

        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

        super.onListItemClick(l, v, position, id);

        // ListView Clicked item index
        int itemPosition     = position;

        // ListView Clicked item value
        String  itemValue    = (String) l.getItemAtPosition(position);

        //content.setText("Click : \n  Position :" + itemPosition + "  \n  ListItem : " + itemValue);

        Intent intent = new Intent(this, FortuneDetailActivity.class);
        intent.putExtra("fortune", itemValue);
        intent.putExtra("position", position);
        startActivity(intent);

    }

}
