package common;
import java.sql.SQLException;
import java.io.IOException;
import java.util.*;
import javax.swing.JFrame;


public abstract class User {
	private String account;
	private String password;
	private unsigned int deposit;

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
			case 6:
				System.out.print(" ‰»Î–¬√‹¬Î: ");
				String new_password = scanner.next();
				try {
					if(this.changeUserInfo(new_password)){
						System.out.print("");
					}
					else{
						System.out.println("");
					}
				} 
				catch (SQLException e) {
					System.out.println(e.getMessage());	
				}
				break;
			default:
				System.out.println("Undefined number");
				break;
			}	
		}
	

	public boolean saveMoney(unsigned int sum){
		return false;

	}

	public boolean withdrewMoney(unsigned int sum){
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


	public void exitSystem(){
		System.out.println("System exit..");
		System.exit(0);
	}


	public String getAccount() {
		return account;
	}

	// public void setAccount(String account) {
	// 	this.account = account;
	// }

	public String getPassword() {
		return password;
	}

	public void setPassword(String new_password) {
		this.password = new_password;
	}

	public unsigned int getDeposit() {
		return deposit;
	}

	public void setDeposit(unsigned int new_deposit) {
		this.deposit = new_deposit;
	}

}
