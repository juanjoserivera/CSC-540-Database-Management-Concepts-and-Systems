package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

import be.CarTypeBe;
import be.DistributorBe;
import be.DistributorPartBe;
import be.PartBe;
import conn.DBConnection;

/**
 * @author Juan Jose Rivera
 *
 */
public class DistributorDao {
	
	public DistributorBe getDistributorByPartId(int partId) {
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			stmt = connection.createStatement();

			rs = stmt.executeQuery("SELECT D.ID, D.NAME,PS.DELIVERY_WINDOW " + 
					"FROM DISTRIBUTOR D,PART_SUPPLIER PS " + 
					"WHERE D.ID=PS.DISTRIBUTOR_ID " + 
					"AND PS.PART_ID=" + partId);
			
			if (rs.next()) {
				DistributorBe distributorBe = new DistributorBe();
				DistributorPartBe distributorPartBe = new DistributorPartBe();
				PartBe partBe = new PartBe();
				
				distributorBe.setId(rs.getInt("ID"));
				distributorBe.setName(rs.getString("NAME"));
				
				partBe.setId(partId);
				
				distributorPartBe.setDeliveryWindow(rs.getInt("DELIVERY_WINDOW"));
				
				distributorPartBe.setPartBe(partBe);
				distributorBe.setDistributorPartList(new ArrayList<DistributorPartBe>(Arrays.asList(distributorPartBe)));

				return distributorBe;
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
	
	public String getDistributorName(int distId) {
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			stmt = connection.createStatement();

			rs = stmt.executeQuery("SELECT NAME FROM DISTRIBUTOR WHERE ID= " + distId);
			
			if (rs.next()) {
				String distName = rs.getString("NAME");
				return distName;
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
