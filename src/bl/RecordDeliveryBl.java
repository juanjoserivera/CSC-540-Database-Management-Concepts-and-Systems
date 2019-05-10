package bl;

import java.util.ArrayList;
import java.util.Arrays;

import dao.PartOrderDao;

public class RecordDeliveryBl {
	public boolean recordDelivery(String orderIds) {
		ArrayList<Integer> orderIdList;
		orderIdList = stringtoIntegerList(orderIds);
		PartOrderDao dao = new PartOrderDao();
		for(int i =0; i<orderIdList.size();i++) {
			dao.recordDelivery(orderIdList.get(i));
		}
		dao.deliveryStoredProcedure();
		return true;
	}
	
	private ArrayList<Integer> stringtoIntegerList(String ordersIds){
		ArrayList<String> part_list = new ArrayList<String>(Arrays.asList(ordersIds.split("\\s*,\\s*")));
		ArrayList<Integer> part_ints = new ArrayList<Integer>();
		for(int i =0;i<part_list.size();i++) {
			part_ints.add(Integer.parseInt(part_list.get(i)));
		}
		return part_ints;

	}
}
