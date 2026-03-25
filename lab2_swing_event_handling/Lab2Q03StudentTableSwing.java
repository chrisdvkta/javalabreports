import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Lab2Q03StudentTableSwing {
    public static void main(String[] args) {
        System.out.println("done by Krish Devkota");
        SwingUtilities.invokeLater(Lab2Q03StudentTableSwing::createAndShowUi);
    }

    private static void createAndShowUi() {
        JFrame frame = new JFrame("Student Table (JTable)");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        DefaultTableModel model = new DefaultTableModel(new Object[]{"Id", "Name", "Age"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        model.addRow(new Object[]{1, "Krish", 21});
        model.addRow(new Object[]{2, "Sita", 20});
        model.addRow(new Object[]{3, "Ram", 22});

        JTable table = new JTable(model);
        table.setFillsViewportHeight(true);

        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);

        frame.setSize(520, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

