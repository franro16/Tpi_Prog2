package models;
import models.*;
import java.util.ArrayList;

public class Historial {
    private Boxeador boxeador;
    private ArrayList<Combate> combates;
    private FaseTorneo faseEliminacion;
    private FaseTorneo faseEliminado;

    public Historial(Boxeador boxeador) {
        this.boxeador = boxeador;
        this.combates = new ArrayList<>();
    }

    public Boxeador getBoxeador() {
        return boxeador;
    }

    public ArrayList<Combate> getCombates() {
        return combates;
    }

    public void agregarCombate(Combate combate) {
        combates.add(combate);
    }

    // Métodos para actualizar estadísticas según resultado
    public void registrarVictoria(boolean porKO) {
        boxeador.incrementarVictorias();
        if (porKO) {
            boxeador.incrementarVictoriasKO();
        }
    }

    public void registrarDerrota() {
        boxeador.incrementarDerrotas();
    }


    public void setFaseEliminacion(FaseTorneo fase) {
        this.faseEliminacion = fase;
    }

    public FaseTorneo getFaseEliminacion() {
        return faseEliminacion;
    }
    public FaseTorneo getFaseEliminado() {
        return faseEliminado;
    }

    public void setFaseEliminado(FaseTorneo faseEliminado) {
        this.faseEliminado = faseEliminado;
    }

    public void mostrarHistorial() {
        for (Combate c : combates) {
            String rival = (c.getBoxeador1() == boxeador) ? c.getBoxeador2().getNombre() : c.getBoxeador1().getNombre();
            String resultadoStr = "";
            switch (c.getResultado()) {
                case GANADOR_BOXEADOR1:
                    resultadoStr = (c.getBoxeador1() == boxeador) ? "Ganó" : "Perdió";
                    break;
                case GANADOR_BOXEADOR2:
                    resultadoStr = (c.getBoxeador2() == boxeador) ? "Ganó" : "Perdió";
                    break;

            }
            System.out.println("- " + resultadoStr + " vs " + rival + " | Fase: " + c.getFase().name());
        }

        System.out.println("Victorias: " + boxeador.getVictorias() +
                " | Derrotas: " + boxeador.getDerrotas() +
                " | Victorias KO: " + boxeador.getVictoriasKO());
    }

}
