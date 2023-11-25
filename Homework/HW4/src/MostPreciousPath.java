public class MostPreciousPath {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String GREEN_BOLD = "\033[1;32m";


    /**
     * Given an n x n grid of squares, each containing a number of gems,
     * find the most precious path from any square in the bottom row
     * to the top row.
     * @param grid a 2D array of integers representing the number of gems in each square
     * @return an array of integers representing the path of the most precious path
     */
    static int[][] mostPreciousPath(int[][] grid) {

        int[][] path = new int[grid.length][2]; // to store the i, j coordinates of the path
        int n = grid.length;

        // base case, the most precious path in the bottom row is just the square with the most gems
        int max = 0;
        int maxIndex = 0;
        for (int i = 0; i < n; i++) {
            if (grid[n-1][i] > max) {
                max = grid[n-1][i];
                maxIndex = i;
            }
        }
        path[0][0] = n-1;
        path[0][1] = maxIndex;

        // find most precious square in the row above the previous square
        for (int i = 1; i < n; i++) {
            int prevRow = path[i-1][0];
            int prevCol = path[i-1][1];

            // if our previous square was in the leftmost column, we can only move up-right or up
            // so we need to check which of those two squares has the most gems
            if (prevCol == 0) {
                // up-right
                if (grid[prevRow - 1][prevCol] > grid[prevRow - 1][prevCol + 1]) {
                    path[i][0] = prevRow - 1;
                    path[i][1] = prevCol;
                }
                // up
                else {
                    path[i][0] = prevRow - 1;
                    path[i][1] = prevCol + 1;
                }
            }

            // if our previous square was in the rightmost column, we can only move up-left or up
            else if (prevCol == n-1) {
                // up-left
                if (grid[prevRow-1][prevCol] > grid[prevRow-1][prevCol-1]) {
                    path[i][0] = prevRow-1;
                    path[i][1] = prevCol;
                }
                // up
                else {
                    path[i][0] = prevRow-1;
                    path[i][1] = prevCol-1;
                }
            }

            // otherwise, we can move up-left, up-right, or up
            else {
                // up-left
                if (grid[prevRow-1][prevCol] > grid[prevRow-1][prevCol-1]
                        && grid[prevRow-1][prevCol] > grid[prevRow-1][prevCol+1]) {
                    path[i][0] = prevRow-1;
                    path[i][1] = prevCol;
                }
                // up-right
                else if (grid[prevRow-1][prevCol-1] > grid[prevRow-1][prevCol]
                        && grid[prevRow-1][prevCol-1] > grid[prevRow-1][prevCol+1]) {
                    path[i][0] = prevRow-1;
                    path[i][1] = prevCol-1;
                }
                // up
                else {
                    path[i][0] = prevRow-1;
                    path[i][1] = prevCol+1;
                }
            }
        }

        return path;
    }

    /**
     * Returns the number of gems collected from a path
     * @param grid the grid of gems
     * @param path the path to be evaluated
     * @return the number of gems collected from the path
     */
    static int getGemsFromPath(int[][] grid, int[][] path) {
        int gems = 0;
        for (int[] square : path) {
            gems += grid[square[0]][square[1]];
        }
        return gems;
    }

    /**
     * Prints the path to the console
     * @param path the path to be printed
     * @param grid the grid of gems
     */
    static void printPath(int[][] path, int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {

                boolean inPath = false;
                for (int[] square : path) {
                    if (square[0] == i && square[1] == j) {
                        inPath = true;
                        break;
                    }
                }

                if (inPath) {
                    System.out.print(GREEN_BOLD + grid[i][j] + ANSI_RESET + "\t");
                } else {
                    System.out.print(grid[i][j] + "\t");
                }
            }

            System.out.println();
        }
    }



    public static void main(String[] args) {

        int[][] grid = {
                {35, 89, 52, 66, 82, 20, 95, 21},
                {79, 5, 14, 23, 78, 37, 40, 74},
                {32, 59, 17, 25, 31, 4, 16, 63},
                {91, 11, 77, 48, 13, 71, 92, 15},
                {56, 70, 47, 64, 22, 88, 67, 12},
                {83, 97, 94, 27, 65, 51, 30, 7},
                {10, 41, 1, 86, 46, 24, 53, 93},
                {96, 33, 44, 98, 75, 68, 99, 84},
        };

        int[][] path = mostPreciousPath(grid);
        int gems = getGemsFromPath(grid, path);

        System.out.println("Bilbo's starting square is: " + "Row 1" + ", " + "Column " + (path[0][1]+1));

        System.out.println("Bilbo collected " + gems + " gems.");

        System.out.println("Bilbo's path is: ");
        printPath(path, grid);

        System.out.println("\nThe Arkenstone is in vault " + (path[path.length-1][1]+1) + ".");

    }
}
