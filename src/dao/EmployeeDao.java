package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;

import be.EmployeeBe;
import be.EmployeeBe.Role;
import be.EmployeeBe.WorkFrequency;

import conn.DBConnection;

public class EmployeeDao {
	public EmployeeBe getEmployee(int id) {
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			stmt = connection.createStatement();

			rs = stmt.executeQuery("SELECT * FROM EMPLOYEE WHERE id=" + id);
			if (rs.next()) {
				EmployeeBe employee = new EmployeeBe();
				employee.setId(id);
				employee.setServiceCenterId(rs.getInt("SERVICE_CENTER_ID"));
				employee.setAddress(rs.getString("ADDRESS"));
				employee.setEmail(rs.getString("EMAIL"));
				employee.setPhoneNumber(rs.getLong("PHONE"));
				employee.setWages(rs.getInt("WAGES"));
				employee.setFrequency(WorkFrequency.valueOf(rs.getString("FREQUENCY")));
				employee.setName(rs.getString("NAME"));
				employee.setStartDate(rs.getDate("START_DATE"));
				employee.setRole(Role.valueOf(rs.getString("ROLE")));
				return employee;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return null;

	}
	
	public EmployeeBe getEmployeebyUserId(String user_id) {
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			stmt = connection.createStatement();

			rs = stmt.executeQuery("SELECT * FROM EMPLOYEE WHERE USER_ID=\'" + user_id + "\'");
			if (rs.next()) {
				EmployeeBe employee = new EmployeeBe();
				employee.setId(rs.getInt("ID"));
				employee.setServiceCenterId(rs.getInt("SERVICE_CENTER_ID"));
				employee.setAddress(rs.getString("ADDRESS"));
				employee.setEmail(rs.getString("EMAIL"));
				employee.setPhoneNumber(rs.getLong("PHONE"));
				employee.setWages(rs.getInt("WAGES"));
				employee.setFrequency(WorkFrequency.valueOf(rs.getString("FREQUENCY")));
				employee.setName(rs.getString("NAME"));
				employee.setStartDate(rs.getDate("START_DATE"));
				employee.setRole(Role.valueOf(rs.getString("ROLE")));
				employee.getUser().setId(user_id);
				return employee;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return null;

	}
	
	public boolean updateName(String newVal,int employeeId) {
		return updateEmployee("NAME", newVal, employeeId);
	}
	
	public boolean updateAddress(String newVal,int employeeId) {
		return updateEmployee("ADDRESS", newVal, employeeId);
	}
	
	public boolean updateEmail(String newVal,int employeeId) {
		return updateEmployee("EMAIL", newVal, employeeId);
	}
	
	public boolean updatePhone(long newVal,int employeeId) {
		return updateEmployee("PHONE", newVal, employeeId);
	}
	
	public boolean updateCompensation(int newVal,int employeeId) {
		return updateEmployee("COMPENSATION", newVal, employeeId);
	}

	public boolean updateEmployee(String column,Object newVal,int employeeId) {
		
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			StringBuilder updateStmt = new StringBuilder("UPDATE EMPLOYEE SET " + column + " = ");
			if (newVal instanceof String) {
				updateStmt.append(" \'" + (String)newVal + "\'"); 
			} else if(newVal instanceof Long){
				updateStmt.append((Long)newVal);
			} else if(newVal instanceof Integer){
				updateStmt.append((Integer)newVal); 
			} else if(newVal instanceof Float){
				updateStmt.append((Float)newVal);
			}else if(newVal instanceof Double){
				updateStmt.append((Double)newVal);
			}
			updateStmt.append(" where ID = " + employeeId);
			ps = connection.prepareStatement(updateStmt.toString());
			int i = ps.executeUpdate();
			if (i == 1) {
				return true;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				ps.close();
				

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return false;

//		Connection connection = null;
//		ResultSet rs = null;
//		Statement stmt = null;
//		try {
//			connection = DBConnection.getInstance().getConnection();
//			stmt = connection.createStatement();
//			String updateStmt = "UPDATE EMPLOYEE SET " + column + " = ";
//			if (newVal instanceof String) {
//				updateStmt += " \'" + (String)newVal + "\' where ID = " + employeeId; 
//			} else {
//				updateStmt += (String)newVal + " where ID = " + employeeId; 
//			}
//			rs = stmt.executeQuery(updateStmt);
//			ArrayList<EmployeeBe> employeeArr = new ArrayList<EmployeeBe>();
//
//			while (rs.next()) {
//				EmployeeBe employee = new EmployeeBe();
//				employee.setId(rs.getInt("ID"));
//				employee.setServiceCenterId(rs.getInt("SERVICE_CENTER_ID"));
//				employee.setAddress(rs.getString("ADDRESS"));
//				employee.setEmail(rs.getString("EMAIL"));
//				employee.setPhoneNumber(rs.getLong("PHONE_NUMBER"));
//				employee.setWages(rs.getInt("WAGES"));
//				employee.setFrequency(WorkFrequency.valueOf(rs.getString("FREQUNECY")));
//				employee.setName(rs.getString("NAME"));
//				employee.setStartDate(rs.getDate("START_DATE"));
//				employee.setRole(Role.valueOf(rs.getString("ROLE")));
//				employeeArr.add(employee);
//			}
//
//			return employeeArr;
//		} catch (SQLException ex) {
//			ex.printStackTrace();
//		} finally {
//			try {
//				rs.close();
//				stmt.close();
//				
//
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//
//		return null;

	}
	public ArrayList<EmployeeBe> getAllEmployees() {
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			stmt = connection.createStatement();

			rs = stmt.executeQuery("SELECT * FROM EMPLOYEE");
			ArrayList<EmployeeBe> employeeArr = new ArrayList<EmployeeBe>();

			while (rs.next()) {
				EmployeeBe employee = new EmployeeBe();
				employee.setId(rs.getInt("ID"));
				employee.setServiceCenterId(rs.getInt("SERVICE_CENTER_ID"));
				employee.setAddress(rs.getString("ADDRESS"));
				employee.setEmail(rs.getString("EMAIL"));
				employee.setPhoneNumber(rs.getLong("PHONE_NUMBER"));
				employee.setWages(rs.getInt("WAGES"));
				employee.setFrequency(WorkFrequency.valueOf(rs.getString("FREQUNECY")));
				employee.setName(rs.getString("NAME"));
				employee.setStartDate(rs.getDate("START_DATE"));
				employee.setRole(Role.valueOf(rs.getString("ROLE")));
				employeeArr.add(employee);
			}

			return employeeArr;
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return null;
	}
	
	public ArrayList<EmployeeBe> getByServiceCenterId(int serviceCenterId) {
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			stmt = connection.createStatement();

			rs = stmt.executeQuery("SELECT * FROM EMPLOYEE WHERE service_center_id=" + serviceCenterId);
			ArrayList<EmployeeBe> employeeArr = new ArrayList<EmployeeBe>();

			while (rs.next()) {
				EmployeeBe employee = new EmployeeBe();
				employee.setId(rs.getInt("ID"));
				employee.setServiceCenterId(rs.getInt("SERVICE_CENTER_ID"));
				employee.setAddress(rs.getString("ADDRESS"));
				employee.setEmail(rs.getString("EMAIL"));
				employee.setName(rs.getString("NAME"));
				employee.setStartDate(rs.getDate("START_DATE"));
				employee.setRole(Role.valueOf(rs.getString("ROLE")));
				employeeArr.add(employee);
			}

			return employeeArr;
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	public boolean insertEmployee(EmployeeBe employee) {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			ps = connection.prepareStatement("INSERT INTO Employee (ID, SERVICE_CENTER_ID, NAME, ADDRESS, EMAIL, PHONE, ROLE, FREQUENCY, WAGES, START_DATE, USER_ID) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
			ps.setInt(1, employee.getId());
			ps.setInt(2, employee.getServiceCenterId());
			ps.setString(3, employee.getName());
			ps.setString(4, employee.getAddress());
			ps.setString(5, employee.getEmail());
			ps.setLong(6, employee.getPhoneNumber());
			ps.setString(7, employee.getRole().toString());
			ps.setString(8, employee.getFrequency().toString());
			ps.setInt(9, employee.getWages());
			ps.setDate(10, employee.getStartDate());
			ps.setString(11, employee.getUser().getId());
			int i = ps.executeUpdate();

			if (i == 1) {
				return true;
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				ps.close();
				

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return false;

	}

	public boolean deleteEmployee(int id) {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			ps = connection.prepareStatement("DELETE FROM EMPLOYEE WHERE ID=?");
			ps.setInt(1, id);
			
			int i = ps.executeUpdate();

			if (i == 1) {
				return true;
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				ps.close();
				

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return false;

	}
	
	
public int getMaxId() {
		
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			stmt = connection.createStatement();

			rs = stmt.executeQuery("SELECT MAX(ID) AS MAX_ID FROM EMPLOYEE");
			if (rs.next()) {
				int maxId = 0;				
				maxId =rs.getInt("MAX_ID");
				return maxId;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return 0;
	}

	
}
