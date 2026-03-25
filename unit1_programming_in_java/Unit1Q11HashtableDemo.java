import java.util.Hashtable;
import java.util.Map;

public class Unit1Q11HashtableDemo {
    public static void main(String[] args) {
        Hashtable<Integer, String> students = new Hashtable<>();
        students.put(1, "Krish");
        students.put(2, "Sita");
        students.put(3, "Ram");

        for (Map.Entry<Integer, String> entry : students.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        System.out.println("done by Krish Devkota");
    }
}

