package Week3;

import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;

//This Class implements quick sort algorithm 
public class QuickSort { 

    public void sort(int[] array, int left, int right) {

        if (left >= right) return;
        int pivotIndex = left; // new Random.nextInt(right - left) + left;
        swap(array, left, pivotIndex);
        int part = partition(array, left, right);
        sort(array, left, part - 1);
        sort(array, part + 1, right);
    }

    private int partition(int[] array, int left, int right) {
        int i = left + 1;
        int j = left + 1;
        int pivot = array[left];

        for(; j<=right; j++) {
            if (array[j] < pivot) {
                swap(array, j, i);
                i++;
            }
        }

        swap(array, left, i-1);
        return i-1;
    }

    private void swap(int[] array, int from, int to) { 
        int tmp = array[from];
        array[from] = array[to];
        array[to] = tmp;
    }

    

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        sortArray(scanner);
        scanner.close();
    }


    private static void sortArray(Scanner scanner) {
        while(true) {
            long startTime = System.currentTimeMillis();
            int[] array = getArrayRandomly(scanner);
            
            if (array.length == 0) return;
            new QuickSort().sort(array, 0, array.length - 1);
            
            System.out.println("Array is " + isSorted(array) + "\n");
            System.out.println("Time needed for sorting " + array.length + " integers with MERGESORT was " + (System.currentTimeMillis() - startTime));
           
        }
    }

    public static String isSorted(int[] array) {
        for(int i=1; i<array.length; i++) {
            if (array[i] < array[i-1]) return "NOT SORTED";
        }
        return "SORTED";
    }

    public static int[] getArrayRandomly(Scanner scanner) {
        Random random = new Random();
        System.out.println("Please enter size of array");
        int size = scanner.nextInt();
        int[] array = new int[size];
        for(int i=0; i < array.length; i++) {
            array[i] = random.nextInt(array.length*10); 
        }
        return array;
    }

    public static int[] getArrayFromInput() {
        Scanner scanner = new Scanner(System.in);
       
        System.out.println("Please enter number of elements in array ");
        String arr = scanner.nextLine();

        int[] array = Arrays.stream(arr.substring(1,arr.length() - 1).split(",")).map(String::trim).mapToInt(Integer::parseInt).toArray();
        scanner.close();
        return array;
    }
}


