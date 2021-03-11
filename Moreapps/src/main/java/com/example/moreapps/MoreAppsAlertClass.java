package com.example.moreapps;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MoreAppsAlertClass {

    List<MoreAppsGetSet> arryList;
    Context mContext;
    Activity mactivity;

    public MoreAppsAlertClass(Context mContext, Activity mactivity) {
        this.mContext = mContext;
        this.mactivity = mactivity;
    }

    public void FetchData(String url) {
        new JsonTask().execute(url);
    }


    private class JsonTask extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            super.onPreExecute();


        }

        protected String doInBackground(String... params) {


            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();


                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                    Log.d("Response: ", "> " + line);   //here u ll get whole response...... :-)

                }

                return buffer.toString();


            } catch (MalformedURLException e) {
                e.printStackTrace();

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (result!=null) {
                parsedata(result);
            }
           // Log.d("response", result);
        }
    }

    private void parsedata(String resp) {

        try {
            JSONArray obj = new JSONArray(resp);
            arryList = new ArrayList<>();
            MoreAppsGetSet gt;

            for (int i = 0; i < obj.length(); i++) {

                JSONObject c = obj.getJSONObject(i);
                gt = new MoreAppsGetSet();
                gt.set_AppName(c.getString("Name"));
                gt.set_iconUrl(c.getString("IconUrl"));
                gt.set_playURL(c.getString("PlayUrl"));


                arryList.add(gt);

            }
            Log.d("response", arryList.toString());

            mactivity.runOnUiThread(new Runnable() {
                public void run() {
                    MoreAppsAlert alert = new MoreAppsAlert(mContext, mactivity, arryList);
                    alert.show();

                }
            });

        } catch (Exception e) {
            e.getMessage();
        }


    }
}