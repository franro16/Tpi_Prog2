package controller;

import models.*;
import view.ConsolaView;

import java.util.ArrayList;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


public class Controlador {
    private ConsolaView vista = new ConsolaView();
    private ArrayList<Categoria> categorias;
    private Map<String, Campeonato> campeonatosPorCategoriaGenero;
    private final String RUTA_ARCHIVO = "src/resources/boxeadores.txt";
    private List<Boxeador> boxeadoresTotales = new ArrayList<>();

    public Controlador() {
        categorias = definirCategorias();
        campeonatosPorCategoriaGenero = new HashMap<>();
    }

    private ArrayList<Categoria> definirCategorias() {
        ArrayList<Categoria> lista = new ArrayList<>();
        lista.add(new Categoria("Mosca", 52, 0));
        lista.add(new Categoria("Gallo", 55, 52.01));
        lista.add(new Categoria("Ligero", 60, 55.01));
        lista.add(new Categoria("Mediano", 72, 60.01));
        lista.add(new Categoria("Superpesado", Double.MAX_VALUE, 90.01));
        return lista;
    }

    public void iniciar() {
        int opcion;
        do {
            vista.mostrarMenu();
            opcion = vista.pedirOpcion();

            switch (opcion) {
                case 1:
                    iniciarCampeonatos();
                    break;
                case 2:
                    iniciarDesdeConsola();
                    break;
                case 3:
                    mostrarBoxeadoresPorCategoriaYGenero();
                    break;
                case 0:
                    vista.mostrarMensaje("Saliendo...");
                    break;
                default:
                    vista.mostrarMensaje("Opción inválida");
            }
        } while (opcion != 0);
    }

    private void iniciarCampeonatos() {
        ArrayList<Boxeador> boxeadores = cargarBoxeadoresDesdeArchivo(RUTA_ARCHIVO);
        if (boxeadores.isEmpty()) {
            vista.mostrarMensaje("No se cargaron boxeadores, verifique el archivo.");
            return;
        }
        prepararYEjecutarCampeonatos(boxeadores);
    }

    private void iniciarDesdeConsola() {
        vista.mostrarMensaje("Ingrese boxeadores.");
        ArrayList<Boxeador> boxeadores = cargarBoxeadoresDesdeArchivo(RUTA_ARCHIVO);

        boolean continuar = true;
        while (continuar) {
            String nombre = vista.pedirString("Nombre:");
            String apellido = vista.pedirString("Apellido:");
            double peso = vista.pedirDouble("Peso:");
            String genStr = vista.pedirString("Género (MASCULINO/FEMENINO):");
            Genero genero;
            try {
                genero = Genero.valueOf(genStr.trim().toUpperCase());
            } catch (Exception e) {
                vista.mostrarMensaje("Género inválido, intente de nuevo.");
                continue;
            }
            Boxeador b = new Boxeador(nombre, apellido, peso);
            b.setGenero(genero);
            asignarCategoria(b);
            boxeadoresTotales.add(b);

            if (b.getCategoria() == null) {
                vista.mostrarMensaje("Peso fuera de rango de categorías definidas.");
            } else {
                boxeadores.add(b);
                vista.mostrarMensaje("Boxeador agregado: " + nombre + " " + apellido +
                        " Categoría: " + b.getCategoria().getNombre() + " Género: " + genero);
                guardarBoxeadorEnArchivo(b, RUTA_ARCHIVO);
            }

            String seguir = vista.pedirString("¿Desea agregar otro boxeador? (S/N):");
            if (!seguir.trim().equalsIgnoreCase("S")) {
                continuar = false;
            }
        }


    }

