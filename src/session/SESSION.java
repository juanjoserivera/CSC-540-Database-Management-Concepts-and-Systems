package session;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import be.SessionBe;
import bl.UsersBl;
import conn.DBConnection;
import dao.UsersDao;

/**
 * @author Juan Jose Rivera
 *
 */
public class SESSION {

	private static SessionBe sessionBe;
	 

	public static boolean login(String username , String password ) {
		UsersBl usersBl = new UsersBl();
		boolean result = false;
		
		if(result= usersBl.login(username, password)) {
			sessionBe =usersBl.getUserInfoByUserId(username);
			
		}else {
			sessionBe=null;
		}
		
		return result;

	}
	
	public static SessionBe getSession() {
		return sessionBe;
	}



}
