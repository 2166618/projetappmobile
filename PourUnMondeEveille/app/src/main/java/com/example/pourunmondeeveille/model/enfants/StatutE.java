package com.example.pourunmondeeveille.model.enfants;

public class StatutE {
    private int id;
    private String statutE;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatutE() {
        return statutE;
    }

    public void setStatutE(String statutE) {
        this.statutE = statutE;
    }

    @Override
    public String toString() {
        return "StatutE{" +
                "id=" + id +
                ", statutE='" + statutE + '\'' +
                '}';
    }
}
