package com.example.equipe1.webservice;

import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Cricri on 06/01/2016.
 */
public class ThreadWS extends AsyncTask<Void, Void, String> {
    @Override
    protected void onPostExecute(String result) {

    }


    @Override
    protected String doInBackground(Void... params) {
        WebService ws = new WebService();
        ws.getTremblement();

        return null;
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected void onProgressUpdate(Void... values) {}
}