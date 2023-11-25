public class GaussJordanElimination {
    
    
    /*
    1. Explain why the (repared)ForwardElimination algorithm on page 210 of Levitin fails to provide a solution for:
    x1 + x2 + x3 = 6
    x1 + x2 + 2x3 = 9
    x1 + 2x2 + 3x3 = 14

    * This algorithm fails for this system of equations because after the first iteration, A[i,i] = 0, which
    * causes a division by zero error.


    1b. How does the betterForwardElimination algorithm on page 211 fix this?
    
    * This algorithm fixes the problem by swapping the rows of the matrix so that the pivot element is not zero,
    * therefore avoiding the division by zero error. This assumes that the system has a unique solution, since such 
    * a row must exist if this is the case.


    2. Explain why the betterForwardElimination algorithm on page 211 of Levitin fails tp provide a solution for:
    x1 + x2 + x3 = 6
    x1 + x2 + 2x3 = 9
    2x1 + 2x2 + 3x3 = 15
    
    * betterForwardElimination fails on this system because the algorithm assumes that there is
    * a single unique solution to the system. However, this in not the case for this system since
    * the third equation is a linear combination of the first two equations. There are an infinite 
    * number of solutions to this system, so the algorithm will not necessarily be able to find a row
    * to swap with the current row that will not result in a division by zero error. 
    */


     /**
     * Prints a matrix to the console
     * @param matrix the matrix to be printed
     */
    static void printMatrix(double[][] matrix) {
        for (double[] row : matrix) {
            for (double element : row) {
                System.out.print(element + "\t ");
            }
            System.out.println();
        }
    }

    /**
     * Performs Gauss-Jordan elimination on a system of linear equations
     * @param matrix the matrix of coefficients
     */
    static void gaussJordanElimination(double[][] matrix) {
        int n = matrix.length;

        for (int i = 0; i < n; i++) {

            // if the pivot element is zero, swap rows
            if (matrix[i][i] == 0) {
                int pivotRow = 1;

                // find a row to swap with 
                // (such a row must exist if the system has a unique solution, which is assumed)
                while ((i + pivotRow) < n && matrix[i + pivotRow][i] == 0) {
                    pivotRow++;
                }

                // swap rows
                for (int k = 0; k <= n; k++) {
                    double temp = matrix[i][k];
                    matrix[i][k] = matrix[i + pivotRow][k];
                    matrix[i + pivotRow][k] = temp;
                }
            }

            // j is the column index, so this zeroing out process is done for each column
            // looping over every i, this converts the entire matrix to reduced row echelon form
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    double temp = matrix[j][i] / matrix[i][i];
                    for (int k = 0; k <= n; k++) {
                        matrix[j][k] = matrix[j][k] - (matrix[i][k]) * temp;
                    }
                }
            }

        }

    }


    public static void main(String[] args) {

        double[][] matrix = {
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 122},
                {1, 1, 1, 1, 1, -1, -1, -1, -1, -88},
                {1, -1, 1, -1, 1, -1, 1, -1, 1, 32},
                {1, 1, 0, 0, 0, 0, 0, 0, 0, 3},
                {0, 0, 1, 1, 0, 0, 0, 0, 0, 7},
                {0, 0, 0, 0, 1, 1, 0, 0, 0, 18},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 76},
                {9, -8, 7, -6, 5, -4, 3, -2, 1, 41},
                {1, 1, -1, 1, 1, -1, 1, 1, -1, 0}
        };

        gaussJordanElimination(matrix);

        System.out.println("Final augmented matrix is: ");
        printMatrix(matrix);

        System.out.println("Solutions are: ");
        for (int i = 0; i < matrix.length; i++) {
            System.out.println("x" + (i+1) + " = " + matrix[i][matrix.length] / matrix[i][i]);
        }
    }

}


