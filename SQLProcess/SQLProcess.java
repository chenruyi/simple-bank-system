package SQLProcess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.w3c.dom.css.ElementCSSInlineStyle;

/*
 * 数据库操作函数：
 * 1、开户：               public Boolean addUser(String id_num, String user_num, String user_password, String loss, float balance)
 * 2、销户：               public Boolean deleteUser(String user_num)
 * 3、修改密码：            public Boolean changePassword(String user_num, String newPassword)
 * 4、账户验证：            public Boolean searchUser(String user_num, String user_password)
 * 5、查询指定user信息：     public String[] queryUser(String user_num)
 * 6、查询指定卡的history信息：public String[][] queryHistory(String user_num)
 * 7、修改余额：            public Boolean changeBalance(String user_num, float balance)
 * 8、插入一条history记录：  public Boolean insertHistory(String user_num,String date,String time,String operation,String operator,float balance)
 * 9、修改挂失标志位：       public Boolean changeLoss(String user_num, String loss)
 * 
 */
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

public class SQLProcess {

	//开户
	public static Boolean addUser(String id_num, String user_num, String user_password, String loss, float balance)
	{
		Connection connection;
		Statement statement = null;
		connection = getcon();
	    
	    try{ 
			statement = connection.createStatement();                      //容器
			
			String sql="insert into user values('" + id_num + "','" + user_num + "','" + user_password + "','" + loss + "'," + balance +")";   //SQL语句
	        statement.executeUpdate(sql);         //将sql语句上传至数据库执行            
            return true;
	    }catch(SQLException e){
	    	System.out.println("数据库连接错误");
	    	return false;
	    }finally {
	       	ResultSet resultSet = null;
			DBClose(resultSet, statement, connection); 
		}
	}
	
	//删除账户
	public static Boolean deleteUser(String user_num)
	{
		Connection connection;
		Statement statement = null;
		connection = getcon();
	    
	    try{   
			statement = connection.createStatement();                      //容器
			
			String sql="DELETE FROM user WHERE user_num='" + user_num + "'";   //SQL语句
	        statement.executeUpdate(sql);         //将sql语句上传至数据库执行
            
            return true;
	    }catch(SQLException e){
	    	System.out.println("数据库连接错误");
	    	return false;
	    }finally {
	    	ResultSet resultSet = null;
			DBClose(resultSet, statement, connection); 
		}
	}
	
	//修改密码
	public static Boolean changePassword(String user_num, String newPassword)
	{
		Connection connection;
		Statement statement = null;
		connection = getcon();
	    try{  
			statement = connection.createStatement();                      //容器
			
			String sql="UPDATE user SET user_password=" + newPassword + "WHERE user_num='" + user_num + "'";   //SQL语句
	        statement.executeUpdate(sql);         //将sql语句上传至数据库执行
            
            return true;
	    }catch(SQLException e){
	    	System.out.println("数据库连接错误");
	    	return false;
	    }finally {
	    	ResultSet resultSet = null;
			DBClose(resultSet, statement, connection); 
		}
	}
	
	//登录时账号验证
	public static Boolean searchUser(String user_num, String user_password)
	{
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
	    connection = getcon();
	    try{ 
			statement = connection.createStatement();
			
			String sql="select * from user where user_num='" + user_num + "'";
			resultSet = statement.executeQuery(sql);

			if(resultSet.next()){
				String pwd=resultSet.getString("user_password");
				System.out.println(pwd);
				if(pwd.equals(user_password))
				{
					return true;
				}
				else return false;
			}
			else 
			{          
				return false;
			}
	    }catch(SQLException e){
	    	System.out.println("数据库连接错误");
	    	return false;
	    }finally {
	    	DBClose(resultSet, statement, connection); 
		}
	}
	
	//查询user信息
	public static String[] queryUser(String user_num)
	{
		String cardInfo[] = {"","","","",""};
		
		Connection connection;
		Statement statement = null;
		ResultSet resultSet = null;
		connection = getcon();
	    try{ 
			statement = connection.createStatement( 
			         ResultSet.TYPE_SCROLL_INSENSITIVE,
			         ResultSet.CONCUR_READ_ONLY );
			String sql="SELECT * FROM user WHERE user_num='" + user_num + "'";
			resultSet = statement.executeQuery(sql);
			String id=resultSet.getString("id_num");
			String userNum=resultSet.getString("user_num");
			String pwd=resultSet.getString("user_password");
			String loss=resultSet.getString("loss");
			String balance=resultSet.getString("balance");
			
			//给数组赋值
			cardInfo[0] = id;
			cardInfo[1] = userNum;
			cardInfo[2] = pwd;
			cardInfo[3] = loss;
			cardInfo[4] = balance;
	    }catch(SQLException e){
	    	System.out.println("数据库连接错误");
	    	//e.printStackTrace();
	    }finally {
	    	DBClose(resultSet, statement, connection); 
		}
		
		return cardInfo;
	}
	
