package com.example.equipe1.webservice;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.equipe1.socialsquare.MainMenu;

public class ThreadScore extends AsyncTask<Void, Integer, Void>
{
    private String jeu;
    private String[] scores;

    public ThreadScore(String jeu) {
        this.jeu = jeu;
    }

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
        scores = ws.getScores(jeu);
        for(int i=0;i<scores.length;i++) {
            Log.d(String.valueOf(i),scores[i]);
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        MainMenu.PlaceholderFragment.scores = scores ;
        MainMenu.PlaceholderFragment.configGridView();
    }
}