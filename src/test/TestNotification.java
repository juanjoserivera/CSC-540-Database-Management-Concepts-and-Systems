package test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

import be.NotificationBe;
import bl.NotificationBl;;
public class TestNotification {
	public static void main(String Args[]) {
		ArrayList<NotificationBe> x = new ArrayList<NotificationBe>();
		NotificationBl b = new NotificationBl();
		x = b.getAllNotifications();
		
		for (int i =0; i<x.size();i++) {
			NotificationBe y = x.get(i);
			System.out.println(y.toString());
		}	
//		int m =NotificationBl.getWorkingDays(y.getPartOrderBe().getExpectedDate(), y.getPartOrderBe().getDeliveryDate());
//	    System.out.println(m);
	}
}
