package com.example.equipe1.webservice;

import android.os.AsyncTask;
import android.util.Log;

import com.example.equipe1.socialsquare.MainMenu;

public class ThreadDispo extends AsyncTask<Void, Integer, Void>
{
    private boolean isDispo;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected void onProgressUpdate(Integer... values){
        super.onProgressUpdate(values);
    }

    @Override
    protected Void doInBackground(Void... arg0) {
        Log.d("doInBackGround", "debut");
        WebService ws = new WebService();
        isDispo = !ws.isUsed();
        Log.d("Thread isDispo",String.valueOf(isDispo));
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        MainMenu.PlaceholderFragment.isDispo = isDispo ;
        MainMenu.PlaceholderFragment.changerDispo();
    }
}