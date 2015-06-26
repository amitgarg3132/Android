package com.example.root.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButtonClickAdd(View v){

        //changing the intent on button press
        Intent newIntent = new Intent(this,AddBuddyActivity.class);
        startActivity(newIntent);
    }

    public void onButtonClickUpdate(View v){

        //changing the intent on button press
        Intent newIntent = new Intent(this,UpdateActivity.class);
        startActivity(newIntent);
    }

    public void onButtonClickView(View v){

        //changing the intent on button press
        Intent newIntent = new Intent(this,ViewActivity.class);
        startActivity(newIntent);
    }

    public void onButtonClickDelete(View v){

        //changing the intent on button press
        Intent newIntent = new Intent(this,DeleteActivity.class);
        startActivity(newIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
