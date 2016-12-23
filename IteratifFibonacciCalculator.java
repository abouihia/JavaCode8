package com.natixis.nie.contrat1.testing_code;

public class IteratifFibonacciCalculator implements FibonacciCalculator {

	public long calculate(int n) {
		if (n < 0) {
			throw new IllegalArgumentException("Le rang doit être positif.");
		}

		long result1 = 1;
		long result2 = 1;
		long temp = 0;

		for (int i = 0; i < n; i++) {
			temp = result1 + result2;
			result1 = result2;
			result2 = temp;
		}

		return result1;
	}

}
