package com.natixis.nie.contrat1.testing_code;

public class DirectFibonacciCalculator implements FibonacciCalculator {

	private final static double NOMBRE_OR = 1.61803398874989;
	private final static double RACINE_5 = 2.236067977499;

	public long calculate(int n) {
		if (n < 0) {
			throw new IllegalArgumentException("Le rang doit être positif.");
		}

		if (n == 0 || n == 1) {
			return 1L;
		}

		final double nominateur = Math.pow(NOMBRE_OR, n + 1);

		final double result = nominateur / RACINE_5;
		
		return Math.round(result);
	}

}
