package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import be.CarMakeBe;
import be.CarTypeBe;
import conn.DBConnection;

/**
 * @author Juan Jose Rivera
 *
 */
public class CarTypeDao {

	public CarTypeDao() {

	}

	public int getMaxId() {
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			stmt = connection.createStatement();

			rs = stmt.executeQuery("SELECT MAX(ID) AS MAX_ID FROM CAR_TYPE");
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
	public CarTypeBe getCarType(int id) {
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			stmt = connection.createStatement();

			rs = stmt.executeQuery("SELECT * FROM CAR_TYPE WHERE id=" + id);
			if (rs.next()) {
				CarTypeBe carType = new CarTypeBe();
				CarMakeBe carMakeBe = new CarMakeBe();
				carType.setId(id);
				carMakeBe.setId(rs.getInt("CAR_MAKE_ID"));
				carType.setCarMake(carMakeBe);
				carType.setModel(rs.getString("MODEL"));

				return carType;
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
	
	public ArrayList<CarTypeBe> getCarTypeByMake(int makeId) {
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			stmt = connection.createStatement();

			rs = stmt.executeQuery("SELECT * FROM CAR_TYPE WHERE CAR_MAKE_ID=" + makeId);
			ArrayList<CarTypeBe> carTypeArr = new ArrayList<CarTypeBe>();

			while (rs.next()) {
				CarTypeBe carType = new CarTypeBe();
				CarMakeBe carMakeBe = new CarMakeBe();
				carMakeBe.setId(rs.getInt("CAR_MAKE_ID"));
				carType.setCarMake(carMakeBe);
				carType.setId(rs.getInt("ID"));
				carType.setModel(rs.getString("MODEL"));

				carTypeArr.add(carType);
			}
			return carTypeArr;
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

	public ArrayList<CarTypeBe> getAllCarTypes() {
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			stmt = connection.createStatement();

			rs = stmt.executeQuery("SELECT * FROM CAR_TYPE");
			ArrayList<CarTypeBe> carTypeArr = new ArrayList<CarTypeBe>();

			while (rs.next()) {
				CarTypeBe carType = new CarTypeBe();
				carType.setId(rs.getInt("ID"));
				carType.getCarMake().setId(rs.getInt("CAR_MAKE_ID"));
				carType.setModel(rs.getString("MODEL"));
				carTypeArr.add(carType);
			}

			return carTypeArr;
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

	public boolean insertCarType(CarTypeBe carTypeBe) {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			ps = connection.prepareStatement("INSERT INTO CAR_TYPE (ID, CAR_MAKE_ID, MODEL) VALUES (?,?,?)");
			ps.setInt(1, carTypeBe.getId());
			ps.setInt(2, carTypeBe.getCarMake().getId());
			ps.setString(3, carTypeBe.getModel());

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

	public boolean deleteCarType(int id) {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			ps = connection.prepareStatement("DELETE FROM CAR_TYPE WHERE ID=?");
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
