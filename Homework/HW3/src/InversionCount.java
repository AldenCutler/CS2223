import java.util.Scanner;

public class InversionCount {

    /**
     * Returns the number of inversions in an array using a fast O(nlogn) algorithm.
     * @param arr the array to be checked
     * @return the number of inversions in the array
     */
    static int fastInversionCount(int[] arr) {
        int inversionCount;
        int[] temp = new int[arr.length];
        inversionCount = mergeSort(arr, temp, 0, arr.length - 1);
        return inversionCount;
    }

    /**
     * Sorts an array using merge sort and returns the number of inversions in the array.
     * @param arr the array to be sorted
     * @param temp a temporary array to store the sorted array
     * @param left the leftmost index of the array
     * @param right the rightmost index of the array
     * @return the number of inversions in the array
     */
    static int mergeSort(int[] arr, int[] temp, int left, int right) {
        int inversionCount = 0;
        if (left < right) {
            int mid = (left + right) / 2;
            inversionCount += mergeSort(arr, temp, left, mid);    // sort left half of array
            inversionCount += mergeSort(arr, temp, mid + 1, right);    // sort right half of array
            inversionCount += merge(arr, temp, left, mid + 1, right);    // merge the two halves
        }
        return inversionCount;
    }

    /**
     * Merges two sorted arrays and returns the number of inversions in the merged array.
     * @param arr the array to be sorted
     * @param temp a temporary array to store the sorted array
     * @param left the leftmost index of the array
     * @param mid the middle index of the array
     * @param right the rightmost index of the array
     * @return the number of inversions in the array
     */
    static int merge(int[] arr, int[] temp, int left, int mid, int right) {
        int inversionCount = 0;
        int i = left;       // index of left subarray
        int j = mid;        // index of right subarray
        int k = left;       // index of merged array

        // while there are elements in both subarrays
        while ((i <= mid - 1) && (j <= right)) {

            // if element in left subarray is less than or equal to element in right subarray, add element in left subarray to merged array
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            }

            // else, if element in right subarray is less than element in left subarray, add element in right subarray to merged array
            else {
                temp[k++] = arr[j++];
                // add number of elements remaining in left subarray to inversion count
                inversionCount += (mid - i);
            }
        }


        // The following code would normally be used to add the remaining elements of the subarray to the merged array,
        // but since we're only interested in the number of inversions, we don't need to spend time copying the elements
        // to the merged array to fully sort it.
        /*

        // while there are elements remaining in left subarray, add them to merged array
        while (i <= mid - 1) {
            temp[k++] = arr[i++];
        }

        // while there are elements remaining in right subarray, add them to merged array
        while (j <= right) {
            temp[k++] = arr[j++];
        }

        // copy merged array to original array
        for (i = left; i <= right; i++) {
            arr[i] = temp[i];
        }

        */

        return inversionCount;
    }

    /**
     * Returns the number of inversions in an array using a naive O(n^2) algorithm.
     * @param arr the array to be checked
     * @return the number of inversions in the array
     */
    static int easyInversionCount(int[] arr) {
        int inversionCount = 0;
        for (int i = 0; i < arr.length - 1; i++) {    // for each element in the array
            for (int j = i + 1; j < arr.length; j++) {    // compare it with every element after it
                if (arr[i] > arr[j]) {    // if the element is greater than the element(s) after it, it is an inversion
                    inversionCount++;
                }
            }
        }
        return inversionCount;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            int n = 8;    // default length of array

            System.out.println("Would you like to choose the length of the array (default length is 8)? (y/n)");
            String choice = sc.nextLine();

            if (choice.equals("y")) {
                System.out.println("Enter the length of the array:");
                n = sc.nextInt();    // get number of elements in array
            } else if (!choice.equals("n")) {
                System.out.println("Invalid input. Using default array of length 8.\n");
            }

            int[] arr = new int[n];    // initialize array
            System.out.println("Enter the elements of the array:");
            for (int i = 0; i < n; i++) {    // get elements of array
                arr[i] = sc.nextInt();
            }
            sc.close(); // close scanner

            System.out.println("The number of inversions in the array (using O(n^2) algorithm) is " + easyInversionCount(arr));
            System.out.println("The number of inversions in the array (using O(nlogn) algorithm) is " + fastInversionCount(arr));

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
