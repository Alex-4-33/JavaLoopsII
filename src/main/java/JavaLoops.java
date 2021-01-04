import java.util.Scanner;

// Solution for the problem https://www.hackerrank.com/challenges/java-loops/problem

public class JavaLoops {

    public static void printSeq(int a, int b, int n) {
        int currentValue; //Текущий член последовательности для вывода
        int currentPowerOf2; //Текущая степень двойки для вычисления след. члена

        currentValue = a;
        currentPowerOf2 = 1;
        for (int i = 0; i < n; i++) {
            //Вычисляем след. член
            //currentValue = currentValue + currentPowerOf2 * b;
            //currentPowerOf2 = currentPowerOf2 * 2;
            currentValue += currentPowerOf2 * b;
            currentPowerOf2 *= 2;

            System.out.print(currentValue + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        System.out.println("Enter q:");
        int q = scan.nextInt();

        for (int j = 0; j < q; j++) {
            System.out.println("Enter a:");
            int a = scan.nextInt();
            System.out.println("Enter b:");
            int b = scan.nextInt();
            System.out.println("Enter n:");
            int n = scan.nextInt();
            System.out.println("Series for a = " + a + ", b = " + b + ", n = " + n + ":");
            printSeq(a, b, n);
        }

    }
}
