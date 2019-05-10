package conn;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.imageio.stream.FileImageInputStream;

/**
 * @author Juan Jose Rivera
 *
 */
public class DBConnection {
    private static DBConnection instance;
    private Connection connection;
    private String url;
    private String username;
    private String password;

    private DBConnection() throws SQLException {
        try {
        	Properties properties = new Properties();
        	properties.load(new FileInputStream("props/config.properties"));
        	//properties.load(DBConnection.class.getClassLoader().getResourceAsStream("config.properties"));

        	Class.forName(properties.getProperty("driver"));
        	url=properties.getProperty("url");
        	username=properties.getProperty("dbuser");
        	password=properties.getProperty("dbpassword");
        	
            this.connection = DriverManager.getConnection(url, username, password);
            
        } catch (ClassNotFoundException ex) {
            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
        }catch(Exception e) {
            System.out.println("Database Connection Creation Failed : " + e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static DBConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DBConnection();
        } else if (instance.getConnection().isClosed()) {
            instance = new DBConnection();
        }

        return instance;
    }
}
