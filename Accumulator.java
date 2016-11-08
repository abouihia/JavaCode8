package com.brahim.testing.java8;

import java.util.function.Function;
import java.util.stream.LongStream;

public class Accumulator {
	
	public long total = 0;
	public void add(long value) { total += value; }

	
	public static void main(String[] args) {
		
		System.out.println("SideEffect parallel sum done in: " +	measurePerf(Accumulator::sideEffectSum, 10_000_000L) + "msecs" );
		
	}
	
	// data race: 
	public static long sideEffectSum(long n) {
		Accumulator accumulator = new Accumulator();
		LongStream.rangeClosed(1, n).parallel().forEach(accumulator::add);
		return accumulator.total;
		}
	
	public static  long measurePerf(Function<Long, Long> adder, long n) {
		long fastest = Long.MAX_VALUE;
		for (int i = 0; i < 10; i++) {
			long start = System.nanoTime();
			long sum = adder.apply(n);
			long duration = (System.nanoTime() - start) / 1_000_000;
			System.out.println("Result: " + sum);
			if (duration < fastest)
				fastest = duration;
		}
		return fastest;
	}
}
