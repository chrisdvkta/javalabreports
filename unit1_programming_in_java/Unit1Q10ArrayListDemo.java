import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Unit1Q10ArrayListDemo {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("Krish");
        names.add("Asha");
        names.add("Bimal");

        System.out.println("Original: " + names);

        names.remove("Asha");
        names.add("Sita");
        Collections.sort(names);

        System.out.println("Updated & sorted: " + names);
        System.out.println("Contains 'Krish'? " + names.contains("Krish"));
        System.out.println("done by Krish Devkota");
    }
}

