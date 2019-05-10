package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import be.CarBe;
import be.CarMakeBe;
import be.CarTypeBe;
import be.UsersBe;
import conn.DBConnection;

public class RoleDao {
	public int getRoleId(String roleName) {
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			stmt = connection.createStatement();

			rs = stmt.executeQuery("SELECT ID FROM ROLE WHERE NAME = \'" + roleName + "\'");
			if (rs.next()) {
				return rs.getInt("ID");
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

		return -1;

	}
}
