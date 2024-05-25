package com.example.pourunmondeeveille.model.familles;

import java.io.Serializable;

public class FamilleAccueil implements Cloneable, Serializable {
    private int id;
    private int Enfant_Age_Min;
    private int Enfant_Age_Max;
    private int Nbre_Enfant_Voulu;
    private Langue langue;
    private Nationalite nationalite;
    private Religion religion;

    private StatutF statutF;
    private Postulant postulant;

    public int getId() {
        return id;
    }

    public int getEnfant_Age_Min() {
        return Enfant_Age_Min;
    }

    public void setEnfant_Age_Min(int enfant_Age_Min) {
        Enfant_Age_Min = enfant_Age_Min;
    }

    public int getEnfant_Age_Max() {
        return Enfant_Age_Max;
    }

    public void setEnfant_Age_Max(int enfant_Age_Max) {
        Enfant_Age_Max = enfant_Age_Max;
    }

    public int getNbre_Enfant_Voulu() {
        return Nbre_Enfant_Voulu;
    }

    public void setNbre_Enfant_Voulu(int nbre_Enfant_Voulu) {
        Nbre_Enfant_Voulu = nbre_Enfant_Voulu;
    }

    public Langue getLangue() {
        return langue;
    }

    public void setLangue(Langue langue) {
        this.langue = langue;
    }

    public Nationalite getNationalite() {
        return nationalite;
    }

    public void setNationalite(Nationalite nationalite) {
        this.nationalite = nationalite;
    }

    public Religion getReligion() {
        return religion;
    }

    public StatutF getStatutF() {
        return statutF;
    }

    public void setStatutF(StatutF statutF) {
        this.statutF = statutF;
    }

    public void setReligion(Religion religion) {
        this.religion = religion;
    }

    public Postulant getPostulant() {
        return postulant;
    }

    public void setPostulant(Postulant postulant) {
        this.postulant = postulant;
    }

    @Override
    public String toString() {
        return "FamilleAccueil{" +
                "id=" + id +
                ", Enfant_Age_Min=" + Enfant_Age_Min +
                ", Enfant_Age_Max=" + Enfant_Age_Max +
                ", Nbre_Enfant_Voulu=" + Nbre_Enfant_Voulu +
                ", langue=" + langue +
                ", nationalite=" + nationalite +
                ", religion=" + religion +
                ", statutF=" + statutF +
                ", postulant=" + postulant +
                '}';
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
