package models;

public class Boxeador {
    private String nombre;
    private String apellido;
    private Double peso;
    private Categoria categoria;
    private Genero genero;
    private RecordBoxeador recorboxeador;

    public Boxeador (String nombre, String apellido, double peso) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.peso = peso;
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

    public RecordBoxeador getRecorboxeador() {
        return recorboxeador;
    }

    public void setRecorboxeador(RecordBoxeador recorboxeador) {
        this.recorboxeador = recorboxeador;
    }
    @Override
    public String toString() {
        String nombreCategoria = (categoria != null) ? categoria.getNombre() : "Sin categoría";
        String generoStr = (genero != null) ? genero.toString() : "Sin género";

        return "Boxeador: " + nombre + " " + apellido +
                " | Peso: " + peso + " kg" +
                " | Categoría: " + nombreCategoria +
                " | Género: " + generoStr;
    }

}
