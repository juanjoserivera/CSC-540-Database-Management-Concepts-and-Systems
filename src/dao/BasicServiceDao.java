package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import be.BasicServiceBe;
import be.CarTypeBe;
import be.CauseBe;
import conn.DBConnection;

/**
 * @author Juan Jose Rivera
 *
 */
public class BasicServiceDao {
	
	private static final String TABLE_NAME = "BASIC_SERVICE";
	public int getMaxId() {
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			stmt = connection.createStatement();

			rs = stmt.executeQuery("SELECT MAX(ID) AS MAX_ID FROM "+TABLE_NAME);
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
	public boolean put(BasicServiceBe basicServiceBe) {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			ps = connection.prepareStatement(
					"Insert into BASIC_SERVICE (ID,EXPECTED_HOURS,CHARGE_RATE_ID,CAR_TYPE_ID,SERVICE_NAME) "
					+ "VALUES (?,?,?,?,?)");
			ps.setInt(1, basicServiceBe.getId());
			ps.setDouble(2, basicServiceBe.getExpectedHours());
			ps.setInt(3, basicServiceBe.getChargeRateId());
			ps.setInt(4, basicServiceBe.getCarTypeId());
			ps.setString(5, basicServiceBe.getName());
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
	public BasicServiceBe get(int id) {
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			stmt = connection.createStatement();

			rs = stmt.executeQuery("SELECT * FROM "+TABLE_NAME+ " WHERE id=" + id);
			if (rs.next()) {
				BasicServiceBe basicService = new BasicServiceBe();
				basicService.setId(id);
				basicService.setName(rs.getString("SERVICE_NAME"));
				basicService.setCarTypeId(rs.getInt("CAR_TYPE_ID"));
				basicService.setExpectedHours(rs.getDouble("EXPECTED_HOURS"));
				basicService.setChargeRateId(rs.getInt("CHARGE_RATE_ID"));


				return basicService;
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
	
	public  ArrayList<BasicServiceBe> getBasicServiceByRepId(int repairServId) {
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			stmt = connection.createStatement();

			rs = stmt.executeQuery("SELECT B.ID AS ID, B.EXPECTED_HOURS AS EXPECTED_HOURS, B.SERVICE_NAME as SERVICE_NAME, B.CAR_TYPE_ID as CAR_TYPE_ID, B.CHARGE_RATE_ID AS CHARGE_RATE_ID "
					+ "FROM  BASIC_SERVICE B, BSXRS BR WHERE B.ID=BR.BASIC_SERVICE_ID AND BR.REPAIR_SERVICE_ID=" + repairServId);
			
			ArrayList<BasicServiceBe> basicServiceBeList = new ArrayList<BasicServiceBe>();

			while (rs.next()) {
				BasicServiceBe basicService = new BasicServiceBe();
				basicService.setId(rs.getInt("ID"));		
				basicService.setName(rs.getString("SERVICE_NAME"));
				basicService.setCarTypeId(rs.getInt("CAR_TYPE_ID"));
				basicService.setExpectedHours(rs.getDouble("EXPECTED_HOURS"));
				basicService.setChargeRateId(rs.getInt("CHARGE_RATE_ID"));
				
				basicServiceBeList.add(basicService);
			}

			return basicServiceBeList;
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

	public  ArrayList<BasicServiceBe> getBasicServiceByMaintId(int maintServId) {
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			stmt = connection.createStatement();

			rs = stmt.executeQuery("SELECT B.ID AS ID, B.EXPECTED_HOURS AS EXPECTED_HOURS, B.SERVICE_NAME as SERVICE_NAME, B.CAR_TYPE_ID as CAR_TYPE_ID, B.CHARGE_RATE_ID AS CHARGE_RATE_ID  "
					+ "FROM  BASIC_SERVICE B, BSXMS BM WHERE B.ID=BM.BASIC_SERVICE_ID AND BM.MAINTENANCE_SERVICE_ID=" + maintServId);
			
			ArrayList<BasicServiceBe> basicServiceBeList = new ArrayList<BasicServiceBe>();

			while (rs.next()) {
				BasicServiceBe basicService = new BasicServiceBe();
				basicService.setId(rs.getInt("ID"));
				basicService.setName(rs.getString("SERVICE_NAME"));
				basicService.setCarTypeId(rs.getInt("CAR_TYPE_ID"));
				basicService.setExpectedHours(rs.getDouble("EXPECTED_HOURS"));
				basicService.setChargeRateId(rs.getInt("CHARGE_RATE_ID"));
				
				basicServiceBeList.add(basicService);
			}

			return basicServiceBeList;
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
