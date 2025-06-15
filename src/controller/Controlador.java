package controller;
import controller.*;
import models.*;
import view.*;
import java.util.ArrayList;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Controlador {

    private ConsolaView vista;
    private ControladorBoxeador controladorBoxeador;
    private ControladorCombate controladorCombate;
    private ControladorCampeonato controladorCampeonato;

    public Controlador() {
        this.vista = new ConsolaView();
        this.controladorBoxeador = new ControladorBoxeador(vista);
        this.controladorCombate = new ControladorCombate(vista);
        this.controladorCampeonato = new ControladorCampeonato(vista, controladorBoxeador, controladorCombate);
    }
    public void iniciarSistema() {
        controladorBoxeador.cargarBoxeadoresDesdeArchivo("src/resources/boxeadores.txt");
        vista.mostrarMensaje("Boxeadores cargados: " + controladorBoxeador.getBoxeadoresTotales().size());

        int opcion;
        do {
            vista.mostrarMenu();
            opcion = vista.pedirOpcion();

            switch (opcion) {
                case 1:
                    controladorCampeonato.iniciarCampeonatos();
                    break;
                case 2:
                    controladorBoxeador.iniciarDesdeConsola();
                    break;
                case 3:
                    controladorBoxeador.mostrarBoxeadoresPorCategoriaYGenero();
                    break;
                case 0:
                    vista.mostrarMensaje("Saliendo...");
                    return;
                default:
                    vista.mostrarMensaje("Opción inválida");
            }
        } while (opcion != 0);
    }
}