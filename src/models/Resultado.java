package models;

public class Resultado {
    private Boxeador ganador;
    private TipoVictoria tipoVictoria;

    public Resultado(Boxeador ganador, TipoVictoria tipoVictoria) {
        this.ganador = ganador;
        this.tipoVictoria = tipoVictoria;
    }

    public Boxeador getGanador() {
        return ganador;
    }

    public void setGanador(Boxeador ganador) {
        this.ganador = ganador;
    }

    public TipoVictoria getTipoVictoria() {
        return tipoVictoria;
    }

    public void setTipoVictoria(TipoVictoria tipoVictoria) {
        this.tipoVictoria = tipoVictoria;
    }
    public String mostrarResultado(){
        return "Ganador: " + ganador.getNombre() + " " + ganador.getApellido() +
                "- Tipo de victoria: " + tipoVictoria;
    }
}
