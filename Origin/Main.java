package Origin;
import java.sql.SQLException;
import java.util.*;

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
				case 2: //开户
					System.out.print("身份证号: ");
					String ID = scanner.next();
					if(DataProcessing.IDs.containsKey(ID)) {
						System.out.println("该身份证已注册！");
						break;
					}else {
						System.out.print("用户名: ");
						String new_account = scanner.next();
						System.out.print("密码: ");
						String new_password = scanner.next();
						System.out.print("再次输入密码: ");
						String check_password = scanner.next();
						if(check_password != new_password) {
							System.out.println("密码不一致！");
							break;
						}
						while(true){
							try{
								if(DataProcessing.insertUser(ID, new_account, new_password))
									System.out.println("开户成功！");
								else
									System.out.println("用户名已存在！");
								break;
							}
							catch(SQLException e){
								System.out.println(e.getMessage());
								System.out.println("连接失败！");
							}
						}
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
