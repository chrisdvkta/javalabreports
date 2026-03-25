import javax.swing.*;
import java.awt.*;

public class Lab2Q02MenuDemoSwing {
    public static void main(String[] args) {
        System.out.println("done by Krish Devkota");
        SwingUtilities.invokeLater(Lab2Q02MenuDemoSwing::createAndShowUi);
    }

    private static void createAndShowUi() {
        JFrame frame = new JFrame("Menu Demo");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JLabel resultLabel = new JLabel("Choose a menu item", SwingConstants.CENTER);
        resultLabel.setFont(resultLabel.getFont().deriveFont(Font.PLAIN, 16f));

        JMenuBar menuBar = new JMenuBar();
        JMenu actions = new JMenu("Actions");

        JMenuItem hello = new JMenuItem("Say Hello");
        hello.addActionListener(e -> resultLabel.setText("Hello from Swing menu!"));

        JMenuItem clear = new JMenuItem("Clear");
        clear.addActionListener(e -> resultLabel.setText("Cleared"));

        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(e -> frame.dispose());

        actions.add(hello);
        actions.add(clear);
        actions.addSeparator();
        actions.add(exit);
        menuBar.add(actions);

        frame.setJMenuBar(menuBar);
        frame.getContentPane().add(resultLabel, BorderLayout.CENTER);

        frame.setSize(420, 220);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

