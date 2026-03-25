import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Lab2Q08CrudOperationSwing {
    public static void main(String[] args) {
        System.out.println("done by Krish Devkota");
        SwingUtilities.invokeLater(Lab2Q08CrudOperationSwing::createAndShowUi);
    }

    private static void createAndShowUi() {
        JFrame frame = new JFrame("CRUD Operation (In-Memory)");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CrudPanel panel = new CrudPanel();
        frame.setContentPane(panel);
        frame.setSize(760, 420);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    static class CrudPanel extends JPanel {
        private final JTextField idField = new JTextField(8);
        private final JTextField nameField = new JTextField(16);
        private final JTextField ageField = new JTextField(8);
        private final JLabel status = new JLabel("Ready");

        private final DefaultTableModel model = new DefaultTableModel(new Object[]{"Id", "Name", "Age"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        private final JTable table = new JTable(model);
        private final List<Student> students = new ArrayList<>();

        CrudPanel() {
            setLayout(new BorderLayout(10, 10));
            setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            add(buildForm(), BorderLayout.NORTH);
            add(new JScrollPane(table), BorderLayout.CENTER);
            add(status, BorderLayout.SOUTH);

            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            table.getSelectionModel().addListSelectionListener(this::onSelectionChanged);

            seedData();
            refreshTable();
        }

        private JPanel buildForm() {
            JPanel form = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.anchor = GridBagConstraints.WEST;

            gbc.gridx = 0;
            gbc.gridy = 0;
            form.add(new JLabel("Id:"), gbc);
            gbc.gridx = 1;
            form.add(idField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 1;
            form.add(new JLabel("Name:"), gbc);
            gbc.gridx = 1;
            form.add(nameField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 2;
            form.add(new JLabel("Age:"), gbc);
            gbc.gridx = 1;
            form.add(ageField, gbc);

            JPanel buttons = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
            JButton addBtn = new JButton("Create");
            JButton updateBtn = new JButton("Update");
            JButton deleteBtn = new JButton("Delete");
            JButton clearBtn = new JButton("Clear");

            addBtn.addActionListener(e -> createStudent());
            updateBtn.addActionListener(e -> updateStudent());
            deleteBtn.addActionListener(e -> deleteStudent());
            clearBtn.addActionListener(e -> clearForm());

            buttons.add(addBtn);
            buttons.add(updateBtn);
            buttons.add(deleteBtn);
            buttons.add(clearBtn);

            gbc.gridx = 2;
            gbc.gridy = 0;
            gbc.gridheight = 3;
            gbc.anchor = GridBagConstraints.NORTHWEST;
            form.add(buttons, gbc);

            return form;
        }

        private void seedData() {
            students.add(new Student(1, "Krish", 21));
            students.add(new Student(2, "Sita", 20));
        }

        private void refreshTable() {
            model.setRowCount(0);
            for (Student s : students) {
                model.addRow(new Object[]{s.id, s.name, s.age});
            }
        }

        private void createStudent() {
            Student parsed = parseForm();
            if (parsed == null) return;
            if (findById(parsed.id) != null) {
                setStatus("Id already exists: " + parsed.id);
                return;
            }
            students.add(parsed);
            refreshTable();
            clearForm();
            setStatus("Created student with id " + parsed.id);
        }

        private void updateStudent() {
            Student parsed = parseForm();
            if (parsed == null) return;
            Student existing = findById(parsed.id);
            if (existing == null) {
                setStatus("No student found with id " + parsed.id);
                return;
            }
            existing.name = parsed.name;
            existing.age = parsed.age;
            refreshTable();
            setStatus("Updated student with id " + parsed.id);
        }

        private void deleteStudent() {
            Integer id = parseIdOnly();
            if (id == null) return;
            Student existing = findById(id);
            if (existing == null) {
                setStatus("No student found with id " + id);
                return;
            }
            students.remove(existing);
            refreshTable();
            clearForm();
            setStatus("Deleted student with id " + id);
        }

        private void clearForm() {
            idField.setText("");
            nameField.setText("");
            ageField.setText("");
            table.clearSelection();
            setStatus("Ready");
        }

        private void onSelectionChanged(ListSelectionEvent e) {
            if (e.getValueIsAdjusting()) return;
            int row = table.getSelectedRow();
            if (row < 0) return;
            idField.setText(String.valueOf(model.getValueAt(row, 0)));
            nameField.setText(String.valueOf(model.getValueAt(row, 1)));
            ageField.setText(String.valueOf(model.getValueAt(row, 2)));
            setStatus("Selected row " + (row + 1));
        }

        private Student parseForm() {
            Integer id = parseIdOnly();
            if (id == null) return null;
            String name = nameField.getText().trim();
            if (name.isEmpty()) {
                setStatus("Name is required");
                return null;
            }
            Integer age = parseAgeOnly();
            if (age == null) return null;
            return new Student(id, name, age);
        }

        private Integer parseIdOnly() {
            String idText = idField.getText().trim();
            try {
                return Integer.parseInt(idText);
            } catch (NumberFormatException ex) {
                setStatus("Invalid id");
                return null;
            }
        }

        private Integer parseAgeOnly() {
            String ageText = ageField.getText().trim();
            try {
                int age = Integer.parseInt(ageText);
                if (age < 0) {
                    setStatus("Age must be >= 0");
                    return null;
                }
                return age;
            } catch (NumberFormatException ex) {
                setStatus("Invalid age");
                return null;
            }
        }

        private Student findById(int id) {
            for (Student s : students) {
                if (s.id == id) return s;
            }
            return null;
        }

        private void setStatus(String message) {
            status.setText(message);
        }

        static class Student {
            final int id;
            String name;
            int age;

            Student(int id, String name, int age) {
                this.id = id;
                this.name = name;
                this.age = age;
            }
        }
    }
}

