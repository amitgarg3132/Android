package com.example.root.mymap;

import android.app.AlertDialog;
import android.app.Fragment;
import android.database.Cursor;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class MainActivity2Activity extends FragmentActivity implements GoogleMap.OnMarkerDragListener {

    private GoogleMap mMap;
    Marker location;
    DataBaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2);

        setUpMapIfNeeded();
        location = mMap.addMarker(new MarkerOptions().position(new LatLng(0,0)).draggable(true));
        mMap.setOnMarkerDragListener(this);

        myDB = new DataBaseHelper(this);

    }

    public void Submit(View v){
        EditText e1 = (EditText)findViewById(R.id.editText2);
        LatLng l1 = location.getPosition();
        float lat = (float) l1.latitude,lng = (float) l1.longitude;

        if(myDB.insertData(lng,lat,e1.getText().toString())){
            TextView t1 = (TextView)findViewById(R.id.textView4);
            t1.setText("Success");
        }
        else{
            TextView t1 = (TextView)findViewById(R.id.textView4);
            t1.setText("failure");
        }
    }

    public void Check(View v){
        Cursor result = myDB.getData();

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
                test.append(result.getString(0)+":\t"+result.getString(1)+":\t"+result.getString(2)+"\t"+result.getString(3)+"\n");
            }

            showMessage("Data",test.toString());


        }
    }

    public void showMessage(String title,String message){
        AlertDialog.Builder test = new AlertDialog.Builder(this);
        test.setTitle(title);
        test.setMessage(message);
        test.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity2, menu);
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

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.

        }
    }

    @Override
    public void onMarkerDragStart(Marker marker) {


    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {


    }

}
