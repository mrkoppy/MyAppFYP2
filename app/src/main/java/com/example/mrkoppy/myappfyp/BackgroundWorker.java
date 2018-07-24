package com.example.mrkoppy.myappfyp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/*GUI conduct message to Background, <doInBackground,Void,doInBackground(return result must return in String type)> */
/*Create constructor for background worker, assign ctx to context(variable create by Context*/
public class BackgroundWorker extends AsyncTask<String,Void,String>{
    Context context;
    AlertDialog alertDialog;

    BackgroundWorker(Context ctx){
        context = ctx;
    }

    /*Background of AsyncTask to contacting with DB*/
    /*This->type is determine whether "login" or "register"*/
    /*Created new instance for HttpURLConnection and use openConnection function in url*/
    /*Used setRequestMethod("POST") function in instance created*/
    /*Set it can read or write data is true*/
    /*"user_name"(DB) = user_name(Data)*/
    /*Outputstream from username and password,use bufferWriter read it,encode username and password will
    * be passed into post_data,write post data,flush encode information into document and close it*/
    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String login_url = "http://192.168.0.103/login.php";
        String register_url = "http://192.168.0.103/register.php";
        if(type.equals("login")){
            try {
                String user_name = params[1];
                String password = params[2];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")+"&"
                        +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!= null){
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if(type.equals("register")){
            try {
                String gender = params[1];
                String name = params[2];
                String email = params[3];
                String mobile = params[4];
                String postcode = params[5];
                String studentid = params[6];
                String password = params[7];
                String role = params[8];
                URL url = new URL(register_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("Gender","UTF-8")+"="+URLEncoder.encode(gender,"UTF-8")+"&"
                        +URLEncoder.encode("Name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"
                        +URLEncoder.encode("Email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"
                        +URLEncoder.encode("Mobile","UTF-8")+"="+URLEncoder.encode(mobile,"UTF-8")+"&"
                        +URLEncoder.encode("Postcode","UTF-8")+"="+URLEncoder.encode(postcode,"UTF-8")+"&"
                        +URLEncoder.encode("StuID","UTF-8")+"="+URLEncoder.encode(studentid,"UTF-8")+"&"
                        +URLEncoder.encode("Password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8")+"&"
                        +URLEncoder.encode("Role","UTF-8")+"="+URLEncoder.encode(role,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!= null){
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /*Title of alert diaglog is Login Status*/
    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Status");
    }

    /*Coz first of all start with String so display result display as String type(String result) */
    @Override
    protected void onPostExecute(String result) {
        alertDialog.setMessage(result);

        if(result.equals("login success!!!!!!")) {
            alertDialog.setButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    Intent intent = new Intent(context,RolePage.class);
                    context.startActivity(intent);
                }
            });
        }

        else if(result.equals("Insert Successfully")){
            alertDialog.setButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    Intent intent = new Intent(context,MainActivity.class);
                    context.startActivity(intent);
                }
            });

        }

        alertDialog.show();


    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

}
