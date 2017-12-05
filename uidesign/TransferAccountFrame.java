package uidesign;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TransferAccountFrame extends JFrameDemo {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    
    private float MoneyNumber = 0.0f;
    private String password;

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
    
    public void init() {
        setTitle("转账");
        setBounds(100, 100, 450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        
        
        JPanel contentPane = new JPanel();
        contentPane.setBounds(0, 0, 432, 253);
        setContentPane(contentPane);
        
        contentPane.setLayout(null);
        
        JButton btnSubmit = new JButton("提交");
        btnSubmit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //判断是否满足条件
                //if() 密码错误
//                JOptionPane.showMessageDialog(null, "密码错误");
//                
//                //else 余额不足
//                JOptionPane.showConfirmDialog(null, "余额不足");
//                
                JOptionPane.showMessageDialog(null, "提交成功");
            }
        });
        btnSubmit.setBounds(14, 213, 113, 27);
        contentPane.add(btnSubmit);
        
        JButton btnReturn = new JButton("返回");
        btnReturn.setBounds(305, 213, 113, 27);
        btnReturn.addMouseListener(new JButtonReturnListener());;
        contentPane.add(btnReturn);
        
        JLabel label = new JLabel("目标账户：");
        label.setBounds(67, 70, 86, 18);
        contentPane.add(label);
        
        
       
        JTextField tfTargetAccount = new JTextField();
        tfTargetAccount.setBounds(161, 67, 212, 24);
        contentPane.add(tfTargetAccount);
        tfTargetAccount.setColumns(10);
        
        JTextField tfMoneyNumber = new JTextField();
        tfMoneyNumber.setBounds(161, 110, 212, 24);
        contentPane.add(tfMoneyNumber);
        tfMoneyNumber.setColumns(10);
        
        JLabel label_1 = new JLabel("转账金额：");
        label_1.setBounds(67, 113, 86, 18);
        contentPane.add(label_1);
        
        this.setBackgroundImg(contentPane);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public float getMoneyNumber() {
        return MoneyNumber;
    }

    public void setMoneyNumber(float moneyNumber) {
        MoneyNumber = moneyNumber;
    }

   
}
