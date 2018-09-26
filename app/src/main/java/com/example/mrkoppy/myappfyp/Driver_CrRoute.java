package com.example.mrkoppy.myappfyp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


public class Driver_CrRoute extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener,DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {



    Button btnShowCoord1,btnShowCoord2;
    EditText edtAddress1,edtAddress2,edtDateAndTime,edt_Price,edt_Seatsleft,edt_DatenTime;
    TextView txtCoord1_lat,txtCoord1_lng,txtCoord2_lat,txtCoord2_lng,txtCoord2,tvStatus,tv_Status,tv_DateTime;
    private String str_startname,str_endname,str_latstartlocation ,str_lngstartlocation,str_latnendlocation,
            str_lngendlocation,str_price,str_seatsleft,str_datentime,str_status,str_duration,type;
    private GoogleMap mMap;
    LatLng origin;
    LatLng dest;
    Polyline line;
    ArrayList<LatLng> MarkerPoints;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    LocationRequest mLocationRequest;
    TextView ShowDistanceDuration,tvduration;
    MarkerOptions options;
    Spinner spinner;
    private int day,month,year,hour,minute;
    private int finalDay,finalMth,finalYear,finalHour,finalMinute;
    public static final String DATE_SERVER_PATTERN = "yyyy-MM-dd";
    RadioGroup goornot;
    RadioButton goono;


    /* - Build.VERSION_CODES,M means that above api 23 - 6.0
     *  - above api 23 need to get permission from users
     *  - Testing version api level 16 - Jelly Bean	4.1.x version
     * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_cr_route);

        btnShowCoord1 = (Button)findViewById(R.id.buttonStartGetCoordinate);
        btnShowCoord2 = (Button)findViewById(R.id.buttonEndGetCoordinate);
        edtAddress1 = (EditText)findViewById(R.id.etStartMyDriver);
        edtAddress2 = (EditText)findViewById(R.id.etDestinationMyDriver);
        txtCoord1_lat = (TextView)findViewById(R.id.textCordinate1_lat);
        txtCoord1_lng = (TextView)findViewById(R.id.textCordinate1_lng);
        txtCoord2_lat = (TextView) findViewById(R.id.textCordinate2_lat);
        txtCoord2_lng = (TextView)findViewById(R.id.textCordinate2_lng);
        edt_Price = (EditText)findViewById(R.id.etTripPrice);
        edt_Seatsleft = (EditText)findViewById(R.id.et_Seatsleft);
        tv_DateTime = (TextView)findViewById(R.id.tv_dateTime);
        tvduration = (TextView)findViewById(R.id.tv_duration);
        goornot = (RadioGroup)findViewById(R.id.radioGrp_gon);

        /*edt_DatenTime = (EditText)findViewById(R.id.et_DatenTime);*/
        /*tv_Status = (TextView)findViewById(R.id.tv_forStatus);*/
        ShowDistanceDuration = (TextView) findViewById(R.id.show_distance_time);

