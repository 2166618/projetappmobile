package com.example.pourunmondeeveille.model;

import com.google.gson.annotations.SerializedName;

public class Utilisateur {
    @SerializedName("id")
    private int id;

    @SerializedName("username")
    private String nomUtilisateur;

    @SerializedName("password")
    private String motDePasse;

    @SerializedName("email")
    private String courriel;

    @SerializedName("first_name")
    private String prenom;

    @SerializedName("last_name")
    private String nomDeFamille;

    public Utilisateur(String nomUtilisateur, String motDePasse) {
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

    public String getCourriel() {
        return courriel;
    }

    public void setCourriel(String courriel) {
        this.courriel = courriel;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNomDeFamille() {
        return nomDeFamille;
    }

    public void setNomDeFamille(String nomDeFamille) {
        this.nomDeFamille = nomDeFamille;
    }
}
