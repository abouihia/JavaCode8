package com.brahim.testing.java8;

import static java.util.Collections.unmodifiableSet;
import static java.util.EnumSet.of;
import static java.util.stream.Collector.Characteristics.CONCURRENT;
import static java.util.stream.Collector.Characteristics.IDENTITY_FINISH;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class PrimeNumberCollector  implements Collector<Integer, Map<Boolean, List<Integer>>,  Map<Boolean, List<Integer>>> {

	@Override
	public Supplier<Map<Boolean, List<Integer>>> supplier() {
		
		return () ->  new HashMap<Boolean, List<Integer>>(){

				{
					put(true, new ArrayList<Integer>());
					put(false, new ArrayList<Integer>());
				}
			};
	}

	@Override
	public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
		
		return ( Map<Boolean, List<Integer>> acc , Integer candidat) ->{
			acc.get(isPrime(acc.get(true), candidat)).add(candidat);
		};
	}

	@Override
	public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
		return (Map<Boolean, List<Integer>> map1 , Map<Boolean, List<Integer>> map2)->{
			
			map1.get(true).addAll(map2.get(true));
			map1.get(false).addAll(map2.get(false));
			return map1;
		};
	}

	@Override
	public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
		return  Function.identity();
	}

	@Override
	public Set<java.util.stream.Collector.Characteristics> characteristics() {
		return unmodifiableSet(of(IDENTITY_FINISH));
	}
	

	public   boolean isPrime(List<Integer>   primes , int candidate) {
		int candidateRoot = (int) Math.sqrt((double) candidate);
		return takeWhile(primes, i  ->  i <= candidateRoot).stream()
		.noneMatch(p -> candidate % p == 0);
		}

	public static <A> List<A> takeWhile(List<A> list, Predicate<A> p){
		 int i = 0;
		  for( A item : list){
			   if(!p.test(item)){
				    return  list.subList(0, i);
			   }
		  }
		return list;
		
	}
	
	
}
