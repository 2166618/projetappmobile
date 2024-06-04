package com.example.pourunmondeeveille.model.enfants;

import android.os.Build;

import java.io.Serializable;
import org.threeten.bp.LocalDate;
import org.threeten.bp.Period;

public class Enfant implements Cloneable, Serializable {
    private int id;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
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

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
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

    // Method to calculate age
    public int getAge() {
        if (dateNaissance != null) {
            LocalDate currentDate = LocalDate.now();
            return Period.between(dateNaissance, currentDate).getYears();
        } else {
            return 0;
        }
    }


    @Override
    public String toString() {
        return "Enfant{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", dateNaissance=" + dateNaissance +
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
