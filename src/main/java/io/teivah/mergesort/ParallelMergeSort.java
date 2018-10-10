package io.teivah.mergesort;

import java.util.concurrent.RecursiveAction;

import static io.teivah.mergesort.MergeSort.mergesort;
import static io.teivah.mergesort.Utils.merge;

public class ParallelMergeSort extends RecursiveAction {
	private final int[] array;
	private final int[] helper;
	private final int low;
	private final int high;

	public ParallelMergeSort(final int[] array, final int low, final int high) {
		this.array = array;
		helper = new int[array.length];
		this.low = low;
		this.high = high;
	}

	@Override
	protected void compute() {
		if (low < high) {
			if (high - low <= 1 << 13) {
				mergesort(array, helper, low, high);
			} else {
				final int middle = (low + high) / 2;
				final ParallelMergeSort left = new ParallelMergeSort(array, low, middle);
				final ParallelMergeSort right = new ParallelMergeSort(array, middle + 1, high);
				invokeAll(left, right);
				merge(array, helper, low, middle, high);
			}
		}
	}
}
