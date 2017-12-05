package Origin;

import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Hashtable;

class DataProcessing {

//	private static boolean connectToDB=false;
	
	static Hashtable<String, String> IDs; // ID code -> user account
	static Hashtable<String, User> users; // user account -> user

	static {
		IDs = new Hashtable<String, String>();
		users = new Hashtable<String, User>();
		IDs.put("112233", "sample");
		users.put("sample", new User("sample", "sample"));
		Init();
	}
	
	public static void Init(){
		// connect to database
	}
	
	public static Enumeration<User> getAllAccount() throws SQLException{
		Enumeration<User> e  = users.elements();
		return e;
	}

	public static Enumeration<String> getAllUser() throws SQLException{	
		Enumeration<String> e  = IDs.elements();
		return e;
	}		
	
	public static User searchUser(String account, String password) throws SQLException {		
		if (users.containsKey(account)) {
			User temp = users.get(account);
			if ((temp.getPassword()).equals(password))
				return temp;
		}
		return null;
	}	

	public static boolean insertUser(String ID, String account, String password) throws SQLException{
		User user;
		if (IDs.containsKey(ID)) return false;	
		if (users.containsKey(account)) return false;
		user = new User(account,password);
		users.put(account, user);
		return true;
	}
	
	public static boolean deleteUser(String account, String password) throws SQLException{		
		if (users.containsKey(account)){
			users.remove(account);
			return true;
		} else
			return false;
	}	
            
}
