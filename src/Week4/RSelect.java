package Week4;

import java.util.Scanner;

class RSelect {

	private static int kSmallest(int[] array, int left, int right, int k) {
		if (right == left)
			return array[k];

		int partition = partition(array, left, right);

		if (partition == k)
			return array[partition];

		if (partition > k)
			return kSmallest(array, left, partition - 1, k);

		return kSmallest(array, partition + 1, right, k);
	}

	private static int partition(int[] array, int left, int right) {

		int i = left + 1;

		for (int index = left + 1; index <= right; index++) {
			if (array[index] < array[left]) {
				swap(array, index, i);
				i++;
			}
		}

		swap(array, left, i - 1);

		return i - 1;
	}

	private static void swap(int[] array, int x, int y) {
		int temp = array[x];
		array[x] = array[y];
		array[y] = temp;
	}

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter size of array:");
		int n = scanner.nextInt();
		int[] array = new int[n];

		for (int i = 0; i < n; i++) {
			System.out.print("Enter number: ");
			array[i] = scanner.nextInt();
		}

		while (true) {
			System.out.print("Which order of statistics do you want? ");
			int k = scanner.nextInt();
			if (k == -1)
				break;
			while (k < 1 || k > array.length) {
				System.out.print("K is out of array bounds, enter correct k: ");
				k = scanner.nextInt();
			}
			int result = kSmallest(array, 0, array.length - 1, k - 1);
			System.out.println("Answer is " + result);
		}
		scanner.close();
	}

}