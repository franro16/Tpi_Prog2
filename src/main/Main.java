package main;

import controller.*;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

// Clase principal que arranca la aplicacion
public class Main {
    public static void main(String[] args) {
        // Crea una instancia del controlador principal del sistema
        Controlador controlador = new Controlador();

        // Inicia el sistema mostrando el menu y gestionando la logica
        controlador.iniciarSistema();
    }
}
