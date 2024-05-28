package com.example.pourunmondeeveille.model.familles;

import java.io.Serializable;

public class FamilleAccueil implements Cloneable, Serializable {
    private int id;
    private int Enfant_Age_Min;
    private int Enfant_Age_Max;
    private int Nbre_Enfant_Voulu;
    private LangueF langueF;
    private NationaliteF nationaliteF;
    private ReligionF religionF;
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

    public LangueF getLangue() {
        return langueF;
    }

    public void setLangue(LangueF langueF) {
        this.langueF = langueF;
    }

    public NationaliteF getNationalite() {
        return nationaliteF;
    }

    public void setNationalite(NationaliteF nationaliteF) {
        this.nationaliteF = nationaliteF;
    }

    public ReligionF getReligion() {
        return religionF;
    }

    public StatutF getStatutF() {
        return statutF;
    }

    public void setStatutF(StatutF statutF) {
        this.statutF = statutF;
    }

    public void setReligion(ReligionF religionF) {
        this.religionF = religionF;
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
                ", langue=" + langueF +
                ", nationalite=" + nationaliteF +
                ", religion=" + religionF +
                ", statutF=" + statutF +
                ", postulant=" + postulant +
                '}';
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
