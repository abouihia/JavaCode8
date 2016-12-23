package com.brahim.testing.java8;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class WordCounterSpliterator implements Spliterator<Character> {
	
	private final String string;
	private int currentChar = 0;
	
	public WordCounterSpliterator(String string){
		this.string = string  ;
	}
	
	@Override
	public boolean tryAdvance(Consumer<? super Character> aAction) {
		 aAction.accept(string.charAt(currentChar++));
		 return  currentChar <string.length();
	}

	@Override
	public Spliterator<Character> trySplit() {
		
		int currentSize = string.length() - currentChar;
		if(currentSize <  10){
			 return null;
		}
		for( int i = currentSize/2 + currentChar;  i< string.length() ; i++){
			if(Character.isWhitespace(string.charAt(i))){
				Spliterator<Character>  spliterator  = new WordCounterSpliterator(string.substring(currentChar, i));
				 currentChar = i;
				return spliterator;
			}
		}
		return null;
	}

	@Override
	public long estimateSize() {
		return string.length() - currentChar;
	}

	@Override
	public int characteristics() {
		return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
	}
	
	

	
	
}
