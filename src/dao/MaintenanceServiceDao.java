package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import be.MaintenanceServiceBe;
import conn.DBConnection;

/**
 * @author Juan Jose Rivera
 *
 */
public class MaintenanceServiceDao {

	private static final String TABLE_NAME = "MAINTENANCE_SERVICE";
	
	public int getDurationMinutesSum(LocalDate date) {
		String query = String.format(
				"SELECT SUM(expected_hours) FROM WORK_APPOINTMENT WHERE REPAIR_SERVICE_ID IS NULL AND TRUNC(start_date)=to_date('%s', 'yyyy/mm/dd')",
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
				"SELECT SUM(expected_hours) FROM BASIC_SERVICE B, BSXMS X WHERE X.BASIC_SERVICE_ID = B.id AND X.MAINTENANCE_SERVICE_ID = %s",
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

	public ArrayList<MaintenanceServiceBe> getByPlateNumber(String planteNumber) {
		return getByQuery(
				String.format("SELECT * FROM %s MS, CAR C WHERE MS.car_type_id = C.car_type_id AND C.plate_number='%s'",
						TABLE_NAME, planteNumber));
	}

	public ArrayList<MaintenanceServiceBe> getByQuery(String query) {
//		System.out.println(query);
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);
			ArrayList<MaintenanceServiceBe> list = new ArrayList<MaintenanceServiceBe>();
			while (rs.next()) {
				list.add(rsToInstance(rs));
			}
			return list;
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

	public MaintenanceServiceBe get(int id) {
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			stmt = connection.createStatement();

			rs = stmt.executeQuery("SELECT * FROM " + TABLE_NAME + " WHERE id=" + id);
			if (rs.next()) {
				MaintenanceServiceBe maintenanceService = new MaintenanceServiceBe();
				maintenanceService.setId(id);
				maintenanceService.setType(rs.getString("TYPE"));
				maintenanceService.setMileage(rs.getInt("MILEAGE"));
				maintenanceService.setNumberOfMonths(rs.getInt("NUMBER_OF_MONTHS"));
				maintenanceService.setCarTypeId(rs.getInt("CAR_TYPE_ID"));

				return maintenanceService;
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

	public int getMaxId() {
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			stmt = connection.createStatement();

			rs = stmt.executeQuery("SELECT MAX(ID) AS MAX_ID FROM " + TABLE_NAME);
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

	public boolean insertMaintenanceService(MaintenanceServiceBe be) {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			ps = connection.prepareStatement("INSERT INTO " + TABLE_NAME
					+ " (ID, TYPE, MILEAGE, NUMBER_OF_MONTHS, CAR_TYPE_ID)" + " VALUES (?,?,?,?,?)");
			setPs(ps, be);

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

	private MaintenanceServiceBe rsToInstance(ResultSet rs) throws SQLException {
		MaintenanceServiceBe be = new MaintenanceServiceBe();
		be.setId(rs.getInt("ID"));
		be.setType(rs.getString("TYPE"));
		be.setMileage(rs.getInt("MILEAGE"));
		be.setNumberOfMonths(rs.getInt("NUMBER_OF_MONTHS"));
		be.setCarTypeId(rs.getInt("CAR_TYPE_ID"));
		be.setId(rs.getInt("ID"));
		return be;
	}

	private void setPs(PreparedStatement ps, MaintenanceServiceBe be) throws SQLException {
		ps.setInt(1, be.getId());
		ps.setString(2, be.getType());
		ps.setInt(3, be.getMileage());
		ps.setInt(4, be.getNumberOfMonths());
		ps.setInt(5, be.getCarTypeId());
	}
}
