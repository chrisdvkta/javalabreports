import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;

public class Unit1Q15SerializationDemo {
    private static final String FILE_NAME = "unit1_serialization_demo.ser";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter student id: ");
        int id = readInt(scanner);
        System.out.print("Enter student name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter student age: ");
        int age = readInt(scanner);

        StudentRecord record = new StudentRecord(id, name.isEmpty() ? "Unknown" : name, age);

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(record);
            System.out.println("Serialized to: " + FILE_NAME);
            System.out.println("Data: " + record);
        } catch (IOException e) {
            System.out.println("Serialization failed: " + e.getMessage());
        }

        System.out.println("done by Krish Devkota");
    }

    private static int readInt(Scanner scanner) {
        while (true) {
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid integer: ");
            }
        }
    }

    static class StudentRecord implements Serializable {
        private static final long serialVersionUID = 1L;

        private final int id;
        private final String name;
        private final int age;

        StudentRecord(int id, String name, int age) {
            this.id = id;
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "StudentRecord{id=" + id + ", name='" + name + "', age=" + age + "}";
        }
    }
}

