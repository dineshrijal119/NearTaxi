/*
package com.example.deadlydragger.neartaxi;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends FragmentActivity {
    private GoogleMap googleMap;
    private ProgressDialog progressDialog;

    public void onCreate(Bundle bd) {
        super.onCreate(bd);
        setContentView(R.layout.activity_main);
        try {
            // Loading map
            initilizeMap();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void initilizeMap() {
        if (googleMap == null) {
            googleMap = ((MapFragment) getFragmentManager().findFragmentById(
                    R.id.map)).getMap();
// latitude and longitude
            double latitude = 27.710235;
            double longitude =85.333005 ;

// create marker
            MarkerOptions marker = new MarkerOptions().position(new LatLng(latitude, longitude)).title("Hello Maps ");

// adding marker
            googleMap.addMarker(marker);
            // check if map is created successfully or not
            if (googleMap == null) {
                Toast.makeText(getApplicationContext(),
                        "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initilizeMap();
    }
    public class Getnearbytaxis extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            JSONObject jsonObject = new JSONObject();
            HttpUrlConnectionJson httpUrlConnectionJson = new HttpUrlConnectionJson();
            String taxi_result;
            try {
                jsonObject.put("dev_token", "");
                jsonObject.put("lat", "27.000232");
                jsonObject.put("lng", "85.001234");
                taxi_result = httpUrlConnectionJson.sendHTTPData("https://node.qpaysolutions.net/QPay.svc/getnearbytaxis", jsonObject);
                Log.d("dinesh", "post data : " + jsonObject.toString());
                Log.d("dinesh", "taxi result :" + taxi_result);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setIndeterminate(false);
            progressDialog.setCancelable(false);
            progressDialog.show();

        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
            if (s != null) {
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONObject jsonObject1 = jsonObject.getJSONObject("GetNearByTaxisResult");
                    String status =  jsonObject1.getString("status");
                    JSONArray taxiDetails = jsonObject1.getJSONArray("taxiDetails");
                    for (int i = 0;i < taxiDetails.length();i++){
                        GetTaxi getTaxi = new GetTaxi();
                        JSONObject jsonObject2 = taxiDetails.getJSONObject(i);
                        String cell_phone = jsonObject2.getString("cell_phone");
                        String distance = jsonObject2.getString("distance");
                        String loc_lat = jsonObject2.getString("loc_lat");
                        String loc_lng = jsonObject2.getString("loc_lng");
                        String merch_name = jsonObject2.getString("merch_name");
                        getTaxi.setCell_phone(cell_phone);
                        getTaxi.setDistance(distance);
                        getTaxi.setLoc_lat(loc_lat);
                        getTaxi.setLoc_lng(loc_lng);
                        getTaxi.setMerch_name(merch_name);
                        getTaxis.add(getTaxi);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                finish();
            }
        }
    }
}*/
