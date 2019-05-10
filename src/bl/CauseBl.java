package bl;

import java.util.ArrayList;

import be.*;
import dao.*;

/**
 * @author Juan Jose Rivera
 *
 */
public class CauseBl {

	public ArrayList<CauseBe> getCausesByServId(int id){
		CauseDao causeDao = new CauseDao();
		ArrayList<CauseBe> causeBeList;
		
		causeBeList = causeDao.getCausesByServId(id);
		
		return causeBeList;
	}
}
