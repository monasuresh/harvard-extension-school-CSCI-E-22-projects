package ps3;

public class Problem7 {
    /*
    This method searches an array for pairs that sum to k. It has a time efficiency of O(n^2) because the outer for
    loop is dependent on the problem size n. On the first pass, the inner loop will execute n - 1 times. On the second
    pass, it will execute n - 2 times. On the (n - 1)st pass, it will execute 1 time. Statements
    in the inner loop will execute (n^2 / 2) - (n / 2) times. By removing the lower order terms and coefficients,
    the time efficiency is O(n^2).
     */

    public static void pairSums(int k, int[] arr) {
        if (arr.length == 1 || arr.length == 0) {
            throw new IllegalArgumentException();
        }

        /*
        Goes through the array and adds the element i to the element j. J begins from i + 1 in the inner loop. If the
        sum is equal to k, then the method prints the pair sum, otherwise, it increments j, adds the element it to
        element i again, and compares the result to the sum. This continues until j reaches arr.length. After the
        first pass of the inner loop ends, i is decremented and the addition and comparisons to k are done all over a
        gain until i reaches arr.length.
         */
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] + arr[j] == k) {
                    System.out.println(arr[i] + " + " + arr[j] + " = " + k);
                }
            }
        }
    }

    /*
    This method searches an array for pairs that sum to k. It has a time efficiency of O(nlogn) because it first sorts
    the array using merge sort. The sorting has a time efficiency of O(nlogn). There is one while loop and it is
    dependent on the problem size n - 1, therefore the statements in the loop will execute n - 1 times. By removing the
    lower order term the time complexity of the while loop is O(n). The overall time complexity is
    O(nlogn) because nlogn is the largest term.
     */

    public static void pairSumsImproved(int k, int[] arr) {
        if (arr.length == 1 || arr.length == 0) {
            throw new IllegalArgumentException();
        }

        Sort.mergeSort(arr);
        int i = 0;
        int j = arr.length - 1;

        /*
        The loop starts at i = 0 and j = arr.length - 1. If element i + element j is equal to the sum, then the method
        prints the pair sum and decrements j, otherwise the method checks if element i + element j is equal to the sum.
        If the result is greater, j is decremented. We know we can decrement j because the array is sorted and if the
        sum of element i and j is greater than k, we know that the element which when added to element i and sums to k
        will potentially come before the current index j. Similarly if element i + element j is less than the sum, we
        know that the element which when added to element j and sums to k will potentially come after the current index
        i.
         */

        while (i < j) {
            if (arr[i] + arr[j] == k) {
                System.out.println(arr[i] + " + " + arr[j] + " = " + k);
                j--;
            } else if (arr[i] + arr[j] > k) {
                j--;
            } else if (arr[i] + arr[j] < k) {
                i++;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("pairSums Test 1: ");
        int[] arr = {10, 4, 7, 7, 8, 5, 15};
        pairSums(12, arr);
        System.out.println();

        System.out.println("pairSums Test 2: ");
        int[] arr2 = {1, 2, 3, 4, 5, 6, 7};
        pairSums(5, arr2);
        System.out.println();

        System.out.println("pairSums Test 3: ");
        int[] arr3 = {2, 2};
        pairSums(4, arr3);
        System.out.println();

        System.out.println("pairSumsImproved Test 1: ");
        int[] arr5 = {7, 7, 3};
        pairSumsImproved(14, arr5);
        System.out.println();

        System.out.println("pairSumsImproved Test 2: ");
        int[] arr6 = {10, 20, 30, 40, 50, 60};
        pairSumsImproved(14, arr6);
        System.out.println();

        System.out.println("pairSumsImproved Test 3: ");
        int[] arr7 = {8, 7, 3, 20, 11, 2, 9};
        pairSumsImproved(11, arr7);
        System.out.println();

    }
}