	//查询history信息
	public static String[][] queryHistory(String user_num)
	{
		String history[][] = new String[100][];
		int p = 0;//数组赋值时的行号
		
		Connection connection;
		Statement statement = null;
		ResultSet resultSet = null;
		connection = getcon();
	    try{
			statement = connection.createStatement( 
			         ResultSet.TYPE_SCROLL_INSENSITIVE,
			         ResultSet.CONCUR_READ_ONLY );
			String sql="SELECT * FROM history WHERE user_num='" + user_num + "'";
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
			    String userNum = resultSet.getString("user_num");
			    String date = resultSet.getString("date");
			    String time = resultSet.getString("time");
			    String operation = resultSet.getString("operation");
			    String operator = resultSet.getString("operator");
			    String balance = resultSet.getString("balance");
			   
			 //给数组赋值
				history[p][0] = userNum;
				history[p][1] = date;
				history[p][2] = time;
				history[p][3] = operation;
				history[p][4] = operator;
				history[p][5] = balance;
				p++;
			}
	    }catch(SQLException e){
	    	System.out.println("数据库连接错误");
	    }finally {
	    	DBClose(resultSet, statement, connection); 
		}
		
		return history;
	}
	//修改余额
	public static Boolean changeBalance(String user_num, float balance)
	{
		Connection connection;
		Statement statement = null;
		connection = getcon();
	    try{
			statement = connection.createStatement();                      //容器
			
			String sql="UPDATE user SET balance=" + balance + "WHERE user_num='" + user_num + "'";   //SQL语句
	        statement.executeUpdate(sql);         //将sql语句上传至数据库执行
            
            return true;
	    }catch(SQLException e){
	    	System.out.println("数据库连接错误");
	    	return false;
	    }finally {
	    	ResultSet resultSet = null;
			DBClose(resultSet, statement, connection); 
		}
	}
	
	//插入一条历史记录
	public static Boolean insertHistory(String user_num,String date,String time,String operation,String operator,float balance)
	{
		Connection connection;
		Statement statement = null;
		connection = getcon();
	    
	    try{ 
			statement = connection.createStatement();                      //容器
			
			String sql="insert into history values('" + user_num + "','" + date + "','" + time + "','" + operation + "','" + operator + "'," + balance +")";   //SQL语句
	        statement.executeUpdate(sql);         //将sql语句上传至数据库执行
            
            return true;
	    }catch(SQLException e){
	    	System.out.println("数据库连接错误");
	    	return false;
	    }finally {
	    	ResultSet resultSet = null;
			DBClose(resultSet, statement, connection); 
		}
	}
	
	//修改挂失标志位
	
    public static Boolean changeLoss(String user_num, String loss)
	{
		Connection connection;
		Statement statement;
		connection = getcon();
	    
	    try{
			statement = connection.createStatement();                      //容器
			
			String sql="UPDATE user SET loss=" + loss + "WHERE user_num='" + user_num + "'";   //SQL语句
	        statement.executeUpdate(sql);         //将sql语句上传至数据库执行
            statement.close();                        
            connection.close();  
            
            return true;
	    }catch(SQLException e){
	    	System.out.println("数据库连接错误");
	    	return false;
	    }
	}

	
    public static Boolean transferAccounts(String user_num0,String user_num1, Float money)
    {//user_num0转给user_num1
    	    Connection connection;
		Statement statement = null;
		ResultSet resultSet = null;
		String balance0,balance1;
		connection = getcon();
	    try{
			statement = connection.createStatement();                      //容器
			
			String sql = "select balance from user where user_num='" + user_num0 + "'";
			resultSet = statement.executeQuery(sql);
			balance0 = resultSet.getString(0);
			
			sql = "select balance from user where user_num='" + user_num1 + "'";
			resultSet = statement.executeQuery(sql);
			balance1 = resultSet.getString(0);
			
			float  balance0_float = Float.parseFloat(balance0) - money;
			float  balance1_float = Float.parseFloat(balance1) + money;
			
			sql="UPDATE user SET balance=" + balance0_float + "WHERE user_num='" + user_num0 + "'";   //SQL语句
	        statement.executeUpdate(sql);
	        
	        sql="UPDATE user SET balance=" + balance1_float + "WHERE user_num='" + user_num1 + "'";   //SQL语句
	        statement.executeUpdate(sql);
            
            return true;
	    }catch(SQLException e){
	    	System.out.println("数据库连接错误");
	    	return false;
	    }finally {
			DBClose(resultSet, statement, connection); 
		}
    }
    
    public static Connection getcon()
	{
		Connection connection = null;
		String driverName="com.mysql.jdbc.Driver";                         // 加载数据库驱动类
	    String url="jdbc:mysql://localhost:3306/simple_credit_card";       // 声明数据库的URL
	    String user="root";                                                // 数据库用户
	    String password="123";
	    
	    try{
	     	Class.forName(driverName);		
			connection=DriverManager.getConnection(url, user, password);   
	    }catch(ClassNotFoundException e ){
	    	System.out.println("数据库驱动错误");
	    }catch(SQLException e){
	    	System.out.println("数据库连接错误");
	    }
		return connection;
		
	}
	
	public static void close(Connection con) {
        if (con != null)
            try {
                con.close();
            } catch (SQLException e) {
                // 不做任何处理，静默处理
            }
    }
 
    public static void close(ResultSet rs) {
        if (rs != null)
            try {
                rs.close();
            } catch (SQLException e) {
                // 不做任何处理，静默处理
            }
    }
 
    public static void close(Statement stmt) {
        if (stmt != null)
            try {
                stmt.close();
            } catch (SQLException e) {
                // 不做任何处理，静默处理
            }
    }
   
    public static void DBClose(ResultSet rs, Statement stmt, Connection conn) {
        try {
            close(rs);
        } finally {
            try {
                close(stmt);
            } finally {
                close(conn);
            }
        }
    }
}
