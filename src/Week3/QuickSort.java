package Week3;

import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;
//import java.util.stream.*;

public class QuickSort { 


    public void sort(int[] array, int left, int right) {

       //System.out.println("In sort method. left is -- " + left + " and right is -- " + right);
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

    //   

    private void swap(int[] array, int from, int to) {
        
        int tmp = array[from];
        array[from] = array[to];
        array[to] = tmp;
    }

    
    

    public static void main(String[] args) {

        //sortArray();
        testing();
    }

    private static void testing()  {
        Scanner scanner = new Scanner(System.in);
        QuickSort sort = new QuickSort();
        System.out.println("Please enter number of elements in array ");
        String arr = scanner.nextLine();
        String [] array = (String[]) Arrays.stream(arr.substring(1, arr.length() - 1).split(",")).map(String::trim).toArray();
        //Arrays.stream(arr.substring(1,arr.length() - 1).split(",")).map(String::trim).mapToInt(Integer::parseInt).toArray();
        System.out.println(Arrays.toString(array));
       
    }

    private static void sortArray() {
        Scanner scanner = new Scanner(System.in);
        QuickSort sort = new QuickSort();
        System.out.println("Please enter number of elements in array ");
        String arr = scanner.nextLine();

        int[] array = Arrays.stream(arr.substring(1,arr.length() - 1).split(",")).map(String::trim).mapToInt(Integer::parseInt).toArray();
        System.out.println("Before sorting " + Arrays.toString(array));
        
        sort.sort(array, 0, array.length - 1);
        System.out.println("After sorting " + Arrays.toString(array));
        scanner.close();
    }
}


// [1,2,3,4]