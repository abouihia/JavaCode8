package optionnal;

import java.util.Optional;

public class PeronOptional {

	private  CarOptional car;

	public Optional<CarOptional> getCar() {
		return Optional.ofNullable(car);
	}

	public String getCarInsuranceName(Person person) {

		// create Empty Optional
		Optional<Car> optCar = Optional.empty();

		// Optional from a non-null value // if car is null it throwinf NPE
		Car car = null;
		Optional<Car> optCar1 = Optional.of(car);

		// Optional form null : object that may hold a null value
		// if car were null , the resulting optional object would be empty
		Optional<Car> optCar2 = Optional.ofNullable(car);

		// Extracting and transforming value form Optional
		// chainning Optional Objects with flatMap.
		Optional<String> insuranceName = optCar.map(Car::getInsurance).map(
				Insurance::getName);

		Optional<Person> optPerson = Optional.of(person);
		Optional<String> name = optPerson.map(Person::getCar)
				.map(Car::getInsurance).map(Insurance::getName);

		return name.get();
	}

	// find a car Insurance company name with optionals
	public String getCarInsuranceNameFlatMap(Optional<PeronOptional> person) {
		return person.flatMap(PeronOptional::getCar)
				.flatMap(CarOptional::getInsurance).map(Insurance::getName)
				.orElse("Unknow");
	}
	public String getCarInsuranceNameFlatMap2(Optional<PeronOptional> person) {
		return  (String) person.flatMap(p -> getCar().flatMap(c -> c.getInsurance().map(i -> i.getName()))).orElse("Unknow");
		
	}

	public static void main(String[] args) {

		PeronOptional lOptional = new PeronOptional();

		 System.out.println(lOptional.getCarInsuranceNameFlatMap(Optional.of(lOptional)));
		 System.out.println(lOptional.getCarInsuranceNameFlatMap2(Optional.of(lOptional)));

	/*	Optional<String> s = Optional.of("input");
		System.out.println(s.map(PeronOptional::getOutput));
		System.out.println(s.flatMap(PeronOptional::getOutputOpt));*/

	}

	static Optional<String> getOutputOpt(String input) {
		return input == null ? Optional.empty() : Optional.of("output for "
				+ input);
	}

	static String getOutput(String input) {
		return input == null ? null : "output for " + input;
	}

}
