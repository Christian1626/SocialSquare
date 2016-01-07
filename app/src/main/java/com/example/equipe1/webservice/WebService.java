package com.example.equipe1.webservice;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Cricri on 06/01/2016.
 */
public class WebService {

    Gson gson;
    private int port = 8000;
    private String ip = "192.168.43.203";


    public WebService() {
        gson = new Gson();
    }

    private InputStream sendRequest(URL url) throws Exception {

        try {
            // Ouverture de la connexion
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            // Connexion à l'URL
            urlConnection.connect();

            // Si le serveur nous répond avec un code OK
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                return urlConnection.getInputStream();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

   public String[] getScores(String jeu) {
       String scoreWS = "http://"+ip+":"+port+"/get_score?game="+jeu;
       Log.d("Webservice","getScores");

        try {
            // Envoi de la requête
            InputStream inputStream = sendRequest(new URL(scoreWS));

            // Vérification de l'inputStream
            if(inputStream != null) {
                // Lecture de l'inputStream dans un reader
                InputStreamReader reader = new InputStreamReader(inputStream);

                // Retourne la liste désérialisée par le moteur GSON
                List<Score> list =  gson.fromJson(reader, new TypeToken<List<Score>>() {
                }.getType());

                String[] array = listToArrayScore(list);

                return array;

            }

        } catch (Exception e) {
            Log.e("WebService", "Impossible de rapatrier les données 1: score");
            e.printStackTrace();
        }
       return null;
    }

    public List<Jeu> getJeu() {
        String jeuWS = "http://"+ip+":"+port+"/get_game";

        try {
            // Envoi de la requête
            InputStream inputStream = sendRequest(new URL(jeuWS));

            // Vérification de l'inputStream
            if(inputStream != null) {
                // Lecture de l'inputStream dans un reader
                InputStreamReader reader = new InputStreamReader(inputStream);

                // Retourne la liste désérialisée par le moteur GSON
                return gson.fromJson(reader, new TypeToken<List<Jeu>>() {
                }.getType());
            }

        } catch (Exception e) {
            Log.e("WebService", "Impossible de rapatrier les données 2: jeu(");
            e.printStackTrace();
        }
        return null;
    }

    public boolean isUsed() {
        boolean estDispo = true;

        String dispoWS = "http://"+ip+":"+port+"/get_terminal?id=1";
        try {
            // Envoi de la requête
            InputStream inputStream = sendRequest(new URL(dispoWS));

            // Vérification de l'inputStream
            if(inputStream != null) {
                // Lecture de l'inputStream dans un reader
                InputStreamReader reader = new InputStreamReader(inputStream);

                // Retourne la liste désérialisée par le moteur GSON
                Disponibilite dispo =  gson.fromJson(reader, new TypeToken<Disponibilite>(){}.getType());
                System.out.println("Webservice dispo:"+dispo);
                return dispo.isUsed();
            }

        } catch (Exception e) {
            Log.e("WebService", "Impossible de rapatrier les données 4: dispo(");
            e.printStackTrace();
        }
        return estDispo;
    }

    public String[] listToArrayScore(List<Score> list) {
        int n = Math.min(list.size(), 7);
        String[] array = new String[n*3+3];
        array[0] = "Place";
        array[1] = "Nom";
        array[2] = "Score";

        for(int i=0;i<n;i++){
            array[3+i*3] = String.valueOf(i+1);
            array[3+i*3+1] = list.get(i).getUsername();
            array[3+i*3+2] = Integer.toString(list.get(i).getScore());
        }

        return array;
    }
}