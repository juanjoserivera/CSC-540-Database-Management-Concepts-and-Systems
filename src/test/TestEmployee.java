package test;

import be.EmployeeBe;
import bl.EmployeeBl;

public class TestEmployee {
	public static void main(String args[]) {
		EmployeeBe employeeBe = EmployeeBl.getEmployeebyUserId("1");
		if(employeeBe == null) {
			System.out.println("Failed");
		}
	}
}
