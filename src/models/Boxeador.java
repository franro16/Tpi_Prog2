package models;

import java.util.ArrayList;

public class Boxeador {
    private String nombre;
    private String apellido;
    private double peso;
    private Categoria categoria;
    private Genero genero;
    private Historial historial;
    private FaseTorneo faseEliminacion;
    private int victorias =0;
    private int derrotas = 0;
    private int empates = 0;
    private int victoriasKO =0;


    // Constructor
    public Boxeador(String nombre, String apellido, double peso) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.peso = peso;
        this.historial = new Historial(this);
        this.faseEliminacion = null;
    }

    public Historial getHistorial() {
        return historial;
    }

    // Getters y Setters

    public FaseTorneo getFaseEliminacion() {
        return faseEliminacion;
    }

    public void setFaseEliminacion(FaseTorneo faseEliminacion) {
        this.faseEliminacion = faseEliminacion;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }


    //estos son metdos para que historial pueda modificar estadísticas
    public void incrementarVictorias() {
        victorias++;
    }

    public void incrementarDerrotas() {
        derrotas++;
    }

    public void incrementarEmpates() {
        empates++;
    }

    public void incrementarVictoriasKO() {
        victoriasKO++;
    }

    public int getVictorias() {
        return victorias;
    }

    public int getDerrotas() {
        return derrotas;
    }

    public int getEmpates() {
        return empates;
    }

    public int getVictoriasKO() {
        return victoriasKO;
    }


    // toString para mostrar datos del boxeador
    @Override
    public String toString() {
        String nombreCategoria = (categoria != null) ? categoria.getNombre() : "Sin categoría";
        String generoStr = (genero != null) ? genero.toString() : "Sin género";
        String faseElimStr = (faseEliminacion != null) ? faseEliminacion.toString() : "Campeón / En torneo";

        return "Boxeador: " + nombre + " " + apellido +
                " | Peso: " + peso + " kg" +
                " | Categoría: " + nombreCategoria +
                " | Género: " + generoStr +
                " | Victorias: " + victorias +
                " | Derrotas: " + derrotas +
                " | Empates: " + empates +
                " | Victorias KO: " + victoriasKO +
                " | Eliminado en: " + faseElimStr;
    }
}
