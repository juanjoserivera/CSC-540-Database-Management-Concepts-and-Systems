package dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import be.ServiceDetailsBe;

import conn.DBConnection;
public class ServiceDetailsDao {
	public ArrayList<ServiceDetailsBe> getAllMS(){
		Connection connection = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			connection = DBConnection.getInstance().getConnection();
			stmt = connection.createStatement();
			ArrayList<ServiceDetailsBe> arrayList= new ArrayList<ServiceDetailsBe>();
			rs = stmt.executeQuery(" SELECT X.name,x.model,x.mileage ,x.type, LISTAGG(x.basic_service_id, ',') WITHIN GROUP (ORDER BY x.basic_service_id) as BS "+
					" FROM (SELECT m.name,c.model, s.mileage,s.type,s.id, B.basic_service_id"+
					" FROM CAR_MAKE M,CAR_TYPE C, MAINTENANCE_SERVICE S, BSXMS B"+
					" WHERE c.car_make_id = m.id"+
					" AND s.car_type_id = c.id"+
					" AND s.id = b.maintenance_service_id) X"+
					" Group by X.name,x.model,x.mileage ,x.type"+
					" ORDER BY X.NAME,X.model,x.type");
			while (rs.next()) {
				ServiceDetailsBe displayServiceBe = new ServiceDetailsBe();
				displayServiceBe.setMake(rs.getString("NAME"));
				displayServiceBe.setModel(rs.getString("MODEL"));
				displayServiceBe.setMiles(rs.getString("MILEAGE"));
				displayServiceBe.setType(rs.getString("TYPE"));
				displayServiceBe.setBasicServices(rs.getString("BS"));
				arrayList.add(displayServiceBe);
			}
			return arrayList;
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
