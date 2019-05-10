package app.manager;

import java.util.ArrayList;

import app.Page;
import be.DisplayOrderBe;
import be.PaymentBe;
import bl.EmployeeBl;
import bl.PaymentBl;
import util.DateFormatUtil;

public class PayrollPage extends Page {

	public String[] getOptions() {
		return new String[] { "Go Back", };
	}

	public Page[] getPages() {
		return new Page[] { Pages.MANAGER };
	}

	public void open() {
		System.out.println("Enter Employee ID:");
		int employeeId = readNumber();
		displayPayroll(employeeId);
		super.open();
	}
	
	private void displayPayroll(int employeeId) {
		// find in db
		EmployeeBl employeeBl = new EmployeeBl();
		PaymentBl paymentBl = new PaymentBl();
		ArrayList<PaymentBe> paymentBeList = null;
		
		if(employeeBl.getEmployee(employeeId)==null) {
			System.out.println("Employee was not found!");

		}else {
			paymentBeList=paymentBl.getAllPaymentsByEmpId(employeeId);
			
			printHeader();
			for(int i=0;i<paymentBeList.size();i++) {
				printRecord(paymentBeList.get(i));
				

			}
			
		}
	}
	
	
	private void printRecord(PaymentBe paymentBe) {
		
		System.out.format("%12s%28s%15s%30s%12s%12s%12s%12s%12s\n", 
				DateFormatUtil.formatDate(paymentBe.getPaymentDate()),
				DateFormatUtil.formatDate(paymentBe.getStartDate())+" : "+DateFormatUtil.formatDate(paymentBe.getEndDate()),
				paymentBe.getEmployeeId(),
				paymentBe.getEmployeeBe().getName(),
				paymentBe.getCompensation(),
				paymentBe.getCompFrequency(),
				paymentBe.getHoursOrDaysWorked(),
				paymentBe.getEarnings(),
				paymentBe.getEarningsYearToDate()

				);
	}
	private void printHeader() {
		System.out.format("%12s%28s%15s%30s%12s%12s%12s%12s%12s\n", 
				"PAY DATE",
				"PAY PERIOD",
				"EMPLOYEE ID",
				"EMPLOYEE NAME",
				"COMP",
				"COMP FREQ",
				"UNIT",
				"EARN(CUR)",
				"EARN(YTD)"
				);
	    System.out.format("%s\n", "--------------------------------------------------"
	    		+ "--------------------------------------------------"
	    		+ "--------------------------------------------------");
	}

}
