package com.natixis.nie.contrat1.testing_code;

public interface FibonacciCalculator {

	/**
	 * Calcule la valeur du membre de la suite de Fibonacci pour le rang n.
	 * 
	 * @param n
	 *            rang positif
	 * @return valeur du membre pour le rand indiqué.
	 * @throws IllegalArgumentException
	 *             si le rang est négatif
	 */
	long calculate(final int n);
	
	

}
