package com.brahim.testing.java8;

import com.brahim.testing.java8.Java8Chap6.CaloricLevel;

public class Dish {

	private String name;
	private boolean vegetarian;
	private int calories;
	private Type type;

	public enum Type {
		OTHER, FISH, MEAT;
	}

	public Dish(String aName, boolean aVegetarian, int aCalories, Type aType) {
		super();
		name = aName;
		vegetarian = aVegetarian;
		calories = aCalories;
		type = aType;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the vegetarian
	 */
	public boolean isVegetarian() {
		return vegetarian;
	}

	/**
	 * @return the calories
	 */
	public int getCalories() {
		return calories;
	}

	/**
	 * @return the type
	 */
	public Type getType() {
		return type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Dish [name=" + name + ", vegetarian=" + vegetarian
				+ ", calories=" + calories + ", type=" + type + "]";
	}

	public CaloricLevel getLevelCalories() {

		if (this.calories < 400) {
			return CaloricLevel.DIET;
		} else if (this.calories < 700) {
			return CaloricLevel.NORMAL;
		} else {
			return CaloricLevel.FAT;
		}

	}

}
