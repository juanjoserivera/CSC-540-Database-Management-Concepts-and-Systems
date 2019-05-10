package app.receptionist;

import java.text.ParseException;
import java.util.ArrayList;

import app.Page;
import be.CarBe;
import be.CarMakeBe;
import be.CarTypeBe;
import be.CustomerBe;
import bl.CarBl;
import bl.CarMakeBl;
import bl.CarTypeBl;
import bl.CustomerBl;
import util.DateFormatUtil;

public class RegisterCarPage extends Page{

	@Override
	public String[] getOptions() {
		return new String[] {"Register", "Cancel"};
	}

	@Override
	public Page[] getPages() {
		return new Page[] {Pages.RECEPTIONIST};
	}

	public void open() {
		CarBe car = new CarBe();
		System.out.println("Enter the car details:");
		System.out.println("Customer Email:");
		String email = readString();
		updateCarsOwnerId(car, email);
		System.out.println("Licence Plate:");
		car.setPlateNumber(readString());
		System.out.println("Purchase Date:");
		updatePurchaseDate(car);
		System.out.println("Car Make:");
		updateCarMake(car);
		System.out.println("Car Model:");
		updateCarModel(car);
		System.out.println("Year:");
		car.setYear(readNumber());
		System.out.println("Current Mileage:");
		car.setLastServiceMileage(readNumber());
		System.out.println("Last Service Date:");
		updateLastServiceDate(car);
		displayOptions();
		int option = readOption();
		if(option == 1) {
			if(CarBl.insertCar(car)) {
				System.out.println("Car successfully registered");
			} else System.out.println("Car registration failed!");
		} 
		Pages.RECEPTIONIST.open();
		

	}
	
	private void updateLastServiceDate(CarBe car) {
		
		try {
			car.setLastServiceDate(DateFormatUtil.getDateFromString(readDate()));
		} catch (ParseException e) {
			
			System.out.println("Error! Invalid Date");
			updateLastServiceDate(car);
		}
	}

	private void updateCarMake(CarBe car) {
		java.util.List<CarMakeBe> carMakes = CarMakeBl.getCarMake();
		System.out.println("Choose a car make:");

		for(int i = 0; i < carMakes.size(); i++) {
			System.out.println(carMakes.get(i).getId() + ". " + carMakes.get(i).getName());
		}
		int option = readNumber();
		if(option > carMakes.size() || option < 1) {
			System.out.println("Invalid choice!!");
			updateCarMake(car);
		}
		car.getCarType().getCarMake().setId(option);
	}
	
	private void updateCarModel(CarBe car) {
		java.util.List<CarTypeBe> carTypes = CarTypeBl.getCarTypes(car.getCarType().getCarMake().getId());
		System.out.println("Choose a car model:");

		for(int i = 0; i < carTypes.size(); i++) {
			System.out.println(i+1 + ". " + carTypes.get(i).getModel());
		}
		int option = readNumber();
		if(option > carTypes.size() || option < 1) {
			System.out.println("Invalid choice!!");
			updateCarModel(car);
		}
		
		car.setCarType(carTypes.get(option-1));
	}

	public void updatePurchaseDate(CarBe car) {
		try {
			car.setPurchaseDate(DateFormatUtil.getDateFromString(readDate()));
		} catch (ParseException e) {
			
			System.out.println("Error! Invalid Date");
			updatePurchaseDate(car);
		}
	}
	
	public void updateCarsOwnerId(CarBe car, String email) {
		car.setCustomerId(CustomerBl.getCustomer(email).getId());
	}
}
