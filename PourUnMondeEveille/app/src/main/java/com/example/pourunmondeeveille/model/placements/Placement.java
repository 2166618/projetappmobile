package com.example.pourunmondeeveille.model.placements;

import com.example.pourunmondeeveille.model.enfants.Enfant;

public class Placement {
    private int id;
    private String dateDebut;
    private String dateFin;
    private DemandePlacement demandePlacement;
    private Enfant enfant;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public DemandePlacement getDemandePlacement() {
        return demandePlacement;
    }

    public void setDemandePlacement(DemandePlacement demandePlacement) {
        this.demandePlacement = demandePlacement;
    }

    public Enfant getEnfant() {
        return enfant;
    }

    public void setEnfant(Enfant enfant) {
        this.enfant = enfant;
    }

    @Override
    public String toString() {
        return "Placement{" +
                "id=" + id +
                ", dateDebut='" + dateDebut + '\'' +
                ", dateFin='" + dateFin + '\'' +
                ", demandePlacement=" + demandePlacement +
                ", enfant=" + enfant +
                '}';
    }
}
