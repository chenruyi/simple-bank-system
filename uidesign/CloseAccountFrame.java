package uidesign;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;

public class CloseAccountFrame extends JFrameDemo {



    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String password;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    JFrame pf = new JFrame("null");
                    CloseAccountFrame window = new CloseAccountFrame(pf);
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public CloseAccountFrame() {
        init();
    }
    private void init() {
        setTitle("销户");
        
        setBounds(100, 100, 450, 300);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        getContentPane().setLayout(null);
        
        JPanel contentPane = new JPanel();
        contentPane.setBounds(0, 0, 432, 253);
        
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(124, 137, 207, 24);
        contentPane.add(passwordField);
        
        JLabel label = new JLabel("密码：");
        label.setBounds(38, 140, 72, 18);
        contentPane.add(label);
        
        JLabel lblNewLabel = new JLabel("<html>注销以后会删除全部的用户信息（包括存款记录、转账金额）<br>操作不可逆，请慎重选择</html>");
        lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
        lblNewLabel.setBounds(38, 49, 326, 62);
        contentPane.add(lblNewLabel);
        
        JButton btnReturn = new JButton("返回");
        btnReturn.setBounds(305, 213, 113, 27);
        btnReturn.addMouseListener(new JButtonReturnListener());
        contentPane.add(btnReturn);
        
        JButton btnSubmit = new JButton("提交");
        btnSubmit.setBounds(14, 213, 113, 27);
        btnSubmit.addMouseListener(new MouseAdapter() {
            

            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub
                if(setPassword(new String(passwordField.getPassword()))!=0) {
                    JOptionPane.showMessageDialog(null, "密码错误");
                }else {
                    JOptionPane.showMessageDialog(null, "提交成功");
                    LoginFrame loginframe = new LoginFrame();
                    loginframe.setVisible(true);;
                    dispose();
                }
            }
            
        });
        contentPane.add(btnSubmit);
        
        this.setBackgroundImg(contentPane);
    }
    
    public CloseAccountFrame(JFrame pframe) {
        super(pframe);
        init();
    }

    public String getPassword() {
        return password;
    }

    private int setPassword(String password) {
        this.password = password;
        
//        if()//密码不正确
//        
        return 0;
    }
}
