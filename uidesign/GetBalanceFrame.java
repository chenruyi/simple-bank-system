package uidesign;

import java.awt.EventQueue;


import java.util.Date;
import java.time.format.DateTimeFormatter;
import java.text.SimpleDateFormat;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


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
    
    private String balance;
    
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
    }
    
    public GetBalanceFrame(JFrame pframe) {
        super(pframe);
        this.parentFrame = pframe;
        init();
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
        lblBalance.setText("");
        lblBalance.setBounds(147, 98, 40, 18);
        contentPane.add(lblBalance);
       
        
        label0 = new JLabel("截至到：");
        label0.setBounds(63, 54, 60, 30);
        
        contentPane.add(label0);
        
        lblDate = new JLabel("2017-11-30");
        label0.setLabelFor(lblDate);
        lblDate.setBounds(137, 54, 135, 30);
        contentPane.add(lblDate);
        
        label1 = new JLabel("账户余额：");
        label1.setLabelFor(lblBalance);
        label1.setBounds(51, 98, 92, 18);
        contentPane.add(label1);
        
        this.setBackgroundImg(contentPane);
    }

    public void setBalance(String balance) {
        this.balance = balance;
        this.lblBalance.setText(balance);
    }
    
    public void setDate(long lo) {
        Date da = new Date(lo);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日  HH:mm");
        this.lblDate.setText(sdf.format(da));
    }
}
