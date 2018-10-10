package io.teivah.mergesort;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ForkJoinPool;

import static io.teivah.mergesort.Utils.random;
import static org.junit.jupiter.api.Assertions.fail;

public class TestMergeSort {
	private static void check(final int[] array) {
		int last = Integer.MIN_VALUE;
		for (int i = 0; i < array.length; i++) {
			if (array[i] < last) {
				fail();
			}

			last = array[i];
		}
	}

	@Test
	public void testSequential() {
		final int[] array = random(1000);
		MergeSort.mergesort(array);

		check(array);
	}

	@Test
	public void testParallel() {
		final int[] array = random(1000);
		final ForkJoinPool forkJoinPool = new ForkJoinPool(7);
		forkJoinPool.invoke(new ParallelMergeSort(array, 0, array.length - 1));

		check(array);
	}
}
