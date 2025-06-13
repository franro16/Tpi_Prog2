package controller;
import controller.*;
import view.*;
import models.*;
import java.util.*;
import java.util.stream.Collectors;


public class ControladorCampeonato {
    private ConsolaView vista;
    private ControladorBoxeador controladorBoxeador;
    private ControladorCombate controladorCombate;

    public ControladorCampeonato(ConsolaView vista, ControladorBoxeador controladorBoxeador, ControladorCombate controladorCombate) {
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

            if (lista.size() >= 16) {

                if (lista.size() > 16) {
                    Collections.shuffle(lista, random);
                    lista = lista.subList(0, 16);
                }

                vista.mostrarMensaje("\nIniciando campeonato para: " + key);
                Boxeador ganador = ejecutarEliminatoria(lista);

                String[] partes = key.split("-");
                String categoria = partes[0];
                String genero = partes[1];

                vista.mostrarGanadorCampeonato(categoria, genero, ganador.getNombre());
            } else {
                vista.mostrarMensaje("\nNo hay suficientes boxeadores para iniciar el campeonato en " + key + " (se necesitan al menos 16).");
            }
        }
    }

    private Boxeador ejecutarEliminatoria(List<Boxeador> participantes) {
        List<Boxeador> ronda = new ArrayList<>(participantes);
        int numCombate = 1;

        while (ronda.size() > 1) {
            String fase = "";
            switch (ronda.size()) {
                case 16: fase = "Octavos de Final"; break;
                case 8: fase = "Cuartos de Final"; break;
                case 4: fase = "Semifinales"; break;
                case 2: fase = "Final"; break;
            }
            vista.mostrarMensaje("\n== " + fase + " ==");

            List<Boxeador> ganadores = new ArrayList<>();
            for (int i = 0; i < ronda.size(); i += 2) {
                vista.mostrarMensaje("\nCombate " + numCombate);
                Boxeador b1 = ronda.get(i);
                Boxeador b2 = ronda.get(i + 1);
                Boxeador ganador = controladorCombate.simularCombate(b1, b2);
                ganadores.add(ganador);
                numCombate++;
            }
            ronda = ganadores;
        }
        return ronda.get(0);
    }
}
