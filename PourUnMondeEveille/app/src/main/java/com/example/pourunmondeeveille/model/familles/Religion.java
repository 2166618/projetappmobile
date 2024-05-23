package com.example.pourunmondeeveille.model.familles;

public class Religion {
    private int id;
    private String nom;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Religion{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                '}';
    }
}
