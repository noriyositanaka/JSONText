package com.example.jsontext;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AsyncHTTPConnection extends AsyncTask<Object, Object,Object> {

    private AsyncHTTPConnectionListener asyncHTTPConnectionListener;

    public void setAsyncHTTPConnectionListener(AsyncHTTPConnectionListener asyncHTTPConnectionListener) {
        this.asyncHTTPConnectionListener = asyncHTTPConnectionListener;
    }

    @Override
    protected Object doInBackground(Object[] objects) {

        URL url;
        HttpURLConnection httpURLConnection;
        BufferedReader bufferedReader;
        StringBuilder stringBuilder = new StringBuilder();

        JSONObject jsonObject;


        try {
            url = new URL("http","192.168.5.108","/YamahaExtendedControl/v1/system/getFeatures");
            httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();

            Log.d("JSONText","doInBackground: " + httpURLConnection.getResponseCode());

            bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));

            String line;

            while((line = bufferedReader.readLine())!=null){
                stringBuilder.append(line);
            }

            jsonObject = new JSONObject(stringBuilder.toString());

            Log.d("JSONTextTest", "doInBackground: "+jsonObject.keys().next().toString());

            return jsonObject;

        } catch (JSONException e) {
            e.printStackTrace();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void onPostExecute(Object o) {

        asyncHTTPConnectionListener.onPostExecute(o);

        super.onPostExecute(o);
    }
}
