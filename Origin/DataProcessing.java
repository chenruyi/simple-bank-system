package Origin;
import java.util.Enumeration;
import java.util.Hashtable;
import java.sql.*;

public  class DataProcessing {

	private static boolean connectToDB=false;
	
	static Hashtable<String, String> IDs;
	static Hashtable<String, User> users;

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
	
	public static Enumeration<String> getAllAccount() throws SQLException{
		Enumeration<User> e  = users.elements();
		return e;
	}

	public static Enumeration<User> getAllUser() throws SQLException{	
		Enumeration<Stirng> e  = IDs.elements();
		return e;
	}
		


	
	// public static boolean updateUser(String account, String password) throws SQLException{
	// 	User user;	
	// 	if (users.containsKey(account)) {
	// 		unsigned int deposit = searchUser(account).getDeposit();
	// 		user = new User(account, password);
	// 		user.setDeposit(deposit);
	// 		users.put(account, user);
	// 		return true;
	// 	}else
	// 		return false;
	// }
	
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
		}else
			return false;
	}	
            
	// public void disconnectFromDB() {
	// 	if ( connectToDB ){
	// 		// close Statement and Connection                                                     
	// 		connectToDB = false;                                      
	// 	} 
 //   }           

	
	public static void main(String[] args) {		

	}
	
}
