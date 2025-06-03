package models;

public class Categoria {
    private String nombre;
    private double pesoMaximo;
    private double pesoMinimo;

    public Categoria(String nombre, double pesoMaximo, double pesoMinimo) {
        this.nombre = nombre;
        this.pesoMaximo = pesoMaximo;
        this.pesoMinimo = pesoMinimo;
    }

    public double getPesoMinimo() {
        return pesoMinimo;
    }

    public void setPesoMinimo(double pesoMinimo) {
        this.pesoMinimo = pesoMinimo;
    }

    public double getPesoMaximo() {
        return pesoMaximo;
    }

    public void setPesoMaximo(double pesoMaximo) {
        this.pesoMaximo = pesoMaximo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String verCategoria() {
        return "Categoría: " + nombre + " | Peso mínimo: " + pesoMinimo + "kg | Peso máximo: " + pesoMaximo + "kg";
    }
     public boolean estaDentroDelRango(double peso) {
        return peso >= pesoMinimo && peso <= pesoMaximo;
    }

    public boolean estaDentroDelRango(double peso) {
        return peso >= pesoMinimo && peso <= pesoMaximo;
    }
}
