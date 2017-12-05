package uidesign;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class ChangePasswordFrame extends JFrameDemo {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String oldPassword;
    private String newPassword;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    JFrame pf = new JFrame("**");
                    ChangePasswordFrame window = new ChangePasswordFrame(pf);
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
    public ChangePasswordFrame() {
        init();
    }

    private void init() {
        setTitle("修改密码");
        setBounds(100, 100, 450, 300);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().setLayout(null);

        // JPanel contentPane = new JPanel();
        JPanel contentPane = new JPanel();
        setContentPane(contentPane);

        contentPane.setBounds(0, 0, 432, 253);
        // getContentPane().add(contentPane);
        contentPane.setLayout(null);

        JLabel label = new JLabel("原密码：");
        label.setBounds(29, 45, 60, 18);
        contentPane.add(label);

        JLabel label_1 = new JLabel("新密码：");
        label_1.setBounds(29, 84, 60, 18);
        contentPane.add(label_1);

        JLabel label_2 = new JLabel("再次输入：");
        label_2.setBounds(29, 126, 75, 18);
        contentPane.add(label_2);

        JPasswordField pfOldPassword;
        JPasswordField pfNewPassword;
        JPasswordField pfNewPassword2;

        pfOldPassword = new JPasswordField();
        pfOldPassword.setBounds(113, 42, 220, 24);
        contentPane.add(pfOldPassword);

        pfNewPassword = new JPasswordField();
        pfNewPassword.setBounds(113, 81, 220, 24);
        contentPane.add(pfNewPassword);

        pfNewPassword2 = new JPasswordField();
        pfNewPassword2.setBounds(113, 123, 217, 24);
        contentPane.add(pfNewPassword2);

        this.setBackgroundImg(contentPane);

        JButton btnSubmit = new JButton("提交");
        btnSubmit.setBounds(29, 213, 113, 27);
        btnSubmit.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub
                if (setOldPassword(new String(pfOldPassword.getPassword())) != 0) {
                    JOptionPane.showMessageDialog(null, "两次的新密码不同");

                    pfNewPassword.setText("");
                    pfNewPassword2.setText("");
                    return ;
                }
                if (setNewPassword(new String(pfNewPassword.getPassword()),
                        new String(pfNewPassword2.getPassword())) != 0) {
                    JOptionPane.showMessageDialog(null, "两次的新密码不同");
                    pfNewPassword.setText("");
                    pfNewPassword2.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "提交成功");
                    LoginFrame loginframe = new LoginFrame();
                    loginframe.setVisible(true);
                    dispose();
                }

            }

        });
        contentPane.add(btnSubmit);

        JButton btnReturn = new JButton("返回");
        btnReturn.setBounds(305, 213, 113, 27);
        contentPane.add(btnReturn);
        btnReturn.addMouseListener(new JButtonReturnListener());

    }

    public ChangePasswordFrame(JFrame pframe) {
        super(pframe);
        init();
        this.parentFrame = pframe;
    }

    public String getNewPassword() {
        return newPassword;
    }

    private int setNewPassword(String newPassword, String newPassword2) {
        // if()//判断原密码是否正确
        //
        if (newPassword.equals(newPassword2)) {
            this.newPassword = newPassword;
            return 0;
        } else {
            return 1;
        }

    }

    public String getOldPassword() {
        return oldPassword;
    }

    public int setOldPassword(String oldpassword) {
        // if()//如果密码不对
        //

        if (true) {
            this.oldPassword = oldpassword;
            return 0;
        }

        return 1;
    }

}
