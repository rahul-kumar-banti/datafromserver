package com.rahul.datafromserver;

import android.os.AsyncTask;
import android.util.JsonReader;

import com.rahul.datafromserver.LoginActivity;
import com.rahul.datafromserver.MainActivity;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class apiClient extends AsyncTask<String,String,String> {
String data="";
    @Override
    protected String doInBackground(String... strings) {
        try {
            URL url=new URL("http://itsevent.000webhostapp.com/api/users/login_api.php");
            HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
            if (httpURLConnection.getResponseCode() == 200) {
                InputStream inputStream=httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader=new InputStreamReader(inputStream,"utf-8");
                BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
                String line="";
                while(line!=null){
                    line=bufferedReader.readLine();
                    data=data+line+"/n";

                }
//                JsonReader jsonReader=new JsonReader(inputStreamReader);

            } else {

            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    protected void onPostExecute(String s) {
LoginActivity.tvresult.setText(data);
    }
}
