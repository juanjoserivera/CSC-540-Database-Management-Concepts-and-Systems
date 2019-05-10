package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.CallableStatement;
import java.util.ArrayList;

import be.CarTypeBe;
import be.DisplayInventoryBe;
import be.DisplayOrderBe;
import be.DistributorWindowPartBe;
import be.PartOrderBe;
import conn.DBConnection;
import session.SESSION;


/**
 * @author Juan Jose Rivera
 *
 */
public class PartOrderDao {

	public int getMaxId() {
			Connection connection = null;
			ResultSet rs = null;
			Statement stmt = null;
			try {
				connection = DBConnection.getInstance().getConnection();
				stmt = connection.createStatement();

				rs = stmt.executeQuery("SELECT MAX(ID) AS MAX_ID FROM PART_ORDER");
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

	public boolean insert(PartOrderBe partOrderBe) {

			Connection connection = null;
			PreparedStatement ps = null;
			try {
				connection = DBConnection.getInstance().getConnection();
				ps = connection.prepareStatement(
						"INSERT INTO PART_ORDER (" +
						"    ID," +
						"    QTY," +
						"    ORDER_DATE," +
						"    EXPECTED_DATE," +
						"    DELIVERY_DATE," +
						"    COST," +
						"    STATUS," +
						"    FROM_SERVICE_ID," +
						"    TO_ID," +
						"    PART_ID," +
						"    FROM_SUPPLIER_ID" +
						") VALUES (" +
						"    ?," +
						"    ?," +
						"    ?," +
						"    ?," +
						"    ?," +
						"    ?," +
						"    ?," +
						"    ?," +
						"    ?," +
						"    ?," +
						"    ?" +
						")");

				ps.setInt(1, partOrderBe.getId());
				ps.setInt(2, partOrderBe.getQty());
				ps.setDate(3,partOrderBe.getOrderDate());
				ps.setDate(4, partOrderBe.getExpectedDate());
				ps.setDate(5,partOrderBe.getDeliveryDate());
				ps.setDouble(6, partOrderBe.getCost());
				ps.setString(7, partOrderBe.getStatus());

				if(partOrderBe.getFromServiceId()==0 ) {
					ps.setNull(8, java.sql.Types.INTEGER);
				}else {
					ps.setInt(8, partOrderBe.getFromServiceId());
				}

				ps.setInt(9, partOrderBe.getToId());
				ps.setInt(10,partOrderBe.getPartId());

				if(partOrderBe.getFromSupplierId()==0 ) {
					ps.setNull(11, java.sql.Types.INTEGER);
				}else {
					ps.setInt(11, partOrderBe.getFromSupplierId());
				}



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

	public ArrayList<DisplayOrderBe> getPendingByPartIdAndServiceCenterId(int partId, int serviceCenterId) {
		String query = String.format("SELECT * FROM PART_ORDER WHERE PART_ID=%s and status='Pending' and to_id=%s", partId, serviceCenterId);
		System.out.println(query);
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			stmt = connection.createStatement();

			rs = stmt.executeQuery(query);

			ArrayList<DisplayOrderBe> displayOrdertList = new ArrayList<DisplayOrderBe>();

			while (rs.next()) {
				DisplayOrderBe displayOrderBe = new DisplayOrderBe();
				displayOrderBe.setExpectedDeliveryDate(rs.getDate("EXPECTED_DATE"));
				displayOrderBe.setQty(rs.getInt("QTY"));
				displayOrdertList.add(displayOrderBe);
			}

			return displayOrdertList;
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

public boolean insertTransfer(PartOrderBe partOrderBe) {

		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			ps = connection.prepareStatement(
					"INSERT INTO PART_ORDER (" +
					"    ID," +
					"    QTY," +
					"    ORDER_DATE," +
					"    EXPECTED_DATE," +
					"    COST," +
					"    STATUS," +
					"    FROM_SERVICE_ID," +
					"    TO_ID," +
					"    PART_ID," +
					") VALUES (" +
					"    ?," +
					"    ?," +
					"    ?," +
					"    ?," +
					"    ?," +
					"    ?," +
					"    ?," +
					"    ?," +
					"    ?" +
					")");

			ps.setInt(1, partOrderBe.getId());
			ps.setInt(2, partOrderBe.getQty());
			ps.setDate(3,partOrderBe.getOrderDate());
			ps.setDate(4, partOrderBe.getExpectedDate());
			ps.setDouble(5, partOrderBe.getCost());
			ps.setString(6, partOrderBe.getStatus());
			ps.setInt(7, partOrderBe.getFromServiceId());
			ps.setInt(8, partOrderBe.getToId());
			ps.setInt(9,partOrderBe.getPartId());

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



	public ArrayList<DisplayOrderBe> displayAllOrders() {
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			stmt = connection.createStatement();

			rs = stmt.executeQuery(
					"SELECT PO.ID,S_FROM.NAME AS SUP,S_TO.NAME AS PUR,P.UNIT_PRICE,PO.COST,PO.ORDER_DATE, PO.EXPECTED_DATE,PO.DELIVERY_DATE, M.NAME AS MAKE_NAME, P.NAME AS PART_NAME, PO.QTY, 'SERV' AS ORIGIN,PO.FROM_SERVICE_ID AS ORIGIN_ID,PO.TO_ID AS DESTINATION_ID,PO.STATUS "
					+"FROM PART_ORDER PO, CAR_MAKE M, PART P, SERVICE_CENTER S_FROM, SERVICE_CENTER S_TO "
					+"WHERE PO.PART_ID = P.ID "
					+"AND PO.FROM_SERVICE_ID =S_FROM.ID "
					+"AND PO.TO_ID =S_TO.ID "
					+"AND M.ID =P.CAR_MAKE_ID "
					+"AND PO.FROM_SERVICE_ID IS NOT NULL "
					+"UNION "
					+"SELECT PO.ID,D.NAME AS SUP,S_TO.NAME AS PUR,P.UNIT_PRICE,PO.COST,PO.ORDER_DATE, PO.EXPECTED_DATE,PO.DELIVERY_DATE, M.NAME AS MAKE_NAME, P.NAME AS PART_NAME, PO.QTY, 'DIST' AS ORIGIN,PO.FROM_SUPPLIER_ID AS ORIGIN_ID,PO.TO_ID AS DESTINATION_ID,PO.STATUS "
					+"FROM PART_ORDER PO, CAR_MAKE M, PART P, DISTRIBUTOR D,SERVICE_CENTER S_TO "
					+"WHERE PO.PART_ID = P.ID "
					+"AND PO.FROM_SUPPLIER_ID =D.ID "
					+"AND PO.TO_ID =S_TO.ID "
					+"AND M.ID =P.CAR_MAKE_ID "
					+"AND PO.FROM_SUPPLIER_ID IS NOT NULL");

			ArrayList<DisplayOrderBe> displayOrdertList = new ArrayList<DisplayOrderBe>();

			while (rs.next()) {
				DisplayOrderBe displayOrderBe = new DisplayOrderBe();
				displayOrderBe.setOrderId(rs.getInt("ID"));
				displayOrderBe.setCost(rs.getDouble("COST"));
				displayOrderBe.setUnitPrice(rs.getDouble("UNIT_PRICE"));
				displayOrderBe.setSuplierName(rs.getString("SUP"));
				displayOrderBe.setPurchaserName(rs.getString("PUR"));


				displayOrderBe.setOrderPlacementDate(rs.getDate("ORDER_DATE"));
				displayOrderBe.setExpectedDeliveryDate(rs.getDate("EXPECTED_DATE"));
				displayOrderBe.setActualDeliveryDate(rs.getDate("DELIVERY_DATE"));
				displayOrderBe.setMakeName(rs.getString("MAKE_NAME"));
				displayOrderBe.setPartName(rs.getString("PART_NAME"));
				displayOrderBe.setQty(rs.getInt("QTY"));
				displayOrderBe.setOrigin(rs.getString("ORIGIN"));
				displayOrderBe.setOriginId(rs.getInt("ORIGIN_ID"));
				displayOrderBe.setDestinationId(rs.getInt("DESTINATION_ID"));
				displayOrderBe.setStatus(rs.getString("STATUS"));


				displayOrdertList.add(displayOrderBe);
			}

			return displayOrdertList;
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

	public DisplayOrderBe getOrder(int id) {
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			stmt = connection.createStatement();

			rs = stmt.executeQuery(
					"SELECT PO.ID,S_FROM.NAME AS SUP,S_TO.NAME AS PUR,P.UNIT_PRICE,PO.COST,PO.ORDER_DATE, PO.EXPECTED_DATE,PO.DELIVERY_DATE, M.NAME AS MAKE_NAME, P.NAME AS PART_NAME, PO.QTY, 'SERV' AS ORIGIN,PO.FROM_SERVICE_ID AS ORIGIN_ID,PO.TO_ID AS DESTINATION_ID,PO.STATUS "
							+"FROM PART_ORDER PO, CAR_MAKE M, PART P, SERVICE_CENTER S_FROM, SERVICE_CENTER S_TO "
							+"WHERE PO.PART_ID = P.ID "
							+"AND PO.FROM_SERVICE_ID =S_FROM.ID "
							+"AND PO.TO_ID =S_TO.ID "
							+"AND M.ID =P.CAR_MAKE_ID "
							+"AND PO.FROM_SERVICE_ID IS NOT NULL "
							+ "AND PO.ID =" +id
							+" UNION "
							+"SELECT PO.ID,D.NAME AS SUP,S_TO.NAME AS PUR,P.UNIT_PRICE,PO.COST,PO.ORDER_DATE, PO.EXPECTED_DATE,PO.DELIVERY_DATE, M.NAME AS MAKE_NAME, P.NAME AS PART_NAME, PO.QTY, 'DIST' AS ORIGIN,PO.FROM_SUPPLIER_ID AS ORIGIN_ID,PO.TO_ID AS DESTINATION_ID,PO.STATUS "
							+"FROM PART_ORDER PO, CAR_MAKE M, PART P, DISTRIBUTOR D,SERVICE_CENTER S_TO "
							+"WHERE PO.PART_ID = P.ID "
							+"AND PO.FROM_SUPPLIER_ID =D.ID "
							+"AND PO.TO_ID =S_TO.ID "
							+"AND M.ID =P.CAR_MAKE_ID "
							+"AND PO.FROM_SUPPLIER_ID IS NOT NULL "
							+ "AND PO.ID ="+id);


			if (rs.next()) {
				DisplayOrderBe displayOrderBe = new DisplayOrderBe();

				displayOrderBe.setOrderId(rs.getInt("ID"));
				displayOrderBe.setCost(rs.getDouble("COST"));
				displayOrderBe.setUnitPrice(rs.getDouble("UNIT_PRICE"));
				displayOrderBe.setSuplierName(rs.getString("SUP"));
				displayOrderBe.setPurchaserName(rs.getString("PUR"));


				displayOrderBe.setOrderPlacementDate(rs.getDate("ORDER_DATE"));
				displayOrderBe.setExpectedDeliveryDate(rs.getDate("EXPECTED_DATE"));
				displayOrderBe.setActualDeliveryDate(rs.getDate("DELIVERY_DATE"));
				displayOrderBe.setMakeName(rs.getString("MAKE_NAME"));
				displayOrderBe.setPartName(rs.getString("PART_NAME"));
				displayOrderBe.setQty(rs.getInt("QTY"));
				displayOrderBe.setOrigin(rs.getString("ORIGIN"));
				displayOrderBe.setOriginId(rs.getInt("ORIGIN_ID"));
				displayOrderBe.setDestinationId(rs.getInt("DESTINATION_ID"));
				displayOrderBe.setStatus(rs.getString("STATUS"));

				return displayOrderBe;
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


	public boolean recordDelivery(int order) {
		Connection connection = null;
		CallableStatement cStmt = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			cStmt = connection.prepareCall("{call UPDATEINVENTORY(?,?)}");
			cStmt.setInt(1, SESSION.getSession().getServiceCenterId());
			cStmt.setInt(2, order);
			cStmt.execute();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				cStmt.close();


			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;

	}

	public void deliveryStoredProcedure() {
		Connection connection = null;
		CallableStatement cStmt = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			cStmt = connection.prepareCall("{call UPDATEDELIVERIES(?)}");
			cStmt.setInt(1, SESSION.getSession().getServiceCenterId());
			cStmt.execute();

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			try {
				cStmt.close();


			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public int getTargetServiceCentre(int serviceCentreId,int partId,int orderQty){
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			stmt = connection.createStatement();
			String query = "SELECT service_center_id, MAX(FREE_QTY) FROM service_center_inventory WHERE service_center_id != "+serviceCentreId+" AND PART_ID = "+partId+" AND free_qty-"+orderQty+" >order_threshold GROUP BY service_center_id";
			rs = stmt.executeQuery(query);
			if(rs.next()) {
				return rs.getInt("SERVICE_CENTER_ID");
			}
		}catch (SQLException ex) {
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
	public DistributorWindowPartBe getDistributorDetailsByPart(int partId) {
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		//
		try {
			connection = DBConnection.getInstance().getConnection();
			stmt = connection.createStatement();
			String query = "SELECT s.distributor_id,s.delivery_window,p.unit_price FROM PART_SUPPLIER s, part p "
					+ "WHERE s.part_id = p.id AND s.part_id = "+partId;
			rs = stmt.executeQuery(query);
			if(rs.next()) {
				DistributorWindowPartBe be = new DistributorWindowPartBe();
				be.setDistributorId(rs.getInt("DISTRIBUTOR_ID"));
				be.setPartId(partId);
				be.setUnitCost(rs.getInt("UNIT_PRICE"));
				be.setWindow(rs.getInt("DELIVERY_WINDOW"));
				return be;
			}
		}catch (SQLException ex) {
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
