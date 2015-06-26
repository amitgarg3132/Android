package com.example.root.myapplication;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class AddBuddyActivity extends ActionBarActivity {

    DataBaseHelper mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_buddy);
        mydb = new DataBaseHelper(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_buddy, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void Add(View v){

        //casting the variable from view id's
        EditText e1 = (EditText)findViewById(R.id.editText2);
        EditText e2 = (EditText)findViewById(R.id.editText);
        TextView t1 = (TextView)findViewById(R.id.textView4);

        //setting the values from edit text to name and Amount Variables
        String name = e1.getText().toString();
        int Amount = Integer.parseInt(e2.getText().toString());

        //adding new entry in the khata.db khata_table (id,name,amount,lastEntry)
        if(mydb.insertData(name,Amount)){

            t1.setText("Success");
        }
        else
            t1.setText("failure");
    }
}
