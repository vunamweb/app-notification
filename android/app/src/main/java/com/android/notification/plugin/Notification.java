package com.android.notification.plugin;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.android.notification.MainActivity;
import com.android.notification.NotificationApplication;
import com.android.notification.NotificationConfig;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class Notification extends CordovaPlugin {

    //String PROJECT_NUMBER = "602980189688";
    String regid;
    GoogleCloudMessaging gcm;

    @Override
    public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {

        if (action.equals("Notification")) {

            String name = data.getString(0);
            String message = "Hello, " + name;
            String PROJECT_NUMBER=NotificationConfig.getInstance().getProjectNumber();
            String url=NotificationConfig.getInstance().getUrl();
            //callbackContext.success(message);
            getRegId(callbackContext, NotificationConfig.getInstance().getContext(),PROJECT_NUMBER,url);
            return true;
        } else {
            return false;
        }
    }

    public void getRegId(CallbackContext callbackContext, Context context,String PROJECT_NUMBER,final String url){
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                //String PROJECT_NUMBER=context.getResources().getString(R.string.project_number);
                String msg = "";
                //String url=context.getResources().getString(R.string.url);
                try {
                    if (gcm == null) {
                        gcm = GoogleCloudMessaging.getInstance(context);
                    }
                    regid = gcm.register(PROJECT_NUMBER);
                    msg = "Device registered, registration ID=" + regid;
                    //url=url + "?" +regid;
                    registerNotification(url+ "?regID=" +regid);
                    Log.i("GCM",  msg);

                } catch (IOException ex) {
                    msg = "Error :" + ex.getMessage();

                }
                return msg;
            }

            @Override
            protected void onPostExecute(String msg) {
                Log.i("GCM",  msg);
                callbackContext.success("");
                //etRegId.setText(msg + "\n");
            }
        }.execute(null, null, null);
    }

    public void registerNotification(String requestURL)
    {
        URL url;
        String response = "";
        try {
            url = new URL(requestURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");

            conn.connect();
            //checks server's status code first
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        conn.getInputStream()));
                while ((line = br.readLine()) != null) {
                    response += line;
                }
            } else {
                response = "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Log.i("GCM", "fdff");
        //return response;
    }
}
