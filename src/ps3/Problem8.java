package ps3;

import java.util.Arrays;

public class Problem8 {
    /*
    This method sorts two arrays using merge sort and subsequently finds the intersection between the two arrays.
    Finally the method puts the intersecting elements into a new array and returns the intersection array
     */
    public static int[] findIntersect(int[] arr1, int[] arr2) {
        int[] intersectionArray;
        if (arr1 == null || arr1.length == 0 || arr2 == null || arr2.length == 0){
            intersectionArray = new int[0];
        } else {
            int length = arr1.length < arr2.length ? arr1.length : arr2.length;
            intersectionArray = new int[length];
            Sort.mergeSort(arr1);
            Sort.mergeSort(arr2);

            int i = 0;
            int j = 0;
            int k = 0;
            int arr1End = arr1.length;
            int arr2End = arr2.length;

            // Searches for intersecting elements and places them into the intersection array
            while (i < arr1End && j < arr2End) {
                if (arr1[i] == arr2[j]) {
                    if (k == 0 || (k > 0 && intersectionArray[k - 1] != arr1[i])) {
                        intersectionArray[k] = arr1[i];
                        k++;
                    }
                    i++;
                    j++;
                } else if (arr1[i] < arr2[j]){
                    i++;
                } else if (arr1[i] > arr2[j]){
                    j++;
                }
            }
        }
        return intersectionArray;
    }

    public static void main(String[] args) {
        System.out.println("Test 1: ");
        int[] a1 = {2, 2, 2, 8};
        int[] a2 = {2, 2, 2, 2, 2, 2};
        System.out.println(Arrays.toString(findIntersect(a1, a2)));
        System.out.println();

        System.out.println("Test 2:");
        int[] a3 = {10, 3, 7, 8, 4};
        int[] a4 = {3, 4, 11, 5, 9};
        System.out.println(Arrays.toString(findIntersect(a3, a4)));
        System.out.println();

        System.out.println("Test 3: ");
        int[] a5 = {1, 2, 3, 4, 5};
        int[] a6 = {1, 2, 3, 4, 5};
        System.out.println(Arrays.toString(findIntersect(a5, a6)));
        System.out.println();

        System.out.println("Test 4: ");
        int[] a7 = {};
        int[] a8 = {};
        System.out.println(Arrays.toString(findIntersect(a7, a8)));
    }
}
