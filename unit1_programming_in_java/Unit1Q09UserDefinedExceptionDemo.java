import java.util.Scanner;

public class Unit1Q09UserDefinedExceptionDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a positive integer: ");
        int n = scanner.nextInt();

        try {
            ensurePositive(n);
            System.out.println("You entered: " + n);
        } catch (NegativeNumberException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("done by Krish Devkota");
    }

    static void ensurePositive(int n) throws NegativeNumberException {
        if (n < 0) {
            throw new NegativeNumberException("Number must be positive, got " + n);
        }
    }

    static class NegativeNumberException extends Exception {
        NegativeNumberException(String message) {
            super(message);
        }
    }
}

