package models;

public class Boxeador {
    private String nombre;
    private String apellido;
    private Double peso;
    private Categoria categoria;
    private Genero genero;
    private RecordBoxeador recorboxeador;

    public Boxeador(String nombre, String apellido, Categoria categoria, Genero genero, RecordBoxeador recorboxeador) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.categoria = categoria;
        this.genero = genero;
        this.recorboxeador = recorboxeador;
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

    public void asignarCategoria(Categoria nuevaCategoria) {
        this.categoria = nuevaCategoria;
    }

    public Boxeador getBoxeador() {
        return this;
    }
}
