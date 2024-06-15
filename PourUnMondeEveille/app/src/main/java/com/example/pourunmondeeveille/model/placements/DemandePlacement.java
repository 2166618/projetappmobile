package com.example.pourunmondeeveille.model.placements;

import com.example.pourunmondeeveille.model.familles.FamilleAccueil;

public class DemandePlacement {
    private int id;
    private String date;
    private int numero;
    private int etat_id;
    private FamilleAccueil familleAccueil;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getEtat_id() {
        return etat_id;
    }

    public void setEtat_id(int etat_id) {
        this.etat_id = etat_id;
    }

    public FamilleAccueil getFamilleAccueil() {
        return familleAccueil;
    }

    public void setFamilleAccueil(FamilleAccueil familleAccueil) {
        this.familleAccueil = familleAccueil;
    }

    @Override
    public String toString() {
        return "DemandePlacement{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", numero=" + numero +
                ", etat_id=" + etat_id +
                ", familleAccueil=" + familleAccueil +
                '}';
    }
}
