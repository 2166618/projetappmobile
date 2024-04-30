package com.example.pourunmondeeveille.model;

import com.google.gson.annotations.SerializedName;

public class ConnexionRequest {
    @SerializedName("username")
    private String nomUtilisateur;

    @SerializedName("password")
    private String motDePasse;

    public ConnexionRequest(String nomUtilisateur, String motDePasse) {
        this.nomUtilisateur = nomUtilisateur;
        this.motDePasse = motDePasse;
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
}
