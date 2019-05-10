package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import be.CarTypeBe;
import be.CauseBe;
import be.RepairServiceBe;
import conn.DBConnection;

/**
 * @author Juan Jose Rivera
 *
 */
public class CauseDao {
	
	
	public  ArrayList<CauseBe> getCausesByServId(int repairId) {
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			stmt = connection.createStatement();

			rs = stmt.executeQuery("SELECT C.ID AS ID, C.DEFINITION AS DEFINITION FROM  CAUSE C, CAUSEXREPAIR_SERVICE CS WHERE C.ID = CS.CAUSE_ID AND CS.REPAIR_SERVICE_ID=" + repairId);
			ArrayList<CauseBe> causeBeList = new ArrayList<CauseBe>();

			while (rs.next()) {
				CauseBe causeBe = new CauseBe();
				causeBe.setId(rs.getInt("ID"));
				causeBe.setDefinition(rs.getString("DEFINITION"));
				
				causeBeList.add(causeBe);
			}

			return causeBeList;
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


}
