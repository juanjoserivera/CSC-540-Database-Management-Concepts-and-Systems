package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import be.CarBe;
import be.CarMakeBe;
import be.CarTypeBe;
import be.WorkAppointmentBe;
import bl.WorkAppointmentBl;
import conn.DBConnection;

public class CarDao {
	
	public CarBe getCar(String plateNumber) {
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			stmt = connection.createStatement();

			rs = stmt.executeQuery(String.format("select C.PLATE_NUMBER, C.LAST_SERVICE_DATE, C.LAST_SERVICE_MILEAGE, C.LAST_SERVICE_TYPE, C.CUSTOMER_ID, C.YEAR, CT.ID AS CT_ID, CT.MODEL, CM.ID AS CM_ID, CM.NAME  FROM CAR C,CAR_TYPE CT,CAR_MAKE CM where C.CAR_TYPE_ID=CT.ID AND CT.CAR_MAKE_ID=CM.ID AND PLATE_NUMBER='%s'", plateNumber));
			if (rs.next()) {
				
				CarMakeBe carMake = new CarMakeBe();
				carMake.setId(rs.getInt("CM_ID"));
				carMake.setName(rs.getString("NAME"));
				
				CarTypeBe carType = new CarTypeBe();
				carType.setId(rs.getInt("CT_ID"));
				carType.setModel(rs.getString("MODEL"));
				carType.setCarMake(carMake);
				
				CarBe car= new CarBe();
				car.setPlateNumber(plateNumber);
				car.setLastServiceDate(rs.getDate("LAST_SERVICE_DATE"));
				car.setLastServiceMileage(rs.getInt("LAST_SERVICE_MILEAGE"));
				car.setLastServiceType(rs.getString("LAST_SERVICE_TYPE"));
				car.setCustomerId(rs.getInt("CUSTOMER_ID") );
				car.setYear(rs.getInt("YEAR"));
				car.setCarType(carType);
				return car;
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
	
	public List<CarBe> getAllCarsByCustomer(int customerId) {
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			stmt = connection.createStatement();

			rs = stmt.executeQuery("SELECT C.PLATE_NUMBER, C.LAST_SERVICE_DATE, C.LAST_SERVICE_MILEAGE, C.LAST_SERVICE_TYPE, C.CUSTOMER_ID,C.YEAR, CT.ID AS CT_ID, CT.MODEL, CM.ID AS CM_ID, CM.NAME  FROM CAR C,CAR_TYPE CT,CAR_MAKE CM where C.CAR_TYPE_ID=CT.ID AND CT.CAR_MAKE_ID=CM.ID AND CUSTOMER_ID=" + customerId);
			List<CarBe> carArr = new ArrayList<CarBe>();

			while (rs.next()) {
				CarMakeBe carMake = new CarMakeBe();
				carMake.setId(rs.getInt("CM_ID"));
				carMake.setName(rs.getString("NAME"));
				
				CarTypeBe carType = new CarTypeBe();
				carType.setId(rs.getInt("CT_ID"));
				carType.setModel(rs.getString("MODEL"));
				carType.setCarMake(carMake);
				
				CarBe car= new CarBe();
				car.setPlateNumber(rs.getString("PLATE_NUMBER"));
				car.setLastServiceDate(rs.getDate("LAST_SERVICE_DATE"));
				car.setLastServiceMileage(rs.getInt("LAST_SERVICE_MILEAGE"));
				car.setLastServiceType(rs.getString("LAST_SERVICE_TYPE"));
				car.setCustomerId(rs.getInt("CUSTOMER_ID") );
				car.setYear(rs.getInt("YEAR"));
				car.setCarType(carType);				
				carArr.add(car);
			}

			return carArr;
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


	public ArrayList<CarBe> getAllCars() {
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			stmt = connection.createStatement();

			rs = stmt.executeQuery("SELECT C.PLATE_NUMBER, C.LAST_SERVICE_DATE, C.LAST_SERVICE_MILEAGE, C.LAST_SERVICE_TYPE, C.CUSTOMER_ID, C.YEAR, CT.ID AS CT_ID, CT.MODEL, CM.ID AS CM_ID, CM.NAME  FROM CAR C,CAR_TYPE CT,CAR_MAKE CM where C.CAR_TYPE_ID=CT.ID AND CT.CAR_MAKE_ID=CM.ID");
			ArrayList<CarBe> carArr = new ArrayList<CarBe>();

			while (rs.next()) {
				CarMakeBe carMake = new CarMakeBe();
				carMake.setId(rs.getInt("CM_ID"));
				carMake.setName(rs.getString("NAME"));
				
				CarTypeBe carType = new CarTypeBe();
				carType.setId(rs.getInt("CT_ID"));
				carType.setModel(rs.getString("MODEL"));
				carType.setCarMake(carMake);
				
				CarBe car= new CarBe();
				car.setPlateNumber(rs.getString("PLATE_NUMBER"));
				car.setLastServiceDate(rs.getDate("LAST_SERVICE_DATE"));
				car.setLastServiceMileage(rs.getInt("LAST_SERVICE_MILEAGE"));
				car.setLastServiceType(rs.getString("LAST_SERVICE_TYPE"));
				car.setCustomerId(rs.getInt("CUSTOMER_ID") );
				car.setYear(rs.getInt("YEAR"));

				car.setCarType(carType);
				carArr.add(car);
			}

			return carArr;
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

	public boolean insertCar(CarBe car) {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			ps = connection.prepareStatement(
					"INSERT INTO CAR (PLATE_NUMBER, CAR_TYPE_ID, CUSTOMER_ID, LAST_SERVICE_DATE, LAST_SERVICE_MILEAGE, LAST_SERVICE_TYPE, PURCHASE_DATE, YEAR) "
					+ "VALUES (?,?,?,?,?,?,?,?)");
			ps.setString(1, car.getPlateNumber());
			ps.setInt(2, car.getCarType().getId());
			ps.setInt(3, car.getCustomerId());
			ps.setDate(4, car.getLastServiceDate());
			ps.setInt(5, car.getLastServiceMileage());
			ps.setString(6,car.getLastServiceType());
			ps.setDate(7, car.getPurchaseDate());
			ps.setInt(8, car.getYear());
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

	public boolean deleteCar(String plateNumber) {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			ps = connection.prepareStatement("DELETE FROM CAR WHERE PLATE_NUMBER=?");
			ps.setString(1, plateNumber);
			
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
	
	public boolean updateLastServiceInfo(String plateNumber, LocalDateTime lastServiceDate, int lastServiceMileage) {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			ps = connection.prepareStatement("UPDATE CAR SET LAST_SERVICE_DATE=?, LAST_SERVICE_MILEAGE=? WHERE PLATE_NUMBER=?");
			ps.setTimestamp(1, Timestamp.valueOf(lastServiceDate));
			ps.setInt(2, lastServiceMileage);
			ps.setString(3, plateNumber);
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
