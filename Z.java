package com.defaul.other;


public class Z implements B1, C1 {
	
	public void hello(){
		 B1.super.hello();
	}
	
	public static void main(String[] args) {
		System.out.println("je suis la ");
	
			new Z().hello();
		
	}

}
