package com.brahim.testing.java8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class BinaryOperatorDemo {

	 public static void main(String[] args) {
	       Map<String,String> map = new HashMap<>();
	       map.put("X", "A");
	       map.put("Y", "B");
	       map.put("Z", "C");
	       BinaryOperator<String> binaryOpt = (s1,s2)-> s1+"-"+s2; 
	       binaryOperatorFun(binaryOpt, map).forEach(x->System.out.println(x)); 
	       
	    /******************/
	       
	       		BiConsumer<String, String> biConsumer = (x, y) -> {
	    	      System.out.println(x);
	    	      System.out.println(y);
	    	    };

	    	    biConsumer.accept("java2s.com", " tutorials");
	    	    biConsumer.andThen(biConsumer).accept("java2s.com", " tutorial");
	    	    
	    	    Consumer<String> c = (x) -> System.out.println(x.toLowerCase());
	    	    c.accept("Java2AAAAAAAs.com");
	       
	    	    
	    	    Supplier<String> i  = ()-> "java2s.com";
	    	    System.out.println(i.get());
	       
	       
	       
	    }
	    private static List<String> binaryOperatorFun(BinaryOperator<String> binaryOpt, Map<String,String> map){
	       List<String> biList = new ArrayList<>();
	       map.forEach((s1,s2)->biList.add(binaryOpt.apply(s1,s2))); 
	       return biList;
	    }
}
