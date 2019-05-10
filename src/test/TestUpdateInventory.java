package test;

import java.util.HashMap;


import dao.UpdateInventoryDao;
public class TestUpdateInventory {
	public static void main(String Args[]) {
	UpdateInventoryDao uDao = new UpdateInventoryDao();
	HashMap<Integer,Integer> valuesParts;
	valuesParts = uDao.getPartstoUpdate();
	System.out.println(valuesParts.size());
	}
}
