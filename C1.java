package com.defaul.other;

import com.defaul.A1;

public interface C1 extends A1 {
	
	default void hello(){
		System.out.println("Hello From C1");
	}

}
