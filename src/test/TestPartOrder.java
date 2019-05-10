package test;

import bl.PartOrderBl;
import dao.PartOrderDao;

public class TestPartOrder {
	public static void main(String Args[]) {
		PartOrderBl pOrderBl = new PartOrderBl();
		boolean x = pOrderBl.createPartTransfer(1, 35, 2);
		if(x == true) {
		System.out.println("Transfer Successfull");
		}
	}

}
