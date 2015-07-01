package com.example.root.mymap;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.DecimalFormat;

import static java.lang.String.*;

public class MapsActivity extends FragmentActivity implements GoogleMap.OnMarkerDragListener{

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private Marker test;
    DataBaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();
        test = mMap.addMarker(new MarkerOptions().position(new LatLng(28.6139391, 0)).title("Marker").draggable(true));
        mMap.setOnMarkerDragListener(this);
        myDB = new DataBaseHelper(this);
    }

    public double distance(LatLng StartP,LatLng EndP){
        int Radius = 6371;// radius of earth in Km
        double lat1 = StartP.latitude;
        double lat2 = EndP.latitude;
        double lon1 = StartP.longitude;
        double lon2 = EndP.longitude;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
                * Math.sin(dLon / 2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double valueResult = Radius * c;
        double km = valueResult / 1;
        DecimalFormat newFormat = new DecimalFormat("####");
        int kmInDec = Integer.valueOf(newFormat.format(km));
        double meter = valueResult % 1000;
        int meterInDec = Integer.valueOf(newFormat.format(meter));
        Log.i("Radius Value", "" + valueResult + "   KM  " + kmInDec
                + " Meter   " + meterInDec);

        return Radius * c;
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.

        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */


    public void onButtonClick(View v){
        CameraUpdate update = CameraUpdateFactory.newLatLng(test.getPosition());
        mMap.animateCamera(update);

        Cursor result = myDB.getData();

        if (result.getCount()==0) {
            showMessage("Relax", "No other nearBy work found");
            return;
        }
        else{
            EditText e1 =(EditText)findViewById(R.id.editText);
            int max = Integer.parseInt(e1.getText().toString());
            LatLng l1 = test.getPosition();

            StringBuffer test = new StringBuffer();

            while (result.moveToNext()){
                LatLng l2 = new LatLng(Double.parseDouble(result.getString(1)),Double.parseDouble(result.getString(3)));

                if(distance(l2,l1) <= max){
                    test.append(result.getString(2)+"\n");
                }
            }
            if(test.toString().length() <= 1)
                showMessage("Relax", "No other nearBy work found");
            else
                showMessage("found some other work too",test.toString());
        }

    }

    private void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setTitle(title);
        builder.show();
    }

    public boolean checkDistance(Double d){
        EditText e1 = (EditText)findViewById(R.id.editText);
        TextView t1 = (TextView)findViewById(R.id.textView2);

        String temp = e1.getText().toString();
        double temp2 = Integer.parseInt(temp);

        if (d<=temp2){
            t1.setText("yes distance is shorter");
            return true;
        }
        else {
            t1.setText("distance is greater");
            return false;
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
