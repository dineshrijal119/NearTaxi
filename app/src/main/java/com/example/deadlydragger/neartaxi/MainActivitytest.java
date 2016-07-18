package com.example.deadlydragger.neartaxi;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by deadlydragger on 7/14/16.
 */
public class MainActivitytest extends AppCompatActivity {
    private GoogleMap googleMap;
    ArrayList<Taxi> listTaxi = new ArrayList<>();
    private ProgressDialog progressDialog;
    Marker marker;
    int markerclicked;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        new Getnearbytaxis().execute();
        insertLatLng();

        try {
            initilizeMap();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void initilizeMap() {
        if (googleMap == null) {
            googleMap = ((MapFragment) getFragmentManager().findFragmentById(
                    R.id.map)).getMap();

            for(int i = 0 ; i < listTaxi.size() ; i++ ) {

                createMarker(listTaxi.get(i).getLat(), listTaxi.get(i).getLng(), listTaxi.get(i).getTitle(),listTaxi.get(i).getImage());
                googleMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

                    // Use default InfoWindow frame
                    @Override
                    public View getInfoWindow(Marker arg0) {
                        return null;
                    }

                    // Defines the contents of the InfoWindow
                    @Override
                    public View getInfoContents(Marker arg0) {

                        // Getting view from the layout file infowindowlayout.xml
                        View v = getLayoutInflater().inflate(R.layout.infowindowlayout, null);

                        LatLng latLng = arg0.getPosition();

                        ImageView im = (ImageView) v.findViewById(R.id.imageView1);
                        TextView tv1 = (TextView) v.findViewById(R.id.textView1);
                        TextView tv2 = (TextView) v.findViewById(R.id.textView2);
                        String title=arg0.getTitle();
                        String informations=arg0.getSnippet();

                        tv1.setText(title);
                        tv2.setText(informations);

                        if(onMarkerClick(arg0)==true && markerclicked==1){
                            im.setImageResource(R.drawable.location);
                        }


                        return v;

                    }
                });
            }



            CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(listTaxi.get(0).getLat(), listTaxi.get(0).getLng())).zoom(12).build();
            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            googleMap.getUiSettings().setMyLocationButtonEnabled(true);

            googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    Toast.makeText(getApplicationContext(),"clicked : "+listTaxi.get(0).getTitle(),Toast.LENGTH_LONG).show();
                    return false;
                }
            });
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
  protected void createMarker(double latitude, double longitude, String title,int image) {

      marker=  googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(latitude, longitude))
                .anchor(0.5f, 0.5f)
                .icon(BitmapDescriptorFactory.fromResource(image))
                .title(title));
    }
    public boolean onMarkerClick(final Marker marker) {

        if (marker.equals(marker))
        {
            markerclicked=1;
            return true;
        }
        return false;
    }

    private void insertLatLng() {
        Taxi taxi = new Taxi();
        taxi.setLat(27.693105);
        taxi.setLng(85.280654);
        taxi.setImage(R.drawable.location);
        taxi.setTitle("Kalanki Taxi Service");
        listTaxi.add(taxi);
        Taxi taxi1 = new Taxi();
        taxi1.setLat(27.706742);
        taxi1.setLng(85.315214);
        taxi1.setImage(R.drawable.location);
        taxi1.setTitle("Kalanki Taxi Service");
        listTaxi.add(taxi1);
        Taxi taxi2 = new Taxi();
        taxi2.setLat(27.686218);
        taxi2.setLng(85.314914);
        taxi2.setImage(R.drawable.location);
        taxi2.setTitle("Kalanki Taxi Service");
        listTaxi.add(taxi2);
    }

    public class Getnearbytaxis extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            JSONObject jsonObject = new JSONObject();
            HttpUrlConnectionJson httpUrlConnectionJson = new HttpUrlConnectionJson();
            String taxi_result;
            try {
                jsonObject.put("dev_token", "7ahdtahhjlocfwrt");
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
            progressDialog = new ProgressDialog(MainActivitytest.this);
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
                        double loc_lat = jsonObject2.getDouble("loc_lat");
                        double loc_lng = jsonObject2.getDouble("loc_lng");
                        String merch_name = jsonObject2.getString("merch_name");
                        getTaxi.setCell_phone(cell_phone);
                        getTaxi.setDistance(distance);
                        getTaxi.setLoc_lat(loc_lat);
                        getTaxi.setLoc_lng(loc_lng);
                        getTaxi.setMerch_name(merch_name);
//                        listTaxi.add(getTaxi);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                finish();
            }
        }
    }


}
