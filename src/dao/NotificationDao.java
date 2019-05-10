package dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import be.NotificationBe;
import be.PartOrderBe;
import conn.DBConnection;
import session.SESSION;

public class NotificationDao {
	private static final String TABLE_NAME1 = "NOTIFICATION";
	private static final String TABLE_NAME2 = "PART_ORDER";
	public ArrayList<NotificationBe> getAllNotifications(){
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		ArrayList<NotificationBe> notificationBeList = new ArrayList<NotificationBe>();
		try {
			connection = DBConnection.getInstance().getConnection();
			stmt = connection.createStatement();
			
			rs = stmt.executeQuery(
					"SELECT N.ID,"
					+ "N.NOTIFICATION_DATE,"
					+ "O.ID AS OID,"
					+ "O.TO_ID,"
					+ "o.from_service_id,"
					+ "o.from_supplier_id,"
					+ "o.expected_date,"
					+ "o.delivery_date "
					+ "FROM "+TABLE_NAME1+" N, "
					+TABLE_NAME2+" O "
					+ "WHERE N.ORDER_ID = O.ID "
					+ "AND O.TO_ID = "+SESSION.getSession().getServiceCenterId());
			while(rs.next()) {
				NotificationBe notificationBe = new NotificationBe();
				PartOrderBe partOrderBe = new PartOrderBe();
				notificationBe.setId(rs.getInt("ID"));
				notificationBe.setNotificationDate(rs.getDate("NOTIFICATION_DATE"));
				partOrderBe.setId(rs.getInt("OID"));
				partOrderBe.setToId(rs.getInt("TO_ID"));
				partOrderBe.setFromSupplierId(rs.getInt("FROM_SUPPLIER_ID"));
				partOrderBe.setFromServiceId(rs.getInt("FROM_SERVICE_ID"));
				partOrderBe.setExpectedDate(rs.getDate("EXPECTED_DATE"));
				partOrderBe.setDeliveryDate(rs.getDate("DELIVERY_DATE"));
				notificationBe.setPartOrderBe(partOrderBe);
				notificationBeList.add(notificationBe);
			}
			return notificationBeList;
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
