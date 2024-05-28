package com.example.pourunmondeeveille.model.familles;

public class NationaliteF {
    private int id;
    private String titre;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    @Override
    public String toString() {
        return "Nationalite{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                '}';
    }
}
