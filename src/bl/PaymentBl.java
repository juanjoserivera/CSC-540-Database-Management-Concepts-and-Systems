package bl;

import java.util.ArrayList;

import be.PaymentBe;
import dao.PaymentDao;

/**
 * @author Juan Jose Rivera
 *
 */
public class PaymentBl {
	
	public ArrayList<PaymentBe> getAllPaymentsByEmpId(int empId) {
		
		PaymentDao paymentDao = new PaymentDao();
		ArrayList<PaymentBe> paymentsArr = new ArrayList<PaymentBe>();
		paymentsArr = paymentDao.getAllPaymentsByEmpId(empId);
		populateEmployee(paymentsArr);
		
		return paymentsArr;

	}
	
	private boolean populateEmployee(ArrayList<PaymentBe> paymentsArr) {
		EmployeeBl employeeBl = new EmployeeBl();
		
		for (int i =0 ; i<paymentsArr.size(); i++) {
			paymentsArr.get(i).setEmployeeBe(employeeBl.getEmployee(paymentsArr.get(i).getEmployeeId()));
			
		}
		
		return true;
		
	}

}
