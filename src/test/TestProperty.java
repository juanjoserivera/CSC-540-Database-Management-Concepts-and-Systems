package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Juan Jose Rivera
 *
 */
public class TestProperty {

	public static void main(String[] args) {
		
		try {
			Properties properties = new Properties();
			properties.load(new FileInputStream("props/config.properties"));
			String url;
			String username;
			String password;

			url = properties.getProperty("url");
			username = properties.getProperty("dbuser");
			password = properties.getProperty("dbpassword");
			
			System.out.println(System.getProperty("user.dir"));

			System.out.println(url);
			System.out.println(username);

			System.out.println(password);

		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}

	}

}
