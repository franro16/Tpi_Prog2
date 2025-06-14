//package models;
//import models.*;
//import java.util.ArrayList;
//
//public class Historial {
//    private Boxeador boxeador;
//    private ArrayList<Combate> combates;
//
//    public Historial(Boxeador boxeador) {
//        this.boxeador = boxeador;
//        this.combates = new ArrayList<>();
//    }
//
//    public Boxeador getBoxeador() {
//        return boxeador;
//    }
//
//    public ArrayList<Combate> getCombates() {
//        return combates;
//    }
//
//    public void agregarCombate(Combate combate) {
//        combates.add(combate);
//    }
//
//    // Métodos para actualizar estadísticas según resultado
//    public void registrarVictoria(boolean porKO) {
//        boxeador.incrementarVictorias();
//        if (porKO) {
//            boxeador.incrementarVictoriasKO();
//        }
//    }
//
//    public void registrarDerrota() {
//        boxeador.incrementarDerrotas();
//    }
//
//    public void registrarEmpate() {
//        boxeador.incrementarEmpates();
//    }
//}
//
