import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        System.out.println("Hello world!");

        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int input;

        try (Scanner scanner = new Scanner(System.in)) {

            do {
                System.out.println("\n" +
                        "if you want to know the minimum value - enter 1:\n" +
                        "if you want to know the maximum value - enter 2:\n" +
                        "if you want to finish - enter 0: ");

                input = scanner.nextInt();
            }

            while (input != 0 && input != 1 && input != 2);

            if (input == 1) {
                System.out.println("Min value: " + findMinNumber(numbers));
            } else if (input == 2) {
                System.out.println("Max value: " + findMaxNumber(numbers));
            } else {
                System.out.println("Thank you!");
            }
        }
    }

        public static int findMinNumber(int[] numbers) {

        int min = numbers[0];

        for(int i = 1; i < numbers.length; i++) {

            if(numbers[i] < min) {
                min = numbers[i];
            }
        }
        return min;

        }

        public static int findMaxNumber(int[] numbers) {

            int max = numbers[0];

            for(int i = 1; i < numbers.length; i++) {

                if(numbers[i] > max) {
                    max = numbers[i];
                }
            }
            return max;
        }
    }