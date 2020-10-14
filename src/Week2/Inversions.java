package Week2;


import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


class Inversions {

    private static final int BASE_CASE = 1;


    private static long sortAndCountInversions(int[] array, int length) {
        if(array.length == BASE_CASE) {
            return 0;
        }


        int[] left = new int[length/2];
        int[] right = new int[length/2 + length%2];

        System.arraycopy(array, 0, left, 0, length/2);
        System.arraycopy(array, length/2, right, 0, length/2 + length%2);

        long leftInv = sortAndCountInversions(left, length/2);
        long rightInv = sortAndCountInversions(right, length/2 + length % 2);

        long splitInv = mergeAndCountSplit(left, right, array, length);


        return leftInv + rightInv + splitInv;

    }

    private static long mergeAndCountSplit(int[] left, int[] right, int[] array, int length) {
        int i = 0, j = 0, k = 0;
        long count = 0;
        for(;k < length; k++) {
            if(i == left.length || j == right.length) break;
            if(left[i] <= right[j]) {
                array[k] = left[i++];

            } else {
                array[k] = right[j++];
                count += (left.length - i);
            }
        }
        
        while(i<left.length) {
            array[k++] = left[i++];
        }
        while(j < right.length) {
            array[k++] = right[j++];
        }

        return count;
    }
    public static void main(String[] args) throws  FileNotFoundException{
        Scanner scanner = new Scanner(new File("/mnt/d/GitHub/Algorithms and Data Structures/src/Week2/a.txt"));
        List<Integer> list = new ArrayList<>();
        
        while(scanner.hasNextInt()) {
            list.add(scanner.nextInt());
        }

        int[] array = list.stream().mapToInt(i -> i).toArray();
        
        long result = sortAndCountInversions(array, array.length);
        
        System.out.println("Result is " + result);
    }

}
