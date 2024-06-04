package com.example.pourunmondeeveille.model.enfants;

import java.io.Serializable;

import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Enfant implements Cloneable, Serializable {
    private int id;
    private String nom;
    private String prenom;
    private String date_naissance;
    private String occupation;
    private String nationaliteE;
    private String religionE;
    private String langueE;
    private StatutE statutE;

    public int getId() {
        return id;
    }
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

    public String getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(String date_naissance) {
        this.date_naissance = date_naissance;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getNationaliteE() {
        return nationaliteE;
    }

    public void setNationaliteE(String nationaliteE) {
        this.nationaliteE = nationaliteE;
    }

    public String getReligionE() {
        return religionE;
    }

    public void setReligionE(String religionE) {
        this.religionE = religionE;
    }

    public String getLangueE() {
        return langueE;
    }

    public void setLangueE(String langueE) {
        this.langueE = langueE;
    }

    public StatutE getStatutE() {
        return statutE;
    }

    public void setStatutE(StatutE statutE) {
        this.statutE = statutE;
    }

    public int getAge() {
        if (date_naissance != null && !date_naissance.isEmpty()) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date birthDate = sdf.parse(date_naissance);
                Calendar birthDay = Calendar.getInstance();
                birthDay.setTimeInMillis(birthDate.getTime());

                Calendar today = Calendar.getInstance();
                int age = today.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR);

                if (today.get(Calendar.DAY_OF_YEAR) < birthDay.get(Calendar.DAY_OF_YEAR)){
                    age--;
                }
                return age;
            } catch (Exception e) {
                System.out.println("Erreur de format de date: " + e.getMessage());
            }
        }
        return 0; // Retourner 0 si date_naissance est nulle ou vide
    }


    @Override
    public String toString() {
        return "Enfant{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", dateNaissance=" + date_naissance +
                ", occupation='" + occupation + '\'' +
                ", nationaliteE='" + nationaliteE + '\'' +
                ", religionE='" + religionE + '\'' +
                ", langueE='" + langueE + '\'' +
                ", statutE=" + statutE +
                '}';
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
