package view;

import models.Boxeador;
import models.Genero;

import java.util.ArrayList;
import java.util.Scanner;

public class ConsolaView {
    Scanner sc = new Scanner(System.in);

    public void mostrarMenu() {
        System.out.println("\n=== Campeonatos de Boxeo ===");
        System.out.println("1. Iniciar campeonatos");
        System.out.println("2. Ingresar boxeadores");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    public String pedirApellido(){
        System.out.println("ingresasr apellido: ");
        return sc.next();
    }
    public String pedirNombre(){
        System.out.println("ingresar nombre: ");
        return sc.next();
    }
    public int pedirPeso(){
        System.out.println("ingresar peso: ");
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

    public int pedirOpcion() {
        return sc.nextInt();
    }
}
