package main;

import controller.BoxeadorController;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import models.*;
import view.CampeonatoView;
public class Main {
    public static void main(String[] args) {
//        URL imageUrl = Main.class.getResource("/resources/Plantilla Torneo.png");
//
//        if (imageUrl == null) {
//            System.err.println("No se encontró la imagen en la ruta especificada.");
//            return;
//        }
//
//        ImageIcon originalIcon = new ImageIcon(imageUrl);
//        // Escalar la imagen
//        Image scaledImage = originalIcon.getImage().getScaledInstance(600, 400, Image.SCALE_SMOOTH);
//        ImageIcon scaledIcon = new ImageIcon(scaledImage);
//
//        JFrame frame = new JFrame("Mostrar imagen escalada");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(620, 440);
//
//        JLabel label = new JLabel(scaledIcon);
//        frame.getContentPane().add(label);
//
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);
//    }
        BoxeadorController controlador = new BoxeadorController();
        controlador.iniciar();

    }

    package controller;
import models.*;

import java.util.ArrayList;
import java.util.Scanner;

    public class CampeonatoController {
        private Campeonato campeonato;
        private Scanner sc = new Scanner(System.in);

        public void iniciarCampeonato() {
            System.out.println("Ingrese el nombre del campeonato:");
            String nombre = sc.nextLine();

            campeonato = new Campeonato();
            campeonato.setNombreCampeonato(nombre);
            campeonato.setCategorias(new ArrayList<>());
            campeonato.setCombates(new ArrayList<>());
            campeonato.setFaseActual(FaseTorneo.OCTAVOS);

            System.out.println("Campeonato creado: " + nombre);
        }

        public void agregarCategoria() {
            System.out.println("Ingrese nombre de la categoría:");
            String nombre = sc.nextLine();
            System.out.println("Ingrese peso mínimo:");
            double min = sc.nextDouble();
            System.out.println("Ingrese peso máximo:");
            double max = sc.nextDouble();
            sc.nextLine(); // limpiar buffer

            Categoria categoria = new Categoria(nombre, max, min, nombre);
            campeonato.getCategorias().add(categoria);

            System.out.println("Categoría agregada: " + categoria.verCategoria());
        }

        public void agregarCombate() {
            System.out.println("Ingrese datos del primer boxeador:");
            Boxeador b1 = crearBoxeador();
            System.out.println("Ingrese datos del segundo boxeador:");
            Boxeador b2 = crearBoxeador();

            Categoria categoria = b1.getCategoria();
            Genero genero = b1.getGenero();

            System.out.println("¿Quién ganó? (1: " + b1.getNombre() + ", 2: " + b2.getNombre() + ")");
            int ganadorOpcion = sc.nextInt();
            Boxeador ganador = (ganadorOpcion == 1) ? b1 : b2;

            TipoVictoria tipoVictoria = seleccionarTipoVictoria();

            Resultado resultado = new Resultado(ganador, tipoVictoria);

            ArrayList<Round> rounds = new ArrayList<>(); // Podés pedirlos también si querés
            Combate combate = new Combate(b1, b2, genero, categoria, campeonato.getFaseActual(), resultado, rounds);
            campeonato.getCombates().add(combate);

            System.out.println("Combate agregado con resultado: " + resultado.mostrarResultado());
        }

        private Boxeador crearBoxeador() {
            System.out.print("Nombre: ");
            String nombre = sc.nextLine();
            System.out.print("Apellido: ");
            String apellido = sc.nextLine();
            System.out.print("Peso: ");
            double peso = sc.nextDouble();
            sc.nextLine();

            System.out.println("Género (1: Masculino, 2: Femenino):");
            int gen = sc.nextInt();
            sc.nextLine();
            Genero genero = (gen == 1) ? Genero.MASCULINO : Genero.FEMENINO;

            Categoria categoria = buscarCategoriaPorPeso(peso);
            if (categoria == null) {
                System.out.println("No hay categoría para ese peso. Asignando nulo.");
            }

            RecordBoxeador record = new RecordBoxeador(0, 0, 0);
            return new Boxeador(nombre, apellido, categoria, genero, record);
        }

        private Categoria buscarCategoriaPorPeso(double peso) {
            for (Categoria cat : campeonato.getCategorias()) {
                if (cat.estaDentroDelRango(peso)) {
                    return cat;
                }
            }
            return null;
        }

        private TipoVictoria seleccionarTipoVictoria() {
            System.out.println("Seleccione tipo de victoria:");
            System.out.println("1. KO");
            System.out.println("2. KOT");
            System.out.println("3. PUNTOS");
            System.out.println("4. ABANDONO");
            int opcion = sc.nextInt();
            sc.nextLine();

            return switch (opcion) {
                case 1 -> TipoVictoria.KO;
                case 2 -> TipoVictoria.KOT;
                case 3 -> TipoVictoria.PUNTOS;
                case 4 -> TipoVictoria.ABANDONO;
                default -> {
                    System.out.println("Opción inválida. Se usará PUNTOS.");
                    yield TipoVictoria.PUNTOS;
                }
            };
        }

        public void avanzarFase() {
            FaseTorneo faseActual = campeonato.getFaseActual();
            switch (faseActual) {
                case OCTAVOS -> campeonato.setFaseActual(FaseTorneo.CUARTOS);
                case CUARTOS -> campeonato.setFaseActual(FaseTorneo.SEMIFINAL);
                case SEMIFINAL -> campeonato.setFaseActual(FaseTorneo.FINAL);
                case FINAL -> System.out.println("El torneo ya está en la final.");
            }
            System.out.println("Nueva fase: " + campeonato.getFaseActual());
        }

        public void mostrarCampeonato() {
            System.out.println(campeonato);
        }

        public Object getCampeonato() {
        }
    }
}
