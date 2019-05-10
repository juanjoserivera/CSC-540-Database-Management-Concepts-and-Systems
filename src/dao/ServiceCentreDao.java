package dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import conn.DBConnection;

public class ServiceCentreDao {
	public String getServiceCentreName(int serviceCentreId) {
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			stmt = connection.createStatement();	
			rs = stmt.executeQuery("SELECT NAME FROM SERVICE_CENTER WHERE ID = " + serviceCentreId);
			if(rs.next()) {
				String name = rs.getString("NAME");
				return name;
			}
		}
		 catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				
	
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return "";
		
	}
}
