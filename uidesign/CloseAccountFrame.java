package uidesign;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import Origin.Main;
import Origin.User;
import SQLProcess.SQLProcess;

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

    public CloseAccountFrame(JFrame pframe) {
        super(pframe);
        init();
        this.parentFrame = pframe;
    }


    public CloseAccountFrame(JFrame pframe,User user) {
        super(pframe,user);
        init();
        this.parentFrame = pframe;
        this.user = user;
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
                if (setPassword(new String(passwordField.getPassword())) != 0) {
                    JOptionPane.showMessageDialog(null, "密码错误");
                } else {
                    Date da =new Date();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
                    String s =formatter.format(da);
                    
                    if (SQLProcess.deleteUser(user.getAccount()) 
                            && SQLProcess.insertHistory(user.getAccount(), s.substring(0, 10),s.substring(11, 8), "注销", user.getAccount(), 0.0f)) {
                        JOptionPane.showMessageDialog(null, "提交成功");
                        LoginFrame loginframe = new LoginFrame();
                        loginframe.setVisible(true);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "删除用户失败");
                    }
                }
            }

        });
        contentPane.add(btnSubmit);

        this.setBackgroundImg(contentPane);
    }

    public String getPassword() {
        return password;
    }

    private int setPassword(String password) {
        if (user.getPassword().equals(password)) {
            this.password = password;
            return 0;
        }

        return 1;
    }
}
