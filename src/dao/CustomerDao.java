package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import be.CustomerBe;
import be.PaymentBe;
import conn.DBConnection;

public class CustomerDao {
	public CustomerBe getCustomer(int id) {
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			stmt = connection.createStatement();

			rs = stmt.executeQuery("SELECT * FROM CUSTOMER WHERE id=" + id);
			if (rs.next()) {
				CustomerBe customer = new CustomerBe();
				customer.setId(id);
				customer.setEmail(rs.getString("EMAIL"));
				customer.setAddress(rs.getString("ADDRESS"));
				customer.setName(rs.getString("NAME"));
				customer.setPhoneNumber(rs.getLong("PHONE_NUMBER"));
				return customer;
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

	public CustomerBe getCustomerByEmail(String email) {
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			stmt = connection.createStatement();

			rs = stmt.executeQuery("SELECT * FROM CUSTOMER WHERE email=\'" + email + "\'");
			if (rs.next()) {
				CustomerBe customer = new CustomerBe();
				customer.setId(rs.getInt("ID"));
				customer.setEmail(rs.getString("EMAIL"));
				customer.setAddress(rs.getString("ADDRESS"));
				customer.setName(rs.getString("NAME"));
				customer.setPhoneNumber(rs.getLong("PHONE_NUMBER"));
				return customer;
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

	public CustomerBe getCustomerByUserId(String userId) {
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			stmt = connection.createStatement();

			rs = stmt.executeQuery("SELECT * FROM CUSTOMER WHERE USER_ID=\'" + userId + "\'");
			if (rs.next()) {
				CustomerBe customer = new CustomerBe();
				customer.setId(rs.getInt("ID"));
				customer.setEmail(rs.getString("EMAIL"));
				customer.setAddress(rs.getString("ADDRESS"));
				customer.setName(rs.getString("NAME"));
				customer.setPhoneNumber(rs.getLong("PHONE_NUMBER"));
				customer.getUser().setId(userId);
				customer.setServiceCenterId(rs.getInt("SERVICE_CENTER_ID"));
				return customer;
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

	public ArrayList<CustomerBe> getAllCustomers() {
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			stmt = connection.createStatement();

			rs = stmt.executeQuery("SELECT * FROM CUSTOMER");
			ArrayList<CustomerBe> customersArr = new ArrayList<CustomerBe>();

			while (rs.next()) {
				CustomerBe customer = new CustomerBe();
				customer.setId(rs.getInt("ID"));
				customer.setEmail(rs.getString("EMAIL"));
				customer.setAddress(rs.getString("ADDRESS"));
				customer.setName(rs.getString("NAME"));
				customer.setPhoneNumber(rs.getLong("PHONE_NUMBER"));
				customersArr.add(customer);
			}

			return customersArr;
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

	public boolean insertCustomer(CustomerBe customer) {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			ps = connection.prepareStatement(
					"INSERT INTO CUSTOMER (ID, NAME, ADDRESS, EMAIL, PHONE_NUMBER, USER_ID, SERVICE_CENTER_ID) VALUES (?,?,?,?,?,?,?)");
			ps.setInt(1, customer.getId());
			ps.setString(2, customer.getName());
			ps.setString(3, customer.getAddress());
			ps.setString(4, customer.getEmail());
			ps.setLong(5, customer.getPhoneNumber());
			ps.setString(6, customer.getUser().getId());
			ps.setInt(7, customer.getServiceCenterId());
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

	public boolean deleteCustomer(int id) {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			ps = connection.prepareStatement("DELETE FROM CUSTOMER WHERE ID=?");
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

			rs = stmt.executeQuery("SELECT MAX(ID) AS MAX_ID FROM CUSTOMER");
			if (rs.next()) {
				int maxId = 0;
				maxId = rs.getInt("MAX_ID");
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

	public boolean updateName(String newName, String email) {
		return updateCustomer("NAME", newName, email);
	}

	public boolean updateAddress(String newAddress, String email) {
		return updateCustomer("ADDRESS", newAddress, email);
	}

	public boolean updatePhone(long newPhone, String email) {
		return updateCustomer("PHONE_NUMBER", newPhone, email);
	}
	
	public boolean updateCustomer(String column,Object newVal,String email) {
		
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			StringBuilder updateStmt = new StringBuilder("UPDATE CUSTOMER SET " + column + " = ");
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
			updateStmt.append(" where EMAIL = \'" + email + "\'");
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

}

}
