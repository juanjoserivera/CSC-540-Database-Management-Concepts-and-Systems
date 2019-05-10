package bl;

import be.EmployeeBe;
import be.UsersBe;
import be.EmployeeBe.Role;
import dao.EmployeeDao;
import dao.RoleDao;
import dao.ServiceCentreDao;
import dao.UsersDao;

public class EmployeeBl {
	private static final String TABLE_FORMAT = "%25s%50s\n";
//	private static final String TABLE_FORMAT_HEADER = "%5s%20s%30s%30s%22s%20s%18s%25s%15s%15s\n";

	public static EmployeeBe getEmployee(int id) {
		EmployeeDao employeeDao = new EmployeeDao();
		return employeeDao.getEmployee(id);
	}
	
	public static boolean setEmployee(EmployeeBe employee) {
		EmployeeDao employeeDao = new EmployeeDao();
		RoleDao roleDao = new RoleDao();
		employee.setId(getMaxId()+1);
		UsersBe newUser = new UsersBe();
		int roleId = roleDao.getRoleId(employee.getRole().toString());
		newUser.setId(String.valueOf(employee.getId()));
		newUser.setRoleId(roleId);
		newUser.setPassword("12345678");
		UsersBl.insertUser(newUser);
		employee.setUser(newUser);
		return employeeDao.insertEmployee(employee);		
	}
	
	public static EmployeeBe getEmployeebyUserId(String userId) {
		EmployeeDao employeeDao = new EmployeeDao();
		return employeeDao.getEmployeebyUserId(userId);
	}
	
	public static int getMaxId() {
		EmployeeDao employeeDao = new EmployeeDao();
		return employeeDao.getMaxId();
	}
	
	public static boolean updateName(int employeeId,String newName) {
		EmployeeDao employeeDao = new EmployeeDao();
		return employeeDao.updateName(newName, employeeId);
	}
	
	public static boolean updateAddress(int employeeId,String newAddress) {
		EmployeeDao employeeDao = new EmployeeDao();
		return employeeDao.updateAddress(newAddress, employeeId);
	}
	
	public static boolean updatePhone(int employeeId,long newPhone) {
		EmployeeDao employeeDao = new EmployeeDao();
		return employeeDao.updatePhone(newPhone, employeeId);
	}
	
	public static boolean updateEmail(int employeeId,String newEmail) {
		EmployeeDao employeeDao = new EmployeeDao();
		return employeeDao.updateEmail(newEmail, employeeId);
	}
	
	public static boolean updatePassword(String userName, String newPass) {
		return (new UsersDao()).updatePassword(userName, newPass);
	}
	
	public static void displayEmployeeProfile(EmployeeBe employee) {
		System.out.println("---------------------------------------------------------------------------------");
		System.out.format(TABLE_FORMAT,"Id",employee.getId());
		System.out.format(TABLE_FORMAT,"Name",employee.getName());
		System.out.format(TABLE_FORMAT,"Address",employee.getAddress());
		System.out.format(TABLE_FORMAT,"Email",employee.getEmail());
		System.out.format(TABLE_FORMAT,"Phone",employee.getPhoneNumber());
		System.out.format(TABLE_FORMAT,"Service Center",(new ServiceCentreDao()).getServiceCentreName(employee.getServiceCenterId()));
		System.out.format(TABLE_FORMAT,"Role",employee.getRole());
		System.out.format(TABLE_FORMAT,"Start Date",employee.getStartDate());
		System.out.format(TABLE_FORMAT,"Compensation",employee.getWages());
		System.out.format(TABLE_FORMAT,"Compensation Frequency",employee.getFrequency());
		System.out.println("----------------------------------------------------------------------------------");

	}
}
