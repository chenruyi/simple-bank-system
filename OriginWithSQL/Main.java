package OriginWithSQL;
import java.sql.SQLException;
import java.util.*;
import SQLProcess.*;

public class Main {

	public static void main(String[] args) throws SQLException {
		try(Scanner scanner = new Scanner(System.in)){
			while(true){
				System.out.println("---Menu---");
				System.out.println("0.退出");
				System.out.println("1.登陆");
				System.out.println("2.开户");
				System.out.println("3.挂失");
				System.out.println("----------");
				int choice;
				while(true){
					try{				
						System.out.print("Choose: ");
						choice = scanner.nextInt();		
						break;
						}
					catch(InputMismatchException e){
						System.out.println("Illegal input, please try again");
						scanner.nextLine();
					}
				}
				
				switch(choice){
				case 0: //退出
					System.out.println("Quit...");
					return;
				case 1: //登陆
					System.out.print("卡号: ");
					String account = scanner.next();
					System.out.print("密码: ");
					String password = scanner.next();
					if(SQLProcess.searchUser(account, password)){
						User user = new User(account, password);
						user.showMenu();
						break;
					}
					System.out.println("登陆失败！");
					break;
				case 2: //开户
					System.out.print("身份证号: ");
					String ID = scanner.next();
					System.out.print("卡号: ");
					String new_account = scanner.next();
					System.out.print("密码: ");
					String new_password = scanner.next();
//					System.out.print("再次输入密码: ");
//					String check_password = scanner.next();
//					if(check_password != new_password) {
//						System.out.println("密码不一致！");
//						break;
//					}
					System.out.print("余额: ");
					float balance = scanner.nextFloat();
					while(true){
						if(SQLProcess.addUser(ID, new_account, new_password, "0", balance))
							System.out.println("开户成功！");
						else
							System.out.println("开户失败！");
						break;
					}
					break;
				case 3: //挂失
					System.out.print("卡号: ");
					String user_account = scanner.next();
					System.out.print("密码: ");
					String user_password = scanner.next();
					if(SQLProcess.searchUser(user_account, user_password)){
						if(SQLProcess.changeLoss(user_account, "1"))
							System.out.println("挂失成功！");
						else
							System.out.println("挂失失败！");
						break;
					}else {
						System.out.println("信息不正确！");
					}
					break;
				default:
					System.out.println("Undefined choice");
					break;
				}
			}
		}
	}
}
