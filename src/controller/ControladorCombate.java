package controller;
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
    public Boxeador simularCombate(Boxeador b1, Boxeador b2, Genero genero, Categoria categoria, FaseTorneo fase) {
        Random rand = new Random();
        int puntosB1 = 0;
        int puntosB2 = 0;
        ArrayList<Round> rounds = new ArrayList<>();

        vista.mostrarMensaje("Combate entre: " + b1.getNombre() + " vs " + b2.getNombre());


        // Simula 3 rounds del combate
        for (int i = 1; i <= 3; i++) {
            int ganadorRound = rand.nextInt(2); // 0 o 1 al azar
            Round round = new Round(i, ganadorRound == 0 ? b1 : b2);
            rounds.add(round);

            // Suma un punto al boxeador que gana el round y muestra el ganador del round
            if (ganadorRound == 0) {
                puntosB1++;
                vista.mostrarGanadorRound(i, b1.getNombre());
            } else {
                puntosB2++;
                vista.mostrarGanadorRound(i, b2.getNombre());
            }
        }

        Resultado resultado;
        Boxeador ganador = null;
        boolean fueKO = false;

        if (puntosB1 > puntosB2) {
            ganador = b1;
            resultado = Resultado.GANADOR_BOXEADOR1;
            // Random para KO (ejemplo 30% chance)
            fueKO = rand.nextInt(100) < 30;
        } else if (puntosB2 > puntosB1) {
            ganador = b2;
            resultado = Resultado.GANADOR_BOXEADOR2;
            fueKO = rand.nextInt(100) < 30;
        } else {
            resultado = Resultado.EMPATE;
        }

        vista.mostrarGanadorCombate(ganador != null ? ganador.getNombre() : "Empate");

        Combate combate = new Combate(b1, b2, genero, categoria, fase, resultado, rounds);


        b1.getHistorial().agregarCombate(combate);
        b2.getHistorial().agregarCombate(combate);

        switch (resultado) {

            case GANADOR_BOXEADOR1:
                b1.getHistorial().registrarVictoria(fueKO);
                b2.getHistorial().registrarDerrota();
                break;
            case GANADOR_BOXEADOR2:
                b2.getHistorial().registrarVictoria(fueKO);
                b1.getHistorial().registrarDerrota();
                break;
            case EMPATE:
                b1.getHistorial().registrarEmpate();
                b2.getHistorial().registrarEmpate();
                break;

            // Registrar fase de eliminación al perdedor
            // EMPATE: Eliminá a ambos

        }

        return ganador;
    }
}

