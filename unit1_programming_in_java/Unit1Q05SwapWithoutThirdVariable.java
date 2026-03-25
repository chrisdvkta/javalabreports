import java.util.Scanner;

public class Unit1Q05SwapWithoutThirdVariable {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter first integer: ");
        int a = scanner.nextInt();
        System.out.print("Enter second integer: ");
        int b = scanner.nextInt();

        System.out.println("Before swap: a=" + a + ", b=" + b);

        a = a ^ b;
        b = a ^ b;
        a = a ^ b;

        System.out.println("After swap:  a=" + a + ", b=" + b);
        System.out.println("done by Krish Devkota");
    }
}

