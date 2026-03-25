import javax.swing.*;
import java.awt.*;

public class Lab2Q01SumTwoNumbersSwing {
    public static void main(String[] args) {
        System.out.println("done by Krish Devkota");
        SwingUtilities.invokeLater(Lab2Q01SumTwoNumbersSwing::createAndShowUi);
    }

    private static void createAndShowUi() {
        JFrame frame = new JFrame("Sum of Two Numbers");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JTextField aField = new JTextField(10);
        JTextField bField = new JTextField(10);
        JTextField sumField = new JTextField(10);
        sumField.setEditable(false);

        JButton addButton = new JButton("Add");
        JLabel status = new JLabel("Enter numbers and click Add");

        addButton.addActionListener(e -> {
            try {
                double a = Double.parseDouble(aField.getText().trim());
                double b = Double.parseDouble(bField.getText().trim());
                sumField.setText(String.valueOf(a + b));
                status.setText("Calculated successfully");
            } catch (NumberFormatException ex) {
                status.setText("Please enter valid numbers");
                sumField.setText("");
            }
        });

        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        form.add(new JLabel("Number 1:"), gbc);
        gbc.gridx = 1;
        form.add(aField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        form.add(new JLabel("Number 2:"), gbc);
        gbc.gridx = 1;
        form.add(bField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        form.add(new JLabel("Sum:"), gbc);
        gbc.gridx = 1;
        form.add(sumField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        form.add(addButton, gbc);

        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(form, BorderLayout.CENTER);
        frame.getContentPane().add(status, BorderLayout.SOUTH);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

