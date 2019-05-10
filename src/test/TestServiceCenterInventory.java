package test;

import bl.ServiceCenterInventoryBl;

/**
 * @author Juan Jose Rivera
 *
 */
public class TestServiceCenterInventory {

	public static void main(String[] args) {
		
		ServiceCenterInventoryBl serviceCenterInventoryBl = new ServiceCenterInventoryBl();
		
		System.out.println(serviceCenterInventoryBl.getServCentPartInvbyServCentId(1));

	}

}
