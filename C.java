package com.defaul;

public class C  extends D implements A1, B {
	
	public void hello(){
		B.super.hello();//sépcify méthode
	}
	
	public static void main(String[] args) {
		
		new C().hello();
	}

	

}
