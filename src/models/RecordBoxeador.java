package models;

public class RecordBoxeador {
    private int victorias;
    private int derrotas;
    private int empates;

    public RecordBoxeador(int victorias, int derrotas, int empates) {
        this.victorias = victorias;
        this.derrotas = derrotas;
        this.empates = empates;
    }

    public int getVictorias() {
        return victorias;
    }

    public int getDerrotas() {
        return derrotas;
    }

    public int getEmpates() {
        return empates;
    }

    public String verRecord() {
        return "Victorias: " + victorias + ", Derrotas: " + derrotas + ", Empates: " + empates;
    }
}
