package Week3;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.concurrent.atomic.AtomicInteger;

//This Class implements quick sort algorithm 
public class QuickSort {

    /**
     * This method is using Quick sort algorithm to sort array of integers
     * @param array array method is sorting
     * @param left  first index of elements from where this call should sort array
     * @param right last index of elements until which method should sort the array
     * @param numberOfComparisons counter of comparisons
     */
    public void sort(int[] array, int left, int right, AtomicInteger numberOfComparisons) {

        if (left >= right)
            return;
        int pivotIndex = medianPivot(array, left, right); // new Random.nextInt(right - left) + left or right or left.
        numberOfComparisons.addAndGet(right- left);
        swap(array, left, pivotIndex);
        int part = partition(array, left, right);

        sort(array, left, part - 1, numberOfComparisons);
        sort(array, part + 1, right, numberOfComparisons);
    }



    /**
     * This method chooses pivot which is middle number between of first middle and last elements of array
     * @param array Array of elements
     * @param left  First index we are interested in
     * @param right Last index we are interested in
     * @return      Returns index of pivot
     */
    private int medianPivot(int[] array, int left, int right) {
        int median = (right - left ) / 2 + left;
        int a = array[left];
        int b = array[median];
        int c = array[right];
        if (( a <= b && b <= c) || (c <= b && b <=a)) return median;
        if ((b <= a && a <= c) || (c <= a && c <= b)) return left;
        return right;
    }


    /**
     * This method is partitioning array around pivot in O(1) time and space complexity
     * @param array Array of elements
     * @param left  First index we are interested in
     * @param right Last index we are interested in
     * @return      Index of pivot after partitioning
     */
    private int partition(int[] array, int left, int right) {
        int i = left + 1;
        int j = left + 1;
        int pivot = array[left];

        for (; j <= right; j++) {
            if (array[j] < pivot) {
                swap(array, j, i);
                i++;
            }
        }

        swap(array, left, i - 1);
        return i - 1;
    }

    // This method is selfexplanatory. It swaps places of two elements in array.
    private void swap(int[] array, int from, int to) {
        int tmp = array[from];
        array[from] = array[to];
        array[to] = tmp;
    }


    public static void main(String[] args) throws FileNotFoundException {
        int[] array = new int[1];

        if (args.length == 0) { //If no argument was passed then get array from file
            array = getArrayFromFile();
        } else if (args[0].equals("Random") || args[0].equals("R") || args[0].equals("RANDOM")) { //Get array randomly
            array = getArrayRandomly();
        } else if (args[0].equals("Input") || args[0].equals("I") || args[0].equals("INPUT")) {  //Get array from input
            array = getArrayFromInput();
        }
        if (array.length == 0)
            return;
        AtomicInteger numberOfComparisons = new AtomicInteger(0);
        new QuickSort().sort(array, 0, array.length - 1, numberOfComparisons);

        System.out.println("Array is " + isSorted(array) + "\n");
        System.out.println("Number of comparisons is: " + numberOfComparisons);

    }
    //This method checks if array of itnegers is sorted.
    public static String isSorted(int[] array) {
        for (int i = 1; i < array.length; i++) {
            if (array[i] < array[i - 1])
                return "NOT SORTED";
        }
        return "SORTED";
    }

    //This method simply populates array with integers from file and returns it.
    private static int[] getArrayFromFile() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(
                "D:\\GitHub\\Algorithms and Data Structures\\src\\Week3\\Assignment3.txt"));
        List<Integer> list = new ArrayList<>();
        while (scanner.hasNextInt()) {
            list.add(scanner.nextInt());
        }
        return list.stream().mapToInt(i -> i).toArray();
    }

    //This method simply populates array with integers randomly and returns it.
    public static int[] getArrayRandomly() {
        Scanner scanner = new Scanner(System.in);

        Random random = new Random();
        System.out.println("Please enter size of array");
        int size = scanner.nextInt();
        int[] array = new int[size];
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(array.length * 10);
        }
        scanner.close();
        return array;
    }

    //This method simply populates array with integers from input typed as string and returns it.
    public static int[] getArrayFromInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter number of elements in array ");
        String arr = scanner.nextLine();

        int[] array = Arrays.stream(arr.substring(1, arr.length() - 1).split(",")).map(String::trim)
                .mapToInt(Integer::parseInt).toArray();
        scanner.close();
        return array;
    }
}
