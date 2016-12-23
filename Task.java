package com.natixis.nie.contrat1.testing_code;

public interface Task {
	public void execute();

	default void print() {

		System.out.println("yes interface");
	}

}
 