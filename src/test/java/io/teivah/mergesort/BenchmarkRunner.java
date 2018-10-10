package io.teivah.mergesort;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

import static io.teivah.mergesort.Utils.random;

public class BenchmarkRunner {
	public static void main(String[] args) throws Exception {
		org.openjdk.jmh.Main.main(args);
	}

	@Benchmark
	@Fork(value = 1, warmups = 1)
	@Warmup(iterations = 1)
	@Measurement(iterations = 3)
	@OutputTimeUnit(TimeUnit.MICROSECONDS)
	@BenchmarkMode(Mode.AverageTime)
	public void benchSequential() {
		final int[] array = random(1_000_00);
		new MergeSort().mergesort(array);
	}

	@Benchmark
	@Fork(value = 1, warmups = 1)
	@Warmup(iterations = 1)
	@Measurement(iterations = 3)
	@OutputTimeUnit(TimeUnit.MICROSECONDS)
	@BenchmarkMode(Mode.AverageTime)
	public void benchParallel() {
		final int[] array = random(1_000_00);
		ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors() - 1);
		forkJoinPool.invoke(new ParallelMergeSort(array, 0, array.length - 1));
	}
}