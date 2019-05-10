package bl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import be.BasicServiceBe;
import be.CarMakeBe;
import be.CarTypeBe;
import be.MaintenanceServiceBe;
import dao.BasicServiceDao;
import dao.CarMakeDao;
import dao.CarTypeDao;
import dao.MaintenanceServiceDao;
import dao.NewCarModelDao;

public class NewCarModelBl {
	
	public boolean registerCarModel(
			String make,String model,
			int miles_a,int months_a,String parts_a,
			int miles_b,int months_b,String parts_b,
			int miles_c,int months_c,String parts_c) 
	{
//		Remember that parts_b = parts_b+','+parts_a;
//		Remember that parts_c = parts_c+','+parts_b;
		CarMakeDao carMakeDao = new CarMakeDao();
		CarMakeBe carMakeBe;
		// GET THE CAR MAKE ID FROM CAR MAKE NAME
		carMakeBe = carMakeDao.getCarMakeId(make);
		CarTypeBe carTypeBe = new CarTypeBe();
		CarTypeDao carTypeDao = new CarTypeDao();
		//GET A NEW ID FOR THE MODEL
		int newCarTypeId = carTypeDao.getMaxId()+1;
		carTypeBe.setCarMake(carMakeBe);
		carTypeBe.setId(newCarTypeId);
		carTypeBe.setModel(model);
		//CREATE THE MODEL
		carTypeDao.insertCarType(carTypeBe);
		int msid_a = createMaintenanceService(newCarTypeId, miles_a, months_a, "A");
		int msid_b = createMaintenanceService(newCarTypeId, miles_b, months_b, "B");
		int msid_c = createMaintenanceService(newCarTypeId, miles_c, months_c, "C");
		ArrayList<Integer> bs_a = createBasicServiceList(newCarTypeId,parts_a);
		ArrayList<Integer> bs_b = createBasicServiceList(newCarTypeId,parts_b);
		ArrayList<Integer> bs_c = createBasicServiceList(newCarTypeId,parts_c);
		bs_b.addAll(bs_a);
		bs_c.addAll(bs_b);
		createBSXMSMapping(msid_a,bs_a);
		createBSXMSMapping(msid_b,bs_b);
		createBSXMSMapping(msid_c,bs_c);
		return true;
	}
	
	private void createBSXMSMapping(int msid, ArrayList<Integer> bsIdList) {
		NewCarModelDao newCarModelDao = new NewCarModelDao();
		for(int i=0;i<bsIdList.size();i++) {
			newCarModelDao.createBSXMSMapping(msid, bsIdList.get(i));
		}
		
	}

	private ArrayList<Integer> createBasicServiceList(int carTypeId, String parts) {
		HashMap<Integer, Integer> part_map;
		part_map = getPartCount(parts);
		ArrayList<Integer> bsIdList = new ArrayList<Integer>();
		NewCarModelDao newCarModelDao = new NewCarModelDao();
		BasicServiceDao basicServiceDao = new BasicServiceDao();
		for(Map.Entry<Integer, Integer> entry : part_map.entrySet()) {
			BasicServiceBe basicServiceBe;
			basicServiceBe = newCarModelDao.reverseLookupByPartId(entry.getKey());
			int newBSId = basicServiceDao.getMaxId()+1;
			basicServiceBe.setId(newBSId);
			basicServiceBe.setCarTypeId(carTypeId);
			basicServiceDao.put(basicServiceBe);
			newCarModelDao.createPARTXBSMapping(newBSId, entry.getKey(), entry.getValue());
			bsIdList.add(newBSId);
		}
		return bsIdList;
	}
	
	private HashMap<Integer, Integer> getPartCount(String parts){
		// Split [partid:count] by ,
		ArrayList<String> part_list = new ArrayList<String>(Arrays.asList(parts.split("\\s*,\\s*")));
		HashMap<Integer,Integer> part_map= new HashMap<Integer,Integer>();
		for(int i =0;i<part_list.size();i++) {
			String part = part_list.get(i);
			// Split partid:count by :
			String[] part_count = part.split("\\s*:\\s*",2);
			part_map.put(Integer.parseInt(part_count[0]), Integer.parseInt(part_count[1]));
		}
		return part_map;
	}

	private int createMaintenanceService(int carTypeId,int miles,int months,String type) {
		MaintenanceServiceDao maintenanceServiceDao = new MaintenanceServiceDao();
		int msid = maintenanceServiceDao.getMaxId()+1;
		MaintenanceServiceBe maintenanceServiceBe = new MaintenanceServiceBe();
		maintenanceServiceBe.setId(msid);
		maintenanceServiceBe.setMileage(miles);
		maintenanceServiceBe.setNumberOfMonths(months);
		maintenanceServiceBe.setCarTypeId(carTypeId);
		maintenanceServiceBe.setType(type);
		maintenanceServiceDao.insertMaintenanceService(maintenanceServiceBe);
		return msid;
	}
	
}
