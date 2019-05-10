package bl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

import be.*;
import dao.*;


/**
 * @author Juan Jose Rivera
 *
 */
public class BasicServiceBl {
	// partOrderBl. createPartTransfer | createPardOrder
	
	
	public BasicServiceBe get(int id) {
		BasicServiceDao basicServiceDao = new BasicServiceDao();
		ChargeRateDao chargeRateDao = new ChargeRateDao();
		PartDao partDao = new PartDao();

		
		BasicServiceBe basicServiceBe;
		PartBe partBe; 
		ChargeRateBe chargeRateBe;
		
		basicServiceBe = basicServiceDao.get(id);
		
		//TODO implement population, depends on implementation of Dao for Part and ChangeRate
		populatePartList(basicServiceBe);
		populateChangeRate(basicServiceBe);
		
		return basicServiceBe;
		
	}
	
	private boolean populatePartList(BasicServiceBe basicServiceBe) {
		PartBl partBl = new PartBl();
		basicServiceBe.setPartNumberBeList(partBl.getPartByBasicServiceId(basicServiceBe.getId())); 
		
		return true;
	}
	private boolean populateChangeRate(BasicServiceBe basicServiceBe) {
		ChargeRateBl chargeRateBl = new ChargeRateBl();
		basicServiceBe.setChargeRateBe(chargeRateBl.get(basicServiceBe.getChargeRateId()));
		
		return true;
	}
	
	public ArrayList<BasicServiceBe> getBasicServiceByRepId(int id){
		ArrayList<BasicServiceBe> basicServiceList;
		BasicServiceDao basicServiceDao = new BasicServiceDao();
		
		basicServiceList = basicServiceDao.getBasicServiceByRepId(id);
		
		ListIterator<BasicServiceBe> it = basicServiceList.listIterator();
		BasicServiceBe basicServiceBe;
		
		while(it.hasNext()){
			basicServiceBe = null;
			basicServiceBe = it.next();
			populateChangeRate(basicServiceBe);
			populatePartList(basicServiceBe);
			it.set(basicServiceBe);
			
		}
			
		
		return basicServiceList;
		
		
	}
	
	public ArrayList<BasicServiceBe> getBasicServiceByMaintId(int id){
		ArrayList<BasicServiceBe> basicServiceList;
		BasicServiceDao basicServiceDao = new BasicServiceDao();
		
		basicServiceList = basicServiceDao.getBasicServiceByMaintId(id);
		
		ListIterator<BasicServiceBe> it = basicServiceList.listIterator();
		BasicServiceBe basicServiceBe;
		
		while(it.hasNext()){
			basicServiceBe = null;
			basicServiceBe = it.next();
			populateChangeRate(basicServiceBe);
			populatePartList(basicServiceBe);
			it.set(basicServiceBe);
			
		}
			
		
		return basicServiceList;
		
		
	}
	public LocalDate getLastServiceDate(int basicServiceId, String carId) {
		WorkAppointmentDao appointmentDao = new WorkAppointmentDao();
		Date last_date = appointmentDao.getLastServiceDate( basicServiceId, carId);
		if (last_date == null) {
			return null;
		}
		else {
		return last_date.toLocalDate();
		}
	}

}
