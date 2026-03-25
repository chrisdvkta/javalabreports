import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Lab2Q07AdapterClassDemo {
    public static void main(String[] args) {
        System.out.println("done by Krish Devkota");
        SwingUtilities.invokeLater(Lab2Q07AdapterClassDemo::createAndShowUi);
    }

    private static void createAndShowUi() {
        JFrame frame = new JFrame("Adapter Class Demo (MouseAdapter)");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Click anywhere in the panel", SwingConstants.CENTER);
        label.setFont(label.getFont().deriveFont(Font.PLAIN, 16f));

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(label, BorderLayout.CENTER);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                label.setText("Mouse pressed at (" + e.getX() + ", " + e.getY() + ")");
            }
        });

        frame.setContentPane(panel);
        frame.setSize(520, 240);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

