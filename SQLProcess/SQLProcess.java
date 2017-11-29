package SQLProcess;

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
	public Boolean addUser(String id_num, String user_num, String user_password, String loss, float balance)
	{
		return false;
	}
	
	//删除账户
	public Boolean deleteUser(String user_num)
	{
		return false;
	}
	
	//修改密码
	public Boolean changePassword(String user_num, String newPassword)
	{
		return false;
	}
	
	//登录时账号验证
	public Boolean searchUser(String user_num, String user_password)
	{
		return false;
	}
	
	//查询user信息
	public String[] queryUser(String user_num)
	{
		String cardInfo[] = null;
		return cardInfo;
	}
	
	//查询history信息
	public String[][] queryHistory(String user_num)
	{
		String history[][] = null;
		return history;
	}
	
	//修改余额
	public Boolean changeBalance(String user_num, String balance)
	{
		return false;
	}
	
	//插入一条历史记录
	public Boolean insertHistory(String user_num,String date,String time,String operation,String operator,String balance)
	{
		return false;
	}
	
	//修改挂失标志位
	public Boolean changeLoss(String user_num, String loss)
	{
		return false;
	}
}
