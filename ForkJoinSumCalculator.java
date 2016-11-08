package com.brahim.testing.java8;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

public class ForkJoinSumCalculator extends RecursiveTask<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private  long [] numbers;
	private  int start;
	private int  end;
	
	public static final long THRESHOLD =1000;
	
	public ForkJoinSumCalculator(long[] aNumbers, int aStart, int aEnd) {
		super();
		numbers = aNumbers;
		start = aStart;
		end = aEnd;
	}
	
	public ForkJoinSumCalculator(long[]  numbers){
		this(numbers, 0, numbers.length);
	}
	

	@Override
	protected Long compute() {
		
		int length  = end -start;
		
		if(length <=  THRESHOLD){
		 return 	computeSequentially();
		}
		
		ForkJoinSumCalculator   leftTask = new ForkJoinSumCalculator(numbers, start , start+length/2);
		leftTask.fork();
		System.out.println(start  +" start "+ length/2);
		ForkJoinSumCalculator   rightTask = new ForkJoinSumCalculator(numbers, start +length/2, end);
		rightTask.fork();
		
		return leftTask.compute() + rightTask.compute();
	}
	
	private  long computeSequentially(){
		long sum = 0;
		for(int i = start; i< end; i++){
			 sum +=  numbers[i];
		}
		return sum;
	}
	
	public static long forkJoinSum(long n) {
		long[] numbers = LongStream.rangeClosed(1, n).toArray();
		ForkJoinSumCalculator  task = new ForkJoinSumCalculator(numbers);
		return new ForkJoinPool().invoke(task);
		}

	
	public static void main(String[] args) {
		System.out.println(ForkJoinSumCalculator.forkJoinSum( 25000));
	}
}
