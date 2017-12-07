package uidesign;

import java.awt.EventQueue;


import java.util.Date;
import java.time.format.DateTimeFormatter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Origin.User;


public class GetBalanceFrame extends JFrameDemo{



    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    //private JPanel contentPane;
    
    private JLabel lblBalance;
    private JLabel label0;
    private JLabel label1;
    private JLabel lblDate;
    private JLabel lblAccount;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    
                    GetBalanceFrame window = new GetBalanceFrame();
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
    public GetBalanceFrame() {
        init();
        this.parentFrame = null;
        //this.user = new User("0", "0");
    }
    
    public GetBalanceFrame(JFrame pframe) {
        super(pframe);
        this.parentFrame = pframe;
        init();
    }
    public GetBalanceFrame(JFrame pframe, User user) {
        super(pframe, user);
        init();
        this.parentFrame = pframe;
        this.user = user;
    }
    
    public void init() {
        setTitle("查询余额");
        
        setBounds(100, 100, 450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        
        JPanel contentPane = new JPanel();
        contentPane.setBounds(0, 0, 432, 253);
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JButton btnReturn = new JButton("返回");
        btnReturn.setBounds(305, 213, 113, 27);
        btnReturn.addMouseListener(new JButtonReturnListener());
        contentPane.add(btnReturn);
        
        lblBalance = new JLabel();
        
        setBalance("00001.0000");
        setBalance(String.valueOf(user.getDeposit()));
        lblBalance.setBounds(137, 126, 163, 30);
        contentPane.add(lblBalance);
       
        
        label0 = new JLabel("截止到：");
        label0.setBounds(63, 60, 92, 30);
        
        contentPane.add(label0);
        
        lblDate = new JLabel("2017-11-30");
        label0.setLabelFor(lblDate);
        lblDate.setBounds(137, 60, 229, 30);
        setDate();
        
        JLabel label = new JLabel("账户：");
        label.setBounds(63, 97, 72, 30);
        contentPane.add(label);
        
        lblAccount = new JLabel();
        lblAccount.setBounds(137, 95, 229, 30);
        setAccount(user.getAccount());
        contentPane.add(lblAccount);
        
        contentPane.add(lblDate);
        
        label1 = new JLabel("余额：");
        label1.setLabelFor(lblBalance);
        label1.setBounds(63, 126, 92, 30);
        
        contentPane.add(label1);
        
        setBackgroundImg(contentPane);
    }

    public void setBalance(String balance) {
        //格式化
        try {
            DecimalFormat decimalFormat=new DecimalFormat(".00");
            balance = decimalFormat.format(Float.parseFloat(balance));
        }catch(Exception e) {
            balance = "***";
        }
        this.lblBalance.setText(balance);
    }
    
    public void setDate() {
        Date da = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        this.lblDate.setText(sdf.format(da));
    }
    
    public void setAccount(String account) {
        if(account.matches("[0-9]{19}")) {
            this.lblAccount.setText(account.substring(0, 4)+String.valueOf("********")+account.substring(15, 19));
        }
        
        
    }
    
}
