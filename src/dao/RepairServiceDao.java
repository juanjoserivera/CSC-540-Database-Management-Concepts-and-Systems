package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import be.RepairServiceBe;
import conn.DBConnection;

/**
 * @author Juan Jose Rivera
 *
 */
public class RepairServiceDao {
	
	private static final String TABLE_NAME = "REPAIR_SERVICE";

	public int getDurationMinutesSum(LocalDate date) {
		String query = String.format(
				"SELECT SUM(expected_hours) FROM WORK_APPOINTMENT WHERE MAINTENANCE_SERVICE_ID IS NULL AND TRUNC(start_date)=to_date('%s', 'yyyy/mm/dd')",
				date);
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);
			if (rs.next()) {
				return (int)(rs.getDouble("SUM(expected_hours)") * 60);
			}
			return -1;
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

	public int getDurationMinutes(int id) {
		String query = String.format(
				"SELECT SUM(expected_hours) FROM BASIC_SERVICE B, BSXRS X WHERE X.BASIC_SERVICE_ID = B.id AND X.REPAIR_SERVICE_ID = %s",
				id);
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
//		System.out.println(query);
		try {
			connection = DBConnection.getInstance().getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);
			if (rs.next()) {
				return (int)(rs.getDouble("SUM(expected_hours)") * 60);
			}
			return -1;
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

	public RepairServiceBe get(int id) {
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			stmt = connection.createStatement();

			rs = stmt.executeQuery("SELECT * FROM "+TABLE_NAME+ " WHERE id=" + id);
			if (rs.next()) {
				RepairServiceBe repairService = new RepairServiceBe();
				repairService.setId(id);
				repairService.setProblem(rs.getString("PROBLEM"));
				repairService.setDiagFee(rs.getDouble("DIAG_FEE"));
				repairService.setCarTypeId(rs.getInt("CAR_TYPE_ID"));
				
				return repairService;
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
	
	public RepairServiceBe getByProblem(String problem, int carTypeId) {
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			stmt = connection.createStatement();

			rs = stmt.executeQuery("SELECT * FROM "+TABLE_NAME+ " WHERE TO_CHAR(TRIM(PROBLEM))= '" + problem+"' AND CAR_TYPE_ID="+carTypeId);
			if (rs.next()) {
				RepairServiceBe repairService = new RepairServiceBe();
				repairService.setId(rs.getInt("ID"));
				repairService.setProblem(rs.getString("PROBLEM"));
				repairService.setDiagFee(rs.getDouble("DIAG_FEE"));
				repairService.setCarTypeId(rs.getInt("CAR_TYPE_ID"));
				
				return repairService;
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

}
