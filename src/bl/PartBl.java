package bl;

import java.util.ArrayList;
import java.util.ListIterator;

import be.BasicServiceBe;
import be.PartBe;
import be.PartNumberBe;
import dao.BasicServiceDao;
import dao.PartDao;

/**
 * @author Juan Jose Rivera
 *
 */
public class PartBl {
	
	public ArrayList<PartNumberBe> getPartByBasicServiceId(int id){
		ArrayList<PartNumberBe>  partNumBeList;
		PartDao partDao = new PartDao();
		
		partNumBeList = partDao.getPartByBasicServiceId(id)	;
		
		return partNumBeList;
		
		
	}
	
	public PartBe getPart(int id) {
		PartBe partBe = new PartBe();
		PartDao partDao = new PartDao();
		
		partBe = partDao.getPart(id);
		
		return partBe;

	}

}
