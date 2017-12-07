package uidesign;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Origin.User;
import uidesign.JFrameDemo.JButtonReturnListener;

import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class FeedBackFrame extends JFrameDemo {


    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FeedBackFrame frame = new FeedBackFrame();
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
    public FeedBackFrame() {
        
        init();
    }
    public FeedBackFrame(JFrame pframe) {
        super(pframe);
        this.parentFrame = pframe;
        init();
    }
    
    public FeedBackFrame(JFrame pframe, User user) {
        super(pframe, user);
        init();
        this.parentFrame = pframe;
        this.user = user;
    }
    
    private void init() {
        setTitle("反馈");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        JPanel contentPane = new JPanel();
        contentPane.setBounds(0, 0, 450, 300);
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JButton btnReturn = new JButton("返回");
        btnReturn.addMouseListener(new JButtonReturnListener());
        btnReturn.setBounds(305, 213, 113, 27);
        contentPane.add(btnReturn);
        
        JTextArea textArea = new JTextArea();
        textArea.setBounds(14, 13, 404, 187);
        contentPane.add(textArea);
        
        JButton btnSubmit = new JButton("提交");
        btnSubmit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                try {
                    FileWriter fr = new FileWriter("feedback.txt");
                    Date  da = new Date();
                    fr.write(da.toString()+":\n"+"卡号为："+user.getAccount()+":"+ textArea.getText());
                    JOptionPane.showMessageDialog(null, "提交成功");
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "失败");
                }
                
                
            }
        });
       
        btnSubmit.setBounds(14, 213, 113, 27);
        contentPane.add(btnSubmit);
        
        this.setBackgroundImg(contentPane);
    }
}
