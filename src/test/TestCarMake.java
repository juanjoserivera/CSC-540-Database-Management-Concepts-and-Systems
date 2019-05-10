package test;

import be.BasicServiceBe;
import bl.NewCarModelBl;
import dao.NewCarModelDao;

public class TestCarMake {
	public static void main(String Args[]) {
	NewCarModelBl bl = new NewCarModelBl();
	bl.registerCarModel(
			"honda","Stupid",
			5000,6,"11:1,2:4",
			12000,12,"20:2,38:1",
			25000,24,"44:4");
//		NewCarModelDao newCarModelDao = new NewCarModelDao();
//		BasicServiceBe basicServiceBe;
//		basicServiceBe = newCarModelDao.reverseLookupByPartId(2);
	}
	
}
