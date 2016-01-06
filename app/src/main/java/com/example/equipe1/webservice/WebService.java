package com.example.equipe1.webservice;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by Cricri on 06/01/2016.
 */
public class WebService {

    Gson gson;

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

   public List<Score> getScores() {
       String scoreWS = "http://earthquake-report.com/feeds/recent-eq?json";

        try {
            // Envoi de la requête
            InputStream inputStream = sendRequest(new URL(scoreWS));

            // Vérification de l'inputStream
            if(inputStream != null) {
                // Lecture de l'inputStream dans un reader
                InputStreamReader reader = new InputStreamReader(inputStream);

                // Retourne la liste désérialisée par le moteur GSON
                return gson.fromJson(reader, new TypeToken<List<Score>>() {
                }.getType());
            }

        } catch (Exception e) {
            Log.e("WebService", "Impossible de rapatrier les données :(");
        }
        return null;
    }

    public void getTremblement() {
        String tremblementWS = "http://earthquake-report.com/feeds/recent-eq?json";


        try {
            // Envoi de la requête
            InputStream inputStream = sendRequest(new URL(tremblementWS));

            // Vérification de l'inputStream
            if(inputStream != null) {
                // Lecture de l'inputStream dans un reader
                InputStreamReader reader = new InputStreamReader(inputStream);

                // Retourne la liste désérialisée par le moteur GSON
                List<Tremblement> list =  gson.fromJson(reader, new TypeToken<List<Tremblement>>() {
                }.getType());
                Log.d("Webservice: list", Integer.toString(list.size()));
                Log.d("Webservice: exemple 1:",list.get(1).toString());
            }

        } catch (Exception e) {
            Log.e("WebService", "Impossible de rapatrier les données :");
            e.printStackTrace();
        }
    }

    public boolean isDispo() {
        boolean estDispo = true;

        String dispoWS = "http://earthquake-report.com/feeds/recent-eq?json";
        try {
            // Envoi de la requête
            InputStream inputStream = sendRequest(new URL(dispoWS));

            // Vérification de l'inputStream
            if(inputStream != null) {
                // Lecture de l'inputStream dans un reader
                InputStreamReader reader = new InputStreamReader(inputStream);

                // Retourne la liste désérialisée par le moteur GSON
                Disponibilite dispo =  gson.fromJson(reader, new TypeToken<Disponibilite>(){}.getType());
                return dispo.isDispo();
            }

        } catch (Exception e) {
            Log.e("WebService", "Impossible de rapatrier les données :(");
        }
        return estDispo;
    }
}