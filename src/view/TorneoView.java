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

                ImageIcon icon = new ImageIcon(IMAGE_PATH);
                g.drawImage(icon.getImage(), 0, 0, width, height, this);

                g.setColor(Color.BLACK);
                Font fontNormal = new Font("Arial", Font.BOLD, 16);
                Font fontGanador = new Font("Arial", Font.BOLD, 24);
                g.setFont(fontNormal);

                double[] yCuartos = {
                        120.0 / 768.0,
                        200.0 / 768.0,
                        300.0 / 768.0,
                        375.0 / 768.0,
                        480.0 / 768.0,
                        555.0 / 768.0,
                        670.0 / 768.0,
                        725.0 / 768.0
                };

                double[] ySemis = {
                        160.0 / 768.0, 340.0 / 768.0, 520.0 / 768.0, 700.0 / 768.0
                };

                double[] yFinal = {
                        250.0 / 768.0, 610.0 / 768.0
                };

                double xCuartos = 85.0 / 1366.0;
                double xSemis = 450.0 / 1366.0;
                double xFinal = 800.0 / 1366.0;
                double xGanador = 1170.0 / 1366.0;
                double yGanador = 430.0 / 768.0;

                List<Boxeador> cuartos = torneo.get("Cuartos");
                if (cuartos != null) {
                    for (int i = 0; i < cuartos.size(); i++) {
                        int x = (int)(width * xCuartos);
                        int y = (int)(height * yCuartos[i]);
                        Boxeador b = cuartos.get(i);
                        g.drawString(b.getNombre() + " " + b.getApellido(), x, y);
                    }
                }

                List<Boxeador> semifinal = torneo.get("Semifinal");
                if (semifinal != null) {
                    for (int i = 0; i < semifinal.size(); i++) {
                        int x = (int)(width * xSemis);
                        int y = (int)(height * ySemis[i]);
                        Boxeador b = semifinal.get(i);
                        g.drawString(b.getNombre() + " " + b.getApellido(), x, y);
                    }
                }

                List<Boxeador> finalistas = torneo.get("Final");
                if (finalistas != null && finalistas.size() >= 2) {
                    int x = (int)(width * xFinal);
                    int y1 = (int)(height * yFinal[0]);
                    int y2 = (int)(height * yFinal[1]);
                    Boxeador b1 = finalistas.get(0);
                    Boxeador b2 = finalistas.get(1);
                    g.drawString(b1.getNombre() + " " + b1.getApellido(), x, y1);
                    g.drawString(b2.getNombre() + " " + b2.getApellido(), x, y2);
                }

                g.setColor(Color.RED);
                g.setFont(fontGanador);
                g.drawString(ganador.toUpperCase(), (int)(width * xGanador), (int)(height * yGanador));
            }
        };

        add(panel);
        setVisible(true);
    }
}
