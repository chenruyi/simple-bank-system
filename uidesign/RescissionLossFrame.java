package uidesign;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import SQLProcess.SQLProcess;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RescissionLossFrame extends JFrameDemo {

    private JPanel contentPane;

    private String Cardid;// 卡号
    private String IDnumber;// 身份证号
    private String password;// 密码

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RescissionLossFrame frame = new RescissionLossFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public RescissionLossFrame() {
        init();
    }

    public RescissionLossFrame(JFrame pframe) {
        super(pframe);
        init();
        this.parentFrame = pframe;
    }

    private void init() {
        setTitle("解除挂失");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("身份证号：");
        lblNewLabel.setBounds(42, 51, 100, 18);
        contentPane.add(lblNewLabel);

        JLabel label = new JLabel("卡号：");
        label.setBounds(70, 92, 72, 18);
        contentPane.add(label);

        JLabel label_1 = new JLabel("密码");
        label_1.setBounds(70, 139, 54, 18);
        contentPane.add(label_1);

        JTextField tfIDnumber = new JTextField();
        tfIDnumber.setBounds(138, 48, 198, 24);
        contentPane.add(tfIDnumber);
        tfIDnumber.setColumns(10);

        JTextField tfCardid = new JTextField();
        tfCardid.setBounds(138, 89, 198, 24);
        contentPane.add(tfCardid);
        tfCardid.setColumns(10);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(138, 136, 198, 24);
        contentPane.add(passwordField);

        JButton btnSubmit = new JButton("提交");
        btnSubmit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Date da =new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
                String s =formatter.format(da);
                
                if (SQLProcess.changeLoss(user.getAccount(), "1") 
                        && SQLProcess.insertHistory(user.getAccount(), s.substring(0, 10),s.substring(11, 8), "解除挂失", user.getAccount(), 0.0f)) {
                 
                    JOptionPane.showMessageDialog(null, "解除挂失成功");
                }else {
                    JOptionPane.showMessageDialog(null, "解除挂失失败");
                }
            }
        });
        btnSubmit.setBounds(14, 213, 113, 27);
        contentPane.add(btnSubmit);

        JButton btnReturn = new JButton("返回");
        btnReturn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (parentFrame != null) {
                    parentFrame.setVisible(true);
                    
                } else {
                    LoginFrame loginframe = new LoginFrame();
                    loginframe.setVisible(true);
                }
                dispose();
            }
        });
        btnReturn.setBounds(305, 213, 113, 27);
        contentPane.add(btnReturn);
        this.setBackgroundImg(contentPane);
    }

    public String getCardid() {
        return Cardid;
    }

    public void setCardid(String cardid) {
        Cardid = cardid;
    }

    public String getIDnumber() {
        return IDnumber;
    }

    public void setIDnumber(String iDnumber) {
        IDnumber = iDnumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
