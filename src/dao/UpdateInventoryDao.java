package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import conn.DBConnection;
import session.SESSION;

public class UpdateInventoryDao {
	public HashMap<Integer, Integer> getPartstoUpdate(){
		HashMap<Integer, Integer> partsNumberMap = new HashMap<Integer,Integer>();
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		int serviceCenterId = SESSION.getSession().getServiceCenterId();
		try {
			connection = DBConnection.getInstance().getConnection();
			stmt = connection.createStatement();

			rs = stmt.executeQuery("SELECT p.part_id, SUM(p.num) as num " + 
					"from bsxrs b,partxbasic_service p " +  
					"where b.basic_service_id = p.basic_service_id " +  
					"AND b.repair_service_id in (SELECT repair_service_id " + 
					"           FROM WORK_APPOINTMENT " + 
					"           WHERE STATUS = 'Pending' " + 
					"           AND repair_service_id is not null " + 
					"           AND start_date < SYSDATE " + 
					"           AND service_center_id = "+serviceCenterId+") " +
					" GROUP BY (p.part_id)" +
					"UNION " + 
					"SELECT p.part_id, SUM(p.num) as NUM " + 
					"from bsxms b, partxbasic_service p " + 
					"where b.basic_service_id = p.basic_service_id " + 
					"AND b.maintenance_service_id in (SELECT work_appointment.maintenance_service_id " + 
					"           FROM WORK_APPOINTMENT  " + 
					"           WHERE STATUS = 'Pending' " + 
					"           AND maintenance_service_id is not null " + 
					"           AND start_date < SYSDate " + 
					"           AND service_center_id = "+serviceCenterId+") " +
					"GROUP BY (p.part_id)");

			while (rs.next()) {
				int part = rs.getInt("PART_ID");
				int partCount = rs.getInt("NUM");
				if (partsNumberMap.containsKey(part)) {
					partsNumberMap.put(part, partsNumberMap.get(part) + partCount);
				}
				else {
					partsNumberMap.put(part, partsNumberMap.get(part));
				}
				
			}
			return partsNumberMap;

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
	public boolean removeFromBookedParts(int partid,int count) {
		Connection connection = null;
		PreparedStatement ps = null;
		int serviceCenterId = SESSION.getSession().getServiceCenterId();
		try {
			connection = DBConnection.getInstance().getConnection();
			ps = connection.prepareStatement("UPDATE service_center_inventory SET booked_qty= booked_qty-? WHERE service_center_id=? AND part_id= ?");
			ps.setInt(1, count);
			ps.setInt(2, serviceCenterId);
			ps.setInt(3, partid);
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
	public boolean updateWorkStatus() {
		Connection connection = null;
		PreparedStatement ps = null;
		int serviceCenterId = SESSION.getSession().getServiceCenterId();
		try {
			connection = DBConnection.getInstance().getConnection();
			ps = connection.prepareStatement("UPDATE work_appointment SET status = 'Complete' WHERE start_date < SYSDATE AND service_center_id = ? AND STATUS = 'Pending'");
			ps.setInt(1, serviceCenterId);
			int i = ps.executeUpdate();

			if (i >= 0) {
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

