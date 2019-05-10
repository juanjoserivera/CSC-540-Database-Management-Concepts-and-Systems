package be;

/**
 * @author Juan Jose Rivera
 *
 */
public class SessionBe {
	
	private String userId;
	private int customerId;
	private int employeeId;
	private int serviceCenterId;
	private String roleName;
	private int roleId;
	private String fullName;
	private String customerEmail;
	
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public int getServiceCenterId() {
		return serviceCenterId;
	}
	public void setServiceCenterId(int serviceCenterId) {
		this.serviceCenterId = serviceCenterId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	@Override
	public String toString() {
		return "SessionBe [userId=" + userId + ", customerId=" + customerId + ", employeeId=" + employeeId
				+ ", serviceCenterId=" + serviceCenterId + ", roleName=" + roleName + ", roleId=" + roleId
				+ ", fullName=" + fullName + ", customerEmail=" + customerEmail + "]";
	}
	
	
	
	

}
