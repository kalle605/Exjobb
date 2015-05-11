package main.tests;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestFilters {
	int arr_temp[] = new int[30];
	int arr_values[] = new int[30];
	Random r = new Random();

	@Before
	public void initialize() {
		for (int i = 0; i < 30; i++) {
			arr_values[i] = r.nextInt() % 400 + 200;
			arr_temp[i] = arr_values[i];
		}
	}

	@Test
	public void testtime() {
		testBubbleSort();
		testInsertSort();
		testSelectionSort();
	}

	@Test
	public void testBubbleSort1() {
		resett();
		BubbleSort();
		Assert.assertEquals(true, testifsorted());

	}

	@Test
	public void testInsertSort1() {
		resett();
		InsertSort();
		Assert.assertEquals(true, testifsorted());
	}

	@Test
	public void testSelectSort1() {
		resett();

		SelectionSort();
		printAll();
		Assert.assertEquals(true, testifsorted());

	}

	public void testBubbleSort() {
		System.out.println("Start bubbleSort test");
		resett();
		long time = System.nanoTime();
		BubbleSort();
		System.out.println("BubbleSort " + (System.nanoTime() - time) + "\n");

	}

	public void testSelectionSort() {
		System.out.println("Start SelectionSort test");
		resett();

		long time = System.nanoTime();
		SelectionSort();
		System.out
				.println("SelectionSort " + (System.nanoTime() - time) + "\n");

	}

	public void testInsertSort() {
		System.out.println("Start InsertSort test");
		resett();
		long time = System.nanoTime();
		InsertSort();
		System.out.println("InsertSort " + (System.nanoTime() - time) + "\n");

		SimpleDateFormat d = new SimpleDateFormat("HH");
		String date = d.format(new Date());

		System.out.println(date + " timmar");
	}

	void swap(int i, int j) {
		int temp = arr_temp[j];
		arr_temp[j] = arr_temp[i];
		arr_temp[i] = temp;
	}

	void BubbleSort() {
		for (int i = 1; i < 30; i++) {
			for (int j = 0; j < 10; j++) {
				if (arr_temp[j] > arr_temp[i]) {
					swap(i, j);
				}
			}
		}
		for (int i = 10; i < 30; i++) {
			for (int j = 29; j >= 20; j--) {
				if (arr_temp[j] < arr_temp[i]) {
					swap(i, j);
				}
			}
		}
	}

	public void InsertSort() {
		for (int i = 1; i < 30; i++) {
			int temp = arr_temp[i];
			int j;
			for (j = i - 1; j >= 0 && temp < arr_temp[j]; j--)
				arr_temp[j + 1] = arr_temp[j];
			arr_temp[j + 1] = temp;
		}
	}

	public void SelectionSort() {
		for (int i = 0; i < 10; i++) {
			int index = i;
			for (int j = i + 1; j < 30; j++)
				if (arr_temp[j] < arr_temp[index])
					index = j;

			int smallerNumber = arr_temp[index];
			arr_temp[index] = arr_temp[i];
			arr_temp[i] = smallerNumber;
		}
		for (int i = 29; i >= 20; i--) {
			int index = i;
			for (int j = 10; j < i; j++)
				if (arr_temp[j] > arr_temp[index])
					index = j;

			int biggerNumber = arr_temp[index];
			arr_temp[index] = arr_temp[i];
			arr_temp[i] = biggerNumber;
		}

	}

	void resett() {
		for (int i = 0; i < 30; i++)
			arr_temp[i] = arr_values[i];
	}

	void printAll() {
		System.out.print("[");
		for (int i = 0; i < 30; i++)
			System.out.print(arr_temp[i] + ",");
		System.out.print("]");
		System.out.println("\n");
		System.out.print("[");
		for (int i = 0; i < 30; i++)
			System.out.print(arr_values[i] + ",");
		System.out.print("]");
		System.out.println("\n");
	}

	boolean testifsorted() {
		for (int i = 0; i < 10; i++)
			for (int j = i + 1; j < 30; j++)
				if (arr_temp[i] > arr_temp[j]) {
					System.out.println("Talen: " + arr_temp[i] + " " + i + " "
							+ arr_temp[j] + " " + j + " ");
					return false;
				}
		for (int i = 99; i > 89; i--)
			for (int j = i + 1; j < 30 - i; j++)
				if (arr_temp[i] < arr_temp[j]) {
					System.out.println("Talen: " + arr_temp[i] + " "
							+ arr_temp[j]);
					return false;
				}
		return true;
	}

}
