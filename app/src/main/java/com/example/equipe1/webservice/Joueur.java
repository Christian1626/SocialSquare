package com.example.equipe1.webservice;

/**
 * Created by Cricri on 06/01/2016.
 */
public class Joueur {
    private int id;
    private String name;

    public Joueur(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Joueur(String name) {

        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
