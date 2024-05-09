package com.example.pourunmondeeveille.model.familles;

public class Postulant {
    private String nom;  // Correspond au champ 'nom' dans le serializer
    private String prenom;  // Correspond au champ 'prenom'

    // Getters et setters pour chaque champ
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    // Ajoutez une méthode toString pour l'affichage
    @Override
    public String toString() {
        return nom + " " + prenom;  // Ajustez selon vos besoins
    }
}
