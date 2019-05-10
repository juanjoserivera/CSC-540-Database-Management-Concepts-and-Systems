package bl;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import be.DisplayOrderBe;
import be.DistributorBe;
import be.DistributorPartBe;
import be.DistributorWindowPartBe;
import be.PartBe;
import be.PartOrderBe;
import be.ServiceCenterInventoryBe;
import dao.PartOrderDao;
import dao.ServiceCenterInventoryDao;
import session.SESSION;

/**
 * @author Juan Jose Rivera
 *
 */
public class PartOrderBl {
	
	public ArrayList<DisplayOrderBe> displayAllOrders() {
		ArrayList<DisplayOrderBe> displayOrdertList = new ArrayList<DisplayOrderBe>();
		PartOrderDao partOrderDao = new PartOrderDao();
		
		displayOrdertList=partOrderDao.displayAllOrders();
		
		return displayOrdertList;

	}
	
	public DisplayOrderBe displayOrder(int id) {
		PartOrderDao partOrderDao = new PartOrderDao();
		DisplayOrderBe displayOrderBe;
		displayOrderBe = partOrderDao.getOrder(id);
		return displayOrderBe;
	}
	
	public int getMaxId() {
		PartOrderDao partOrderDao = new PartOrderDao();
		
		return partOrderDao.getMaxId();
		
	}

	
	private Date addDaystoDate(Date date, int days) {
		Date addedDate;
		
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_MONTH, days);
		addedDate=new Date(c.getTime().getTime());
			
		return addedDate;
		
	}
	
	public HashMap<String,String> insetPartOrderFromPartId(int partId, int qty) {
		PartBl partBl = new PartBl();
		DistributorBl distributorBl = new DistributorBl();
		PartOrderBl partOrderBl = new PartOrderBl();
		PartOrderBe partOrderBe = new PartOrderBe();
		DistributorBe distributorBe;
		PartBe partBe;
		Date currentDate =new Date(Calendar.getInstance().getTime().getTime());
		Date expectedDate ;
		HashMap<String,String> result;
		int maxId = getMaxId();
		
		partBe=partBl.getPart(partId);
		distributorBe=distributorBl.getDistributorByPartId(partId);
		expectedDate=addDaystoDate(currentDate,distributorBe.getDistributorPartList().get(0).getDeliveryWindow());
		
		
		partOrderBe.setFromSupplierId(distributorBe.getId());
		partOrderBe.setPartId(partId);
		partOrderBe.setToId(SESSION.getSession().getServiceCenterId());
		partOrderBe.setStatus("Pending");
		partOrderBe.setCost(qty*partBe.getUnitPrice());
		partOrderBe.setOrderDate(currentDate);
		partOrderBe.setExpectedDate(expectedDate);
		partOrderBe.setQty(qty);
		partOrderBe.setId(maxId+1);
		
		
		if(partOrderBl.insert(partOrderBe)) {
			result = new HashMap<String,String>();
			result.put("orderId", Integer.toString(maxId+1));
			result.put("expectedDeliveryDate",expectedDate.toString());
			return result;
			
		}
			
		return null;
		
		
		
	}
	
	public boolean insert(PartOrderBe partOrderBe) {
		PartOrderDao partOrderDao = new PartOrderDao();

		return partOrderDao.insert(partOrderBe);
	}
	
	public int getMinOrder(int partId,int serviceCenterId,int quantity) {
		ServiceCenterInventoryDao centerInventoryDao = new ServiceCenterInventoryDao();
		ServiceCenterInventoryBe centerInventoryBe;
		centerInventoryBe = centerInventoryDao.getPartDetailsByPartAndServiceCentre(partId, serviceCenterId);
		int min_order = centerInventoryBe.getMinOrderQty();
		if (min_order > quantity) {
			return min_order;
		}
		else {
			return quantity;
		}
	}
	
	public boolean createPartTransfer(int serviceCentreId,int partId,int orderQty) {
		PartOrderDao partOrderDao = new PartOrderDao();
		int targetServiceCenter = partOrderDao.getTargetServiceCentre(serviceCentreId,partId,orderQty);
		if (targetServiceCenter == 0) {
			return false;
		}
		else {
			LocalDate today = LocalDate.now();
			PartOrderBe partOrderBe = new PartOrderBe();
			partOrderBe.setId(partOrderDao.getMaxId() +1);
			partOrderBe.setQty(orderQty);
			partOrderBe.setOrderDate(Date.valueOf(today));
			partOrderBe.setExpectedDate(Date.valueOf(nextWorkingDate(today, 1)));
			partOrderBe.setCost(0);
			partOrderBe.setToId(serviceCentreId);
			partOrderBe.setPartId(partId);
			partOrderBe.setFromServiceId(targetServiceCenter);
			partOrderDao.insert(partOrderBe);
			return true;
		}
	}
	public boolean createPartOrder(int serviceCentreId,int partId,int orderQty) {
		PartOrderDao partOrderDao = new PartOrderDao();
		DistributorWindowPartBe distributorWindowPartBe;
		distributorWindowPartBe = partOrderDao.getDistributorDetailsByPart(partId);
		LocalDate today = LocalDate.now();
		PartOrderBe partOrderBe = new PartOrderBe();
		partOrderBe.setId(partOrderDao.getMaxId() +1);
		partOrderBe.setQty(orderQty);
		partOrderBe.setOrderDate(Date.valueOf(today));
		partOrderBe.setExpectedDate(Date.valueOf(nextWorkingDate(today, distributorWindowPartBe.getWindow())));
		partOrderBe.setCost(orderQty*distributorWindowPartBe.getUnitCost());
		partOrderBe.setToId(serviceCentreId);
		partOrderBe.setPartId(partId);
		partOrderBe.setFromSupplierId(distributorWindowPartBe.getDistributorId());
		partOrderDao.insert(partOrderBe);
		return true;
		
	}
	
	public LocalDate nextWorkingDate(LocalDate fromDate, int diff) {
		LocalDate nextDate = fromDate.plusDays(diff);
		while(nextDate.getDayOfWeek() == DayOfWeek.SATURDAY || nextDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
			nextDate = fromDate.plusDays(1);
		}
		return nextDate;
		
	}

}
