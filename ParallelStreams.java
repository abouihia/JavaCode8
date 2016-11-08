package com.brahim.testing.java8;

import java.util.stream.Stream;

public class ParallelStreams {

	public static long iterativeSum(long n) {
		long result = 0;
		for (long i = 1L; i <= n; i++) {
			result += i;
		}
		return result;
	}

	public static long paralleleSum(long n) {
		return Stream.iterate(1L, i -> i + 1).limit(n).parallel()
				.reduce(0L, Long::sum);
	}
	
	
	public static long squentialSum(long n) {
		return Stream.iterate(1L, i -> i + 1).limit(n)
				.reduce(0L, Long::sum);
	}
}
