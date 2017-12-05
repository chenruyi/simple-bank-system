package SQLProcess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
数据库表结构：
1、user：   id_num            //身份证号(char)
           user_num          //卡号(char)
           user_password     //密码(char)
           loss              //是否挂失(char)0为是，1为否
           balance           //余额(float)
2、history:user_num          //卡号(char)
           date              //日期(char)
           time              //时间(char)
           operation         //操作(char)
           operator          //操作人卡号(char)
           balance           //余额(float)
*/
public class SQLLink {
	public static void main(String[] args) {
		Connection connection;
		Statement statement;
		ResultSet resultSet;
		String driverName="com.mysql.jdbc.Driver";                         // 加载数据库驱动类
	    String url="jdbc:mysql://localhost:3306/simple_credit_card";       // 声明数据库的URL
	    String user="root";                                                // 数据库用户
	    String password="123";
	    try{
	    	Class.forName(driverName);		
			connection=DriverManager.getConnection(url, user, password);   
			statement = connection.createStatement( 
			         ResultSet.TYPE_SCROLL_INSENSITIVE,
			         ResultSet.CONCUR_READ_ONLY );
			String sql="select * from user";
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()){
				String id=resultSet.getString("id_num");
				String userNum=resultSet.getString("user_num");
				String pwd=resultSet.getString("user_password");
				String loss=resultSet.getString("loss");
				String balance=resultSet.getString("balance");
				System.out.println(id+";"+userNum+";"+pwd+";"+loss+";"+balance);
			}	
			resultSet.close();                        
            statement.close();                        
            connection.close();  
	    }catch(ClassNotFoundException e ){
	    	System.out.println("数据库驱动错误");
	    }catch(SQLException e){
	    	System.out.println("数据库连接错误");
	    	//e.printStackTrace();
	    }
	    
	    //*******数据库成功连接则弹出登录界面********
	}
}
