package com.example.pourunmondeeveille.model;

import com.google.gson.annotations.SerializedName;

public class Utilisateur {
    @SerializedName("id")
    private int id;

    @SerializedName("nom_utilisateur")
    private String nomUtilisateur;

    @SerializedName("mot_de_passe")
    private String motDePasse;

    public Utilisateur(String nomUtilisateur, String motDePasse) {
        this.nomUtilisateur = nomUtilisateur;
        this.motDePasse = motDePasse;
    }


}
