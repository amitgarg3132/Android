package com.example.root.myapplication;

import android.app.AlertDialog;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class ViewActivity extends ActionBarActivity {

    DataBaseHelper mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        mydb = new DataBaseHelper(this);
        //calling the show function which will show the all entries there in database
        show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view, menu);
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

    public void show(){
        Cursor result = mydb.getData();

        if (result.getCount()==0) {
            showMessage("Error", "No data found");
            return;
        }

        else {
            StringBuffer test = new StringBuffer();

            //appending the header for the view page
            test.append("Name Amount latest Entry\n");

            //getting the data from result Cursor
            while (result.moveToNext()){
                test.append(result.getString(1)+":\t"+result.getString(2)+"\n");
            }

            //showMessage("Data",test.toString());
            TextView show = (TextView)findViewById(R.id.textView5);
            show.setText(test.toString());
        }
    }

    //showing message
    public void showMessage(String title,String mess){
        AlertDialog.Builder test = new AlertDialog.Builder(this);
        test.setTitle(title);
        test.setMessage(mess);
        test.show();
    }
}
