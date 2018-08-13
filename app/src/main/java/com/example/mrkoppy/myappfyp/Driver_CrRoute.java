package com.example.mrkoppy.myappfyp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;

public class Driver_CrRoute extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver__cr_route);
    }

    /*
     * In this method, Start PlaceAutocomplete activity
     * PlaceAutocomplete activity provides--
     * a search box to search Google places
     */
    public void findPlace1(View view) {
        try {
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

                ((EditText) findViewById(R.id.etStartMyDriver))
                        .setText(place.getName()+",\n"+
                                place.getAddress() +"\n" + place.getPhoneNumber());


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


                ((EditText) findViewById(R.id.etDestinationMyDriver))
                        .setText(place.getName()+",\n"+
                                place.getAddress() +"\n" + place.getPhoneNumber());

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
