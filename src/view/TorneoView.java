package view;

import models.Boxeador;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class TorneoView extends JFrame {
    private static final String IMAGE_PATH = "src/resources/Plantilla Torneo.jpg";

    public TorneoView(Map<String, List<Boxeador>> torneo, String categoria, String genero, String ganador) {
        setTitle("Torneo de Boxeo - " + categoria + " " + genero);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int width = getWidth();
                int height = getHeight();

                // Fondo
                ImageIcon icon = new ImageIcon(IMAGE_PATH);
                if (icon.getImageLoadStatus() == MediaTracker.COMPLETE) {
                    g.drawImage(icon.getImage(), 0, 0, width, height, this);
                } else {
                    g.setColor(Color.WHITE);
                    g.fillRect(0, 0, width, height);
                }

                // Configuración de texto
                g.setColor(Color.BLACK);
                Font fontNormal = new Font("Arial", Font.BOLD, 16);
                Font fontGanador = new Font("Arial", Font.BOLD, 24);
                g.setFont(fontNormal);

                // Cuartos (8 boxeadores)
                List<Boxeador> cuartos = torneo.get("Cuartos");
                if (cuartos != null) {
                    for (int i = 0; i < cuartos.size(); i++) {
                        g.drawString(cuartos.get(i).getNombre(), width/10, height/8 + i*(height/12));
                    }
                }

                // Semifinal (4 boxeadores)
                List<Boxeador> semifinal = torneo.get("Semifinal");
                if (semifinal != null) {
                    for (int i = 0; i < semifinal.size(); i++) {
                        g.drawString(semifinal.get(i).getNombre(), width/3, height/4 + i*(height/8));
                    }
                }

                // Final (2 boxeadores)
                List<Boxeador> finalistas = torneo.get("Final");
                if (finalistas != null && finalistas.size() >= 2) {
                    g.drawString(finalistas.get(0).getNombre(), width/2 + 100, height/3);
                    g.drawString(finalistas.get(1).getNombre(), width/2 + 100, height/2);
                }

                // Ganador
                g.setColor(Color.RED);
                g.setFont(fontGanador);
                g.drawString("GANADOR: " + ganador.toUpperCase(), width/2 - 150, height - 100);
            }
        };

        add(panel);
        setVisible(true);
    }
}