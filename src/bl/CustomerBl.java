package bl;


import java.util.List;

import be.CarBe;
import be.CustomerBe;
import be.EmployeeBe;
import be.UsersBe;
import be.EmployeeBe.Role;
import dao.CarDao;
import dao.CustomerDao;
import dao.EmployeeDao;
import dao.RoleDao;
import dao.UsersDao;

public class CustomerBl {
	
	private static final String TABLE_FORMAT = "%25s%50s\n";

	
	static CustomerDao customerDao = new CustomerDao();
	public static CustomerBe getCustomer(String email) {
		return customerDao.getCustomerByEmail(email);
	}
	
	public static CustomerBe getCustomerByUserId(String userId) {
		return customerDao.getCustomerByUserId(userId);
	}
	
	public static void displayCustomerProfile(String email) {
		CustomerBe customer = getCustomer(email);
		if(customer == null) {
			System.out.println("Customer not found!");
			return;
		}
		System.out.print("---------------------------------------------------------------------------");
		System.out.println("---------------------------------------------------------------------------");
		System.out.format(TABLE_FORMAT, "Id", customer.getId());
		System.out.format(TABLE_FORMAT, "Name", customer.getName());
		System.out.format(TABLE_FORMAT, "Address", customer.getAddress());
		System.out.format(TABLE_FORMAT, "Email", customer.getEmail());
		System.out.format(TABLE_FORMAT, "Phone", customer.getPhoneNumber());
		List<CarBe> ownedCars = CarBl.getCarsOfCustomer(customer.getId());
		CarBl.displayCarsInfo(ownedCars);
		System.out.print("---------------------------------------------------------------------------");
		System.out.println("---------------------------------------------------------------------------");

	}
	
	public static String setCustomer(CustomerBe customerBe, String password) {
		RoleDao roleDao = new RoleDao();
		customerBe.setId(getMaxId()+1);
		UsersBe newUser = new UsersBe();
		newUser.setId(customerBe.getEmail());
		newUser.setRoleId(roleDao.getRoleId(Role.CUSTOMER.toString())); 
		newUser.setPassword(password);
		UsersBl.insertUser(newUser);
		
		customerBe.setUser(newUser);
		customerDao.insertCustomer(customerBe);		
		
		return newUser.getId();
	}
	
	public static int getMaxId() {
		return customerDao.getMaxId();
	}
	
	public static boolean updateName(String email,String newName) {
		return customerDao.updateName(newName, email);
	}
	
	public static boolean updateAddress(String email,String newAddress) {
		return customerDao.updateAddress(newAddress, email);
	}
	
	public static boolean updatePhone(String email,long newPhone) {
		return customerDao.updatePhone(newPhone, email);
	}
	
	public static boolean updatePassword(String email,String newPass) {
		UsersDao usersDao = new UsersDao();
		return usersDao.updatePassword(email, newPass);
	}
	
}
