package models;

public class Categoria {
    private String nombre;
    private double pesoMaximo;
    private double pesoMinimo;
    private String verCategoria;

    public Categoria(String nombre, double pesoMaximo, double pesoMinimo, String verCategoria) {
        this.nombre = nombre;
        this.pesoMaximo = pesoMaximo;
        this.pesoMinimo = pesoMinimo;
        this.verCategoria= verCategoria;
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

    public String getVerCategoria() {
        return verCategoria;
    }

    public void setVerCategoria(String verCategoria) {
        this.verCategoria = verCategoria;
    }

    public String verCategoria() {
        return "Categoría: " + nombre + " | Peso mínimo: " + pesoMinimo + "kg | Peso máximo: " + pesoMaximo + "kg";
    }
     public boolean estaDentroDelRango(double peso) {
        return peso >= pesoMinimo && peso <= pesoMaximo;
    }

}
