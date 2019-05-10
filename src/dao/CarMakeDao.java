package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import be.CarMakeBe;
import conn.DBConnection;

/**
 * @author Juan Jose Rivera
 *
 */
public class CarMakeDao {
	
	public CarMakeDao() {

	}
	public CarMakeBe getCarMakeId(String name) {
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			stmt = connection.createStatement();

			rs = stmt.executeQuery("SELECT * FROM CAR_MAKE WHERE NAME= '" + name.trim().toUpperCase()+"'");
			if (rs.next()) {
				CarMakeBe carMakeBe = new CarMakeBe();
				carMakeBe.setId(rs.getInt("ID"));
				carMakeBe.setName(rs.getString("NAME"));
				if(carMakeBe.getId() != 0) {
					return carMakeBe;
				}
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
	
	public CarMakeBe getCarMake(int id) {
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			stmt = connection.createStatement();

			rs = stmt.executeQuery("SELECT * FROM CAR_MAKE WHERE id=" + id);
			if (rs.next()) {
				CarMakeBe carMake = new CarMakeBe();
				carMake.setId(id);
				carMake.setName(rs.getString("NAME"));
				

				return carMake;
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

	
	public java.util.List<CarMakeBe> getCarMakes() {
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			stmt = connection.createStatement();

			rs = stmt.executeQuery("SELECT * FROM CAR_MAKE");
			java.util.List<CarMakeBe> carMakes = new ArrayList<CarMakeBe>();
			while (rs.next()) {
				CarMakeBe carMake = new CarMakeBe();
				carMake.setId(rs.getInt("ID"));
				carMake.setName(rs.getString("NAME"));
				carMakes.add(carMake);
			}
			return carMakes;
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
