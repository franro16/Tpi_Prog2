package controller;
import controller.*;
import view.*;
import models.*;
import java.util.*;

public class ControladorCombate {
    private ConsolaView vista;

    public ControladorCombate(ConsolaView vista) {
        this.vista = vista;
    }

    public Boxeador simularCombate(Boxeador b1, Boxeador b2) {
        Random rand = new Random();
        int puntosB1 = 0;
        int puntosB2 = 0;

        for (int i = 1; i <= 3; i++) {
            int ganadorRound = rand.nextInt(2);
            if (ganadorRound == 0) {
                puntosB1++;
                vista.mostrarGanadorRound(i, b1.getNombre());
            } else {
                puntosB2++;
                vista.mostrarGanadorRound(i, b2.getNombre());
            }
        }
        vista.mostrarMensaje("Combate entre: " + b1.getNombre() + " vs " + b2.getNombre());

        Boxeador ganador = (puntosB1 > puntosB2) ? b1 : b2;
        vista.mostrarGanadorCombate(ganador.getNombre());
        return ganador;
    }
}
