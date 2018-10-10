package io.teivah.mergesort;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public interface Utils {
	static int[] random(int n) {
		int[] a = new int[n];

		for (int i = 0; i < n; i++) {
			a[i] = new Random().nextInt(n);
		}

		return a;
	}

	static void merge(int[] array, int[] helper, int low, int middle, int high) {
		for (int i = low; i <= high; i++) {
			helper[i] = array[i];
		}

		int helperLeft = low;
		int helperRight = middle + 1;
		int current = low;

		while (helperLeft <= middle && helperRight <= high) {
			if (helper[helperLeft] <= helper[helperRight]) {
				array[current] = helper[helperLeft++];
			} else {
				array[current] = helper[helperRight++];
			}
			current++;
		}

		while (helperLeft <= middle) {
			array[current++] = helper[helperLeft++];
		}
	}
}
