package com.defaul.other;

import com.defaul.A1;

public interface B1 extends A1 {
	
	default void hello(){
		System.out.println("Hello From B1");
	}

}
