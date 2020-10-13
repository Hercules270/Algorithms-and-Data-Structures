package Week2;


import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;


class Inversions {

    private static final int BASE_CASE = 1;


    private static int sortAndCountInversions(int[] array, int length) {
        if(array.length == BASE_CASE) {
            return 0;
        }


        int[] left = new int[length/2];
        int[] right = new int[length/2 + length%2];

        System.arraycopy(array, 0, left, 0, length/2);
        System.arraycopy(array, length/2, right, 0, length/2 + length%2);

        int leftInv = sortAndCountInversions(left, length/2);
        int rightInv = sortAndCountInversions(right, length/2 + length % 2);

        int splitInv = mergeAndCountSplit(left, right, array, length);


        return leftInv + rightInv + splitInv;

    }

    private static int mergeAndCountSplit(int[] left, int[] right, int[] array, int length) {
        int i = 0, j = 0, k = 0;
        int count = 0;
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

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] strInts;
        int[] array = new int[100000];

        for(int i=0; i<100000; i++) {
            array[i] = i;

        }
        strInts = reader.readLine().split("\\s");
        System.out.println("Reading ended successfully...");
   /* while(true) {
      strInts = reader.readLine().split("\\s");
	  System.out.println("Reading ended successfully...");
      int[] array = new int[strInts.length];
      for (int i=0; i < strInts.length; i++) {
       array[i] = Integer.parseInt(strInts[i]);
      }
      */
        System.out.println("Copying ended succesfully...");

        System.out.println("Number of inversions is " + sortAndCountInversions(array, array.length));
        // System.out.println(Arrays.toString(array));

    }

}
