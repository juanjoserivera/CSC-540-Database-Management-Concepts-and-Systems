package bl;

import be.CustomerBe;
import be.EmployeeBe;
import be.EmployeeBe.Role;
import be.SessionBe;
import be.UsersBe;
import dao.RoleDao;
import dao.UsersDao;

public class UsersBl {
	private static UsersDao usersDao = new UsersDao();
	public static void insertUser(UsersBe user) {
		user.setId(user.getId());
		usersDao.insertUser(user);
	}
	
	public static boolean updatePassword(String username, String newPassword) {
		return usersDao.updatePassword(username, newPassword);
	}
	
	public static SessionBe getUserInfoByUserId(String username) {
		SessionBe sessionBe = new SessionBe();
		CustomerBl customerBl = new CustomerBl();
		EmployeeBe employeeBe;
		
		employeeBe = EmployeeBl.getEmployeebyUserId(username);
		
		if (employeeBe !=  null) {
			RoleDao roleDao = new RoleDao();

			sessionBe.setCustomerId(0);
			sessionBe.setEmployeeId(employeeBe.getId());
			sessionBe.setRoleName(employeeBe.getRole().toString());
			sessionBe.setRoleId(roleDao.getRoleId(employeeBe.getRole().toString()));
			sessionBe.setServiceCenterId(employeeBe.getServiceCenterId());
			sessionBe.setUserId(username);
			sessionBe.setFullName(employeeBe.getName());
			
			
		}else {
			CustomerBe customerBe;
			customerBe = CustomerBl.getCustomerByUserId(username);
			if (customerBe !=null ) {
				RoleDao roleDao = new RoleDao();

				
				sessionBe.setCustomerId(customerBe.getId());
				sessionBe.setEmployeeId(0);
				sessionBe.setRoleName(Role.CUSTOMER.toString());
				sessionBe.setRoleId(roleDao.getRoleId(Role.CUSTOMER.toString()));
				sessionBe.setServiceCenterId(customerBe.getServiceCenterId());
				sessionBe.setUserId(username);
				sessionBe.setFullName(customerBe.getName());
				sessionBe.setCustomerEmail(customerBe.getEmail());
				sessionBe.setServiceCenterId(customerBe.getServiceCenterId());
			}
			
		}
			
			
		return sessionBe;
	}
	
	public boolean login(String username, String password) {
		UsersDao usersDao = new UsersDao();
		
		return usersDao.login(username, password);
	}
	
	public static String generateUserId(int id, String role) {
		StringBuilder userName = new StringBuilder();
		userName.append(role.charAt(0));

		userName.append(id);
		return userName.toString();
		
	}
}
