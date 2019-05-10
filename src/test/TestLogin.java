
package test;

import bl.UsersBl;
import session.SESSION;

/**
 * @author Juan Jose Rivera
 *
 */
public class TestLogin {
	
	public static void main(String args[]) {
		
		
		SESSION.login("1", "950932130");
		System.out.println(SESSION.getSession());
		
		SESSION.login("j@gmail.com", "1234");
		System.out.println(SESSION.getSession());
		
//		SESSION.login("2", "634622236");
//		System.out.println(SESSION.getSession());
//		
//		SESSION.login("3", "291056276");
//		System.out.println(SESSION.getSession());
//		
//		SESSION.login("4", "911639633");
//		System.out.println(SESSION.getSession());
//		
//		SESSION.login("5", "1001");
//		System.out.println(SESSION.getSession());
//		
//		SESSION.login("6", "1002");
//		System.out.println(SESSION.getSession());
//		
//		SESSION.login("7", "1003");
//		System.out.println(SESSION.getSession());
//
//		SESSION.login("8", "1004");
//		System.out.println(SESSION.getSession());
//		

		
	}

}
