package com.example.mrkoppy.myappfyp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Bgworker_vehicle extends AsyncTask<String,Post_available_vehicle,String> {
    Context ctx;
    AlertDialog alertDialog;
    ArrayList<Post_available_vehicle> arrayList = new ArrayList<>();

    Bgworker_vehicle(Context ctx){
        this.ctx = ctx;
    }

    /*hostel*/
    String fetching_vehicle_data = "http://192.168.0.103/selectvehicle.php";
    /*String type = "create_route";*/
    /*Uni*/
    /*String fetching_json_data = "http://192.168.43.41/get_availableride.php";*/

    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        if (type.equals("create_route")) {
            try {
                URL url = new URL(fetching_vehicle_data);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line + "\n");

                }

                httpURLConnection.disconnect();
                String fetching_vehicle_data = stringBuilder.toString().trim();

                JSONObject jsonObject = new JSONObject(fetching_vehicle_data);

                JSONArray jsonArray = jsonObject.getJSONArray("server_response");
                int count = 0;
                SharedPreferences sharedPreferences;
                while (count < jsonArray.length()) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(count);
                    count++;
                    Post_available_vehicle post_available_vehicle = new Post_available_vehicle(jsonObject1.getString("Carplate"),
                            jsonObject1.getString("Seat"));
                    publishProgress(post_available_vehicle);
                }

                Log.d("JSON_DATA", fetching_vehicle_data);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        return null;
    }



    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(ctx).create();
        alertDialog.setTitle("Status");

    }

    @Override
    protected void onPostExecute(String result) {
        /*super.onPostExecute(result);*/

        alertDialog.setMessage(result);


        if(result.equals("Woohoo")) {
            alertDialog.setButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    Intent intent = new Intent(ctx,trip.class);
                    ctx.startActivity(intent);
                }
            });
        }
    }

    @Override
    protected void onProgressUpdate(Post_available_vehicle... values) {
        arrayList.add(values[0]);
    }
}
