package optionnal;

public class Person {

	private Car car;

	public Car getCar() {
		return car;
	}

	/* without optionnal */

	public String getCarInsuranceName(Person person) {

		if (person != null) {
			Car car = person.getCar();
			if (car != null) {
				Insurance insurance = car.getInsurance();
				if (insurance != null) {
					return insurance.getName();
				}
			}
		}

		return "Unkown";
	}

	/* without optionnal seconde methode */

	public String getCarInsuranceName1(Person person) {

		if (person == null) {
			return "Unkown";
		}
		
		Car car = person.getCar();
		if (car == null) {
			return "Unkown";
		}
		
		Insurance insurance = car.getInsurance();
		if (insurance == null) {
			return "Unkown";
		}
		
		return insurance.getName();

	}

}
