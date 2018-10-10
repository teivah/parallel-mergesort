package io.teivah.mergesort;

import java.util.concurrent.RecursiveAction;

import static io.teivah.mergesort.MergeSort.mergesort;
import static io.teivah.mergesort.Utils.merge;

public class ParallelSmartMergeSort extends RecursiveAction {
	private static final int MAX = 1 << 13;
	private final int[] array;
	private final int[] helper;
	private final int low;
	private final int high;

	public ParallelSmartMergeSort(final int[] array, final int low, final int high) {
		this.array = array;
		helper = new int[array.length];
		this.low = low;
		this.high = high;
	}

	@Override
	protected void compute() {
		if (low < high) {
			if (high - low <= MAX) {
				mergesort(array, helper, low, high);
			} else {
				final int middle = (low + high) / 2;
				final ParallelSmartMergeSort left = new ParallelSmartMergeSort(array, low, middle);
				final ParallelSmartMergeSort right = new ParallelSmartMergeSort(array, middle + 1, high);
				invokeAll(left, right);
				merge(array, helper, low, middle, high);
			}
		}
	}
}
