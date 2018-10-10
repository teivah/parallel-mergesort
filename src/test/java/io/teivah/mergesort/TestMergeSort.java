package io.teivah.mergesort;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.ForkJoinPool;

import static io.teivah.mergesort.Utils.random;
import static org.junit.jupiter.api.Assertions.fail;

public class TestMergeSort {
	@Test
	public void testSequential() {
		final int[] array = random(100);
		new MergeSort().mergesort(array);

		int last = Integer.MIN_VALUE;
		for (int i = 0; i < array.length; i++) {
			if(array[i] < last) {
				fail();
			}

			last = array[i];
		}
	}

	@Test
	public void testParallel() {
		final int[] array = random(100);
		ForkJoinPool forkJoinPool = new ForkJoinPool(7);
		forkJoinPool.invoke(new ParallelMergeSort(array, 0, array.length-1));

		int last = Integer.MIN_VALUE;
		for (int i = 0; i < array.length; i++) {
			if(array[i] < last) {
				fail();
			}

			last = array[i];
		}
	}
}
