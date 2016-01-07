package com.example.equipe1.webservice;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.equipe1.socialsquare.MainMenu;

import java.util.List;

/**
 * Created by Cricri on 06/01/2016.
 */
public class ThreadWS extends AsyncTask<Void, Void, String[]> {
    private String jeu;
    private String[] scores;

    public ThreadWS(String jeu) {
        this.jeu = jeu;
    }

    @Override
    protected String[] doInBackground(Void... params) {
        Log.d("doInBackGround","debut");
        WebService ws = new WebService();
        scores = ws.getScores(jeu);

        Log.d("OnPreExcute", "1");
        String[] array = {"test","test","test"};
        System.out.println("scores:"+scores);
        MainMenu.PlaceholderFragment.scores = scores ;
        MainMenu.PlaceholderFragment.configGridView();
        Log.d("OnPreExcute", "2");

        return scores;
    }


    @Override
    protected void onPreExecute() {

    }



    @Override
    protected void onProgressUpdate(Void... values) {

    }
}