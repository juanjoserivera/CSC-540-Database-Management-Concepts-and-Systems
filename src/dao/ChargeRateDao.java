package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import be.ChargeRateBe;
import conn.DBConnection;

public class ChargeRateDao {

	private static String TABLE_NAME = "CHARGE_RATE";

	public ChargeRateBe get(int id) {
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

	public ArrayList<ChargeRateBe> getAll() {
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery("SELECT * FROM " + TABLE_NAME);
			ArrayList<ChargeRateBe> list = new ArrayList<ChargeRateBe>();
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

	public boolean insert(ChargeRateBe be) {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			ps = connection.prepareStatement("INSERT INTO "
					+ TABLE_NAME + " (ID, TYPE, COST_PER_HOUR) VALUES (?,?,?)");
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

  private ChargeRateBe rsToInstance(ResultSet rs) throws SQLException {
    ChargeRateBe be = new ChargeRateBe();
    be.setId(rs.getInt("ID"));
    be.setType(rs.getString("TYPE"));
    be.setCostPerHour(rs.getInt("COST_PER_HOUR"));
    return be;
  }

  private void setPs(PreparedStatement ps, ChargeRateBe be) throws SQLException {
    ps.setInt(1, be.getId());
    ps.setString(2, be.getType());
    ps.setInt(3, be.getCostPerHour());
  }
}
