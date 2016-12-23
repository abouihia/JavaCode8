package com.natixis.nie.contrat1.testing_code;

public class TerminalFibonnacciCalculator implements FibonacciCalculator {

	@Override
	public long calculate(final int n) {

		if (n < 0) {
			throw new IllegalArgumentException("Le rang doit être positif.");
		}

		return calculate(n, 1, 1);
	}

	private long calculate(final int n, final long result1, final long result2) {

		if (n == 0) {
			return result2;
		}

		return calculate(n - 1, result1 + result2, result1);
	}

}
