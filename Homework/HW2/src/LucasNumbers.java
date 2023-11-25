import java.util.Scanner;

public class LucasNumbers {

    static Scanner sc = new Scanner(System.in);

    /**
     * @param n the nth Alden number to be calculated
     * @return  the nth Alden number
     */
    static long alden(int n) {
        if (n == 0) return 9;
        if (n == 1) return 24;
        return (alden(n - 1) + alden(n - 2));
    }

    static double timeAlden(int n) {
        long startTime = System.nanoTime();
        alden(n);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        return (double) duration / 1000000;
    }

    static void printAldenNumbers() {
        int n;
        while (true) {
            System.out.println("How many Alden numbers do you want to generate?");
            n = sc.nextInt();
            if (n > 0) {
                break;
            } else {
                System.out.println("Please enter a positive integer.");
            }
        }

        for (int i = 0; i < n; i++) {
            long startTime = System.nanoTime();
            long num = alden(i);
            long endTime = System.nanoTime();
            long duration = (endTime - startTime);
            double durationInMs = (double) duration / 1000000;      // convert to milliseconds

            double ratio = (double) alden(i + 1) / num;
            double ratioTime = timeAlden(i + 1) / durationInMs;


            System.out.println("A(" + i + "): " + num + " (time: " + durationInMs + " ms, ratio: " + ratio + ", ratioTime: " + ratioTime + ")");
        }

        System.out.println("\n----------------------------------------");
        System.out.println("The order of growth of the algorithm is exponential, O(2^n), because the function calls itself twice.");
        System.out.println("The ratio of successive Alden numbers A(n+1)/A(n) is the golden ratio, phi: " + (1 + Math.sqrt(5)) / 2);
        System.out.println("The ratio of successive calculation times Time(A(n+1))/Time(A(n)) is the golden ratio, phi: " + (1 + Math.sqrt(5)) / 2);
        System.out.println("----------------------------------------\n");
    }



    /** Lucas numbers
     * @param n the nth Lucas number to be calculated
     * @return the nth Lucas number
     */
    static int lucas(int n) {
        if (n == 0) return 2;
        if (n == 1) return 1;
        return (lucas(n - 1) + lucas(n - 2));

        /*
        * Interesting note: Lucas is also known for the Tower of Hanoi puzzle (which we talked about in class),
        * sometimes called Lucas' Tower, which was invented by Édouard Lucas in 1883. The story of the Indian temple
        * was apparently created by Lucas himself and spread by one of Lucas's friends.
        */
    }

    /**
     * Calculates the time in milliseconds it takes to calculate the nth Lucas number
     * @param n the nth Lucas number to be calculated
     * @return the time in milliseconds it takes to calculate the nth Lucas number
     */
    // I would like to do this without calling lucas() twice, but I'm not sure how to do that right now.
    static double timeLucas(int n) {
        long startTime = System.nanoTime();
        lucas(n);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        return (double) duration / 1000000;
    }

    static void printLucasNumbers() {
        int n;
        while (true) {
            System.out.println("How many Lucas numbers do you want to generate?");
            n = sc.nextInt();
            if (n > 0) {
                break;
            } else {
                System.out.println("Please enter a positive integer.");
            }
        }

        for (int i = 0; i < n; i++) {
            long startTime = System.nanoTime();

            long num = lucas(i);

            long endTime = System.nanoTime();
            long duration = (endTime - startTime);
            double durationInMs = (double) duration / 1000000;      // convert to milliseconds

            double ratio = (double) lucas(i + 1) / lucas(i);
            double ratioTime = timeLucas(i + 1) / timeLucas(i);


            System.out.println("L(" + i + "): " + num + " (time: " + durationInMs + " ms, ratio: " + ratio + ", ratioTime: " + ratioTime + ")");
        }

        System.out.println("\n----------------------------------------");
        System.out.println("The order of growth of the algorithm is exponential, O(2^n).");
        System.out.println("The ratio of successive calculations L(n+1)/L(n) is the golden ratio, phi: " + (1 + Math.sqrt(5)) / 2);
        System.out.println("The ratio of successive calculation times Time(L(n+1))/Time(L(n)) approaches the golden ratio, phi: " + (1 + Math.sqrt(5)) / 2);
        System.out.println("----------------------------------------\n");
    }


    public static void main(String[] args) {

        String choice;
        while (true) {
            System.out.println("Would you like to calculate Lucas numbers or Alden numbers? (l/a)");
            choice = sc.nextLine();

            if (choice.equalsIgnoreCase("l") || choice.equalsIgnoreCase("a")) {
                break;
            } else {
                System.out.println("Please enter l or a.");
            }
        }

        if (choice.equalsIgnoreCase("l")) {
            printLucasNumbers();
        } else {
            printAldenNumbers();
        }

        sc.close();
    }
}