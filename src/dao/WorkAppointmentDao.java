package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import be.WorkAppointmentBe;
import conn.DBConnection;

public class WorkAppointmentDao {
	private static final String TABLE_NAME = "WORK_APPOINTMENT";

	private static final String MAINTENANCE_ATTRIBUTES = "ID, STATUS, CAR_ID, CUSTOMER_ID, SERVICE_CENTER_ID, EMPLOYEE_ID, MAINTENANCE_SERVICE_ID, TOTAL_COST, START_DATE, EXPECTED_HOURS";
	private static final String REPAIR_ATTRIBUTES = "ID, STATUS, CAR_ID, CUSTOMER_ID, SERVICE_CENTER_ID, EMPLOYEE_ID, REPAIR_SERVICE_ID, TOTAL_COST, START_DATE, EXPECTED_HOURS";
	
	public boolean reschedule(int id, LocalDateTime date) {
		Connection connection = null;
		PreparedStatement ps = null;
		String query = String.format("UPDATE %s SET START_DATE=? WHERE id=%s", TABLE_NAME, id);
		try {
			connection = DBConnection.getInstance().getConnection();
			ps = connection.prepareStatement(query);
			ps.setTimestamp(1, Timestamp.valueOf(date));
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

	public int count() {
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("SELECT count(*) FROM " + TABLE_NAME);
			if (rs.next()) {
				return rs.getInt("count(*)");
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

	public WorkAppointmentBe get(int id) {
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("SELECT * FROM " + TABLE_NAME + " WHERE id=" + id);
			if (rs.next()) {
				return rsToInstance(rs);
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

	public ArrayList<WorkAppointmentBe> getAll() {
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("SELECT * FROM " + TABLE_NAME);
			ArrayList<WorkAppointmentBe> list = new ArrayList<WorkAppointmentBe>();
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

	public ArrayList<WorkAppointmentBe> getAllForServiceCenter(int serviceCenterId) {
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			stmt = connection.createStatement();
			String query = "SELECT * FROM " + TABLE_NAME + " WHERE SERVICE_CENTER_ID=" + serviceCenterId;
			rs = stmt.executeQuery(query);
			ArrayList<WorkAppointmentBe> list = new ArrayList<WorkAppointmentBe>();
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
	
	public ArrayList<WorkAppointmentBe> getByCustomer(int cust) {
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			stmt = connection.createStatement();
			String query = "SELECT * FROM " + TABLE_NAME + " WHERE CUSTOMER_ID=" + cust;
			rs = stmt.executeQuery(query);
			ArrayList<WorkAppointmentBe> list = new ArrayList<WorkAppointmentBe>();
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

	public ArrayList<WorkAppointmentBe> getAllForCustomer(int serviceCenterId, int cust_id) {
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			stmt = connection.createStatement();
			String query = "SELECT * FROM " + TABLE_NAME + " WHERE SERVICE_CENTER_ID=" + serviceCenterId
					+ " AND CUSTOMER_ID =" + cust_id;
			rs = stmt.executeQuery(query);
			ArrayList<WorkAppointmentBe> list = new ArrayList<WorkAppointmentBe>();
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

	public WorkAppointmentBe getLatestForCar(String carPlateNumber) {
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			stmt = connection.createStatement();
			String query = "SELECT * FROM " + TABLE_NAME + " WHERE CAR_ID=\'" + carPlateNumber
					+ "\' ORDER BY START_DATE DESC";
			rs = stmt.executeQuery(query);
			if (rs.next()) {
				return rsToInstance(rs);
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

	public ArrayList<WorkAppointmentBe> getByServiceCenter(int serviceCenterId) {
		return getByQuery(String.format("SELECT * FROM %s WHERE SERVICE_CENTER_ID=%s", TABLE_NAME, serviceCenterId));
	}

	public ArrayList<WorkAppointmentBe> getCompletedByServiceCenter(int serviceCenterId) {
		return getByQuery(String.format("SELECT * FROM %s WHERE SERVICE_CENTER_ID=%s AND status='Completed'",
				TABLE_NAME, serviceCenterId));
	}

	public ArrayList<WorkAppointmentBe> getByServiceCenterAndDateAndEmployee(int serviceCenterId, LocalDate date,
			int employeeId) {
		return getByQuery(String.format(
				"SELECT * FROM %s WHERE SERVICE_CENTER_ID=%s AND trunc(start_date)=to_date('%s', 'yyyy/mm/dd') AND employee_id=%s",
				TABLE_NAME, serviceCenterId, date, employeeId));
	}
	
	public ArrayList<WorkAppointmentBe> getPendingByCustomerId(int customerId) {
		return getByQuery(String.format(
				"SELECT * FROM %s WHERE CUSTOMER_ID=%s AND status='Pending'",
				TABLE_NAME, customerId));
	}

	public ArrayList<WorkAppointmentBe> getByQuery(String query) {
//		System.out.println(query);
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);
			ArrayList<WorkAppointmentBe> list = new ArrayList<WorkAppointmentBe>();
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

	public boolean insertForMaintenance(WorkAppointmentBe be) {
		Connection connection = null;
		PreparedStatement ps = null;
		String query = String.format("INSERT INTO %s (%s) VALUES (?,?,?,?,?,?,?,?,?,?)", TABLE_NAME, MAINTENANCE_ATTRIBUTES);
		try {
			connection = DBConnection.getInstance().getConnection();
			ps = connection.prepareStatement(query);
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
	
	public boolean insertForRepair(WorkAppointmentBe be) {
		Connection connection = null;
		PreparedStatement ps = null;
		String query = String.format("INSERT INTO %s (%s) VALUES (?,?,?,?,?,?,?,?,?,?)", TABLE_NAME, REPAIR_ATTRIBUTES);
		try {
			connection = DBConnection.getInstance().getConnection();
			ps = connection.prepareStatement(query);
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

	public boolean delete(int id) {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			ps = connection.prepareStatement("DELETE FROM " + TABLE_NAME + " WHERE ID=?");
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

	public Date getLastServiceDate(int basicServiceId, String carId) {
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			stmt = connection.createStatement();
			String query = String.format(
					"SELECT max(x.start_date) AS LAST_DATE FROM "
							+ "(SELECT b.basic_service_id, w.start_date FROM work_appointment w, "
							+ "bsxrs b WHERE w.car_id = '%s' AND w.repair_service_id is not null AND "
							+ "b.repair_service_id = w.repair_service_id UNION SELECT b.basic_service_id, "
							+ "w.start_date FROM work_appointment w,bsxms b WHERE w.car_id = '%s' "
							+ "AND w.maintenance_service_id is not null AND w.maintenance_service_id = "
							+ "b.maintenance_service_id) X WHERE x.basic_service_id = %d",
					carId, carId, basicServiceId);
			rs = stmt.executeQuery(query);
			if (rs.next()) {
				return rs.getDate("LAST_DATE");
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

	private WorkAppointmentBe rsToInstance(ResultSet rs) throws SQLException {
		WorkAppointmentBe be = new WorkAppointmentBe();
		be.setId(rs.getInt("ID"));
		be.setStatus(rs.getString("STATUS"));
		be.setCustomerId(rs.getInt("CUSTOMER_ID"));
		be.setCarId(rs.getString("CAR_ID"));
		be.setServiceCenterId(rs.getInt("SERVICE_CENTER_ID"));
		be.setEmployeeId(rs.getInt("EMPLOYEE_ID"));
		be.setRepairServiceId(rs.getInt("REPAIR_SERVICE_ID"));
		be.setMaintenanceServiceId(rs.getInt("MAINTENANCE_SERVICE_ID"));
		be.setTotalCost(rs.getInt("TOTAL_COST"));
		be.setStartDate(rs.getTimestamp("START_DATE").toLocalDateTime());
		be.setExpectedHours(rs.getDouble("EXPECTED_HOURS"));
		return be;
	}

	private void setPs(PreparedStatement ps, WorkAppointmentBe be) throws SQLException {
		ps.setInt(1, be.getId());
		ps.setString(2, be.getStatus());
		ps.setString(3, be.getCarId());
		ps.setInt(4, be.getCustomerId());
		ps.setInt(5, be.getServiceCenterId());
		ps.setInt(6, be.getEmployeeId());
		ps.setInt(7, (be.getRepairServiceId() > 0) ? be.getRepairServiceId() : be.getMaintenanceServiceId());
		ps.setDouble(8, be.getTotalCost());
		ps.setTimestamp(9, Timestamp.valueOf(be.getStartDate()));
		ps.setDouble(10, be.getExpectedHours());
	}		
}
