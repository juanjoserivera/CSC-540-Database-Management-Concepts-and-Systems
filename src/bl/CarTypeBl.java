package bl;

import java.util.ArrayList;
import java.util.List;

import be.CarTypeBe;
import dao.CarMakeDao;
import dao.CarTypeDao;

/**
 * @author Juan Jose Rivera
 *
 */
public class CarTypeBl {

	public CarTypeBe getCarType(int id) {
		CarTypeBe carTypeBe = null;
		CarTypeDao carTypeDao = new CarTypeDao();
		CarMakeDao carMakeDao = new CarMakeDao();

		carTypeBe = carTypeDao.getCarType(id);
		
		carTypeBe.setCarMake(carMakeDao.getCarMake(carTypeBe.getCarMake().getId()));
		

		return carTypeBe;

	}

	public ArrayList<CarTypeBe> getAllCarType() {
		CarTypeDao carTypeDao = new CarTypeDao();
		CarMakeDao carMakeDao = new CarMakeDao();
		ArrayList<CarTypeBe> carTypeBeArr = null;

		carTypeBeArr = carTypeDao.getAllCarTypes();
		
		for (int i =0 ; i<carTypeBeArr.size(); i++) {
			carTypeBeArr.get(i).setCarMake(carMakeDao.getCarMake(carTypeBeArr.get(i).getCarMake().getId()));
			
		}

		return carTypeBeArr;

	}

	public boolean insertCarType(CarTypeBe carType) {

		CarTypeDao carTypeDao = new CarTypeDao();

		return carTypeDao.insertCarType(carType);
	}

	public boolean deleteCarType(int id) {

		CarTypeDao carTypeDao = new CarTypeDao();

		return carTypeDao.deleteCarType(id);
	}

	public static List<CarTypeBe> getCarTypes(int makeId) {
		CarTypeDao carTypeDao = new CarTypeDao();

		return carTypeDao.getCarTypeByMake(makeId);
	}

}
