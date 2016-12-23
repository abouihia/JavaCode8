package com.defaul.other;

public interface A {
	
	default void hello(){
		System.out.println("Hello From A");
	}
}
