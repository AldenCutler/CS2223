import java.util.ArrayList;

public class CoinRowProblem {

    public static void main(String[] args) {
        int[] coins = {7, 12, 8, 6, 10, 4, 3, 5, 2, 11, 9};

        int[] maxSum = new int[coins.length];
        ArrayList<Integer> usedCoins = new ArrayList<>();

        maxSum[0] = coins[0];
        maxSum[1] = coins[1];

        for (int i = 2; i < coins.length; i++) {
            maxSum[i] = Math.max(maxSum[i - 1], maxSum[i - 2] + coins[i]);
        }

        int i = coins.length - 1;
        while (i >= 0) {
            if (i == 0 || maxSum[i] != maxSum[i - 1]) {
                usedCoins.add(coins[i]);
                i -= 2;
            } else {
                i -= 1;
            }
        }

        System.out.println("Maximum sum of coins: " + maxSum[coins.length - 1]);
        System.out.print("Coins used in the solution: ");
        for (int j = usedCoins.size() - 1; j >= 0; j--) {
            System.out.print(usedCoins.get(j) + " ");
        }
    }
}