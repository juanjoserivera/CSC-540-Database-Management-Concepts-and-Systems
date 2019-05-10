package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import be.EmployeeBe;
import be.EmployeeBe.Role;
import be.EmployeeBe.WorkFrequency;
import be.PaymentBe;
import conn.DBConnection;

public class PaymentDao {
	public PaymentBe getPayment(int id) {
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			stmt = connection.createStatement();

			rs = stmt.executeQuery("SELECT * FROM PAYMENT WHERE id=" + id);
			if (rs.next()) {
				PaymentBe payment = new PaymentBe();
				payment.setId(id);
				payment.setEmployeeId(rs.getInt("EMPLOYEE_ID"));
				payment.setPaymentDate(rs.getDate("PAYMENT_DATE"));
				payment.setCompFrequency(rs.getString("COMP_FREQUENCY"));
				payment.setStartDate(rs.getDate("START_DATE"));
				payment.setEndDate(rs.getDate("END_DATE"));
				payment.setHoursOrDaysWorked(rs.getInt("HOURS_OR_DAYS_WORKED"));
				payment.setEarningsYearToDate(rs.getInt("EARNINGS_YEAR_TO_DATE"));
				payment.setEarnings(rs.getInt("EARNINGS"));
				payment.setCompensation(rs.getInt("COMPENSATION"));
				return payment;
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
	
	

	public ArrayList<PaymentBe> getAllPayments() {
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			stmt = connection.createStatement();

			rs = stmt.executeQuery("SELECT * FROM PAYMENT");
			ArrayList<PaymentBe> paymentsArr = new ArrayList<PaymentBe>();

			while (rs.next()) {
				PaymentBe payment = new PaymentBe();
				payment.setId(rs.getInt("ID"));
				payment.setEmployeeId(rs.getInt("EMPLOYEE_ID"));
				payment.setPaymentDate(rs.getDate("PAYMENT_DATE"));
				payment.setCompFrequency(rs.getString("COMP_FREQUENCY"));
				payment.setStartDate(rs.getDate("START_DATE"));
				payment.setEndDate(rs.getDate("END_DATE"));
				payment.setHoursOrDaysWorked(rs.getInt("HOURS_OR_DAYS_WORKED"));
				payment.setEarningsYearToDate(rs.getInt("EARNINGS_YEAR_TO_DATE"));
				payment.setEarnings(rs.getInt("EARNINGS"));
				payment.setCompensation(rs.getInt("COMPENSATION"));
				paymentsArr.add(payment);
			}

			return paymentsArr;
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
	
	public ArrayList<PaymentBe> getAllPaymentsByEmpId(int empId) {
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			stmt = connection.createStatement();

			rs = stmt.executeQuery("SELECT * FROM PAYMENT WHERE EMPLOYEE_ID="+empId);
			ArrayList<PaymentBe> paymentsArr = new ArrayList<PaymentBe>();

			while (rs.next()) {
				PaymentBe payment = new PaymentBe();
				payment.setId(rs.getInt("ID"));
				payment.setEmployeeId(rs.getInt("EMPLOYEE_ID"));
				payment.setPaymentDate(rs.getDate("PAYMENT_DATE"));
				payment.setCompFrequency(rs.getString("COMP_FREQUENCY"));
				payment.setStartDate(rs.getDate("START_DATE"));
				payment.setEndDate(rs.getDate("END_DATE"));
				payment.setHoursOrDaysWorked(rs.getInt("HOURS_OR_DAYS_WORKED"));
				payment.setEarningsYearToDate(rs.getInt("EARNINGS_YEAR_TO_DATE"));
				payment.setEarnings(rs.getInt("EARNINGS"));
				payment.setCompensation(rs.getInt("COMPENSATION"));
				paymentsArr.add(payment);
			}

			return paymentsArr;
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

	public boolean insertPayment(PaymentBe payment) {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			ps = connection.prepareStatement("INSERT INTO PAYMENT (ID, EMPLOYEE_ID, PAYMENT_DATE, COMP_FREQUENCY, START_DATE, END_DATE, HOURS_OR_DAYS_WORKED, EARNINGS_YEAR_TO_DATE, EARNINGS, COMPENSATION) VALUES (?,?,?,?,?,?,?,?,?,?)");
			ps.setInt(1, payment.getId());
			ps.setInt(2, payment.getEmployeeId());
			ps.setDate(3, payment.getPaymentDate());
			ps.setString(4, payment.getCompFrequency());
			ps.setDate(5, payment.getStartDate());
			ps.setDate(6, payment.getEndDate());
			ps.setInt(7, payment.getHoursOrDaysWorked());
			ps.setInt(8, payment.getEarningsYearToDate());
			ps.setInt(9, payment.getEarnings());
			ps.setInt(10, payment.getCompensation());
			
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

	public boolean deletePayment(int id) {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			ps = connection.prepareStatement("DELETE FROM PAYMENT WHERE ID=?");
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
}
