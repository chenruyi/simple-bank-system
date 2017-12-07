package Origin;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import SQLProcess.SQLProcess;
import uidesign.CreateAccountFrame;
import uidesign.LoginFrame;
import uidesign.MainMenuFrame;
import uidesign.ReportLossFrame;

public class Main {
    //public static SQLProcess sqlprocess ;//= new SQLProcess();
	public static void main(String[] args) throws SQLException {
		 Main m = new Main();
		 m.showLoginFrame();
		
	}

    
    public void creatAccount() {
        CreateAccountFrame creatAccountFrame = new CreateAccountFrame();
        creatAccountFrame.setVisible(true);
        if(creatAccountFrame.isDisplayable()) {
            this.showLoginFrame();
        }
    }
    
    public void showLoginFrame() {
        LoginFrame loginframe =new LoginFrame();
        loginframe.setVisible(true);
        
        
        while(true) {
            if(loginframe.getActionChoice() == LoginFrame.LOGIN_ACCOUNT) {
                String user_password = loginframe.getPassword();
                String userid = loginframe.getUsername();
                if(SQLProcess.searchUser(userid, user_password)) {
                    //String [] user = sqlprocess.queryUser(userid);
                    if(SQLProcess.queryUser(userid)[0].equals("1")) {//未挂失
                        User user = new User(userid,user_password);
                        MainMenuFrame mainmenu = new MainMenuFrame(user);
                        mainmenu.setVisible(true);
                        loginframe.dispose();
                        break;
                    }
                    else {
                        JOptionPane.showMessageDialog( loginframe, "该账号在挂失中，无法登陆");
                    }
                }
            }else if(loginframe.getActionChoice() == LoginFrame.CREAT_ACCOUNT) {
                
                this.creatAccount() ;
                break;
            }else if(loginframe.getActionChoice() == LoginFrame.RESCISSION_LOSS) {
                
                this.showReportLossFrame();
                break;
            }
        
        }
        
    }
    public void showReportLossFrame() {
        ReportLossFrame reportlossFrame = new ReportLossFrame();
        reportlossFrame.setVisible(true);
        if(reportlossFrame.isDisplayable()) {
            this.showLoginFrame();
        }
    }
    
    
    
    
    
    
}
