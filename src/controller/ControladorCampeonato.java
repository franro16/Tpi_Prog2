package controller;

// Importaciones necesarias
import controller.*;
import view.*;
import models.*;
import java.util.*;
import java.util.stream.Collectors;

// Clase principal encargada de organizar y ejecutar campeonatos de boxeadores
public class ControladorCampeonato {
    private ConsolaView vista;
    private ControladorBoxeador controladorBoxeador;
    private ControladorCombate controladorCombate;

    // Constructor: recibe las dependencias necesarias (vista, controlador de boxeadores y combates)
    public ControladorCampeonato(ConsolaView vista, ControladorBoxeador controladorBoxeador, ControladorCombate controladorCombate) {
        this.vista = vista;
        this.controladorBoxeador = controladorBoxeador;
        this.controladorCombate = controladorCombate;
    }

    // Metodo principal que inicia todos los campeonatos por categoría y género
    public void iniciarCampeonatos() {
        // Obtiene todos los boxeadores registrados
        List<Boxeador> boxeadores = controladorBoxeador.getBoxeadoresTotales();

        // Agrupa los boxeadores por categoría y género
        Map<String, List<Boxeador>> agrupados = boxeadores.stream()
                .filter(b -> b.getCategoria() != null && b.getGenero() != null)
                .collect(Collectors.groupingBy(b -> b.getCategoria().getNombre() + "-" + b.getGenero()));

        Random random = new Random();

        // Recorre cada grupo de boxeadores (por categoría-género)
        for (String key : agrupados.keySet()) {
            List<Boxeador> lista = agrupados.get(key);

            // Si hay suficientes boxeadores (al menos 16) para armar un campeonato
            if (lista.size() >= 16) {

                // Si hay más de 16, mezcla y selecciona solo 16 al azar
                if (lista.size() > 16) {
                    Collections.shuffle(lista, random);
                    lista = lista.subList(0, 16);
                }

                // Muestra mensaje de inicio del campeonato para ese grupo
                vista.mostrarMensaje("\nIniciando campeonato para: " + key);

                // Ejecuta la eliminatoria (serie de combates) y obtiene el ganador final
                Boxeador ganador = ejecutarEliminatoria(lista);

                // Separa la clave para mostrar nombre de categoría y género por separado
                String[] partes = key.split("-");
                String categoria = partes[0];
                String genero = partes[1];

                // Muestra el ganador del campeonato de ese grupo
                vista.mostrarGanadorCampeonato(categoria, genero, ganador.getNombre());
            } else {
                // Si no hay suficientes boxeadores para competir, muestra un aviso
                vista.mostrarMensaje("\nNo hay suficientes boxeadores para iniciar el campeonato en " + key + " (se necesitan al menos 16).");
            }
        }
    }

    //Metodo que ejecuta el campeonato por eliminación directa
    private Boxeador ejecutarEliminatoria(List<Boxeador> participantes) {
        List<Boxeador> ronda = new ArrayList<>(participantes);
        int numCombate = 1;

        // Mientras haya más de un boxeador, se sigue compitiendo
        while (ronda.size() > 1) {
            String fase = "";
            // Determina el nombre de la fase según la cantidad de participantes restantes
            switch (ronda.size()) {
                case 16: fase = "Octavos de Final"; break;
                case 8: fase = "Cuartos de Final"; break;
                case 4: fase = "Semifinales"; break;
                case 2: fase = "Final"; break;
            }
            vista.mostrarMensaje("\n== " + fase + " ==");

            List<Boxeador> ganadores = new ArrayList<>();
            // Realiza los combates por pares (b1 vs b2)
            for (int i = 0; i < ronda.size(); i += 2) {
                vista.mostrarMensaje("\nCombate " + numCombate);
                Boxeador b1 = ronda.get(i);
                Boxeador b2 = ronda.get(i + 1);
                // Simula el combate y guarda al ganador
                Boxeador ganador = controladorCombate.simularCombate(b1, b2);
                ganadores.add(ganador);
                numCombate++;
            }
            // Los ganadores pasan a la siguiente ronda
            ronda = ganadores;
        }

        // Cuando queda un solo boxeador, es el campeón
        return ronda.get(0);
    }
}
