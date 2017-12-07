package uidesign;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Origin.User;
import SQLProcess.SQLProcess;

import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JPasswordField;

public class TransferAccountFrame extends JFrameDemo {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    // 未完成，检查对方账户是否存在

    private float MoneyNumber = 0.0f;
    private String password = "";
    private String Account = "";
    private User TargetAccount;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TransferAccountFrame window = new TransferAccountFrame();
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
    public TransferAccountFrame() {

        init();

    }

    public TransferAccountFrame(JFrame pframe) {
        super(pframe);
        this.parentFrame = pframe;
        init();
    }

    public TransferAccountFrame(JFrame pframe, User user) {
        super(pframe, user);
        init();
        this.parentFrame = pframe;
        this.user = user;
    }

    public void init() {
        setTitle("转账");
        setBounds(100, 100, 450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        JPanel contentPane = new JPanel();
        contentPane.setBounds(0, 0, 432, 253);
        setContentPane(contentPane);

        contentPane.setLayout(null);

        JLabel label = new JLabel("目标账户：");
        label.setBounds(67, 42, 86, 18);
        contentPane.add(label);

        JTextField tfTargetAccount = new JTextField();
        tfTargetAccount.setBounds(161, 39, 212, 24);
        contentPane.add(tfTargetAccount);
        tfTargetAccount.setColumns(10);

        JTextField tfMoneyNumber = new JTextField();
        tfMoneyNumber.setBounds(161, 89, 212, 24);
        contentPane.add(tfMoneyNumber);
        tfMoneyNumber.setColumns(10);

        JLabel label_1 = new JLabel("转账金额：");
        label_1.setBounds(67, 92, 86, 18);
        contentPane.add(label_1);

        JLabel label_2 = new JLabel("密码：");
        label_2.setBounds(67, 139, 72, 18);
        contentPane.add(label_2);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(161, 126, 212, 24);
        contentPane.add(passwordField);

        JButton btnSubmit = new JButton("提交");
        btnSubmit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(setAccount(tfTargetAccount.getText()) != 0) {
                    JOptionPane.showMessageDialog(null, "该用户不存在");
                    return ;
                }
                
                if (setPassword(new String(passwordField.getPassword())) != 0) {
                    JOptionPane.showMessageDialog(null, "密码错误");
                    return ;
                } else {
                    float input = 0.0f;
                    try {
                        input = Float.parseFloat(tfMoneyNumber.getText());
                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(null, "输入的金额只允许数字，必须是正数");
                    }
                    if (setMoneyNumber(input) != 0) {
                        JOptionPane.showMessageDialog(null, "余额不足");
                    } else {
                        Date da =new Date();
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
                        String s =formatter.format(da);
                        
                        if (SQLProcess.transferAccounts(user.getAccount(), Account, MoneyNumber)                             
                                && SQLProcess.insertHistory(user.getAccount(), s.substring(0, 10),s.substring(11, 8), "转出", user.getAccount(),user.getDeposit()-MoneyNumber )
                                && SQLProcess.insertHistory(Account, s.substring(0, 10),s.substring(11, 8), "转入", TargetAccount.getAccount(),MoneyNumber )
                                ) {
                       
                            JOptionPane.showMessageDialog(null, "转账成功");
                        } else {
                            JOptionPane.showMessageDialog(null, "转账失败");
                        }
                    }
                }

            }
        });
        btnSubmit.setBounds(14, 213, 113, 27);
        contentPane.add(btnSubmit);

        JButton btnReturn = new JButton("返回");
        btnReturn.setBounds(305, 213, 113, 27);
        btnReturn.addMouseListener(new JButtonReturnListener());
        ;
        contentPane.add(btnReturn);

        this.setBackgroundImg(contentPane);
    }

    public String getPassword() {
        return password;
    }

    public int setPassword(String password) {
        if (user.getPassword().equals(password)) {
            this.password = password;
            return 0;
        }
        return 1;
    }

    public float getMoneyNumber() {
        return MoneyNumber;
    }

    public int setMoneyNumber(float moneyNumber) {
        if (moneyNumber > 0 && user.getDeposit() > moneyNumber) {
            MoneyNumber = moneyNumber;
            return 0;
        }
        return 1;
    }

    public String getAccount() {
        return Account;
    }

    public int setAccount(String account) {
        if (account.matches("[0-9]{19}")) {
            String []cardinfo= SQLProcess.queryUser(account);
            if(cardinfo[1].equals(account)) {
                TargetAccount.setDeposit(Float.parseFloat(cardinfo[4]));
                return 0;
            }
            
            
        }
        return 1;
    }

   
}
