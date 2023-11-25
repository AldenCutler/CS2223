public class GaussJordanElimination {

    /*
    *
    * ALGORITHM: ForwardElimination(A[1..n, 1..n], b[1..n])
    * // Applies Gaussian elimination to matrix A of a system's coefficients,
    * // augmented with vector b of the system's right hand side values.
    * // Input: Matrix A[1..n, 1..n] and the column-vector b[1..n].
    * // Output: An equivalent upper-triangular matrix in place of A with the
    * // corresponding right hand side values in the (n+1)st column
    for i = 1 to n do A[i, n + 1] = b[i] // augments the matrix
    for i = 1 to n - 1 do
    for j = i + 1 to n do
    for k = i + 1 to n + 1 do
    A[j, k] = A[j, k] - A[i, k] * (A[j, i] / A[i, i])
    *
    * 1. Explain why the (repared)ForwardElimination algorithm on page 210 of Levitin fails to provide a solution for:
    * x1 + x2 + x3 = 6
    * x1 + x2 + 2x3 = 9
    * x1 + 2x2 + 3x3 = 14

    * --> This algorithm fails for this system of equations because after the first iteration, A[i,i] = 0, which
    * causes a division by zero error.


    * ALGORITHM: betterForwardElimination(A[1..n, 1..n], b[1..n])
    * // Implements Gaussian Elimination with partial pivoting.
    * // Input: Matrix A[1..n, 1..n] and the column-vector b[1..n].
    * // Output: An equivalent upper-triangular matrix in place of A with the
    * // corresponding right hand side values in the (n+1)st column
    for i = 1 to n do A[i, n + 1] = b[i] // augments the matrix
    for i = 1 to n - 1 do
        pivotrow = i
        for j = i + 1 to n do
            if |A[j, i]| > |A[pivotrow, i]| then pivotrow = j
        for k = i to n + 1 do
            swap A[i, k] and A[pivotrow, k]
        for j = i + 1 to n do
            temp = A[j, i] / A[i, i]
            for k = i to n + 1 do
                A[j, k] = A[j, k] - A[i, k] * temp

    * 1b. How does the betterForwardElimination algorithm on page 211 fix this?
    *
    * --> This algorithm fixes the problem by swapping the rows of the matrix so that the pivot element is not zero,
    * therefore avoiding the division by zero error. This assumes that the system has a unique solution, since such 
    * a row must exist if this is the case.
    * */


    /*
    * 2. Explain why the betterForwardElimination algorithm on page 211 of Levitin fails tp provide a solution for:
    * x1 + x2 + x3 = 6
    * x1 + x2 + 2x3 = 9
    * 2x1 + 2x2 + 3x3 = 15
    *
    * --> betterForwardElimination fails on this system because this algorithm assumes that there is
    * a single unique solution to the system. However, this in not the case for this system since
    * the third equation is a linear combination of the first two equations. There are an infinite 
    * number of solutions to this system, so the algorithm will not necessarily be able to find a row
    * to swap with the current row that will not result in a division by zero error.
    * */


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

                // find a row to swap with (such a row must exist if the system has a unique solution)
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

            // convert matrix to RREF
            for (int j = 0; j < n; j++) {
                // excluding all i == j, so don't zero-out the pivot element
                if (i != j) {
                    // zero-out the element at row j, column i
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


