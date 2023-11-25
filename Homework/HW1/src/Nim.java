import java.util.Scanner;
import java.util.Random;

public class Nim {

    /*
    * This assignment was meant to model the game of Nim. Nim is a solved game, meaning either player can force a win, depending on the specific rules,
    * as Nim has many variants, and who goes first. In this variation, there are 3 heaps with 3, 7, and 5 items. The player and computer take turns removing
    * items from the heaps. Each player can remove as many items as they would like, but only from one pile per turn. The player who removes the last item
    * wins. The computer plays optimally, and will always win if it goes first. The player can win if they go first and play optimally.
    * The name Nim was coined by Charles L. Bouton of Harvard University, and first appeared in Bouton's 1901 book Nim, a game with a complete mathematical theory.
    */

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String CYAN_BOLD = "\033[1;36m";
    public static final String GREEN_BOLD = "\033[1;32m";

    static int[] heaps = {3, 5, 7};

    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();


    static void playerMove() {

        int heap;
        while (true) {
            System.out.println("Which heap?");
            heap = scanner.nextInt() - 1;
            if (heap >= 0 && heap <= 2 && heaps[heap] != 0) {
                break;
            }
            System.out.println("Invalid heap");
        }


        int num;
        while (true) {
            System.out.println("How many?");
            num = scanner.nextInt();
            if (num <= heaps[heap]) {
                break;
            }
            System.out.println("Invalid number");
        }

        heaps[heap] -= num;
        System.out.println("You removed " + ANSI_BLUE + num + ANSI_RESET + " from heap " + ANSI_BLUE + (heap+1) + ANSI_RESET + "\n");
    }

    static void computerMove() {

        // losing position if human plays optimally
        if ((heaps[0] ^ heaps[1] ^ heaps[2]) == 0) {
            int heap;
            do {                                            // only pick a heap that is not empty
                heap = random.nextInt(3);
            } while (heaps[heap] == 0);                     // these do-while loops were originally while (true) loops like the playerMove method,
                                                            // but IntelliJ suggested this instead.
                                                            // I'm not sure if this is better or not, but it seems to work, so I left it.
            int num;
            do {                                            // only pick a number that is valid
                num = random.nextInt(heaps[heap]) + 1;
            } while (num > heaps[heap]);

            heaps[heap] -= num;
            System.out.println("I removed " + ANSI_RED + num + ANSI_RESET + " from heap " + ANSI_RED + (heap+1) + ANSI_RESET);

            return;
        }

        // otherwise, computer can make a winning position if it plays optimally by making nimsum 0
        // this is a brute force algorithm and can likely be improved upon.
        for (int i = 0; i < 3; i++) {
            if (heaps[i] == 0) continue;                    // skip empty heaps
            for (int j = 1; j <= heaps[i]; j++) {
                heaps[i] -= j;                              // make test move
                int nimsum = heaps[0] ^ heaps[1] ^ heaps[2];
                if (nimsum == 0) {                          // found a winning position
                    System.out.println("Computer removed " + ANSI_RED + j + ANSI_RESET + " from heap " + ANSI_RED + (i+1) + ANSI_RESET);
                    return;
                } else {
                    heaps[i] += j;                          // undo test move if it doesn't result in winning position
                }
            }
        }
    }


    public static void main(String[] args) {

//        URI uri = URI.create("https://aldencutler.github.io/nim");

        System.out.println(CYAN_BOLD + "Welcome to Nim!" + ANSI_RESET);
//        System.out.println("I have also created a visual (GUI) version of this game which you can find at "+ uri + ". (not fully functional yet)\n");
        System.out.println("There are 3 heaps with 3, 5, and 7 stones.");

        System.out.println("Would you like to go first? "+ GREEN_BOLD + "(y/n)" + ANSI_RESET);
        String answer = scanner.nextLine();

        if (!answer.equals("y") && !answer.equals("n")) {
            System.out.println("Invalid input\n\n\n");
            main(args);
        }

        boolean playerTurn = answer.equals("y");


        // game loop
        while ((heaps[0] + heaps[1] + heaps[2]) > 0) {
            System.out.println("Heaps: " + heaps[0] + ", " + heaps[1] + ", " + heaps[2]);
            if (playerTurn) {
                System.out.println("\nYour turn:");
                playerMove();
            } else {
                System.out.println("\nComputer's turn:");
                computerMove();
            }

            // switch turns
            playerTurn = !playerTurn;
        }

        // check who won
        if (playerTurn) {
            System.out.println(ANSI_RED + "\nComputer wins!\n" + ANSI_RESET);
        } else {
            System.out.println(GREEN_BOLD + "\nYou win!\n" + ANSI_RESET);
        }

        scanner.close();

    }
}