package com.example.pourunmondeeveille.model.connexion;

import com.google.gson.annotations.SerializedName;

public class ConnexionResponse {
    @SerializedName("id")
    private int id;  // L'identifiant unique de l'utilisateur

    @SerializedName("username")
    private String username;  // Le nom d'utilisateur

    @SerializedName("email")
    private String email;  // L'adresse e-mail de l'utilisateur

    @SerializedName("first_name")
    private String firstName;  // Le prénom (peut être vide)

    @SerializedName("last_name")
    private String lastName;  // Le nom de famille (peut être vide)

    // Getters pour accéder aux valeurs des champs
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
