import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Unit1Q16DeserializationDemo {
    private static final String FILE_NAME = "unit1_serialization_demo.ser";

    public static void main(String[] args) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            Object obj = in.readObject();
            System.out.println("Deserialized from: " + FILE_NAME);
            System.out.println("Data: " + obj);
        } catch (IOException e) {
            System.out.println("Deserialization failed: " + e.getMessage());
            System.out.println("Tip: Run Unit1Q15SerializationDemo first to create " + FILE_NAME);
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found: " + e.getMessage());
        }

        System.out.println("done by Krish Devkota");
    }
}

