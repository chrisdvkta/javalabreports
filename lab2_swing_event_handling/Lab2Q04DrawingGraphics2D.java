import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class Lab2Q04DrawingGraphics2D {
    public static void main(String[] args) {
        System.out.println("done by Krish Devkota");
        SwingUtilities.invokeLater(Lab2Q04DrawingGraphics2D::createAndShowUi);
    }

    private static void createAndShowUi() {
        JFrame frame = new JFrame("Graphics2D Drawing Demo");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setContentPane(new DrawingPanel());
        frame.setSize(640, 420);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    static class DrawingPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setStroke(new BasicStroke(3f));
            g2.setPaint(new Color(0x1565C0));
            g2.draw(new Line2D.Double(40, 40, 280, 120));

            g2.setPaint(new Color(0x2E7D32));
            g2.draw(new Rectangle2D.Double(40, 160, 200, 120));

            g2.setPaint(new Color(0x6A1B9A));
            g2.draw(new Ellipse2D.Double(300, 60, 140, 140)); // circle

            g2.setPaint(new Color(0xEF6C00));
            g2.draw(new Ellipse2D.Double(300, 230, 220, 120)); // ellipse

            g2.dispose();
        }
    }
}

