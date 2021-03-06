package com.banane.cootiecatcher;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ListView;

/**
 * Created by annabillstrom on 11/3/15.
 */
public class FortunesActivity extends ListActivity {

    private static final int REQUEST_CODE = 5;
    private static final int RESULT_OK = 1;
    private String values[] = new String[8];
    private FortuneAdapter adapter;
    private String resetValues[];
    private CootieApp app;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.fortunes_activity);
        app = (CootieApp)getApplication();
        System.arraycopy(app.values,0,values,0,8);

        adapter = new FortuneAdapter(this, android.R.layout.simple_list_item_1,values, "fonts/WalterTurncoat.ttf");

        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

        super.onListItemClick(l, v, position, id);

        int itemPosition     = position;
        String  itemValue    = (String) l.getItemAtPosition(position);

        Intent intent = new Intent(this, FortuneDetailActivity.class);
        intent.putExtra("fortune", itemValue);
        intent.putExtra("position", position);
        startActivityForResult(intent, REQUEST_CODE);

    }

    @Override
    protected void onActivityResult(int request, int result, Intent data) {
        super.onActivityResult(request, result, data);
        if (result == RESULT_OK && request == REQUEST_CODE) {
            //get your extra stuff from intent
            int position = data.getIntExtra("position", 0);
            String fortune = data.getStringExtra("fortune");
            values[position] = fortune;
            app.writeFortunesToPreferences(values);
            adapter.notifyDataSetChanged();

        } else {
            //do nada
        }
    }
}
