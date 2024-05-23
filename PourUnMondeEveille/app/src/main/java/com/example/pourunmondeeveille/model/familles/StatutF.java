package com.example.pourunmondeeveille.model.familles;

public class StatutF {
    private int id;
    private String statutF;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatutF() {
        return statutF;
    }

    public void setStatutF(String statutF) {
        this.statutF = statutF;
    }

    @Override
    public String toString() {
        return "StatutF{" +
                "id=" + id +
                ", statutF='" + statutF + '\'' +
                '}';
    }
}
