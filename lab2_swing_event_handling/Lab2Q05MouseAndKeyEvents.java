import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Lab2Q05MouseAndKeyEvents {
    public static void main(String[] args) {
        System.out.println("done by Krish Devkota");
        SwingUtilities.invokeLater(Lab2Q05MouseAndKeyEvents::createAndShowUi);
    }

    private static void createAndShowUi() {
        JFrame frame = new JFrame("Mouse & Key Events");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JLabel info = new JLabel("Click inside the panel and press keys", SwingConstants.CENTER);
        info.setFont(info.getFont().deriveFont(Font.PLAIN, 15f));

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.add(info, BorderLayout.CENTER);
        panel.setFocusable(true);

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                info.setText("Mouse clicked at (" + e.getX() + ", " + e.getY() + ")");
                panel.requestFocusInWindow();
            }
        });

        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                info.setText("Key typed: '" + e.getKeyChar() + "'");
            }
        });

        frame.setContentPane(panel);
        frame.setSize(520, 260);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        SwingUtilities.invokeLater(panel::requestFocusInWindow);
    }
}

