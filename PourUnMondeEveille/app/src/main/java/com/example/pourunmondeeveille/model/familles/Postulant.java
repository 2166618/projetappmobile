package com.example.pourunmondeeveille.model.familles;

public class Postulant {
    private int id;
    private String nom;
    private String prenom;
    private String telephone;
    private String adresse;
    private String ville;
    private String province;
    private String code_postal;
    private int Nbre_enfant;

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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCode_postal() {
        return code_postal;
    }

    public void setCode_postal(String code_postal) {
        this.code_postal = code_postal;
    }

    public int getNbre_enfant() {
        return Nbre_enfant;
    }

    public void setNbre_enfant(int nbre_enfant) {
        Nbre_enfant = nbre_enfant;
    }

    @Override
    public String toString() {
        return "Postulant{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", telephone='" + telephone + '\'' +
                ", adresse='" + adresse + '\'' +
                ", ville='" + ville + '\'' +
                ", province='" + province + '\'' +
                ", code_postal='" + code_postal + '\'' +
                ", Nbre_enfant=" + Nbre_enfant +
                '}';
    }
}
