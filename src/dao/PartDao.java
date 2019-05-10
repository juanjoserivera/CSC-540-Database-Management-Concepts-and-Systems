package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import be.BasicServiceBe;
import be.CarMakeBe;
import be.CarTypeBe;
import be.PartBe;
import be.PartNumberBe;
import conn.DBConnection;

/**
 * @author Juan Jose Rivera
 *
 */
public class PartDao {

	public PartBe getPart(int id) {
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			stmt = connection.createStatement();

			rs = stmt.executeQuery("SELECT P.ID, P.NAME, P.UNIT_PRICE, P.CAR_MAKE_ID, P.WARRANTY_MONTHS, C.NAME AS CAR_NAME "
					+ "FROM PART P, CAR_MAKE C "
					+ "WHERE C.ID=P.CAR_MAKE_ID "
					+ "AND P.ID=" + id);
			if (rs.next()) {

				PartBe partBe = new PartBe();
				CarMakeBe carMakeBe = new CarMakeBe();

				partBe.setId(rs.getInt("ID"));
				partBe.setName(rs.getString("NAME"));
				partBe.setUnitPrice(rs.getInt("UNIT_PRICE"));
				partBe.setCarMakeId(rs.getInt("CAR_MAKE_ID"));
				partBe.setWarrantyMonths(rs.getInt("WARRANTY_MONTHS"));
				
				carMakeBe.setId(rs.getInt("CAR_MAKE_ID"));
				carMakeBe.setName(rs.getString("CAR_NAME"));
				
				partBe.setCarMakeBe(carMakeBe);

				return partBe;
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

	public ArrayList<PartNumberBe> getPartByBasicServiceId(int basicServiceId) {
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			stmt = connection.createStatement();

			rs = stmt.executeQuery(
					"SELECT P.ID, P.NAME, P.UNIT_PRICE, P.CAR_MAKE_ID, P.WARRANTY_MONTHS, PS.NUM FROM PART P, PARTXBASIC_SERVICE PS "
							+ "WHERE P.ID=PS.PART_ID AND PS.BASIC_SERVICE_ID=" + basicServiceId);

			ArrayList<PartNumberBe> partNumBeList = new ArrayList<PartNumberBe>();

			while (rs.next()) {
				PartNumberBe partNumBe = new PartNumberBe();
				PartBe partBe = new PartBe();

				partBe.setId(rs.getInt("ID"));
				partBe.setName(rs.getString("NAME"));
				partBe.setUnitPrice(rs.getInt("UNIT_PRICE"));
				partBe.setCarMakeId(rs.getInt("CAR_MAKE_ID"));
				partBe.setWarrantyMonths(rs.getInt("WARRANTY_MONTHS"));
				partNumBe.setNum(rs.getInt("NUM"));

				partNumBe.setPartBe(partBe);

				partNumBeList.add(partNumBe);
			}

			return partNumBeList;
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

	public ArrayList<PartNumberBe> getPartsByMaintenanceServiceId(int maintenanceServiceId) {
		return getPartsByQuery(
				"SELECT P.ID, P.NAME, P.UNIT_PRICE, P.CAR_MAKE_ID, P.WARRANTY_MONTHS, PS.NUM "
				+ "FROM PART P, PARTXBASIC_SERVICE PS, BSXMS BM "
				+ "WHERE P.ID=PS.PART_ID AND PS.BASIC_SERVICE_ID=BM.BASIC_SERVICE_ID AND BM.MAINTENANCE_SERVICE_ID=" + maintenanceServiceId);
	}
	
	public ArrayList<PartNumberBe> getPartsByRepairServiceId(int repairServiceId) {
		return getPartsByQuery(
				"SELECT P.ID, P.NAME, P.UNIT_PRICE, P.CAR_MAKE_ID, P.WARRANTY_MONTHS, PS.NUM "
				+ "FROM PART P, PARTXBASIC_SERVICE PS, BSXRS BR "
				+ "WHERE P.ID=PS.PART_ID AND PS.BASIC_SERVICE_ID=BR.BASIC_SERVICE_ID AND BR.REPAIR_SERVICE_ID=" + repairServiceId);
	}
	
	public ArrayList<PartNumberBe> getPartsByQuery(String query) {
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(query);
			ArrayList<PartNumberBe> partNumBeList = new ArrayList<PartNumberBe>();
			while (rs.next()) {
				partNumBeList.add(rsToPartNumberBe(rs));
			}
			return partNumBeList;
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
	
	private static PartNumberBe rsToPartNumberBe(ResultSet rs) throws SQLException {
		PartNumberBe partNumBe = new PartNumberBe();
		PartBe partBe = new PartBe();
		partBe.setId(rs.getInt("ID"));
		partBe.setName(rs.getString("NAME"));
		partBe.setUnitPrice(rs.getInt("UNIT_PRICE"));
		partBe.setCarMakeId(rs.getInt("CAR_MAKE_ID"));
		partBe.setWarrantyMonths(rs.getInt("WARRANTY_MONTHS"));
		partNumBe.setNum(rs.getInt("NUM"));
		partNumBe.setPartBe(partBe);	
		return partNumBe;
	}
}
