package view;

import models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsolaView {
    Scanner sc = new Scanner(System.in);

    public void mostrarMenu() {
        System.out.println("\n 🥊🥊Campeonatos de Boxeo 🥊🥊");
        System.out.println("1. Iniciar campeonatos✔️");
        System.out.println("2. Ingresar boxeadores📝");
        System.out.println("3. Listado de boxeadores por categoría y género👨🏽👩🏽");
        System.out.println("0. Salir❌");
        System.out.print("Seleccione una opción: ");
    }

    public int pedirOpcion() {
        while (true) {
            try {
                int opcion = Integer.parseInt(sc.nextLine());
                return opcion;
            } catch (NumberFormatException e) {
                System.out.print("Opción inválida. Intente de nuevo: ");
            }
        }
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }


    public String pedirString(String mensaje) {
        System.out.print(mensaje + " ");
        return sc.nextLine();
    }

    public double pedirDouble(String mensaje) {
        while (true) {
            System.out.print(mensaje + " ");
            try {
                double valor = Double.parseDouble(sc.nextLine());
                return valor;
            } catch (NumberFormatException e) {
                System.out.println("Número inválido. Intente de nuevo.");
            }
        }
    }
    public String seleccionarCampeonato(List<String> opcionesDisponibles) {
        System.out.println("\n🏆 Campeonatos disponibles:");
        for (int i = 0; i < opcionesDisponibles.size(); i++) {
            System.out.println((i + 1) + ". " + opcionesDisponibles.get(i));
        }
        System.out.println("0. Volver al menú principal");

        int opcion = -1;
        while (opcion < 0 || opcion > opcionesDisponibles.size()) {
            System.out.print("Seleccione el número del campeonato a iniciar (o 0 para volver): ");
            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Ingrese un número.");
            }
        }

        if (opcion == 0) {
            return null; // el usuario eligió salir
        }
        return opcionesDisponibles.get(opcion - 1);
    }
    public void mostrarGanadorRound(int numeroRound, String nombreGanador) {
        System.out.println("Ganador del round: " + numeroRound + ": " + nombreGanador);
    }

    public void mostrarGanadorCombate(String nombreGanador) {
        System.out.println("Ganador del combate🎉: " + nombreGanador);
    }

    public void mostrarGanadorCampeonato(String categoria, String genero, String nombreGanador) {
        try {
            System.out.println();
            System.out.println("🏆 GANADOR DEL CAMPEONATO 🏆");
            Thread.sleep(1000);

            System.out.println("Categoría: " + categoria);
            Thread.sleep(1000);

            System.out.println("Género: " + genero);
            Thread.sleep(1000);

            System.out.println("Ganador: " + nombreGanador + " 🎉");
            Thread.sleep(1000);

            System.out.println();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }


    public String pedirApellido(){
        System.out.println("Ingresar apellido: ");
        return sc.next();
    }
    public String pedirNombre(){
        System.out.println("Ingresar nombre: ");
        return sc.next();
    }
    public int pedirPeso(){
        System.out.println("Ingresar peso: ");
        return sc.nextInt();
    }
    public Genero pedirGenero() {
        System.out.println("Seleccionar género:");
        System.out.println("1 - Masculino");
        System.out.println("2 - Femenino");
        int opcion;
        do {
            System.out.print("Opción (1 o 2): ");
            opcion = sc.nextInt();
        } while (opcion != 1 && opcion != 2);

        return opcion == 1 ? Genero.MASCULINO : Genero.FEMENINO;
    }


    public void mostrarDatos(Boxeador dato) {
        System.out.println(dato);

    }
    public void mostrarDatos (ArrayList<Boxeador> dato){
        for (Object o : dato)
            System.out.println(o);
    }

    public void mostrarHistorial(Historial historial){
        System.out.println("Historial del Boxeador " + historial.getBoxeador().getNombre() + ":");
        if (historial.getCombates().isEmpty()){
            System.out.println("No tiene combates");
        }
        else {
            for (Combate c : historial.getCombates()){
                System.out.println(c.resumenCombate());

            }
        }
        if (historial.getFaseEliminado() != null) {
            System.out.println("Eliminado en fase: " + historial.getFaseEliminado());
        }

    }

}
