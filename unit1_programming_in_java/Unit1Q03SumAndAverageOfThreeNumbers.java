import java.util.Scanner;

public class Unit1Q03SumAndAverageOfThreeNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter first number: ");
        double a = scanner.nextDouble();
        System.out.print("Enter second number: ");
        double b = scanner.nextDouble();
        System.out.print("Enter third number: ");
        double c = scanner.nextDouble();

        double sum = a + b + c;
        double average = sum / 3.0;

        System.out.println("Sum: " + sum);
        System.out.println("Average: " + average);
        System.out.println("done by Krish Devkota");
    }
}

