package bl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

import be.NotificationBe;
import be.PartOrderBe;
import dao.NotificationDao;
import dao.DistributorDao;
import dao.ServiceCentreDao;

public class NotificationBl {
	public ArrayList<NotificationBe> getAllNotifications(){
		ArrayList<NotificationBe> notificationBeList = new ArrayList<NotificationBe>();
		NotificationDao notificationDao = new NotificationDao();
		DistributorDao distributorDao = new DistributorDao();
		ServiceCentreDao centreDao = new ServiceCentreDao();
		notificationBeList = notificationDao.getAllNotifications();
		for(int i = 0; i< notificationBeList.size();i++)
		{
			NotificationBe nbe = new NotificationBe();
			PartOrderBe pbe = new PartOrderBe();
			nbe = notificationBeList.get(i);
			pbe = nbe.getPartOrderBe();
			int sid =  pbe.getFromServiceId();
			int did = pbe.getFromSupplierId();
			Date expected = pbe.getExpectedDate();
			Date delivered = pbe.getDeliveryDate();
			if(sid == 0 && did!= 0) {
				String fromName = distributorDao.getDistributorName(pbe.getFromSupplierId());
				nbe.setFromName(fromName);
			}
			if(sid != 0 && did == 0) {
				String fromName = centreDao.getServiceCentreName(pbe.getFromServiceId());
				nbe.setFromName(fromName);
			}
			int delay = NotificationBl.getWorkingDays(expected, delivered);
			nbe.setDelay(delay);
			notificationBeList.set(i, nbe);
		}
		return notificationBeList;
	}
	public static int getWorkingDays(Date startDate, Date endDate) {
	    Calendar startCal = Calendar.getInstance();
	    startCal.setTime(startDate);        
	    Calendar endCal = Calendar.getInstance();
	    if(endDate!= null) {
	    	endCal.setTime(endDate);
	    }
	    int workDays = 0;

	    //Return 0 if start and end are the same
	    if (startCal.getTimeInMillis() == endCal.getTimeInMillis()) {
	        return 0;
	    }

	    if (startCal.getTimeInMillis() > endCal.getTimeInMillis()) {
	        startCal.setTime(endDate);
	        endCal.setTime(startDate);
	    }

	    do {
	       //excluding start date
	        startCal.add(Calendar.DAY_OF_MONTH, 1);
	        if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
	            ++workDays;
	        }
	    } while (startCal.getTimeInMillis() < endCal.getTimeInMillis()); //excluding end date

	    return workDays;
	}
	
}
