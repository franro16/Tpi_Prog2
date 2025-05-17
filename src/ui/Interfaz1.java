package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class Interfaz1 extends JFrame {

    private final HashMap<String, String[]> boxeadores = new HashMap<>();
    private final ArrayList<JButton> botones = new ArrayList<>();
    private final ArrayList<Point> posiciones = new ArrayList<>();
    private FondoPanel fondoPanel;

    public Interfaz1() {
        setTitle("Torneo de Boxeo");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(600, 450));
        setLayout(new BorderLayout());

        fondoPanel = new FondoPanel("/resources/Plantilla Torneo.png");
        fondoPanel.setLayout(null);

        agregarBotones();

        add(fondoPanel, BorderLayout.CENTER);

        fondoPanel.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                actualizarPosiciones();
            }
        });

        setVisible(true);
    }
    //Botones para agregar
    private void agregarBotones() {
        agregar("4tos - 1er combate, Boxeador 1", 70, 70);
        agregar("4tos - 2do combate, Boxeador 2", 70, 135);
        agregar("4tos - 3er combate, Boxeador 1", 70, 213);
        agregar("4tos - 4to combate, Boxeador 2", 70, 275);
        agregar("4tos - 5to combate, Boxeador 1", 70, 355);
        agregar("4tos - 6to combate, Boxeador 2", 70, 418);
        agregar("4tos - 7to combate, Boxeador 1", 70, 500);
        agregar("4tos - 8vo combate, Boxeador 2", 70, 560);

        agregar("Semis - 1er Combate, Boxeador 1", 270, 100);
        agregar("Semis - 2do Combate, Boxeador 2", 270, 245);
        agregar("Semis - 3er Combate, Boxeador 1", 270, 390);
        agregar("Semis - 4to Combate, Boxeador 2", 270, 530);

        agregar("Final - 1er Combate, Boxeador 1", 475, 173);
        agregar("Final - 2do Combate, Boxeador 2", 475, 460);

        agregar("Ganador del Campeonato", 680, 316);
    }

    private void agregar(String texto, int x, int y) {
        JButton boton = new JButton("+");
        boton.setToolTipText(texto);
        boton.addActionListener(e -> cargarDatos(texto));
        fondoPanel.add(boton);
        botones.add(boton);
        posiciones.add(new Point(x, y));
    }
    //Posiciones de los Botones
    private void actualizarPosiciones() {
        int panelAncho = fondoPanel.getWidth();
        int panelAlto = fondoPanel.getHeight();

        int baseAncho = 800;
        int baseAlto = 600;

        double escalaX = (double) panelAncho / baseAncho;
        double escalaY = (double) panelAlto / baseAlto;
        double escala = Math.min(escalaX, escalaY);

        int offsetX = (int) ((panelAncho - baseAncho * escala) / 2);
        int offsetY = (int) ((panelAlto - baseAlto * escala) / 2);

        for (int i = 0; i < botones.size(); i++) {
            Point p = posiciones.get(i);
            int x = offsetX + (int) (p.x * escala);
            int y = offsetY + (int) (p.y * escala);

            JButton boton = botones.get(i);
            boton.setBounds(x, y, 50, 30);
            boton.setFont(boton.getFont().deriveFont((float) (12 * escala)));
        }
    }
    //Metodo para cargar los datos al apretar los boton
    private void cargarDatos(String id) {
        //El cuadrito de diálogo con las opciones si o no y el nombre del boxeador y su comprobación de entrada correcta
        int opc = JOptionPane.showConfirmDialog(this, "¿Querés ingresar " + id + "?", "Editar", JOptionPane.YES_NO_OPTION);
        if (opc == JOptionPane.YES_OPTION) {
            String nombre = JOptionPane.showInputDialog(this, "Nombre del boxeador:");
            if (nombre == null || nombre.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nombre inválido.");
                return;
            }
            //La parte del pais y comprobación
            String pais = JOptionPane.showInputDialog(this, "País:");
            if (pais == null || pais.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "País inválido.");
                return;
            }

            boxeadores.put(id, new String[]{nombre.trim(), pais.trim()});
            System.out.println(id + ": " + nombre + " (" + pais + ")");
        }
    }

    // Interfaz con la imagen de fondo
    static class FondoPanel extends JPanel {
        private final Image imagen;
        //Ruteo de la imagen de fondo
        public FondoPanel(String ruta) {
            URL url = getClass().getResource(ruta);
            imagen = (url != null) ? new ImageIcon(url).getImage() : null;
            if (imagen == null) System.err.println("Imagen no encontrada: " + ruta);
        }

        // Metodo para centrar la imagen y que no se corte o bugee cuando se mueva la pestaña
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (imagen == null) return;

            Graphics2D g2d = (Graphics2D) g.create();

            int anchoPanel = getWidth();
            int altoPanel = getHeight();
            int anchoImg = imagen.getWidth(this);
            int altoImg = imagen.getHeight(this);

            double escala = Math.min((double) anchoPanel / anchoImg, (double) altoPanel / altoImg);
            int anchoFinal = (int) (anchoImg * escala);
            int altoFinal = (int) (altoImg * escala);

            int x = (anchoPanel - anchoFinal) / 2;
            int y = (altoPanel - altoFinal) / 2;

            g2d.drawImage(imagen, x, y, anchoFinal, altoFinal, this);
            g2d.dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Interfaz1::new);
    }
}
