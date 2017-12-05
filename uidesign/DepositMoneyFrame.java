package uidesign;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DepositMoneyFrame extends JFrameDemo{

    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
 //   private JTextField tfMoneyNumber;
    private float MoneyNumber=0f;

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
                }else {
                    
                    JOptionPane.showMessageDialog(null, "提交成功");
               }
            }
            
        });
        contentPane.add(btnSubmit);
        
        JButton btnReturn = new JButton("返回");
        btnReturn.addMouseListener(new JButtonReturnListener());
        btnReturn.setBounds(305, 213, 113, 27);
        contentPane.add(btnReturn);
        
        this.setBackgroundImg(contentPane);
    }
    public DepositMoneyFrame(JFrame pframe) {
        super(pframe);
        this.parentFrame = pframe;
        init();
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
            // TODO Auto-generated catch block
            //e.printStackTrace();
            return 0;
        }
        
        return 1;
         
        
    }

}
