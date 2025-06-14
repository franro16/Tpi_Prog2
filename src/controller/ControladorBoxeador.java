package controller;
import controller.*;
import view.*;
import models.*;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

    //Definiciones generales y ruta del archivo de carga boxeadores
    public class ControladorBoxeador {
    private ConsolaView vista;
    private final String RUTA_ARCHIVO = "src/resources/boxeadores.txt";
    private List<Boxeador> boxeadoresTotales = new ArrayList<>();
    private ArrayList<Categoria> categorias;

    public ControladorBoxeador(ConsolaView vista) {
        this.vista = vista;
        categorias = definirCategorias();
    }
    //Definicion de liites de peso para cada categoria de cada boxeador
    private ArrayList<Categoria> definirCategorias() {
        ArrayList<Categoria> lista = new ArrayList<>();
        lista.add(new Categoria("Mosca", 52, 0));
        lista.add(new Categoria("Gallo", 55, 52.01));
        lista.add(new Categoria("Ligero", 60, 55.01));
        lista.add(new Categoria("Mediano", 72, 60.01));
        lista.add(new Categoria("Pesado", 100, 72.01));
        lista.add(new Categoria("Superpesado", Double.MAX_VALUE, 100.01));
        return lista;
    }
    //Metodo iniciar vista consola para la carga manual de un boxeador y asignacion de los valores ingresados a su respectivo lugar
    public void iniciarDesdeConsola() {
        vista.mostrarMensaje("Ingrese boxeadores.");
        ArrayList<Boxeador> boxeadores = cargarBoxeadoresDesdeArchivo(RUTA_ARCHIVO);

        boolean continuar = true;
        while (continuar) {
            String nombre = vista.pedirString("Nombre:");
            String apellido = vista.pedirString("Apellido:");
            double peso = vista.pedirDouble("Peso:");
            String genStr = vista.pedirString("Género (MASCULINO/FEMENINO):");
            Genero genero;
            try {
                genero = Genero.valueOf(genStr.trim().toUpperCase());
            } catch (Exception e) {
                vista.mostrarMensaje("Género inválido, intente de nuevo.");
                continue;
            }
            Boxeador b = new Boxeador(nombre, apellido, peso);
            b.setGenero(genero);
            asignarCategoria(b);
            boxeadoresTotales.add(b);

            if (b.getCategoria() == null) {
                vista.mostrarMensaje("Peso fuera de rango de categorías definidas.");
            } else {
                boxeadores.add(b);
                vista.mostrarMensaje("Boxeador agregado: " + nombre + " " + apellido +
                        " Categoría: " + b.getCategoria().getNombre() + " Género: " + genero);
                guardarBoxeadorEnArchivo(b, RUTA_ARCHIVO);
            }

            String seguir = vista.pedirString("¿Desea agregar otro boxeador? (S/N):");
            if (!seguir.trim().equalsIgnoreCase("S")) {
                continuar = false;
            }
        }
    }
    //Metodo guardar boxeador en el .txt
    private void guardarBoxeadorEnArchivo(Boxeador b, String ruta) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta, true))) {
            String linea = b.getNombre() + "," + b.getApellido() + "," + b.getPeso() + "," +
                    b.getCategoria().getNombre() + "," + b.getGenero().toString();
            bw.write(linea);
            bw.newLine();
        } catch (IOException e) {
            vista.mostrarMensaje("Error guardando boxeador en archivo: " + e.getMessage());
        }
    }
    //Metodo cargar boxeadores del .txt
    public ArrayList<Boxeador> cargarBoxeadoresDesdeArchivo(String nombreArchivo) {
        ArrayList<Boxeador> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length != 5) continue;

                String nombre = partes[0].trim();
                String apellido = partes[1].trim();
                double peso = Double.parseDouble(partes[2].trim());
                String genStr = partes[4].trim().toUpperCase();

                Genero genero;
                try {
                    genero = Genero.valueOf(genStr);
                } catch (Exception e) {
                    continue;
                }

                Boxeador b = new Boxeador(nombre, apellido, peso);
                b.setGenero(genero);
                asignarCategoria(b);
                lista.add(b);
                boxeadoresTotales.add(b);
            }
        } catch (Exception e) {
            vista.mostrarMensaje("Error leyendo archivo: " + e.getMessage());
        }
        return lista;
    }
    //asigna categoria (comprobando peso maximo y minimo) al boxeador ingresado segun peso ingresado
    private void asignarCategoria(Boxeador b) {
        for (Categoria c : categorias) {
            if (c.estaDentroDelRango(b.getPeso())) {
                b.setCategoria(c);
                return;
            }
        }
        b.setCategoria(null);
    }

    //Metodo mostrar todos los boxeadores cargados con su respectiva categoria y genero
    public void mostrarBoxeadoresPorCategoriaYGenero() {
        Map<String, List<Boxeador>> agrupados = boxeadoresTotales.stream()
                .filter(b -> b.getCategoria() != null && b.getGenero() != null)
                .collect(Collectors.groupingBy(b -> b.getCategoria().getNombre() + " - " + b.getGenero()));

        for (String key : agrupados.keySet()) {
            vista.mostrarMensaje("\n" + key + ":");
            for (Boxeador b : agrupados.get(key)) {
                vista.mostrarMensaje("-" + b.getNombre() + " " + b.getApellido() + " (" + b.getPeso() + "kg)");
            }
        }
    }
    //Metodo mostrar todos los boxeadores

    public List<Boxeador> getBoxeadoresTotales() {
        return boxeadoresTotales;
    }

    public Categoria buscarCategoriaPorNombre(String nombre) {
        for (Categoria c : categorias) {
            if (c.getNombre().equalsIgnoreCase(nombre)) return c;
        }
        return null;
    }

}
