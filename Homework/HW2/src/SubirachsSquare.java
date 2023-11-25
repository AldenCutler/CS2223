import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class SubirachsSquare {

    static Integer[] magicSquare = {
            1, 14, 14,  4,
            11,  7,  6,  9,
            8, 10, 10,  5,
            13,  2,  3, 15
    };

    static final int NUM_CELLS = magicSquare.length;
    static final int ROW_SUM = 33;
    static final int MAX_SUM = ROW_SUM * 4;     //  = 132 = 1 + 14 + 14 + 4 + ... + 3 + 15
    static int totalCombos = 0;

    public static void main(String[] args) {
        getAllSums();   // counts the number of combinations that sum to each number from 0 to the sum of all cells in the square

        countAllCombos(ROW_SUM);    // counts the number of combinations that have the same sum as the rows/columns, etc.
        System.out.println("\nThere are " + countFourElementCombos() + " 4-element combinations that have the same sum as the rows/columns, etc.");
        System.out.println("There are " + totalCombos + " total combinations that have the same sum as the rows/columns, etc.");
    }

    /**
     * Counts the number of ways every possible sum can be formed.
     * Includes 0 as a possible sum; the largest sum will be created by summing every cell of the square.
     */
    static void getAllSums() {
        for (int i = 0; i <= MAX_SUM; i++) {
            countAllCombos(i);
            System.out.println("There are " + totalCombos + " combinations that sum to " + i + ".");
        }
    }


    /**
     * Counts the number of 4-element combinations that have the same sum as the rows/columns, etc.
     * @return the number of 4-element combinations that have the same sum as the rows/columns, etc.
     */
    static int countFourElementCombos() {

        // sort the array
        Arrays.sort(magicSquare);

        int count = 0;

        for (int i = 0; i < NUM_CELLS; i++) {
            // if number == magicSquare[i], then it's a duplicate so skip it
            if (i > 0 && Objects.equals(magicSquare[i], magicSquare[i - 1])) {
                continue;
            }

            for (int j = i + 1; j < NUM_CELLS; j++) {
                // if number == magicSquare[j], then it's a duplicate so skip it
                if (j > i + 1 && Objects.equals(magicSquare[j], magicSquare[j - 1])) {
                    continue;
                }

                for (int k = j + 1; k < NUM_CELLS; k++) {
                    // if number == magicSquare[k], then it's a duplicate so skip it
                    if (k > j + 1 && Objects.equals(magicSquare[k], magicSquare[k - 1])) {
                        continue;
                    }

                    for (int l = k + 1; l < NUM_CELLS; l++) {
                        // if number == magicSquare[l], then it's a duplicate so skip it
                        if (l > k + 1 && Objects.equals(magicSquare[l], magicSquare[l - 1])) {
                            continue;
                        }

                        if (magicSquare[i] + magicSquare[j] + magicSquare[k] + magicSquare[l] == ROW_SUM) {
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }


    /**
     * Counts the total number of combinations that have the same sum as the rows/columns, etc. (not necessarily only 4-element combinations)
     */
    static void countAllCombos(int n) {
        totalCombos = 0;    // reset totalCombos to 0
        countAllRowSumCombosRecursive(new ArrayList<>(Arrays.asList(magicSquare)), n, new ArrayList<>());
    }

    /**
     * Recursive helper method for countAllRowSumCombos()
     * @param numbers the numbers to be used in the combinations
     * @param target the target sum
     * @param partial the partial combination
     */
    static void countAllRowSumCombosRecursive(ArrayList<Integer> numbers, int target, ArrayList<Integer> partial) {
        int s = 0;
        for (int x: partial) s+= x;

        if (s == target) totalCombos++;     // if s == target, then we have a combination that has the same sum as the rows/columns, etc.
        if (s >= target) return;    // if s is greater than target, there's no point in continuing since the numbers are sorted in ascending order

        for (int i = 0; i < numbers.size(); i++) {
            ArrayList<Integer> remaining = new ArrayList<>();
            int n = numbers.get(i);

            for (int j = i + 1; j < numbers.size(); j++) remaining.add(numbers.get(j));

            ArrayList<Integer> partial_rec = new ArrayList<>(partial);
            partial_rec.add(n);

            countAllRowSumCombosRecursive(remaining, target, partial_rec);
        }
    }
}
