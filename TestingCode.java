package com.brahim.testing.java8;

public class TestingCode {
	
	
	
	private void printScores(String... players) {
	    for (int i = 0; i < players.length; ++i) {
	    	String val  = players[i];
	        System.out.format("%d%n", val);
	    }
	}

}
