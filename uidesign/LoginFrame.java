package uidesign;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import ui.BackgroundPanel;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class LoginFrame extends JFrame {

    
   
   
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String password;
    private String username;

    
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginFrame window = new LoginFrame();
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
    public LoginFrame() {
        this.password = null;
        this.username = null;
        init();
    }
    
    private void init() {
        setTitle("银行");
        setBounds(100, 100, 450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        JPanel contentPane = new JPanel();
        contentPane.setBackground(new Color(192, 192, 192));
        contentPane.setBounds(0, 0, 432, 253);
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel label_login = new JLabel("登录");
        label_login.setBounds(106, 0, 185, 47);
        contentPane.add(label_login);
        label_login.setHorizontalAlignment(SwingConstants.CENTER);
        
        JLabel label_uername = new JLabel("用户名：");
        label_uername.setBounds(106, 70, 60, 24);
        contentPane.add(label_uername);
        
        JTextField tfUsername = new JTextField();
        tfUsername.addInputMethodListener(new InputMethodListener() {
            public void caretPositionChanged(InputMethodEvent arg0) {
            }
            public void inputMethodTextChanged(InputMethodEvent arg0) {
                
            }
        });
        tfUsername.setBounds(189, 70, 158, 24);
        contentPane.add(tfUsername);
        tfUsername.setColumns(10);
        
        JLabel  label_password = new JLabel("密码：");
        label_password.setBounds(106, 129, 60, 24);
        contentPane.add(label_password);
        
        JPasswordField tfPassword = new JPasswordField();
        tfPassword.setBounds(189, 129, 158, 24);
        contentPane.add(tfPassword);
        
       JButton btnLogin = new JButton("登陆");
        btnLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(true) {//判断是否正确
                    MainMenuFrame mainmenu = new MainMenuFrame();
                    mainmenu.setVisible(true);
                    dispose();
                }
                
            }
        });
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                MainMenuFrame mainmenu = new MainMenuFrame();
                mainmenu.setVisible(true);
                dispose();                
            }
        });
        btnLogin.setBounds(316, 213, 100, 27);
        contentPane.add(btnLogin);
        
        JButton btnCreateAccount = new JButton("创建账户");
        btnCreateAccount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CreateAccountFrame createaccountframe = new CreateAccountFrame();
                createaccountframe.setVisible(true);
                dispose();
            }
        });

       
        btnCreateAccount.setBounds(14, 213, 100, 27);
        contentPane.add(btnCreateAccount);
        
        
        File f = new File("C:\\Users\\chen\\Desktop\\back.jpg");
        BufferedImage  bufimage = new BufferedImage(300, 300,BufferedImage.SCALE_DEFAULT);
        Image image;
        try {
            bufimage = ImageIO.read(f);
           
        } catch (IOException e) {
            Graphics g  = bufimage.getGraphics();
            g.setColor(new Color(0,0,0,0));
        }finally {
            image = bufimage.getScaledInstance(contentPane.getWidth(), contentPane.getHeight(), Image.SCALE_DEFAULT);
        }
        
        JButton btnRescissionLoss = new JButton("解除挂失");
        btnRescissionLoss.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                RescissionLossFrame rescissionlossframe = new RescissionLossFrame();
                rescissionlossframe.setVisible(true);
                dispose();
            }
        });
        btnRescissionLoss.setBounds(130, 213, 113, 27);
        contentPane.add(btnRescissionLoss);
        
        BackgroundPanel backgroundPane = new BackgroundPanel(image,BackgroundPanel.SCALED,1.0f,0.5f);
        backgroundPane.setBounds(0,0,contentPane.getWidth(),contentPane.getHeight());
        backgroundPane.setSize(contentPane.getSize());
        backgroundPane.setPreferredSize(contentPane.getPreferredSize());
        contentPane.add(backgroundPane);
        
       
    }

    /**
     * Initialize the contents of the frame.
     */
   

    public String getPassword() {
        return password;
    }

//    public void setPassword(String password) {
//        this.password = password;
//    }

    public String getUsername() {
        return username;
    }
}
