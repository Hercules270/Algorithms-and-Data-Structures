package Week1;

import java.util.Scanner;
import Week3.QuickSort;

class MergeSort {

    private void sort(int[] arr) {
        if (arr.length <= 1) {
            return;
        }

        int half = arr.length / 2;
        int[] first = new int[half];
        int[] second = new int[arr.length - half];

        for (int i = 0; i < half; i++) {
            first[i] = arr[i];

        }
        int j = 0;
        for (int i = half; i < arr.length; i++) {
            second[j] = arr[i];
            j++;
        }

        sort(first);
        sort(second);

        merge(first, second, arr);

    }

    private void merge(int[] first, int[] second, int[] arr) {
        int i = 0, j = 0, k = 0;
        while (i < first.length && j < second.length) {
            if (first[i] < second[j]) {
                arr[k++] = first[i++];
            } else {
                arr[k++] = second[j++];
            }
        }

        while (i < first.length)
            arr[k++] = first[i++];

        while (j < second.length)
            arr[k++] = second[j++];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] array = QuickSort.getArrayRandomly();
        while (array.length != 0) {
            long startTime = System.currentTimeMillis();
            new MergeSort().sort(array);
            System.out.println("Array is " + QuickSort.isSorted(array) + "\n");
            System.out.println("Time needed for sorting " + array.length + " integers with MERGESORT was "
                    + (System.currentTimeMillis() - startTime));
            array = QuickSort.getArrayRandomly();
        }
        scanner.close();
    }
}
