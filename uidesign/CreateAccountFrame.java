package uidesign;
import IDValidator.IDValidator;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;

import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class CreateAccountFrame extends JFrameDemo {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String password; //密码
    private String cardid;   //卡号
    private String IDnumber; //身份证号
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CreateAccountFrame window = new CreateAccountFrame();
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the contentFrame.
     */
    public CreateAccountFrame() {
        init();
    }

    private void init() {
        setTitle("开户");

        setBounds(100, 100, 450, 300);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().setLayout(null);

        JPanel contentPane = new JPanel();
        contentPane.setBounds(0, 0, 432, 253);
        // getContentPane().add(contentPane);
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel label = new JLabel("卡号：");
        label.setBounds(42, 38, 72, 18);
        contentPane.add(label);

        JLabel label_2 = new JLabel("身份证号：");
        label_2.setBounds(29, 74, 99, 18);
        contentPane.add(label_2);

        JLabel label_3 = new JLabel("银行卡密码:");
        label_3.setBounds(27, 107, 99, 18);
        contentPane.add(label_3);

        JLabel label_4 = new JLabel("再次输入密码：");
        label_4.setBounds(26, 143, 110, 18);
        contentPane.add(label_4);
        
        JTextField tfCardid = new JTextField();
        tfCardid.setBounds(140, 33, 250, 24);
        contentPane.add(tfCardid);
        tfCardid.setColumns(10);

        JTextField tfIDnumber = new JTextField();
        tfIDnumber.setBounds(140, 68, 250, 24);
        contentPane.add(tfIDnumber);
        tfIDnumber.setColumns(10);

        JPasswordField  tfPassword = new JPasswordField();
        tfPassword.setBounds(140, 102, 250, 24);
        contentPane.add(tfPassword);

        JPasswordField  tfPassword2 = new JPasswordField();
        tfPassword2.setBounds(140, 137, 250, 24);
        contentPane.add(tfPassword2);

        JButton btnSubmit = new JButton("提交");
        btnSubmit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               if(setIDnumber(tfIDnumber.getText())!=0) {
                   JOptionPane.showMessageDialog(null,"身份证输入不合法");
               }else if(setPassword(new String(tfPassword.getPassword()),new String(tfPassword2.getPassword()))!=0) {
                   JOptionPane.showMessageDialog(null,"两次输入的密码不想等");
               }else {
                   JOptionPane.showMessageDialog(null, "提交成功");
                   LoginFrame loginframe = new LoginFrame();
                   loginframe.setVisible(true);;
                   dispose();
               }
            }
        });
        btnSubmit.setBounds(14, 213, 113, 27);
        contentPane.add(btnSubmit);

        JButton btnReturn = new JButton("返回");
        btnReturn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new LoginFrame().setVisible(true);
                dispose();
            }
        });
        btnReturn.setBounds(305, 213, 113, 27);
        contentPane.add(btnReturn);      
        
        this.setBackgroundImg(contentPane);
       
    }

    public String getIDnumber() {
        return IDnumber;
    }
    private int setIDnumber(String s) {
        IDValidator validator = new IDValidator();
        if(!validator.isValid(s)) {
            return 1;
        }
        this.IDnumber = s;
        return 0;
    }

    
    public String getPassword() {
        return password;
    }
    private int setPassword(String s1,String s2) {
        
        if(!s1.equals(s2)) {
            return 1;
        }
        
        this.password = s1;
        return 0;
    }

    public String getCardid() {
        
        return cardid;
    }
    public int setCardid(String s) {
//        if(!true) {
//            return 1;
//        }
        
        this.cardid = s;
        return 0;
    }

}
