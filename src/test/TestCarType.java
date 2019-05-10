package test;

import be.CarTypeBe;
import bl.CarTypeBl;

/**
 * @author Juan Jose Rivera
 *
 */
public class TestCarType {

	public static void main(String[] args) {
		
		CarTypeBl carTypeBl = new CarTypeBl();
		
		System.out.println( carTypeBl.getCarType(1));
		
		System.out.println(carTypeBl.getAllCarType());
		
//		CarTypeBe carTypeBe = new CarTypeBe();
//		
//		carTypeBe.setId(3);
//		carTypeBe.setMake("TOYOTA");
//		carTypeBe.setModel("COROLLA");
//		carTypeBe.setYear(2017);
//		
//		carTypeBl.insertCarType(carTypeBe);
//		
//		System.out.println(carTypeBl.getAllCarType());
//		
//		carTypeBl.deleteCarType(3);
//		
//		System.out.println(carTypeBl.getAllCarType());


		
	}

}
