package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import be.BasicServiceBe;
import be.CarMakeBe;
import be.PartBe;
import be.ServiceCenterInventoryBe;
import be.DisplayInventoryBe;
import conn.DBConnection;
import session.SESSION;

/**
 * @author Juan Jose Rivera
 *
 */
public class ServiceCenterInventoryDao {
	
	public ServiceCenterInventoryBe getPartDetailsByPartAndServiceCentre(int partId,int serviceCenterId) {
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			stmt = connection.createStatement();

			rs = stmt.executeQuery("SELECT PART_ID,BOOKED_QTY,FREE_QTY,MIN_ORDER_QTY,ORDER_THRESHOLD,SERVICE_CENTER_ID "
					+ "FROM SERVICE_CENTER_INVENTORY WHERE SERVICE_CENTER_ID=" + serviceCenterId +" AND PART_ID = "+partId );
			if (rs.next()) {
				ServiceCenterInventoryBe serviceCenterPartBe = new ServiceCenterInventoryBe();
				
				serviceCenterPartBe.setPartId(rs.getInt("PART_ID"));
				serviceCenterPartBe.setBookedQty(rs.getInt("BOOKED_QTY"));
				serviceCenterPartBe.setFreeQty(rs.getInt("FREE_QTY"));
				serviceCenterPartBe.setMinOrderQty(rs.getInt("MIN_ORDER_QTY"));
				serviceCenterPartBe.setOrderThreshold(rs.getInt("ORDER_THRESHOLD"));
				serviceCenterPartBe.setServiceCenterId(rs.getInt("SERVICE_CENTER_ID"));
				return serviceCenterPartBe;
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

	public ArrayList<ServiceCenterInventoryBe> getServCentPartInvbyServCentId(int serviceCenterId) {
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			stmt = connection.createStatement();

			rs = stmt.executeQuery("SELECT PART_ID,BOOKED_QTY,FREE_QTY,MIN_ORDER_QTY,ORDER_THRESHOLD,SERVICE_CENTER_ID "
					+ "FROM SERVICE_CENTER_INVENTORY WHERE SERVICE_CENTER_ID=" + serviceCenterId);

			ArrayList<ServiceCenterInventoryBe> serviceCenterPartList = new ArrayList<ServiceCenterInventoryBe>();

			while (rs.next()) {
				ServiceCenterInventoryBe serviceCenterPartBe = new ServiceCenterInventoryBe();
				
				serviceCenterPartBe.setPartId(rs.getInt("PART_ID"));
				serviceCenterPartBe.setBookedQty(rs.getInt("BOOKED_QTY"));
				serviceCenterPartBe.setFreeQty(rs.getInt("FREE_QTY"));
				serviceCenterPartBe.setMinOrderQty(rs.getInt("MIN_ORDER_QTY"));
				serviceCenterPartBe.setOrderThreshold(rs.getInt("ORDER_THRESHOLD"));
				serviceCenterPartBe.setServiceCenterId(rs.getInt("SERVICE_CENTER_ID"));
				
			

				serviceCenterPartList.add(serviceCenterPartBe);
			}

			return serviceCenterPartList;
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
	
	public ArrayList<DisplayInventoryBe> displayInventory(int serviceCenterId) {
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			stmt = connection.createStatement();

			
			rs = stmt.executeQuery("SELECT PART_ID,PART.NAME AS P_NAME,PART.UNIT_PRICE,CAR_MAKE.ID AS M_ID,CAR_MAKE.NAME AS M_NAME,BOOKED_QTY,FREE_QTY,MIN_ORDER_QTY,ORDER_THRESHOLD,SERVICE_CENTER_ID " 
					+"FROM SERVICE_CENTER_INVENTORY,PART, CAR_MAKE " 
					+"WHERE SERVICE_CENTER_INVENTORY.PART_ID=PART.ID "
					+ "AND PART.CAR_MAKE_ID = CAR_MAKE.ID "
					+ "AND SERVICE_CENTER_ID=" + serviceCenterId);

			ArrayList<DisplayInventoryBe> displayInventorytList = new ArrayList<DisplayInventoryBe>();

			while (rs.next()) {
				DisplayInventoryBe displayInventoryBe = new DisplayInventoryBe();
				
				displayInventoryBe.setPartId(rs.getInt("PART_ID"));
				displayInventoryBe.setPartName(rs.getString("P_NAME"));
				displayInventoryBe.setPartMake(rs.getString("M_NAME"));
				displayInventoryBe.setQuantity(rs.getInt("FREE_QTY"));
				displayInventoryBe.setUnitPrice(rs.getInt("UNIT_PRICE"));
				displayInventoryBe.setMinQty(rs.getInt("MIN_ORDER_QTY"));
				displayInventoryBe.setMinOrdThr(rs.getInt("ORDER_THRESHOLD"));

				displayInventorytList.add(displayInventoryBe);
			}

			return displayInventorytList;
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
	public boolean bookPartForService(int partId, int qty) {
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			int serviceCenterId =SESSION.getSession().getServiceCenterId();
		connection = DBConnection.getInstance().getConnection();
		ps = connection.prepareStatement(
				"UPDATE SERVICE_CENTER_INVENTORY SET FREE_QTY = (FREE_QTY - ?), "
				+ "BOOKED_QTY = (BOOKED_QTY +?) WHERE PART_ID = ? AND SERVICE_CENTER_ID = ?");
		ps.setInt(1, qty);
		ps.setInt(2, qty);
		ps.setInt(3, partId);
		ps.setInt(4, serviceCenterId);
		int i = ps.executeUpdate();
		if (i==1) {
			return true;
		}
		}
		catch (SQLException ex) {
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
