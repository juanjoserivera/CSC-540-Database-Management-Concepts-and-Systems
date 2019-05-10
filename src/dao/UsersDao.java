package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import be.CarTypeBe;
import be.UsersBe;
import conn.DBConnection;

public class UsersDao {

	private static final String USERS_TABLE = "USERS";

//changing id to username varchar2

//	public int getMaxId() {
//		
//		Connection connection = null;
//		ResultSet rs = null;
//		Statement stmt = null;
//		try {
//			connection = DBConnection.getInstance().getConnection();
//			stmt = connection.createStatement();
//
//			rs = stmt.executeQuery("SELECT MAX(ID) AS MAX_ID FROM " + USERS_TABLE);
//			if (rs.next()) {
//				int maxId = 0;				
//				maxId =rs.getInt("MAX_ID");
//				return maxId;
//			}
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
//		return 0;
//	}

	public boolean updatePassword(String username, String newPass) {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			String updateStmt = "UPDATE USERS SET PASSWORD = \'" + newPass + "\' WHERE ID = \'" + username + "\'";
			ps = connection.prepareStatement(updateStmt);
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

	public boolean insertUser(UsersBe user) {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			String query = "INSERT INTO " + USERS_TABLE + " (ID, ROLE_ID, PASSWORD) VALUES (?,?,?)";
			ps = connection.prepareStatement(query);
			ps.setString(1, user.getId());
			ps.setInt(2, user.getRoleId());
			ps.setString(3, user.getPassword());
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

	public boolean login(String username, String password) {

		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		boolean result = false;
		try {
			connection = DBConnection.getInstance().getConnection();
			stmt = connection.createStatement();

			rs = stmt.executeQuery("SELECT COUNT(*) AS NUMUSERS FROM " + USERS_TABLE + " WHERE ID = " + "'"+username+ "'"
					+ " AND PASSWORD = '" + password+"'");
			if (rs.next()) {
				int countUsers = 0;
				countUsers = rs.getInt("NUMUSERS");
				return (countUsers == 1);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return result;

		} finally {
			try {
				rs.close();
				stmt.close();
				

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return false;
	}

}
