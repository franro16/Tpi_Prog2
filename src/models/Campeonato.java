package models;

import java.util.ArrayList;

public class Campeonato {
    private String nombreCampeonato;
    private ArrayList<Categoria> categorias;
    private FaseTorneo faseActual;
    private ArrayList<Combate> combates;

    public Campeonato(String nombreCampeonato, ArrayList<Categoria> categorias, FaseTorneo faseActual, ArrayList<Combate> combates) {
        this.nombreCampeonato = nombreCampeonato;
        this.categorias = categorias;
        this.faseActual = faseActual;
        this.combates = combates;
    }

    public Campeonato() {
    }

    public String getNombreCampeonato() {
        return nombreCampeonato;
    }

    public void setNombreCampeonato(String nombreCampeonato) {
        this.nombreCampeonato = nombreCampeonato;
    }

    public ArrayList<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(ArrayList<Categoria> categorias) {
        this.categorias = categorias;
    }

    public FaseTorneo getFaseActual() {
        return faseActual;
    }

    public void setFaseActual(FaseTorneo faseActual) {
        this.faseActual = faseActual;
    }

    public ArrayList<Combate> getCombates() {
        return combates;
    }

    public void setCombates(ArrayList<Combate> combates) {
        this.combates = combates;
    }

    @Override
    public String toString() {
        return "Campeonato{" +
                "nombreCampeonato='" + nombreCampeonato + '\'' +
                ", categorias=" + categorias +
                ", faseActual=" + faseActual +
                ", combates=" + combates +
                '}';
    }
}
