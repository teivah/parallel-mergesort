package io.teivah.mergesort;

import java.util.concurrent.RecursiveAction;

import static io.teivah.mergesort.Utils.merge;

public class ParallelMergeSort extends RecursiveAction {
	private int[] array;
	private int[] helper;
	private int low;
	private int high;

	public ParallelMergeSort(int[] array, int low, int high) {
		this.array = array;
		helper = new int[array.length];
		this.low = low;
		this.high = high;
	}

	@Override
	protected void compute() {
		if (low < high) {
			int middle = (low + high) / 2;
			ParallelMergeSort left = new ParallelMergeSort(array, low, middle);
			ParallelMergeSort right = new ParallelMergeSort(array, middle + 1, high);
			invokeAll(left, right);
			merge(array, helper, low, middle, high);
		}
	}
}
