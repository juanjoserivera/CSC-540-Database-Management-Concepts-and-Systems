package bl;

import dao.CarMakeDao;

import java.util.ArrayList;
import java.util.List;

import be.CarMakeBe;


public class CarMakeBl {

	public static List<CarMakeBe> getCarMake() {
		CarMakeDao carMakeDao = new CarMakeDao();
		return carMakeDao.getCarMakes();
		
	}
}
