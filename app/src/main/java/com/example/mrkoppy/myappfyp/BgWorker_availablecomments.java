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

public class BgWorker_availablecomments extends AsyncTask<Void,Post_admincomments,Void> {
    Context ctx;
    Activity activty;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Post_admincomments> arrayList = new ArrayList<>();
    my_cmtfromuser_interface my_cmtfromuser_interface;


    public BgWorker_availablecomments(Context ctx, my_cmtfromuser_interface my_cmtfromuser_interface){
        this.ctx = ctx;
        this.my_cmtfromuser_interface = my_cmtfromuser_interface;
        activty = (Activity)ctx;
    }

    /*hostel*/
    String fetching_json_data = "http://192.168.0.103/cmtfromuser.php";
    /*String fetching_vehicle_data = "http://192.168.0.103/selectvehicle.php";
    String type = "create_route";*/
    /*Uni*/
    /*String fetching_json_data = "http://192.168.43.41/cmtfromuser.php";*/

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
                Post_admincomments post_admincomments = new Post_admincomments(jsonObject1.getString("UserID"),
                        jsonObject1.getString("RouteID"),
                        jsonObject1.getString("Comment"),jsonObject1.getString("Overallstar"));
                publishProgress(post_admincomments);
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

        recyclerView = (RecyclerView)activty.findViewById(R.id.usercincat);
        layoutManager = new LinearLayoutManager(ctx);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new Admincmtsections_adapter(arrayList, my_cmtfromuser_interface);
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }

    @Override
    protected void onProgressUpdate(Post_admincomments... values) {
        arrayList.add(values[0]);
        adapter.notifyDataSetChanged();
    }
}
