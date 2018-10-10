package io.teivah.mergesort;

import org.openjdk.jmh.annotations.*;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

import static io.teivah.mergesort.Utils.random;

public class BenchmarkRunner {

	private static final int SIZE = 100_000;

	public static void main(final String[] args) throws Exception {
		org.openjdk.jmh.Main.main(args);
	}

	@Benchmark
	@Fork(value = 1, warmups = 1)
	@Warmup(iterations = 1)
	@Measurement(iterations = 3)
	@OutputTimeUnit(TimeUnit.MILLISECONDS)
	@BenchmarkMode(Mode.AverageTime)
	public void benchSequential() {
		final int[] array = random(SIZE);
		MergeSort.mergesort(array);
	}

	@Benchmark
	@Fork(value = 1, warmups = 1)
	@Warmup(iterations = 1)
	@Measurement(iterations = 3)
	@OutputTimeUnit(TimeUnit.MILLISECONDS)
	@BenchmarkMode(Mode.AverageTime)
	public void benchParallel() {
		final int[] array = random(SIZE);
		final ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors() - 1);
		forkJoinPool.invoke(new ParallelMergeSort(array, 0, array.length - 1));
		Arrays.parallelSort(array);
	}

	@Benchmark
	@Fork(value = 1, warmups = 1)
	@Warmup(iterations = 1)
	@Measurement(iterations = 3)
	@OutputTimeUnit(TimeUnit.MILLISECONDS)
	@BenchmarkMode(Mode.AverageTime)
	public void benchParallelSmart() {
		final int[] array = random(SIZE);
		final ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors() - 1);
		forkJoinPool.invoke(new ParallelSmartMergeSort(array, 0, array.length - 1));
		Arrays.parallelSort(array);
	}
}