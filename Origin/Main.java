package Origin;
import java.sql.SQLException;
import java.io.IOException;
import javax.swing.JFrame;
import java.util.Enumeration;
import java.util.Hashtable;
import java.sql.*;
import java.util.*;


public class Main {

	public static void main(String[] args) throws SQLException {
		try(Scanner scanner = new Scanner(System.in)){
			while(true){
				System.out.println("---Menu---");
				System.out.println("1.登陆");
				System.out.println("2.退出");
				System.out.println("----------");
				int choice;
				while(true){
					try{				
						System.out.print("choose: ");
						choice = scanner.nextInt();		
						break;
						}
					catch(InputMismatchException e){
						System.out.println("Illegal input, please try again");
						scanner.nextLine();
					}
				}
				
				switch(choice){
				case 1:
					System.out.print("用户名: ");
					String account = scanner.next();
					System.out.print("密码: ");
					String password = scanner.next();
					User user = null;
					while(true){
						try{
							user = DataProcessing.searchUser(account, password);
							break;
						}
						catch(SQLException e){
							System.out.println(e.getMessage());
							System.out.println("Connecting failed...");
						}
					}
					if(user != null){
						System.out.println("...Loading");
						user.showMenu();
					}
					else{
						System.out.println("...failed");
					}
					break;
				case 2:
					System.out.println("Quit...");
					return;
				default:
					break;
				}
			}
		}
	}
}

class User {
	private String account;
	private String password;
	private int deposit;

	Scanner scanner = new Scanner(System.in);
	
	protected User(String account, String password){
		this.account = account;
		this.password = password;
		deposit = 0;
	}
	
	// public boolean changeUserInfo(String password) throws SQLException{
	// 	if (DataProcessing.updateUser(account, password, deposit)){
	// 		this.password = password;
	// 		return true;
	// 	}else
	// 		return false;
	// }
	
	public void showMenu() {
		while(true){
			System.out.print(
					  "----Menu----\n"
					+ "0.退出\n"
					+ "1.存款\n"
					+ "2.取款\n"
					+ "3.挂失\n"
					+ "4.销户\n"
					+ "5.转账\n"
					+ "6.修改密码\n"
					+ "7.查看余额\n"
					+ "8.交易历史\n"
					+ "------------\n"
					+ "choose: ");	

			int choice;
			while(true){
				try{
					choice = scanner.nextInt();
					break;
				}
				catch(InputMismatchException e){
					System.out.println("Illegal Input, please try again");
					scanner.nextLine();
					System.out.print("choose: ");
				}
			}
			switch(choice){
			case 0:
				exitSystem();
				break;
			// case 6:
			// 	System.out.print(": ");
			// 	String new_password = scanner.next();
			// 	try {
			// 		if(this.changeUserInfo(new_password)){
			// 			System.out.print("");
			// 		}
			// 		else{
			// 			System.out.println("");
			// 		}
			// 	} 
			// 	catch (SQLException e) {
			// 		System.out.println(e.getMessage());	
			// 	}
			// 	break;
			default:
				System.out.println("Undefined number");
				break;
			}	
		}
	}

	public boolean saveMoney(){
		System.out.println("Input sum of money:");
		String str = scanner.next();
		int sum = Integer.parseInt(str);
		int new_deposit = this.getDeposit() + sum;
		// try {
			if(this.setDeposit(new_deposit)){
				System.out.println("Deposit: " + this.getDeposit());
			}else {
				System.out.println("Operation failed!");
			}
		// }catch(SQLException e){
		// 	System.out.println(e.getMessage());
		// }
		return false;
	}

	public boolean withdrewMoney(int sum){
		return false;

	}

	public boolean reportLoss(){
		return false;

	}

	public boolean cancelAccount(){
		return false;

	}

	public boolean transfer(){
		return false;

	}

	public boolean changePassword(){
		return false;

	}

	public boolean showDeposit(){
		return false;

	}

	public boolean showHistory(){
		return false;

	}


	public void exitSystem() {
		System.out.println("System exit..");
		System.exit(0);
	}


	public String getAccount() {
		return account;
	}


	public String getPassword() {
		return password;
	}

	public boolean setPassword(String new_password) {
		this.password = new_password;
		return true;
	}

	public int getDeposit() {
		return deposit;
	}

	public boolean setDeposit(int new_deposit) {
		this.deposit = new_deposit;
		return true;
	}
}


class DataProcessing {

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
	
	public static Enumeration<User> getAllAccount() throws SQLException{
		Enumeration<User> e  = users.elements();
		return e;
	}

	public static Enumeration<String> getAllUser() throws SQLException{	
		Enumeration<String> e  = IDs.elements();
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
		} else
			return false;
	}	
            
}
