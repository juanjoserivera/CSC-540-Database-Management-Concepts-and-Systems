package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import be.CarTypeBe;
import be.InvoiceBe;
import conn.DBConnection;

/**
 * @author Juan Jose Rivera
 *
 */
public class InvoiceDao {
	
	public String getLaborWagesByMaintenanceService(int serviceId) {
		return getLaborWagesByQuery("SELECT CHARGE_RATE_ID FROM "
				+ "BASIC_SERVICE B, BSXMS BM "
				+ "WHERE B.id=BM.BASIC_SERVICE_ID AND BM.MAINTENANCE_SERVICE_ID=" + serviceId);
	}
	
	public String getLaborWagesByRepairService(int serviceId) {
		return getLaborWagesByQuery("SELECT CHARGE_RATE_ID FROM "
				+ "BASIC_SERVICE B, BSXRS BR WHERE B.id = BR.BASIC_SERVICE_ID AND BR.REPAIR_SERVICE_ID = " + serviceId);
	}
	
	public String getLaborWagesByQuery(String query) {
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);
			StringBuilder charges = new StringBuilder();
			while (rs.next()) {
				int id = rs.getInt("charge_rate_id");
				charges.append((id == 1 ? "L" : "H") + " ");
			}
			return charges.toString();
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
	
	
	public InvoiceBe get(int id) {
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("SELECT * FROM INVOICE WHERE id=" + id);
			if (rs.next()) {
				InvoiceBe invoice = new InvoiceBe();
				invoice.setId(id);
				invoice.setDate(rs.getDate("DATE"));
				invoice.setTotalCost(rs.getDouble("TOTAL_COST"));
				invoice.setStatus(rs.getString("STATUS"));
				return invoice;
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
	
	public boolean insert(InvoiceBe invoiceBe) {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			ps = connection.prepareStatement("INSERT INTO INVOICE (ID, DATE, TOTAL_COST, STATUS) VALUES (?,?,?,?)");
			ps.setInt(1, invoiceBe.getId());
			ps.setDate(2, invoiceBe.getDate());
			ps.setDouble(3, invoiceBe.getTotalCost());
			ps.setString(4, invoiceBe.getStatus());

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
	
	public boolean insertInvoiceCustomer(InvoiceBe invoiceBe) {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			ps = connection.prepareStatement("INSERT INTO  CUSTOMERXINVOICE (INVOICE_ID, CUSTOMER_ID) VALUES (?,?)");
			ps.setInt(1, invoiceBe.getId());
			ps.setInt(2, invoiceBe.getCustomerBe().getId());
		
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
	public boolean insertInvoiceWorkApp(InvoiceBe invoiceBe) {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			ps = connection.prepareStatement("INSERT INTO WORK_APPOINTMENTXINVOICE (INVOICE_ID, WORK_APPOINTMENT_ID) VALUES (?,?)");
			ps.setInt(1, invoiceBe.getId());
			ps.setInt(2, invoiceBe.getWorkAppointmentBe().getId());
			

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
