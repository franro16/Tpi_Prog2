package controller;

import models.Boxeador;
import models.Categoria;
import models.Genero;
import view.ConsolaView;

import java.util.ArrayList;

public class BoxeadorController {
    private ConsolaView vista = new ConsolaView();
    private ArrayList<Boxeador> boxeadorList = new ArrayList<>();
    private ArrayList<Categoria> categorias = new ArrayList<>();

    public BoxeadorController() {

        categorias.add(new Categoria("Peso Mosca", 52.0, 48.0));
        categorias.add(new Categoria("Peso Pluma", 60.0, 52.1));
        categorias.add(new Categoria("Peso Mediano", 75.0, 60.1));
        categorias.add(new Categoria("Peso Pesado", 120.0, 75.1));
    }

    public void registrarBoxeador() {
        String nombre = vista.pedirNombre();
        String apellido = vista.pedirApellido();
        double peso = vista.pedirPeso();
        Genero genero = vista.pedirGenero();

        Boxeador boxeador = new Boxeador(nombre, apellido, peso);
        boxeador.setGenero(genero);

        // esto va a asignar la categotia segun el peso automativamente
        for (Categoria cat : categorias) {
            if (cat.estaDentroDelRango(peso)) {
                boxeador.setCategoria(cat);
                break;
            }
        }

        boxeadorList.add(boxeador);
        vista.mostrarDatos(boxeadorList);
    }

    public void iniciar() {
        vista.mostrarMenu();
        int opcion = vista.pedirOpcion();
        while (opcion != 0) {
            if (opcion == 1) {
                registrarBoxeador();
            }
            vista.mostrarMenu();
            opcion = vista.pedirOpcion();
        }
    }
}
