package models;

import java.util.ArrayList;

public class Historial {
    private Boxeador boxeador;
    private ArrayList<Combate> combates;
//constructor
    public Historial(Boxeador boxeador, ArrayList<Combate> combates) {
        this.boxeador = boxeador;
        this.combates = combates;
    }
//get and set
    public Boxeador getBoxeador() {
        return boxeador;
    }

    public void setBoxeador(Boxeador boxeador) {
        this.boxeador = boxeador;
    }

    public ArrayList<Combate> getCombates() {
        return combates;
    }

    public void setCombates(ArrayList<Combate> combates) {
        this.combates = combates;
    }
//toString
    @Override
    public String toString() {
        return "Historial{" +
                "boxeador=" + boxeador +
                ", combates=" + combates +
                '}';
    }
}

