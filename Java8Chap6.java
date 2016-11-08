package com.brahim.testing.java8;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.partitioningBy;
import static java.util.stream.Collectors.reducing;
import static java.util.stream.Collectors.summarizingInt;
import static java.util.stream.Collectors.summingInt;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Java8Chap6 {

	public enum CaloricLevel {
		DIET, NORMAL, FAT
	};

	public static void main(String[] args) {

		List<Dish> menu = Arrays.asList(new Dish("pork", false, 800,
				Dish.Type.MEAT), new Dish("beef", false, 700, Dish.Type.MEAT),
				new Dish("chicken", false, 400, Dish.Type.MEAT), new Dish(
						"french fries", true, 530, Dish.Type.OTHER), new Dish(
						"rice", true, 350, Dish.Type.OTHER), new Dish(
						"season fruit", true, 120, Dish.Type.OTHER), new Dish(
						"pizza", true, 550, Dish.Type.OTHER), new Dish(
						"prawns", false, 300, Dish.Type.FISH), new Dish(
						"salmon", false, 450, Dish.Type.FISH));

		// calculate howManyDishes

		Long howManyDishes = menu.stream().collect(counting());
		Long howManyDishes12 = menu.stream().collect(
				reducing(0L, e -> 1L, Long::sum));
		int howManyDishes13 = menu.stream().map(Dish::getCalories)
				.reduce(Integer::sum).get();
		int howManyDishes14 = menu.stream().mapToInt(Dish::getCalories).sum();
		// or
		Long howManyDishes1 = menu.stream().count();

		System.out.println(howManyDishes + "///////" + howManyDishes1
				+ "--------" + howManyDishes12 + "    " + howManyDishes13);

		// max and min (1)
		Optional<Dish> maxCalories = menu.stream().collect(
				maxBy(comparingInt(Dish::getCalories)));
		System.out.println(maxCalories.get().toString());

		// summarization

		Integer sumCalories = menu.stream().collect(
				summingInt(Dish::getCalories));
		System.out.println("sumCalories----> " + sumCalories);
		// statistics:
		IntSummaryStatistics menuStatistics = menu.stream().collect(
				summarizingInt(Dish::getCalories));

		System.out.println(menuStatistics);

		// String opération
		String shortMenu = menu.stream().map(Dish::getName)
				.collect(joining(" "));
		System.out.println(shortMenu);

		// calcule total property of liste object

		int totalCalories = menu.stream().collect(
				Collectors.reducing(0, Dish::getCalories, (i, j) -> i + j));
		// or
		int totalCalories1 = menu.stream().collect(
				reducing(0, Dish::getCalories, Integer::sum));
		System.out.println(" total calories is " + totalCalories
				+ "  other methodes " + totalCalories1);

		// find the highest-calories dish(other way than ((1))
		Optional<Dish> mostCalorieDish = menu.stream().collect(
				reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1
						: d2));

		System.out.println("find the highest-calories dish" + mostCalorieDish);

		/************** difference between : Collect vs. reduce ************************/

		Stream<Integer> stream = Arrays.asList(1, 2, 3, 4, 5, 6).stream();
		stream.reduce(new ArrayList<Integer>(),
				(List<Integer> l, Integer e) -> {
					l.add(e);
					return l;
				}, (List<Integer> l1, List<Integer> l2) -> {
					l1.addAll(l2);
					return l1;
				});

		/************************************************** Grouping ********************************************/

		// Grouping by type dish

		Map<Dish.Type, List<Dish>> mapDishType = menu.stream().collect(
				groupingBy(Dish::getType));

		mapDishType.forEach((k, v) -> System.out.println(k + "  : " + v));

		// Grouping by type healty menu

		Map<CaloricLevel, List<Dish>> menuDiet = menu.stream().collect(
				groupingBy(d -> {
					if (d.getCalories() < 400)
						return CaloricLevel.DIET;
					else if (d.getCalories() < 700)
						return CaloricLevel.NORMAL;
					else
						return CaloricLevel.FAT;
				}));

		menuDiet.forEach((k, v) -> System.out.println(k + "  : " + v));

		// Multilevel grouping

		Map<Dish.Type, Map<CaloricLevel, List<Dish>>> menuDietByType = menu
				.stream().collect(groupingBy(Dish::getType, groupingBy(d -> {
					if (((Dish) d).getCalories() < 400)
						return CaloricLevel.DIET;
					else if (((Dish) d).getCalories() < 700)
						return CaloricLevel.NORMAL;
					else
						return CaloricLevel.FAT;
				})));

		menuDietByType.forEach((k, v) -> System.out.println(k + "  : " + v));

		// Collecting data in subgroups

		Map<Dish.Type, Long> countMenuType = menu.stream().collect(
				groupingBy(Dish::getType, counting()));

		countMenuType.forEach((k, v) -> System.out.println(k + "  : " + v));

		Comparator<Dish> comparCalories = Comparator
				.comparing(Dish::getCalories);

		// group dishby max
		Map<Dish.Type, Optional<Dish>> mostCaloricByType = menu.stream()
				.collect(
						Collectors.groupingBy(Dish::getType,
								maxBy(comparCalories)));

		mostCaloricByType.forEach((k, v) -> System.out.println(k + "  : " + v));

		// get type in map
		Map<Dish.Type, Dish> mostCaloricByType1 = menu.stream().collect(
				Collectors
						.groupingBy(
								Dish::getType,
								collectingAndThen(maxBy(comparCalories),
										Optional::get)));

		Map<Dish.Type, Integer> totalCaloriesbyType = menu.stream().collect(
				groupingBy(Dish::getType, summingInt(Dish::getCalories)));

		totalCaloriesbyType.forEach((s, v) -> System.out.println(s + " " + v));

		Map<Dish.Type, Set<CaloricLevel>> menubyCaloriesLevel = menu.stream()
				.collect(groupingBy(Dish::getType, mapping(dish -> {
					if (dish.getCalories() < 400)
						return CaloricLevel.DIET;
					else if (dish.getCalories() < 400)
						return CaloricLevel.NORMAL;
					else
						return CaloricLevel.FAT;
				}, Collectors.toSet())));

		menubyCaloriesLevel.forEach((k, v) -> System.out.println(k + " " + v));

		Map<Dish.Type, Map<Object, Optional<Dish>>> menuDietByType2 = menu
				.stream().collect(groupingBy(Dish::getType, groupingBy(d -> {
					if (((Dish) d).getCalories() < 400)
						return CaloricLevel.DIET;
					else if (((Dish) d).getCalories() < 700)
						return CaloricLevel.NORMAL;
					else
						return CaloricLevel.FAT;
				}, Collectors.maxBy(comparCalories))));

		menuDietByType2.forEach((k, v) -> System.out.println(k + " " + v));

		/*************************************************** Partitioning (having) ****************************************/

		Map<Boolean, List<String>> menuPartitioned = menu.stream().collect(
				partitioningBy(Dish::isVegetarian, mapping(d -> {
					return d.getName();
				}, Collectors.toList())));

		menuPartitioned.forEach((k, v) -> System.out.println(k + " " + v));

		Map<Boolean, Map<Dish.Type, List<Dish>>> menuPartitioned2 = menu
				.stream().collect(
						partitioningBy(Dish::isVegetarian,
								groupingBy(Dish::getType)));

		menuPartitioned2.forEach((k, v) -> System.out.println(k + " " + v));

		Map<Boolean, Map<Dish.Type, List<Object>>> menuPartitioned3 = menu
				.stream().collect(
						partitioningBy(Dish::isVegetarian,
								groupingBy(Dish::getType, mapping(d -> {
									return d.getName();
								}, toList()))));

		menuPartitioned3.forEach((k, v) -> System.out.println(k + " " + v));

		Map<Boolean, Dish> menuPartitioned4 = menu.stream()
				.collect(
						partitioningBy(
								Dish::isVegetarian,
								collectingAndThen(maxBy(comparCalories),
										Optional::get)));

		menuPartitioned4.forEach((k, v) -> System.out.println(k + " " + v));

		Map<Boolean, Map<Boolean, List<Dish>>> menuPartitioned5 = menu
				.stream()
				.collect(
						partitioningBy(
								Dish::isVegetarian,
								partitioningBy(d -> ((Dish) d).getCalories() > 500)));

		menuPartitioned5.forEach((k, v) -> System.out.println(k + " " + v));

		List<Dish> dishes = menu.stream().collect(new ToListCollector<Dish>());
		System.out.println("Mine");
		dishes.forEach((e) -> System.out.println(e));
		System.out.println("for Java");
		List<Dish> dishes1 = menu.stream().parallel()
				.collect(Collectors.toList());
		dishes1.forEach((e) -> System.out.println(e));

		System.out.println("Performing a customer  collector");

		// ///////////////////////////
		Map<Boolean, List<Integer>> list = IntStream.rangeClosed(2, 10).boxed()
				.collect(Collectors.partitioningBy(e -> isPrime(e)));
		// list.forEach((e,l ) -> System.out.print(e+" "+l));

		Map<Boolean, List<Integer>> listValues = IntStream.rangeClosed(2, 10)
				.boxed().collect(new PrimeNumberCollector());

		listValues.forEach((e, v) -> System.out.print(e + " " + v));

		System.setProperty(
				"java.util.concurrent.ForkJoinPool.common.parallelism", "12");

		System.out.println(Runtime.getRuntime().availableProcessors());
		
		System.out.println("Sequential sum done in: " + measureSumPerf(ParallelStreams::squentialSum, 10_000_000) + " msecs");
		
		System.out.println("Iterative sum done in: "  + measureSumPerf(ParallelStreams::iterativeSum, 10_000_000) + " msecs");
		
		System.out.println("Parallel sum done in: "   +	measureSumPerf(ParallelStreams::paralleleSum, 10_000_000) + " msecs" );

	}

	public static boolean isPrime(int candidate) {
		int candidateRoot = (int) Math.sqrt((double) candidate);
		return IntStream.rangeClosed(2, candidateRoot).noneMatch(
				i -> candidate % i == 0);
	}

	public static  long measureSumPerf(Function<Long, Long> adder, long n) {
		long fastest = Long.MAX_VALUE;
		for (int i = 0; i < 10; i++) {
			long start = System.nanoTime();
			long sum = adder.apply(n);
			long duration = (System.nanoTime() - start) / 1_000_000;
			System.out.println("Result: " + sum);
			if (duration < fastest)
				fastest = duration;
		}
		return fastest;
	}

}
