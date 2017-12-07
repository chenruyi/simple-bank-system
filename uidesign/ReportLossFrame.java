package uidesign;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import Origin.User;
import SQLProcess.SQLProcess;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportLossFrame extends JFrameDemo {

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
                    ReportLossFrame window = new ReportLossFrame();
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
    public ReportLossFrame() {
        init();

    }

    public ReportLossFrame(JFrame pframe) {
        super(pframe);
        this.parentFrame = pframe;
        init();
    }

    public ReportLossFrame(JFrame pframe, User user) {
        super(pframe, user);
        init();
        this.parentFrame = pframe;
        this.user = user;
    }

    private void init() {
        setTitle("挂失");
        setBounds(100, 100, 450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().setLayout(null);

        JPanel contentPane = new JPanel();
        contentPane.setBackground(SystemColor.control);
        contentPane.setBounds(0, 0, 432, 253);
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("密码：");
        lblNewLabel.setBounds(47, 96, 72, 18);
        contentPane.add(lblNewLabel);

        JPasswordField passwordField = new JPasswordField();
        lblNewLabel.setLabelFor(passwordField);
        passwordField.setBounds(116, 93, 269, 24);
        contentPane.add(passwordField);

        JButton btnSubmit = new JButton("提交");
        btnSubmit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //
                if (setPassword(new String(passwordField.getPassword())) != 0) {
                    JOptionPane.showMessageDialog(null, "密码错误");
                } else {
                    Date da =new Date();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
                    String s =formatter.format(da);
                    
                    if (SQLProcess.changeLoss(user.getAccount(), "0") 
                            && SQLProcess.insertHistory(user.getAccount(), s.substring(0, 10),s.substring(11, 8), "挂失", user.getAccount(), 0.0f)) {
                          JOptionPane.showMessageDialog(null, "挂失成功");// 直接返回主菜单
                        
                        parentFrame.setVisible(true);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "挂失失败");
                    }
                }
            }
        });
        btnSubmit.setBounds(14, 213, 113, 27);
        contentPane.add(btnSubmit);
        JButton btnReturn = new JButton("返回");
        btnReturn.setBounds(305, 213, 113, 27);
        btnReturn.addMouseListener(new JButtonReturnListener());
        contentPane.add(btnReturn);

        this.setBackgroundImg(contentPane);
    }

    public String getPassword() {
        return password;
    }

    private int setPassword(String password) {
       if(user.getPassword().equals(password)) {
       // if (true) {// 判断是否合法
            this.password = password;
            return 0;
        }

        return 1;
    }

}
