package models;

import java.util.ArrayList;

public class CuadroCampeonato {
    private ArrayList<Combate> octavos;
    private ArrayList<Combate> cuartos;
    private ArrayList<Combate> semiFinales;
    private Combate finalCombate;

    public CuadroCampeonato(ArrayList<Combate> octavos, ArrayList<Combate> cuartos, ArrayList<Combate> semiFinales) {
        this.octavos = octavos;
        this.cuartos = cuartos;
        this.semiFinales = semiFinales;
        this.finalCombate = finalCombate;
    }

    public CuadroCampeonato() {
    }

    public ArrayList<Combate> getOctavos() {
        return octavos;
    }

    public void setOctavos(ArrayList<Combate> octavos) {
        this.octavos = octavos;
    }

    public ArrayList<Combate> getCuartos() {
        return cuartos;
    }

    public void setCuartos(ArrayList<Combate> cuartos) {
        this.cuartos = cuartos;
    }

    public ArrayList<Combate> getSemiFinales() {
        return semiFinales;
    }

    public void setSemiFinales(ArrayList<Combate> semiFinales) {
        this.semiFinales = semiFinales;
    }

    public Combate getFinalCombate() {
        return finalCombate;
    }

    public void setFinalCombate(Combate finalCombate) {
        this.finalCombate = finalCombate;
    }

    @Override
    public String toString() {
        return "CuadroCampeonato{" +
                "octavos=" + octavos +
                ", cuartos=" + cuartos +
                ", semiFinales=" + semiFinales +
                '}';
    }
}
