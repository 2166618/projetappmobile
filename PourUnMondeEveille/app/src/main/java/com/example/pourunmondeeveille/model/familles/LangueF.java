package com.example.pourunmondeeveille.model.familles;

public class LangueF {
    private int id;
    private String libele;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibele() {
        return libele;
    }

    public void setLibele(String libele) {
        this.libele = libele;
    }

    @Override
    public String toString() {
        return "Langue{" +
                "id=" + id +
                ", libele='" + libele + '\'' +
                '}';
    }
}
