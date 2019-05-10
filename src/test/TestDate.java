package test;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import be.DistributorBe;
import be.PartBe;
import be.PartOrderBe;
import bl.DistributorBl;
import bl.PartBl;
import bl.PartOrderBl;
import dao.PartOrderDao;
import util.DateFormatUtil;

/**
 * @author Juan Jose Rivera
 *
 */
public class TestDate {

	public static void main(String[] args) {
		

//	    SimpleDateFormat formatter = new SimpleDateFormat("d-MMMM-yyyy");  
//	    
//		Date currentDate =new Date(Calendar.getInstance().getTime().getTime());
//
//		System.out.println(formatter.format(currentDate));

		//System.out.println(DateFormatUtil.formatStringDate("2018-11-14"));
			System.out.println(DateFormatUtil.formatDate(Date.valueOf("2018-11-14")));

	

	}

}
