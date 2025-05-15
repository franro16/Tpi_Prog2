import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.HashMap;
import java.util.ArrayList;

public class Interfaz1 extends JFrame {

    // Si querés usar después, dejalo; si no, sacalo para no warning
    private final HashMap<String, String[]> boxeadores = new HashMap<>();

    private final FondoPanel fondoPanel;
    private final ArrayList<BotonInfo> botonesInfo = new ArrayList<>();

    private final int imgWidth = 800;
    private final int imgHeight = 600;

    public Interfaz1() {
        setTitle("Torneo de Boxeo");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(600, 450));
        setLayout(new BorderLayout());

        fondoPanel = new FondoPanel("/resources/Plantilla Torneo.png");
        fondoPanel.setLayout(null);

        agregarBoton("O1-B1", 20, 20);
        agregarBoton("O1-B2", 20, 50);
        agregarBoton("O2-B1", 20, 100);
        agregarBoton("O2-B2", 20, 130);
        agregarBoton("O3-B1", 20, 180);
        agregarBoton("O3-B2", 20, 210);
        agregarBoton("O4-B1", 20, 260);
        agregarBoton("O4-B2", 20, 290);

        agregarBoton("C1-B1", 180, 35);
        agregarBoton("C1-B2", 180, 115);
        agregarBoton("C2-B1", 180, 195);
        agregarBoton("C2-B2", 180, 275);

        agregarBoton("S1-B1", 340, 75);
        agregarBoton("S1-B2", 340, 235);

        agregarBoton("F-B1", 500, 155);
        agregarBoton("F-B2", 500, 185);

        add(fondoPanel, BorderLayout.CENTER);

        fondoPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent ignored) {
                actualizarPosicionesBotones();
            }
        });

        setVisible(true);
    }

    private void agregarBoton(String id, int xOrig, int yOrig) {
        JButton boton = new JButton("🖊");
        boton.setToolTipText(id);
        boton.addActionListener(e -> cargarBoxeador(id));
        fondoPanel.add(boton);
        botonesInfo.add(new BotonInfo(boton, xOrig, yOrig));
    }

    private void actualizarPosicionesBotones() {
        double escalaX = (double) fondoPanel.getWidth() / imgWidth;
        double escalaY = (double) fondoPanel.getHeight() / imgHeight;

        double escala = Math.min(escalaX, escalaY);

        int imgAnchoEscalado = (int) (imgWidth * escala);
        int imgAltoEscalado = (int) (imgHeight * escala);

        int offsetX = (fondoPanel.getWidth() - imgAnchoEscalado) / 2;
        int offsetY = (fondoPanel.getHeight() - imgAltoEscalado) / 2;

        for (BotonInfo bi : botonesInfo) {
            int xEsc = offsetX + (int) (bi.xOrig * escala);
            int yEsc = offsetY + (int) (bi.yOrig * escala);
            bi.boton.setBounds(xEsc, yEsc, (int)(50 * escala), (int)(20 * escala));
            bi.boton.setFont(bi.boton.getFont().deriveFont((float)(12 * escala)));
        }
    }

    private void cargarBoxeador(String id) {
        int respuesta = JOptionPane.showConfirmDialog(
                this,
                "¿Querés ingresar datos para " + id + "?",
                "Editar",
                JOptionPane.YES_NO_OPTION
        );

        if (respuesta == JOptionPane.YES_OPTION) {
            String nombre = JOptionPane.showInputDialog(this, "Nombre del boxeador:");
            if (nombre == null || nombre.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nombre inválido.");
                return;
            }
            String pais = JOptionPane.showInputDialog(this, "País:");
            if (pais == null || pais.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "País inválido.");
                return;
            }

            boxeadores.put(id, new String[]{nombre.trim(), pais.trim()});
            System.out.println(id + ": " + nombre + " (" + pais + ")");
        }
    }

    private static class BotonInfo {
        JButton boton;
        int xOrig, yOrig;

        BotonInfo(JButton boton, int xOrig, int yOrig) {
            this.boton = boton;
            this.xOrig = xOrig;
            this.yOrig = yOrig;
        }
    }

    static class FondoPanel extends JPanel {
        private final Image imagen;

        public FondoPanel(String resourcePath) {
            URL url = getClass().getResource(resourcePath);
            if (url == null) {
                System.err.println("No se encontró la imagen en la ruta: " + resourcePath);
                imagen = null;
            } else {
                imagen = new ImageIcon(url).getImage();
            }
            setBackground(Color.LIGHT_GRAY);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (imagen == null) return;  // no dibuja si no hay imagen

            Graphics2D g2d = (Graphics2D) g.create();

            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
                    RenderingHints.VALUE_RENDER_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            int panelWidth = getWidth();
            int panelHeight = getHeight();

            int imgWidth = imagen.getWidth(this);
            int imgHeight = imagen.getHeight(this);

            double escalaX = (double) panelWidth / imgWidth;
            double escalaY = (double) panelHeight / imgHeight;
            double escala = Math.min(escalaX, escalaY);

            int anchoEscalado = (int) (imgWidth * escala);
            int altoEscalado = (int) (imgHeight * escala);

            int x = (panelWidth - anchoEscalado) / 2;
            int y = (panelHeight - altoEscalado) / 2;

            g2d.drawImage(imagen, x, y, anchoEscalado, altoEscalado, this);

            g2d.dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Interfaz1::new);
    }
}
