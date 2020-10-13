package Week1;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Arrays;

class MergeSort {


    private void sort(int[] arr) {
        if(arr.length <= 1) {
            return;
        }

        int half = arr.length/2;
        int[] first = new int[half];
        int[] second = new int[arr.length-half];

        for(int i=0; i < half; i++) {
            first[i] = arr[i];

        }
        int j = 0;
        for(int i=half; i <arr.length; i++) {
            second[j] = arr[i];
            j++;
        }

        sort(first);
        sort(second);

        int i = 0;
        j = 0;

        merge(first, second, arr);

    }

    private void merge(int[] first, int[] second, int[] arr) {
        int i = 0, j = 0, k = 0;
        while(i < first.length && j < second.length) {
            if(first[i] < second[j]) {
                arr[k++] = first[i++];
            } else {
                arr[k++] = second[j++];
            }
        }

        while(i < first.length) arr[k++] = first[i++];

        while(j < second.length) arr[k++] = second[j++];
    }


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        MergeSort sort = new MergeSort();
        List<Integer> list = new ArrayList<>();

        int x = scan.nextInt();
        while(x != -1) {
            list.add(x);
            x = scan.nextInt();

        }
        int[] arr = new int[list.size()];
        for(int i=0; i<arr.length; i++) {
            arr[i] = list.get(i);
        }

        sort.sort(arr);

        System.out.println(Arrays.toString(arr));

    }
}
