package com.example.pourunmondeeveille.model.familles;

public class FamilleAccueil implements Cloneable {
    private int Enfant_Age_Min;  // Correspond au champ 'Enfant_Age_Min'
    private int Enfant_Age_Max;  // Correspond au champ 'Enfant_Age_Max'
    private int Nbre_Enfant_Voulu;  // Correspond au champ 'Nbre_Enfant_Voulu'
    private String langueF;  // Correspond au champ 'langueF'
    private String nationaliteF;  // Correspond au champ 'nationaliteF'
    private String religionF;  // Correspond au champ 'religionF'
    private String statutF;  // Correspond au champ 'statutF'
    private Postulant postulant;  // Correspond au champ imbriqué 'postulant'

    // Getters et setters pour chaque champ
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

    public String getLangueF() {
        return langueF;
    }

    public void setLangueF(String langueF) {
        this.langueF = langueF;
    }

    public String getNationaliteF() {
        return nationaliteF;
    }

    public void setNationaliteF(String nationaliteF) {
        this.nationaliteF = nationaliteF;
    }

    public String getReligionF() {
        return religionF;
    }

    public void setReligionF(String religionF) {
        this.religionF = religionF;
    }

    public String getStatutF() {
        return statutF;
    }

    public void setStatutF(String statutF) {
        this.statutF = statutF;
    }

    public Postulant getPostulant() {
        return postulant;
    }

    public void setPostulant(Postulant postulant) {
        this.postulant = postulant;
    }

    // Ajoutez une méthode toString pour l'affichage ou le débogage
    @Override
    public String toString() {
        return "FamilleAccueil{" +
                "Enfant_Age_Min=" + Enfant_Age_Min +
                ", Enfant_Age_Max=" + Enfant_Age_Max +
                ", Nbre_Enfant_Voulu=" + Nbre_Enfant_Voulu +
                ", langueF='" + langueF + '\'' +
                ", nationaliteF='" + nationaliteF + '\'' +
                ", religionF='" + religionF + '\'' +
                ", statutF='" + statutF + '\'' +
                ", postulant=" + postulant +
                '}';
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
