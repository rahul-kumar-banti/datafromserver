package com.rahul.datafromserver;

import android.content.Context;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.rahul.datafromserver.LoginActivity;
import com.rahul.datafromserver.MainActivity;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class apiClient extends AsyncTask<String,String,String> {
String data="",mydata="";
    public resultOnLogin rl;

    public apiClient(resultOnLogin r) {
        this.rl=r;
    }

    String val;
    int i=0;
    @Override
    protected void onPreExecute() {

    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            URL url=new URL("http://itsevent.000webhostapp.com/api/users/login_api.php");
            String name=strings[0];
            String pass=strings[1];
            publishProgress(String.valueOf((int)(i++)));

            HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
            if (httpURLConnection.getResponseCode() == 200) {
                InputStream inputStream=httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader=new InputStreamReader(inputStream,"utf-8");
                BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
                String line="";
                while(line!=null){
                    line=bufferedReader.readLine();
                    if(line!=null)
                    data += line;

                }

                Log.d("data",data);

//                LoginActivity.tvresult.setText(data);
//                JsonReader jsonReader=new JsonReader(inputStreamReader);

            } else {
                Log.v("error1","server not  working");


            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    @Override
    protected void onProgressUpdate(String... values) {

    }

    @Override
    protected void onPostExecute(String s) {
this.mydata=s;
rl.resultpublish(this.mydata);

    }
}