        edtDateAndTime = (EditText) findViewById(R.id.et_DatenTime);
        edtDateAndTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Calendar calender = Calendar.getInstance();
                    year = calender.get(Calendar.YEAR);
                    month = calender.get(Calendar.MONTH);
                    day = calender.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog datePickerDialog = new DatePickerDialog(Driver_CrRoute.this,Driver_CrRoute.this,year,
                            month,day);
                    datePickerDialog.show();
                }
        });

        /*origin_and_dest = (TextView) findViewById(R.id.tv_origin_destination);*/
        /*horigin = (TextView)findViewById(R.id.tvorg);
        hdestination = (TextView)findViewById(R.id.tvdtn);*/


        btnShowCoord1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new GetCoordinates1().execute(edtAddress1.getText().toString().replace(" ","+"));

            }

            class GetCoordinates1 extends AsyncTask<String,Void,String> {
                ProgressDialog dialog = new ProgressDialog(Driver_CrRoute.this);

                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    dialog.setMessage("Please wait..");
                    dialog.setCanceledOnTouchOutside(false);
                    dialog.show();
                }

                @Override
                protected String doInBackground(String... strings) {
                    String response;
                    try{
                        String address = strings[0];
                        HttpDataHandler http = new HttpDataHandler();
                        String url = String.format("https://maps.googleapis.com/maps/api/geocode/json?address=%s",address);
                        /*Log.e("SSSSS", String.format("https://maps.googleapis.com/maps/api/geocode/json?address=%s",address));*/
                        response = http.getHTTPData(url);
                        return response;
                    }
                    catch (Exception ex)
                    {

                    }
                    return null;
                }

                @Override
                protected void onPostExecute(String s) {
                    try{
                        JSONObject jsonObject = new JSONObject(s);

                        String lat = ((JSONArray)jsonObject.get("results")).getJSONObject(0).getJSONObject("geometry")
                                .getJSONObject("location").get("lat").toString();
                        String lng = ((JSONArray)jsonObject.get("results")).getJSONObject(0).getJSONObject("geometry")
                                .getJSONObject("location").get("lng").toString();

                        txtCoord1_lat.setText(String.format("%s",lat));
                        txtCoord1_lng.setText(String.format("%s",lng));
                        double d1 = Double.parseDouble(lat);
                        double d2 = Double.parseDouble(lng);
                        mMap.clear();
                        mMap.addMarker(new MarkerOptions()
                                .position(new LatLng(d1,d2))
                                .title("First Position")
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                        LatLng position = new LatLng(d1, d2);

                        MarkerPoints.add(position);



                        Log.i("SSSSSSSSSS", lat + "," + lng);
                        Toast.makeText(Driver_CrRoute.this, "Confirmed start location", Toast.LENGTH_SHORT).show();

                        if(dialog.isShowing())
                            dialog.dismiss();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

        });

        btnShowCoord2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new GetCoordinates2().execute(edtAddress2.getText().toString().replace(" ","+"));

            }


            class GetCoordinates2 extends AsyncTask<String,Void,String> {
                ProgressDialog dialog = new ProgressDialog(Driver_CrRoute.this);

                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    dialog.setMessage("Please wait....");
                    dialog.setCanceledOnTouchOutside(false);
                    dialog.show();
                }

                @Override
                protected String doInBackground(String... strings) {
                    String response;
                    try{
                        String address = strings[0];
                        HttpDataHandler http = new HttpDataHandler();
                        String url = String.format("https://maps.googleapis.com/maps/api/geocode/json?address=%s",address);
                        /*Log.e("SSSSS2", String.format("https://maps.googleapis.com/maps/api/geocode/json?address=%s",address));*/
                        response = http.getHTTPData(url);
                        return response;
                    }
                    catch (Exception ex)
                    {

                    }
                    return null;
                }

                @Override
                protected void onPostExecute(String s) {
                    try{
                        JSONObject jsonObject = new JSONObject(s);

                        String lat = ((JSONArray)jsonObject.get("results")).getJSONObject(0).getJSONObject("geometry")
                                .getJSONObject("location").get("lat").toString();
                        String lng = ((JSONArray)jsonObject.get("results")).getJSONObject(0).getJSONObject("geometry")
                                .getJSONObject("location").get("lng").toString();

                        txtCoord2_lat.setText(String.format("%s",lat));
                        txtCoord2_lng.setText(String.format("%s",lng));
                        double d1 = Double.parseDouble(lat);
                        double d2 = Double.parseDouble(lng);

                        mMap.addMarker(new MarkerOptions()
                                .position(new LatLng(d1,d2))
                                .title("Second Position")
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                        Log.i("SSSSSSSSSS", lat + "," + lng);

                        MarkerOptions options = new MarkerOptions();
                        LatLng position = new LatLng(d1, d2);

                        MarkerPoints.add(position);

                        Toast.makeText(Driver_CrRoute.this, "Confirmed end location", Toast.LENGTH_SHORT).show();
                        /*txtCoord2.setText(String.format("Lat for coordinate2 : %s \n" +
                                "Lng for coordinate1 : %s ",lat,lng));*/

                        if(dialog.isShowing())
                            dialog.dismiss();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

        });


        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPermission();
        }

        // Initializing
        MarkerPoints = new ArrayList<>();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        finalYear = i;
        finalMth = i1 + 1;
        finalDay = i2;

        Calendar c = Calendar.getInstance();
        /*c.set(Calendar.YEAR,i);
        c.set(Calendar.MONTH,i1);
        c.set(Calendar.DAY_OF_MONTH,i2);*/
        hour = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(Driver_CrRoute.this,Driver_CrRoute.this,hour,minute,
                DateFormat.is24HourFormat(this));
        timePickerDialog.show();

    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        finalHour = i;
        finalMinute = i1;

        /*Toast.makeText(Driver_CrRoute.this, "The Date You Choose : " + "\n" + "Year : " + finalYear + "\n" +
                "Month : " + finalMth + "\n" +
                "Day : " + finalDay + "\n" +
                "Hour : " + finalHour + "\n" +
                "Minute : " + finalMinute,Toast.LENGTH_SHORT).show();*/
        String finalYearValue = Integer.toString(finalYear);
        String finalMthValue = Integer.toString(finalMth);
        String finalDayValue = Integer.toString(finalDay);
        String finalHourValue = Integer.toString(finalHour);
        String finalMinuteValue = Integer.toString(finalMinute);
        String finalOutcome = finalYearValue + "-" + finalMthValue + "-" + finalDayValue + " " + finalHourValue + ":" + finalMinuteValue + ":" + "00";
        edtDateAndTime.setText(finalOutcome,TextView.BufferType.EDITABLE);
        tv_DateTime.setText(finalOutcome);
        Toast.makeText(this,convertDate(finalOutcome),Toast.LENGTH_SHORT).show();
        /*edtAddress2.setText(EndLocation, TextView.BufferType.EDITABLE);*/
        Log.i("Time information : ", "Year : " + finalYear + "Month : " + finalMth + "Day : " + finalDay +
                "Hour : " + finalHour + "Minute : " + finalMinute);

    }

    private String convertDate(String finalOutcome) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date d = format.parse(finalOutcome);
            SimpleDateFormat serverFormat = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
            /*  'T'
            .SSS'Z'*/
            return serverFormat.format(d);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /*
     * PlaceAutoComplete Activity
     */
    public void findPlace1(View view) {
        try {
            /*maybe can set *LatLngBounds* */

                /*AutocompleteFilter autocompleteFilter = new AutocompleteFilter().Builder()
                    .setTypeFilter(Place.TYPE_COUNTRY)
                    .setCountry("MY")
                    .build();*/
            Intent intent1 =
                    new PlaceAutocomplete
                            .IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                            .build(this);
            startActivityForResult(intent1, 1);
        } catch (GooglePlayServicesRepairableException e) {
            // TODO: Handle the error.
        } catch (GooglePlayServicesNotAvailableException e) {
            // TODO: Handle the error.
        }
    }

    public void findPlace2(View view) {
        try {
                /*AutocompleteFilter autocompleteFilter = new AutocompleteFilter().Builder()
                    .setTypeFilter(Place.TYPE_COUNTRY)
                    .setCountry("MY")
                    .build();*/
            Intent intent2 =
                    new PlaceAutocomplete
                            .IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                            .build(this);
            startActivityForResult(intent2, 2);
        } catch (GooglePlayServicesRepairableException e) {
            // TODO: Handle the error.
        } catch (GooglePlayServicesNotAvailableException e) {
            // TODO: Handle the error.
        }
    }


    // A place has been received; use requestCode to track the request.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                // retrive the data by using getPlace() method.
                Place place = PlaceAutocomplete.getPlace(this, data);
                Log.e("Tag", "Place: " + place.getAddress() + place.getPhoneNumber());

                Toast.makeText(getApplicationContext(),"Got Location", Toast.LENGTH_LONG).show();

                ((EditText) findViewById(R.id.etStartMyDriver))
                        .setText(String.format("%s,\n%s\n%s", place.getName(), place.getAddress(), place.getPhoneNumber()));


            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
                // TODO: Handle the error.
                Log.e("Tag", status.getStatusMessage());

            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
        if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                // retrive the data by using getPlace() method.
                Place place = PlaceAutocomplete.getPlace(this, data);
                Log.e("Tag", "Place: " + place.getAddress() + place.getPhoneNumber());

                Toast.makeText(getApplicationContext(),"Got Location", Toast.LENGTH_LONG).show();

                ((EditText) findViewById(R.id.etDestinationMyDriver))
                        .setText(String.format("%s,\n%s\n%s", place.getName(), place.getAddress(), place.getPhoneNumber()));



            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
                // TODO: Handle the error.
                Log.e("Tag", status.getStatusMessage());

            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
    }

    public void btn_registerroute(View view){
        str_startname = edtAddress1.getText().toString();
        str_endname = edtAddress2.getText().toString();
        str_latstartlocation = txtCoord1_lat.getText().toString() ;
        str_lngstartlocation = txtCoord1_lng.getText().toString() ;
        str_latnendlocation = txtCoord2_lat.getText().toString();
        str_lngendlocation = txtCoord2_lng.getText().toString();
        str_price = edt_Price.getText().toString();
        str_seatsleft = edt_Seatsleft.getText().toString() ;
        str_datentime = tv_DateTime.getText().toString();
        str_duration = tvduration.getText().toString();
        /*spinner.getSelectedItem().toString();*/
        /*Log.i("Str_datentime", str_datentime);*/
        int goornot1 = goornot.getCheckedRadioButtonId();
        goono = findViewById(goornot1);
        str_status = goono.getText().toString();
        type = "register_route";

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, str_startname, str_endname,str_latstartlocation,str_lngstartlocation,str_latnendlocation,
                str_lngendlocation,str_price,str_seatsleft,str_datentime,str_status,str_duration);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    /*Override google map and set dynamic map*/
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //Initialize Google Play Services
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient();
                mMap.setMyLocationEnabled(true);
            }
        }
        else {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }

        // Setting onclick event listener for the map
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng point) {

                // Already two locations
                if (MarkerPoints.size() > 1) {
                    MarkerPoints.clear();
                    mMap.clear();
                    /*    MarkerPoints = new ArrayList<>();*/
                    ShowDistanceDuration.setText("");
                    /*    new ReverseGeocodingTask(getBaseContext()).execute(point);*/
                }

                // Adding new item to the ArrayList
                MarkerPoints.add(point);

                // Creating MarkerOptions
                MarkerOptions options = new MarkerOptions();

                // Setting the position of the marker
                options.position(point);

                /*
                 * For the start location, the color of marker is GREEN and
                 * for the end location, the color of marker is RED.
                 */
                if (MarkerPoints.size() == 1) {
                    options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                    /* new ReverseGeocodingTask(getBaseContext()).execute(point);*/
                    /*options.draggable(true);*/

                } else if (MarkerPoints.size() == 2) {
                    options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                    /*new ReverseGeocodingTask(getBaseContext()).execute(point);*/
                    /*options.draggable(true);*/
                }


                // Add new marker to the Google Map Android API V2
                mMap.addMarker(options);

                // Adding Marker on the touched location with address
                /*new ReverseGeocodingTask(getBaseContext()).execute(point);*/

                // Checks, whether start and end locations are captured
                if (MarkerPoints.size() >= 2) {
                    /**
                     *  if global declaration then dont have bug, but inside here got bug
                     *  This line cannot use
                     *  LatLng origin = MarkerPoints.get(0);
                     *   LatLng dest = MarkerPoints.get(1);*/
               /*     LatLng origin = MarkerPoints.get(0);
                    LatLng dest = MarkerPoints.get(1);*/
                    /*        new ReverseGeocodingTask(getBaseContext()).execute(point);*/
                    origin = MarkerPoints.get(0);
                    dest = MarkerPoints.get(1);

                    // Getting URL to the Google Directions API
                    String url = getUrl(origin, dest);
                    Log.d("onMapClick", url.toString());

                    Driver_CrRoute.FetchUrl FetchUrl = new Driver_CrRoute.FetchUrl();

                    // Start downloading json data from Google Directions API
                    FetchUrl.execute(url);
                    //move map camera
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(origin));
                    mMap.animateCamera(CameraUpdateFactory.zoomTo(11));

                }

            }
        });

    }

    private String getUrl(LatLng origin, LatLng dest) {

        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
        Log.i("Origin",str_origin);

        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
        Log.i("Origin",str_dest);

        // Sensor enabled, not need to set users location set it to false
        String sensor = "sensor=false";
        Log.i("Sensor",sensor);

        /*Set it to driveing mode*/
        String mode = "mode = driving";

        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + sensor;
        Log.i("Parameters",parameters);

        // Output to json format
        String output = "json";

        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters;


        return url;
    }

    /**
     * A method to download json data from url
     */
    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(strUrl);

            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            data = sb.toString();
            Log.d("downloadUrl", data.toString());

            br.close();

        } catch (Exception e) {
            Log.d("Exception", e.toString());
        } finally {
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }

    // Fetches data from url passed
    private class FetchUrl extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... url) {

            // For storing data from web service
            String data = "";

            try {
                // Fetching the data from web service
                data = downloadUrl(url[0]);
                Log.d("Background Task data", data.toString());
            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }
            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            Driver_CrRoute.ParserTask parserTask = new Driver_CrRoute.ParserTask();

            // Invokes the thread for parsing the JSON data
            parserTask.execute(result);

        }
    }

    /**
     * Parse the Json data returned by DownloadURL
     * A class to parse the Google Places in JSON format
     */
    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {

        // Parsing the data in non-ui thread
        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try {
                jObject = new JSONObject(jsonData[0]);
                Log.d("ParserTask", jsonData[0]);
                DirectionsJSONParser parser = new DirectionsJSONParser();
                Log.d("ParserTask", parser.toString());

                // Starts parsing data
                routes = parser.parse(jObject);
                Log.d("ParserTask","Executing routes");
                Log.d("ParserTask",routes.toString());

            } catch (Exception e) {
                Log.d("ParserTask",e.toString());
                e.printStackTrace();
            }
            return routes;
        }

        // Executes in UI thread, after the parsing process
        // Draw line onto the map from origin to destination
        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result) {
            ArrayList<LatLng> points = null;
            PolylineOptions lineOptions = null;
            /*Options*/
            MarkerOptions markerOptions = new MarkerOptions();
            String distance = "";
            String duration = "";
            String StartLocation = "";
            String EndLocation = "";

            if(result.size()<1){
                Toast.makeText(getBaseContext(), "No Points", Toast.LENGTH_SHORT).show();
                return;
            }

            // Traversing through all the routes
            for (int i = 0; i < result.size(); i++) {
                points = new ArrayList<LatLng>();

                lineOptions = new PolylineOptions();

                // Fetching i-th route
                List<HashMap<String, String>> path = result.get(i);

                // Fetching all the points in i-th route
                for (int j = 0; j < path.size(); j++) {
                    HashMap<String, String> point = path.get(j);
                    /*  HashMap<String, String> point1 = path1.get(j);*/

                    if(j==0){    // Get distance from the list
                        distance = (String)point.get("distance");
                        continue;
                    }else if(j==1){ // Get duration from the list
                        duration = (String)point.get("duration");
                        continue;
                    }else if(j==2){ // Get Start address from the list
                        StartLocation = (String)point.get("start_address");
                        continue;
                    }else if(j==3){  // Get End address from the list
                        EndLocation = (String)point.get("end_address");
                        continue;
                    }

                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = new LatLng(lat, lng);

                    points.add(position);
                }

                lineOptions.addAll(points);
                lineOptions.width(5);
                lineOptions.color(Color.RED);

                Log.d("onPostExecute","onPostExecute lineoptions decoded");
            }

            // Drawing polyline in the Google Map for the i-th route
            if(lineOptions != null) {
                mMap.addPolyline(lineOptions);
                ShowDistanceDuration.setText("Distance:"+distance + ", Duration:"+duration);
                edtAddress1.setText(StartLocation, TextView.BufferType.EDITABLE);
                edtAddress2.setText(EndLocation, TextView.BufferType.EDITABLE);
                /*origin_and_dest.setText("StartLocation:"+StartLocation + ", EndLocation:"+EndLocation);*/
                /*horigin.setText("StartLocation : " + StartLocation);
                hdestination.setText("EndLocation : " + EndLocation);*/
                Toast.makeText(Driver_CrRoute.this,"Distance:"+distance + ", Duration:"+duration ,Toast.LENGTH_SHORT).show();
                tvduration.setText(duration);
            }
            else {
                Log.d("onPostExecute","without Polylines drawn");
            }
        }
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onConnected(Bundle bundle) {

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, (com.google.android.gms.location.LocationListener) this);
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {

        mLastLocation = location;
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }

        //Place current location marker
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Position");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        mCurrLocationMarker = mMap.addMarker(markerOptions);

        //move map camera
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(11));

        //stop location updates
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, (com.google.android.gms.location.LocationListener) this);
        }

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    public boolean checkLocationPermission(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Asking user if explanation is needed
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                //Prompt the user once explanation has been shown
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted. Do the
                    // contacts-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        if (mGoogleApiClient == null) {
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }

                } else {

                    // Permission denied, Disable the functionality that depends on this permission.
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }

            // other 'case' lines to check for other permissions this app might request.
            // You can add here other case statements according to your requirement.
        }
    }

    public void btn_redirect_main_menu_mydrivermap(View view){
        Intent intent = new Intent(this, trip.class);
        startActivity(intent);
    }

    public void btn_redirect_main_menu_driver_cr_route(View view){
        Intent intent = new Intent(this,mydriver.class);
        startActivity(intent);
    }


}
