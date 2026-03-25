import javax.swing.*;
import java.awt.*;

public class Lab2Q06LayoutManagersDemo {
    public static void main(String[] args) {
        System.out.println("done by Krish Devkota");
        SwingUtilities.invokeLater(Lab2Q06LayoutManagersDemo::createAndShowUi);
    }

    private static void createAndShowUi() {
        JFrame frame = new JFrame("Layout Managers Demo");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("FlowLayout", flowLayoutPanel());
        tabs.addTab("BorderLayout", borderLayoutPanel());
        tabs.addTab("GridLayout", gridLayoutPanel());
        tabs.addTab("BoxLayout", boxLayoutPanel());

        frame.setContentPane(tabs);
        frame.setSize(600, 360);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static JPanel flowLayoutPanel() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        for (int i = 1; i <= 8; i++) p.add(new JButton("Button " + i));
        return p;
    }

    private static JPanel borderLayoutPanel() {
        JPanel p = new JPanel(new BorderLayout(10, 10));
        p.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        p.add(new JButton("North"), BorderLayout.NORTH);
        p.add(new JButton("South"), BorderLayout.SOUTH);
        p.add(new JButton("West"), BorderLayout.WEST);
        p.add(new JButton("East"), BorderLayout.EAST);
        p.add(new JLabel("Center", SwingConstants.CENTER), BorderLayout.CENTER);
        return p;
    }

    private static JPanel gridLayoutPanel() {
        JPanel p = new JPanel(new GridLayout(3, 3, 10, 10));
        p.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        for (int i = 1; i <= 9; i++) p.add(new JButton("B" + i));
        return p;
    }

    private static JPanel boxLayoutPanel() {
        JPanel p = new JPanel();
        p.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        for (int i = 1; i <= 5; i++) {
            JButton b = new JButton("Row " + i);
            b.setAlignmentX(Component.LEFT_ALIGNMENT);
            p.add(b);
            p.add(Box.createVerticalStrut(8));
        }
        return p;
    }
}

