package com.example.mrkoppy.myappfyp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Driver_CrRoute extends AppCompatActivity {
    Button btnShowCoord1,btnShowCoord2;
    EditText edtAddress1,edtAddress2;
    TextView txtCoord1,txtCoord2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver__cr_route);

        btnShowCoord1 = (Button)findViewById(R.id.buttonStartGetCoordinate);
        btnShowCoord2 = (Button)findViewById(R.id.buttonEndGetCoordinate);
        edtAddress1 = (EditText)findViewById(R.id.etStartMyDriver);
        edtAddress2 = (EditText)findViewById(R.id.etDestinationMyDriver);
        txtCoord1 = (TextView)findViewById(R.id.textCordinate1);
        txtCoord2 = (TextView)findViewById(R.id.textCordinate2);


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

                        txtCoord1.setText(String.format("Coordinates1 : %s / %s ",lat,lng));

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

                        txtCoord2.setText(String.format("Coordinates2 : %s / %s ",lat,lng));

                        if(dialog.isShowing())
                            dialog.dismiss();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

        });
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



    public void btn_redirect_main_menu_driver_cr_route(View view){
        Intent intent = new Intent(this, trip.class);
        startActivity(intent);
    }


}
