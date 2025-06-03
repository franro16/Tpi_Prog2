package main;

import controller.BoxeadorController;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

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
}
