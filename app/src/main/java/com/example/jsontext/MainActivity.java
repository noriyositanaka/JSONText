package com.example.jsontext;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
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
import java.util.function.ObjDoubleConsumer;

public class MainActivity extends AppCompatActivity {

    JSONObject devFeatureJSON;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String HOST = "";


        AsyncHTTPConnection asyncHTTPConnection = new AsyncHTTPConnection();
        asyncHTTPConnection.execute();
        asyncHTTPConnection.setAsyncHTTPConnectionListener(new AsyncHTTPConnectionListener() {
            @Override
            public Object onPostExecute(Object o) {
                devFeatureJSON = (JSONObject)o;
                try {
                    JSONObject systemJSON = new JSONObject();

                    JSONArray input_list = devFeatureJSON.getJSONObject("system").getJSONArray("input_list");

     //               JSONObject inputList= systemJSON.getJSONObject("input_list");


                    Log.d("inputlist", "onPostExecute: "+input_list.toString(4));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return devFeatureJSON;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
