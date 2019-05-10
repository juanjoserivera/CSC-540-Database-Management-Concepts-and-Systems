package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import be.BasicServiceBe;
import conn.DBConnection;
public class NewCarModelDao {
	public BasicServiceBe reverseLookupByPartId(int id) {
			Connection connection = null;
			ResultSet rs = null;
			Statement stmt = null;
			try {
				connection = DBConnection.getInstance().getConnection();
				stmt = connection.createStatement();

				rs = stmt.executeQuery("SELECT DISTINCT b.expected_hours, b.charge_rate_id, b.service_name " + 
						"FROM PARTXBASIC_SERVICE P, basic_service b " + 
						"WHERE p.basic_service_id = b.id " + 
						"AND p.part_id = "+id);
				if (rs.next()) {
					BasicServiceBe basicServiceBe = new BasicServiceBe();
					basicServiceBe.setChargeRateId(rs.getInt("CHARGE_RATE_ID"));
					basicServiceBe.setExpectedHours(rs.getDouble("EXPECTED_HOURS"));
					basicServiceBe.setName(rs.getString("SERVICE_NAME"));
					return basicServiceBe;
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
	public boolean createPARTXBSMapping(int bsid,int partid,int count) {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			ps = connection.prepareStatement(
					"Insert into PARTXBASIC_SERVICE (BASIC_SERVICE_ID,PART_ID,NUM) values (?,?,?)");
			ps.setInt(1, bsid);
			ps.setInt(2, partid);
			ps.setInt(3, count);
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
	public boolean createBSXMSMapping(int msid,int bsid) {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			ps = connection.prepareStatement(
					"Insert into BSXMS (BASIC_SERVICE_ID,MAINTENANCE_SERVICE_ID) values (?,?)");
			ps.setInt(1, bsid);
			ps.setInt(2, msid);
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