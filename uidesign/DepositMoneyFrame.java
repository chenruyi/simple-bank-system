package uidesign;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Origin.Main;
import Origin.User;
import SQLProcess.SQLProcess;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JRadioButton;
import java.awt.Font;
import javax.swing.ButtonGroup;

public class DepositMoneyFrame extends JFrameDemo{

    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
 //   private JTextField tfMoneyNumber;
    private float MoneyNumber=0f;
    private int depositKind;//0:定期（1年），1：活期
   

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DepositMoneyFrame window = new DepositMoneyFrame();
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

   
    public DepositMoneyFrame() {
        init();
    }
    
    
    public DepositMoneyFrame(JFrame pframe, User user) {
        super(pframe, user);
        init();
        this.parentFrame = pframe;
        this.user = user;
    }
    public DepositMoneyFrame(JFrame pframe) {
        super(pframe);
        this.parentFrame = pframe;
        init();
    }
    
    public void init() {
        setTitle("存款");
        setBounds(100, 100, 450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        
        JPanel contentPane = new JPanel();
        contentPane.setBounds(0, 0, 432, 253);
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel label = new JLabel("存款金额：");
        label.setBounds(61, 65, 75, 18);
        contentPane.add(label);
        
        JTextField tfMoneyNumber = new JTextField();
        tfMoneyNumber.setBounds(150, 62, 183, 24);
        contentPane.add(tfMoneyNumber);
        tfMoneyNumber.setColumns(10);
        
        JButton btnSubmit = new JButton("提交");
        btnSubmit.setBounds(14, 213, 113, 27);
        btnSubmit.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub
                if(setMoneyNumber(tfMoneyNumber.getText())!=0) {
                    JOptionPane.showMessageDialog(null,"金额只允许数字出现,且为正数，请检查输入");
                    tfMoneyNumber.setText("");
                }else {
                    Date da =new Date();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
                    String s =formatter.format(da);
                    if(SQLProcess.changeBalance(user.getAccount(), user.getDeposit()+MoneyNumber)
                            && SQLProcess.insertHistory(user.getAccount(), s.substring(0, 10),s.substring(11, 8), "存款",user.getAccount() , 0.0f)) {
                        JOptionPane.showMessageDialog(null, "存款成功");
                    }else {
                        JOptionPane.showMessageDialog(null, "失败"); 
                    }
                    
               }
            }
            
        });
        contentPane.add(btnSubmit);
        
        JButton btnReturn = new JButton("返回");
        btnReturn.addMouseListener(new JButtonReturnListener());
        
        ButtonGroup buttonGroup = new ButtonGroup();
        JRadioButton rbCurrent = new JRadioButton("活期");
        rbCurrent.setSelected(true);
        depositKind  = 1;
        rbCurrent.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                depositKind = 1;
            }
        });
        buttonGroup.add(rbCurrent);
      
        rbCurrent.setBounds(35, 123, 157, 52);
     
        contentPane.add(rbCurrent);
        
        JRadioButton rbFixed = new JRadioButton("定期");
        rbFixed.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                depositKind = 0;
            }
        });
        buttonGroup.add(rbFixed);

        rbFixed.setBounds(222, 123, 157, 52);

        contentPane.add(rbFixed);
        
        
        btnReturn.setBounds(305, 213, 113, 27);
        contentPane.add(btnReturn);
        
        this.setBackgroundImg(contentPane);
    }
   


    public float getMoneyNumber() {
        return MoneyNumber;
    }


    private int setMoneyNumber(String text) {
        float  money = 0f;
        
        try {
            money= Float.parseFloat(text);
            
            if(money<=0f) {
                throw new NumberFormatException("输入的金额必须>0");
            }
            MoneyNumber = money;
            
        } catch (NumberFormatException e) {
            
            return 0;
        }
        
        return 1;
         
        
    }
}
