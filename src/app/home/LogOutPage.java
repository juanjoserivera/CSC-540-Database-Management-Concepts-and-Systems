package app.home;

import java.sql.SQLException;

import app.Page;
import conn.DBConnection;
import session.SESSION;

/**
 * @author Juan Jose Rivera
 *
 */
public class LogOutPage extends Page {
	
	public  String[] getOptions() {
		return null;
	}

	public  Page[] getPages() {
		return null;
	}

	public void open() {
		System.out.println("Good Bye "+SESSION.getSession().getFullName()+" ("+SESSION.getSession().getRoleName()+")\n");

		try {
			DBConnection.getInstance().getConnection().close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		System.exit(0);
	}

}
