package com.defaul;

public interface B  {
	
	default void hello(){
		System.out.println("Hello From B");
	}

}
