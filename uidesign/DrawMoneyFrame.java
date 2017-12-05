package uidesign;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;

public class DrawMoneyFrame extends JFrameDemo{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;


    private float drawMoneyNumber = 0.0f;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DrawMoneyFrame window = new DrawMoneyFrame();
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
    public DrawMoneyFrame() {
        setTitle("取款");
        init();
    }
    
    public DrawMoneyFrame(JFrame pframe) {
        super(pframe);
        this.parentFrame = pframe;
        init();
    }
    private void init() {
        this.setBounds(100, 100, 450, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);
        
        JPanel contentPane = new JPanel();
        contentPane.setBounds(0, 0, 432, 253);
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel label = new JLabel("取款金额：");
        label.setBounds(41, 58, 84, 18);
        contentPane.add(label);
        
        JTextField textField = new JTextField();
        textField.setBounds(172, 52, 144, 24);
        contentPane.add(textField);
        textField.setColumns(10);
        
        JButton btnSubmit = new JButton("提交");
        btnSubmit.setBounds(14, 213, 113, 27);
        btnSubmit.addMouseListener(new MouseAdapter() {
            

            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub
                if(setDrawMoneyNumber(textField.getText())!=0) {
                    JOptionPane.showMessageDialog(null,"金额只允许数字出现,且为正数，请检查输入");
                    
                }else {
                
                JOptionPane.showMessageDialog(null, "提交成功");
                }
            }
            
        });
        contentPane.add(btnSubmit);
        
        JButton btnReturn = new JButton("返回");
        btnReturn.setBounds(305, 213, 113, 27);
        btnReturn.addMouseListener(new JButtonReturnListener());
        contentPane.add(btnReturn);
        
        this.setBackgroundImg(contentPane);
    }

    public float getDrawMoneyNumber() {
        return drawMoneyNumber;
    }

    public int setDrawMoneyNumber(String text) {
        float  money = 0f;
        
        try {
            money= Float.parseFloat(text);
            
            if(money<=0f) {
                throw new NumberFormatException("输入的金额必须>0");
            }
            this.drawMoneyNumber = money;
            
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
            return 0;
        }
        
        return 1;
    }

    
}
