package com.brahim.testing.java8;

import com.natixis.nie.contrat1.testing_code.Task;

public class TestingCode {
	
	
	
	public void doSomething(Runnable  r ){
		 r.run();
	}
	public void doSomething(Task  r ){
		 r.execute();
	}
	
	public static void main(String[] args) {
		TestingCode lCode  = new TestingCode();
		lCode.doSomething((Task)() ->{
			System.out.println("Danger");
		});
	}

}
