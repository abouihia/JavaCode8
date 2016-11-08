package com.brahim.testing.java8;

import static java.util.Collections.unmodifiableSet;
import static java.util.EnumSet.of;
import static java.util.stream.Collector.Characteristics.CONCURRENT;
import static java.util.stream.Collector.Characteristics.IDENTITY_FINISH;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * T: type element in the stream
 * A: type of the object used to accumlate partial results
 * R: type of the final  result of the collect operation
 * 
 * @author abouihiasb
 *
 * @param <T>
 */

public class ToListCollector<T> implements Collector<T, List<T>, List<T>> {

	@Override
	public Supplier<List<T>> supplier() {
		
		return ArrayList:: new;
	}

	@Override
	public BiConsumer<List<T>, T> accumulator() {
		return List::add;
	}

	@Override
	public BinaryOperator<List<T>> combiner() {
		return (list1, list2) ->{ 
					list1.addAll(list2) ;  return list1;};
	}

	@Override
	public Function<List<T>, List<T>> finisher() {
		return Function.identity();
	}

	@Override
	public Set<java.util.stream.Collector.Characteristics> characteristics() {
		return unmodifiableSet(of(IDENTITY_FINISH, CONCURRENT));
	}

}
