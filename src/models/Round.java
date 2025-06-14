package models;

public class Round {
    private int numero;
    private int duracionSegundos;
    private Boxeador ganador;
//constructor
    public Round(int numero, Boxeador ganador) {
        this.numero = numero;
        this.duracionSegundos = duracionSegundos;
        this.ganador = ganador;
    }
//metodos
//get and set
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getDuracionSegundos() {
        return duracionSegundos;
    }

    public void setDuracionSegundos(int duracionSegundos) {
        this.duracionSegundos = duracionSegundos;
    }

    public Boxeador getGanador() {
        return ganador;
    }

    public void setGanador(Boxeador ganador) {
        this.ganador = ganador;
    }
//toString
    @Override
    public String toString() {
        return "Round{" +
                "numero=" + numero +
                ", duracionSegundos=" + duracionSegundos +
                ", ganador=" + ganador +
                '}';
    }
}
