package controller;

import models.Boxeador;
import models.Categoria;
import models.Genero;
import view.ConsolaView;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoxeadorController {
    private ConsolaView vista = new ConsolaView();
    private List<Categoria> categorias;
    private Map<String, Campeonato> campeonatosPorCategoriaGenero;
//    public BoxeadorController() {
//
//        categorias.add(new Categoria("Peso Mosca", 52.0, 48.0));
//        categorias.add(new Categoria("Peso Pluma", 60.0, 52.1));
//        categorias.add(new Categoria("Peso Mediano", 75.0, 60.1));
//        categorias.add(new Categoria("Peso Pesado", 120.0, 75.1));
//    }
    private final String RUTA_ARCHIVO = "src/resources/boxeadores.txt";
    public BoxeadorController() {
        categorias = definirCategorias();
        campeonatosPorCategoriaGenero = new HashMap<>();
    }

    private List<Categoria> definirCategorias() {
        List<Categoria> lista = new ArrayList<>();
        lista.add(new Categoria("Mosca", 52, 0));
        lista.add(new Categoria("Gallo", 55, 52.01));
        lista.add(new Categoria("Ligero", 60, 55.01));
        lista.add(new Categoria("Mediano", 72, 60.01));
        lista.add(new Categoria("Superpesado", Double.MAX_VALUE, 90.01));
        return lista;
    }
    private void guardarBoxeadorEnArchivo(Boxeador b, String ruta) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta, true))) { // modo append

            String linea = b.getNombre() + "," + b.getApellido() + "," + b.getPeso() + "," +
                    b.getCategoria().getNombre() + "," + b.getGenero().toString();
            bw.write(linea);
            bw.newLine();
        } catch (IOException e) {
            vista.mostrarMensaje("Error guardando boxeador en archivo: " + e.getMessage());
        }
    }
    private List<Boxeador> cargarBoxeadoresDesdeArchivo(String ruta) {
        List<Boxeador> lista = new ArrayList<>();
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
            }
        } catch (Exception e) {
            vista.mostrarMensaje("Error leyendo archivo: " + e.getMessage());
        }
        return lista;
    }
//
//    public void registrarBoxeador() {
//        String nombre = vista.pedirNombre();
//        String apellido = vista.pedirApellido();
//        double peso = vista.pedirPeso();
//        Genero genero = vista.pedirGenero();
//
//        Boxeador boxeador = new Boxeador(nombre, apellido, peso);
//        boxeador.setGenero(genero);
//
//        // esto va a asignar la categotia segun el peso automativamente
//        for (Categoria cat : categorias) {
//            if (cat.estaDentroDelRango(peso)) {
//                boxeador.setCategoria(cat);
//                break;
//            }
//        }
//
//        boxeadorList.add(boxeador);
//        vista.mostrarDatos(boxeadorList);
//    }
//
//    public void iniciar() {
//        vista.mostrarMenu();
//        int opcion = vista.pedirOpcion();
//        while (opcion != 0) {
//            if (opcion == 1) {
//                registrarBoxeador();
//            }
//            vista.mostrarMenu();
//            opcion = vista.pedirOpcion();
//        }
//    }
//}
