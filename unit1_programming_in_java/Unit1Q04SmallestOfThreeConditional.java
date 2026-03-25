import java.util.Scanner;

public class Unit1Q04SmallestOfThreeConditional {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter first number: ");
        double a = scanner.nextDouble();
        System.out.print("Enter second number: ");
        double b = scanner.nextDouble();
        System.out.print("Enter third number: ");
        double c = scanner.nextDouble();

        double smallest = (a < b) ? ((a < c) ? a : c) : ((b < c) ? b : c);

        System.out.println("Smallest: " + smallest);
        System.out.println("done by Krish Devkota");
    }
}