    private void guardarBoxeadorEnArchivo(Boxeador b, String ruta) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta, true))) {
            String linea = b.getNombre() + "," + b.getApellido() + "," + b.getPeso() + "," +
                    b.getCategoria().getNombre() + "," + b.getGenero().toString();
            bw.write(linea);
            bw.newLine();
        } catch (IOException e) {
            vista.mostrarMensaje("Error guardando boxeador en archivo: " + e.getMessage());
        }
    }

    private ArrayList<Boxeador> cargarBoxeadoresDesdeArchivo(String ruta) {
        ArrayList<Boxeador> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length != 5) continue;

                String nombre = partes[0].trim();
                String apellido = partes[1].trim();
                double peso = Double.parseDouble(partes[2].trim());
                String genStr = partes[4].trim().toUpperCase();

                Genero genero;
                try {
                    genero = Genero.valueOf(genStr);
                } catch (Exception e) {
                    continue;
                }

                Boxeador b = new Boxeador(nombre, apellido, peso);
                b.setGenero(genero);
                asignarCategoria(b);
                lista.add(b);
                boxeadoresTotales.add(b);
            }
        } catch (Exception e) {
            vista.mostrarMensaje("Error leyendo archivo: " + e.getMessage());
        }
        return lista;
    }

    private Campeonato crearCampeonato(Categoria categoria, Genero genero, ArrayList<Boxeador> boxeadores) {
        Campeonato campeonato = new Campeonato();
        campeonato.setNombreCampeonato(categoria + " " + genero.toString());
        campeonato.setCategorias(new ArrayList<>());
        campeonato.setFaseActual(FaseTorneo.OCTAVOS);


        Collections.shuffle(boxeadores);
        ArrayList<Boxeador> boxeadoresLimitados = new ArrayList<>(boxeadores.subList(0, 16));

        campeonato.getCategorias().add(categoria);

        ArrayList<Combate> octavos = new ArrayList<>();
        for (int i = 0; i < boxeadoresLimitados.size(); i += 2) {
            Combate combate = new Combate();
            combate.setBoxeador1(boxeadoresLimitados.get(i));
            combate.setBoxeador2(boxeadoresLimitados.get(i + 1));
            combate.setGenero(genero);
            combate.setFase(FaseTorneo.OCTAVOS);
            octavos.add(combate);
        }
        campeonato.setCombates(octavos);
        return campeonato;
    }


    private Categoria buscarCategoriaPorNombre(String nombre) {
        for (Categoria c : categorias) {
            if (c.getNombre().equalsIgnoreCase(nombre)) return c;
        }
        return null;
    }

    private void asignarCategoria(Boxeador b) {
        for (Categoria c : categorias) {
            if (c.estaDentroDelRango(b.getPeso())) {
                b.setCategoria(c);
                return;
            }
        }
        b.setCategoria(null);
    }

    private void prepararYEjecutarCampeonatos(ArrayList<Boxeador> boxeadores) {
        Map<String, ArrayList<Boxeador>> boxeadoresPorCatGen = new HashMap<>();
        for (Boxeador b : boxeadores) {
            if (b.getCategoria() == null || b.getGenero() == null) continue;
            String key = b.getCategoria().getNombre() + "-" + b.getGenero().toString();
            boxeadoresPorCatGen.putIfAbsent(key, new ArrayList<>());
            boxeadoresPorCatGen.get(key).add(b);
        }

        if (boxeadoresPorCatGen.isEmpty()) {
            vista.mostrarMensaje("No hay boxeadores válidos para campeonatos.");
            return;
        }

        for (String key : boxeadoresPorCatGen.keySet()) {
            ArrayList<Boxeador> lista = boxeadoresPorCatGen.get(key);
            if (lista.size() < 16) {
                vista.mostrarMensaje("No hay suficientes boxeadores (mín 16) para campeonato " + key);
                continue;
            }

            String[] partes = key.split("-");
            String nombreCat = partes[0];
            Genero genero = Genero.valueOf(partes[1]);

            Categoria categoria = buscarCategoriaPorNombre(nombreCat);

            Campeonato campeonato = crearCampeonato(categoria, genero, lista);
            campeonatosPorCategoriaGenero.put(key, campeonato);
            ejecutarCampeonato(campeonato, categoria, genero);
        }
    }

    private void ejecutarCampeonato(Campeonato campeonato, Categoria categoria, Genero genero) {
        List<Boxeador> boxeadoresFiltrados = boxeadoresTotales.stream()
                .filter(b -> b.getCategoria().equals(categoria) && b.getGenero() == genero)
                .limit(16)
                .collect(Collectors.toList());

        if (boxeadoresFiltrados.size() < 16) {
            System.out.println("No hay suficientes boxeadores (mín 16) para campeonato " + categoria + "-" + genero);
            return;
        }


        Collections.shuffle(boxeadoresFiltrados);
        List<Boxeador> boxeadoresCampeonato = boxeadoresFiltrados.subList(0, 16);
        vista.mostrarMensaje("\nIniciando campeonato: " );
        ArrayList<Combate> faseActual = new ArrayList<>(campeonato.getCombates());

        while (true) {
            ArrayList<Combate> siguienteFase = new ArrayList<>();
            FaseTorneo fase = campeonato.getFaseActual();

            vista.mostrarMensaje("\nFASE: " + fase);

            int contador = 1;
            for (Combate combate : faseActual) {
                vista.mostrarMensaje("Combate " + contador + ": " +
                        combate.getBoxeador1().getNombre() + " " + combate.getBoxeador1().getApellido() +
                        " vs " +
                        combate.getBoxeador2().getNombre() + " " + combate.getBoxeador2().getApellido());
                contador++;

                Combate resultadoCombate = simularCombate(combate);
                siguienteFase.add(resultadoCombate);
            }

            vista.mostrarMensaje("\nGanadores de la fase " + fase + ":");
            for (Combate c : siguienteFase) {
                Boxeador ganador = c.getResultado().getGanador();
                vista.mostrarMensaje("Ganador: " + ganador.getNombre() + " " + ganador.getApellido());
            }

            if (fase == FaseTorneo.FINAL) {
                Boxeador campeon = siguienteFase.get(0).getResultado().getGanador();
                vista.mostrarGanadorCampeonato(
                        campeonato.getCategorias().get(0).getNombre(),
                        siguienteFase.get(0).getGenero().toString(),
                        campeon.getNombre() + " " + campeon.getApellido());
                break;
            }

            campeonato.setFaseActual(fase.siguienteFase());
            faseActual = new ArrayList<>(crearSiguienteFase(siguienteFase, campeonato.getFaseActual()));
            campeonato.setCombates(faseActual);

            if (faseActual.isEmpty()) {
                vista.mostrarMensaje("No hay suficientes combates para continuar.");
                break;
            }
        }
    }


    private Combate simularCombate(Combate combate) {
        Random random = new Random();
        ArrayList<Round> rounds = new ArrayList<>();
        int victoriasBoxeador1 = 0;
        int victoriasBoxeador2 = 0;

        for (int i = 1; i <= 3; i++) {
            Boxeador ganadorRound = random.nextBoolean() ? combate.getBoxeador1() : combate.getBoxeador2();
            if (ganadorRound.equals(combate.getBoxeador1())) victoriasBoxeador1++;
            else victoriasBoxeador2++;

            Round round = new Round(i, 180, ganadorRound);
            rounds.add(round);
            vista.mostrarGanadorRound(i, ganadorRound.getNombre() + " " + ganadorRound.getApellido());
        }

        Boxeador ganadorCombate = (victoriasBoxeador1 > victoriasBoxeador2) ? combate.getBoxeador1() : combate.getBoxeador2();
        if (victoriasBoxeador1 == victoriasBoxeador2) {
            ganadorCombate = random.nextBoolean() ? combate.getBoxeador1() : combate.getBoxeador2();
        }

        Resultado resultado = new Resultado(ganadorCombate, TipoVictoria.PUNTOS);
        combate.setResultado(resultado);
        combate.setRounds(rounds);
        vista.mostrarGanadorCombate(ganadorCombate.getNombre() + " " + ganadorCombate.getApellido());
        return combate;
    }

    private ArrayList<Combate> crearSiguienteFase(ArrayList<Combate> ganadores, FaseTorneo fase) {
        ArrayList<Combate> siguientesCombates = new ArrayList<>();
        ArrayList<Boxeador> boxeadores = new ArrayList<>();
        for (Combate c : ganadores) {
            boxeadores.add(c.getResultado().getGanador());
        }
        for (int i = 0; i < boxeadores.size() - 1; i += 2) {
            Combate c = new Combate();
            c.setBoxeador1(boxeadores.get(i));
            c.setBoxeador2(boxeadores.get(i + 1));
            c.setCategoria(buscarCategoriaPorNombre(boxeadores.get(i).getCategoria().getNombre()));
            c.setGenero(boxeadores.get(i).getGenero());
            c.setFase(fase);
            siguientesCombates.add(c);
        }
        return siguientesCombates;
    }
    private void mostrarBoxeadoresPorCategoriaYGenero() {

    }



}