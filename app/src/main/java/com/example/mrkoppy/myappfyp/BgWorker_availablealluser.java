package com.example.mrkoppy.myappfyp;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

public class BgWorker_availablealluser extends AsyncTask<Void,Post_alluser,Void> {
    Context ctx;
    Activity activty;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Post_alluser> arrayList = new ArrayList<>();
    my_alluser_interface my_alluser_interface;


    public BgWorker_availablealluser(Context ctx, my_alluser_interface my_alluser_interface){
        this.ctx = ctx;
        /*, my_rider_booking_interface my_rider_booking_interface*/
        this.my_alluser_interface = my_alluser_interface;
        activty = (Activity)ctx;
    }

    /*hostel*/
    String fetching_json_data = "http://192.168.0.103/getalluser.php";
    /*String fetching_vehicle_data = "http://192.168.0.103/selectvehicle.php";
    String type = "create_route";*/
    /*Uni*/
    /*String fetching_json_data = "http://192.168.43.41/getalluser.php";*/

    @Override
    protected Void doInBackground(Void... voids) {
        try{
            URL url = new URL(fetching_json_data);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line;

            while ((line=bufferedReader.readLine())!=null) {
                stringBuilder.append(line + "\n");

            }

            httpURLConnection.disconnect();
            String fetching_json_data = stringBuilder.toString().trim();

            JSONObject jsonObject = new JSONObject(fetching_json_data);

            JSONArray jsonArray = jsonObject.getJSONArray("server_response");
            int count = 0;
            while(count<jsonArray.length()){
                JSONObject jsonObject1 = jsonArray.getJSONObject(count);
                count++;
                Post_alluser post_alluser = new Post_alluser(jsonObject1.getInt("UserID"),jsonObject1.getString("Name"),
                        jsonObject1.getString("Gender"), jsonObject1.getString("Email"), jsonObject1.getString("Mobile")
                        , jsonObject1.getString("Postcode"), jsonObject1.getString("StuID"), jsonObject1.getString("Carplate")
                        , jsonObject1.getString("Seat"));
                publishProgress(post_alluser);
            }

            Log.d("JSON_DATA",fetching_json_data);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }



    @Override
    protected void onPreExecute() {

        recyclerView = (RecyclerView)activty.findViewById(R.id.alluserdata);
        layoutManager = new LinearLayoutManager(ctx);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new Available_alluseradapter(arrayList,my_alluser_interface);
        /*, my_rider_booking_interface*/
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }

    @Override
    protected void onProgressUpdate(Post_alluser... values) {
        arrayList.add(values[0]);
        adapter.notifyDataSetChanged();
    }
}
