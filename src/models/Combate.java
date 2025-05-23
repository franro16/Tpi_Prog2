package models;

import java.awt.*;
import java.util.ArrayList;

public class Combate {
    private Boxeador boxeador1;
    private Boxeador boxeador2;
    private Genero genero;
    private Categoria categoria;
    private FaseTorneo fase;
    private Resultado resultado;
    private ArrayList<Round> rounds;
//constructor
    public Combate(Boxeador boxeador1, Boxeador boxeador2, Genero genero, Categoria categoria, FaseTorneo fase, Resultado resultado, ArrayList<Round> rounds) {
        this.boxeador1 = boxeador1;
        this.boxeador2 = boxeador2;
        this.genero = genero;
        this.categoria = categoria;
        this.fase = fase;
        this.resultado = resultado;
        this.rounds = rounds;
    }
//metodos
//get and set
    public Boxeador getBoxeador1() {
        return boxeador1;
    }

    public void setBoxeador1(Boxeador boxeador1) {
        this.boxeador1 = boxeador1;
    }

    public Boxeador getBoxeador2() {
        return boxeador2;
    }

    public void setBoxeador2(Boxeador boxeador2) {
        this.boxeador2 = boxeador2;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public FaseTorneo getFase() {
        return fase;
    }

    public void setFase(FaseTorneo fase) {
        this.fase = fase;
    }

    public Resultado getResultado() {
        return resultado;
    }

    public void setResultado(Resultado resultado) {
        this.resultado = resultado;
    }

    public ArrayList<Round> getRounds() {
        return rounds;
    }

    public void setRounds(ArrayList<Round> rounds) {
        this.rounds = rounds;
    }
//toString
    @Override
    public String toString() {
        return "Combate{" +
                "boxeador1=" + boxeador1 +
                ", boxeador2=" + boxeador2 +
                ", genero=" + genero +
                ", categoria=" + categoria +
                ", fase=" + fase +
                ", resultado=" + resultado +
                ", rounds=" + rounds +
                '}';
    }
}
