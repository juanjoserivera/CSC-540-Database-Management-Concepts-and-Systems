package bl;
import java.util.ArrayList;

import be.ServiceDetailsBe;
import dao.ServiceDetailsDao;

public class ServiceDetailsBl {
	public ArrayList<ServiceDetailsBe> getMS(){
		ArrayList<ServiceDetailsBe> arrayList;
		ServiceDetailsDao dao = new ServiceDetailsDao();
		arrayList = dao.getAllMS();
		return arrayList;
	}
}
