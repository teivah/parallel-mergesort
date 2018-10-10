package io.teivah.mergesort;

import java.util.Random;

public interface Utils {
	static int[] random(final int n) {
		final int[] a = new int[n];

		for (int i = 0; i < n; i++) {
			a[i] = new Random().nextInt(n);
		}

		return a;
	}

	static void merge(final int[] array, final int[] helper, final int low, final int middle, final int high) {
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
