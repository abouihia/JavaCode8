package com.brahim.testing.java8;

import java.util.Collections;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class WordCounter {

	private final int counter;
	private final boolean lastSpace;

	public WordCounter(int aCounter, boolean aLastSpace) {
		super();
		counter = aCounter;
		lastSpace = aLastSpace;
	}

	public WordCounter accumulate(Character c) {
		if (Character.isWhitespace(c)) {
			return lastSpace ? this : new WordCounter( this.counter + 1, true);
		}
		return lastSpace ? new WordCounter(this.counter + 1, false) : this;

	}

	public WordCounter combine(WordCounter aCounter) {
		return new WordCounter(this.counter + aCounter.counter, aCounter.lastSpace);
	}

	private int countWords(Stream<Character> stream) {
		WordCounter counter = stream.reduce(new WordCounter(0, true),
				WordCounter::accumulate, WordCounter::combine);

		return counter.getCounter();
	}

	/**
	 * @return the counter
	 */
	public int getCounter() {
		return counter;
	}

	public static void main(String[] args) {
		
		final String SENTENCE =
				" Nel mezzo del cammin di nostra vita mi ritrovai in una selva oscura ché la dritta via era smarrita ";
				
		

		Stream<Character> stream = IntStream.range(0, SENTENCE.length())
				.mapToObj(SENTENCE::charAt);
		
		WordCounter   lCounter  = new WordCounter(0, true);
		
		System.out.println("Found " + lCounter.countWords(stream) + " words");
		/**************************/
		
		Spliterator<Character> spliterator = new WordCounterSpliterator(SENTENCE);
		
		Stream<Character> stream2 = StreamSupport.stream(spliterator, true);
		
		System.out.println("seconde one : " + lCounter.countWords(stream2) + " words");
		
	}

}
