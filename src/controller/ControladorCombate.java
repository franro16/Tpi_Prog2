package controller;
import controller.*;
import view.*;
import models.*;
import java.util.*;

// Clase que se encarga de simular un combate entre dos boxeadores
public class ControladorCombate {
    private ConsolaView vista;

    // Constructor que recibe la vista para mostrar mensajes en consola
    public ControladorCombate(ConsolaView vista) {
        this.vista = vista;
    }

    // Metodo que simula un combate entre dos boxeadores y devuelve el ganador
    public Boxeador simularCombate(Boxeador b1, Boxeador b2) {
        Random rand = new Random();
        int puntosB1 = 0;
        int puntosB2 = 0;

        // Simula 3 rounds del combate
        for (int i = 1; i <= 3; i++) {
            int ganadorRound = rand.nextInt(2); // 0 o 1 al azar

            // Suma un punto al boxeador que gana el round y muestra el ganador del round
            if (ganadorRound == 0) {
                puntosB1++;
                vista.mostrarGanadorRound(i, b1.getNombre());
            } else {
                puntosB2++;
                vista.mostrarGanadorRound(i, b2.getNombre());
            }
        }

        // Muestra mensaje del combate entre ambos boxeadores
        vista.mostrarMensaje("Combate entre: " + b1.getNombre() + " vs " + b2.getNombre());

        // Determina el ganador final segun los puntos acumulados
        Boxeador ganador = (puntosB1 > puntosB2) ? b1 : b2;

        // Muestra el ganador del combate
        vista.mostrarGanadorCombate(ganador.getNombre());

        // Devuelve el boxeador ganador
        return ganador;
    }
}
