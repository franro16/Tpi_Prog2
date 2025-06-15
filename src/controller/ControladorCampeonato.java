package controller;

import view.*;
import models.*;
import java.util.*;
import java.util.stream.Collectors;
import javax.swing.*;

public class ControladorCampeonato {
    private ConsolaView vista;
    private ControladorBoxeador controladorBoxeador;
    private ControladorCombate controladorCombate;
    private Map<String, List<Boxeador>> torneoActual = new LinkedHashMap<>();

    public ControladorCampeonato(ConsolaView vista, ControladorBoxeador controladorBoxeador,
                                 ControladorCombate controladorCombate) {
        this.vista = vista;
        this.controladorBoxeador = controladorBoxeador;
        this.controladorCombate = controladorCombate;
    }

    public void iniciarCampeonatos() {
        List<Boxeador> boxeadores = controladorBoxeador.getBoxeadoresTotales();
        Map<String, List<Boxeador>> agrupados = boxeadores.stream()
                .filter(b -> b.getCategoria() != null && b.getGenero() != null)
                .collect(Collectors.groupingBy(b -> b.getCategoria().getNombre() + "-" + b.getGenero()));

        Random random = new Random();

        for (String key : agrupados.keySet()) {
            List<Boxeador> lista = agrupados.get(key);
            if (lista.size() >= 8) {  // Requerimos 8 boxeadores para Cuartos
                if (lista.size() > 8) {
                    Collections.shuffle(lista, random);
                    lista = lista.subList(0, 8);
                }

                torneoActual.clear();
                torneoActual.put("Cuartos", new ArrayList<>(lista));

                Boxeador ganador = ejecutarEliminatoria(lista);
                String[] partes = key.split("-");

                // Mostrar en consola
                vista.mostrarGanadorCampeonato(partes[0], partes[1], ganador.getNombre());

                // Mostrar interfaz gráfica
                SwingUtilities.invokeLater(() -> {
                    new TorneoView(torneoActual, partes[0], partes[1], ganador.getNombre() + " " + ganador.getApellido());
                });
            } else {
                vista.mostrarMensaje("\nNo hay suficientes boxeadores para iniciar el campeonato en " + key + " (se necesitan al menos 8).");
            }
        }
    }

    private Boxeador ejecutarEliminatoria(List<Boxeador> participantes) {
        List<Boxeador> ronda = new ArrayList<>(participantes);
        int numCombate = 1;

        while (ronda.size() > 1) {
            FaseTorneo fase = null;
            switch (ronda.size()) {
                case 8: fase = FaseTorneo.CUARTOS; break;
                case 4: fase = FaseTorneo.SEMIFINAL; break;
                case 2: fase = FaseTorneo.FINAL; break;
            }

            if (fase != null) {
                vista.mostrarMensaje("\n== " + fase.nombreMostrar() + " ==");
            }

            List<Boxeador> ganadores = new ArrayList<>();
            for (int i = 0; i < ronda.size(); i += 2) {
                vista.mostrarMensaje("\nCombate " + numCombate);
                Boxeador b1 = ronda.get(i);
                Boxeador b2 = ronda.get(i + 1);

                // Guardar finalistas ANTES del combate
                if (ronda.size() == 2) {
                    torneoActual.put("Final", Arrays.asList(b1, b2));
                }

                Boxeador ganador = controladorCombate.simularCombate(b1, b2, b1.getGenero(), b1.getCategoria(), fase);
                ganadores.add(ganador);
                numCombate++;

                // Guardar semifinalistas
                if (ronda.size() == 4) {
                    torneoActual.put("Semifinal", new ArrayList<>(ronda));
                }
            }
            ronda = ganadores;
        }


        vista.mostrarMensaje("\n=== Historial de combates ===");
        for (Boxeador b : participantes) {
            vista.mostrarMensaje("\n" + b.getNombre() + " " + b.getApellido());
            b.getHistorial().mostrarHistorial();
        }

        return ronda.get(0);
    }
}