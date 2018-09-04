package com.example.mrkoppy.myappfyp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;

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

import static android.content.Context.MODE_PRIVATE;

/*GUI conduct message to Background, <doInBackground,Void,doInBackground(return result must return in String type)> */
/*Create constructor for background worker, assign ctx to context(variable create by Context*/
public class BackgroundWorker extends AsyncTask<String,Void,String>{
    Context context;
    AlertDialog alertDialog;
    EditText name;
    SharedPreferences sharedpre;

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
        /*Home*/
        String login_url = "http://192.168.0.103/login.php";
        String register_url = "http://192.168.0.103/register.php";
        String renew_url = "http://192.168.0.103/update.php";
        String update_url = "http://192.168.0.103/updatevehicle.php";
        String register_route_url = "http://192.168.0.103/driver_route.php";
        String booking_url = "http://192.168.0.103/updatebybooktrip.php";
        String reset = "http://192.168.0.103/Resetpassword.php";
        /*Uni*/
        /*String login_url = "http://192.168.43.41/login.php";
        String register_url = "http://192.168.43.41/register.php";
        String renew_url = "http://192.168.43.41/update.php";
        String update_url = "http://192.168.43.41/updatevehicle.php";
        String register_route_url = "http://192.168.43.41/driver_route.php";
        String booking_url = "http://192.168.43.41/updatebybooktrip.php";*/
        sharedpre = context.getSharedPreferences("UserData", MODE_PRIVATE);

            if(type.equals("login")){
            try {
                String user_name = params[1];

                sharedpre = context.getSharedPreferences("UserData", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpre.edit();
                editor.putString("user_name", user_name);
                editor.apply();

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

                sharedpre = context.getSharedPreferences("UserData", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpre.edit();
                editor.putString("user_email", email);
                editor.apply();

                String mobile = params[4];
                String postcode = params[5];
                String studentid = params[6];
                String password = params[7];
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
                        +URLEncoder.encode("Password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
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
        } else if (type.equals("renew")){
            try {
                String Name = params[1];
                String Password = params[2];
                String username = sharedpre.getString("user_name", "");
                URL url = new URL(renew_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("Name","UTF-8")+"="+URLEncoder.encode(Name,"UTF-8")+"&"
                        +URLEncoder.encode("Password","UTF-8")+"="+URLEncoder.encode(Password,"UTF-8") +"&"
                        +URLEncoder.encode("UserName","UTF-8")+"="+URLEncoder.encode(username,"UTF-8");
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
        }else if (type.equals("update")){
            try {
                String carplate = params[1];
                String carseats = params[2];
                String username = sharedpre.getString("user_name", "");
                URL url = new URL(update_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("cpnumber","UTF-8")+"="+URLEncoder.encode(carplate,"UTF-8")+"&"
                        +URLEncoder.encode("csnumber","UTF-8")+"="+URLEncoder.encode(carseats,"UTF-8") +"&"
                        +URLEncoder.encode("UserName","UTF-8")+"="+URLEncoder.encode(username,"UTF-8");
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
        } else if (type.equals("register_route")){
                try {
                    String username = sharedpre.getString("user_name", "");
                    String start_name = params[1];
                    String end_name = params[2];
                    String latstartlocation = params[3];
                    String lngstartlocation = params[4];
                    String latnendlocation = params[5];
                    String lngendlocation = params[6];
                    String price = params[7];
                    String seats_left = params[8];
                    String datentime = params[9];
                    String status = params[10];
                    String duration = params[11];
                    /*String username = sharedpre.getString("user_name", "");*/
                    URL url = new URL(register_route_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(username,"UTF-8") +"&"
                            +URLEncoder.encode("Start_name","UTF-8")+"="+URLEncoder.encode(start_name,"UTF-8")+"&"
                            +URLEncoder.encode("End_name","UTF-8")+"="+URLEncoder.encode(end_name,"UTF-8") +"&"
                            +URLEncoder.encode("Latstart_location","UTF-8")+"="+URLEncoder.encode(latstartlocation,"UTF-8") +"&"
                            +URLEncoder.encode("Lngstart_location","UTF-8")+"="+URLEncoder.encode(lngstartlocation,"UTF-8") +"&"
                            +URLEncoder.encode("Latend_location","UTF-8")+"="+URLEncoder.encode(latnendlocation,"UTF-8") +"&"
                            +URLEncoder.encode("Lngend_location","UTF-8")+"="+URLEncoder.encode(lngendlocation,"UTF-8") + '&'
                            +URLEncoder.encode("Price","UTF-8")+"="+URLEncoder.encode(price,"UTF-8") +"&"
                            +URLEncoder.encode("Seats_left","UTF-8")+"="+URLEncoder.encode(seats_left,"UTF-8") +"&"
                            +URLEncoder.encode("DateNTime","UTF-8")+"="+URLEncoder.encode(datentime,"UTF-8") +"&"
                            +URLEncoder.encode("Status","UTF-8")+"="+URLEncoder.encode(status,"UTF-8") +"&"
                            +URLEncoder.encode("Duration","UTF-8")+"="+URLEncoder.encode(duration,"UTF-8") ;
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

            } else if (type.equals("booking")){
                try {
                    String username = sharedpre.getString("user_name", "");
                    String routeID = sharedpre.getString("RouteID","");

                    URL url = new URL(booking_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("UserName","UTF-8")+"="+URLEncoder.encode(username,"UTF-8")+"&"
                            +URLEncoder.encode("RouteID","UTF-8")+"="+URLEncoder.encode(routeID,"UTF-8");
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
            } else if (type.equals("reset")){
                try {
                    String Name = params[1];

                    URL url = new URL(reset);
                    HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("Name","UTF-8")+"="+URLEncoder.encode(Name,"UTF-8") +"&"
                            +URLEncoder.encode("Name","UTF-8")+"="+URLEncoder.encode(Name,"UTF-8");
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
        Log.d("result", result);
        alertDialog.setMessage(result);


        if(result.equals("Login Success")) {
            alertDialog.setButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    Intent intent = new Intent(context,trip.class);
                    context.startActivity(intent);
                }
            });
        }

        else if(result.equals("Registered Successfully")){
            alertDialog.setButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    Intent intent = new Intent(context,Login.class);
                    context.startActivity(intent);

                }
            });

        }

        else if(result.equals("Update Successfully")){
            alertDialog.setButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    Intent intent = new Intent(context,Login.class);
                    context.startActivity(intent);
                }
            });

        }

        else if(result.equals("Insert Vehicle Successfully")){
            alertDialog.setButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    Intent intent = new Intent(context,trip.class);
                    context.startActivity(intent);
                }
            });

        }

        else if(result.equals("Route Registered Successfully")){
            alertDialog.setButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    Intent intent = new Intent(context,trip.class);
                    context.startActivity(intent);
                }
            });
        }

        else if(result.equals("Book Trip Successfully")){
            alertDialog.setButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    SharedPreferences sharedpre = context.getSharedPreferences("UserData", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpre.edit();
                    editor.putString("RouteID", "");
                    editor.apply();

                    Intent intent = new Intent(context,trip.class);
                    context.startActivity(intent);
                }
            });
        }

        else if(result.equals("Reset Successfully")){
            alertDialog.setButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    Intent intent = new Intent(context,Login.class);
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
