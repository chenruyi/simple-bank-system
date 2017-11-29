package Origin;

import java.util.InputMismatchException;
import java.util.Scanner;

public class User {
	private String account;
	private String password;
	private int deposit;

	Scanner scanner = new Scanner(System.in);
	
	protected User(String account, String password){
		this.account = account;
		this.password = password;
		deposit = 0;
	}
	
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
		if(this.setDeposit(new_deposit)){
			System.out.println("Deposit: " + this.getDeposit());
		}else {
			System.out.println("Operation failed!");
		}
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
