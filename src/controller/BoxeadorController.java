package controller;

import models.Boxeador;
import models.Categoria;
import models.Genero;
import view.ConsolaView;

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
