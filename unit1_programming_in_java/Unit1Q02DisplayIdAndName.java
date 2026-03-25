import java.util.Scanner;

public class Unit1Q02DisplayIdAndName {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter id: ");
        String id = scanner.nextLine().trim();

        System.out.print("Enter name: ");
        String name = scanner.nextLine().trim();

        System.out.println("Id: " + id);
        System.out.println("Name: " + name);
        System.out.println("done by Krish Devkota");
    }
}
