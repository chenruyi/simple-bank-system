package uidesign;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;

import Origin.Main;
import Origin.User;
import SQLProcess.SQLProcess;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MainMenuFrame extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JFrame mainmenuframe;

    private JPanel contentPane;
    
    private User user;


    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainMenuFrame window = new MainMenuFrame();
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

    public MainMenuFrame() {
        init();
        mainmenuframe = this;
    }

    public MainMenuFrame(User user) {
        init();
        mainmenuframe = this;
        this.user = user;
    }

    private void init() {

        this.setTitle("主菜单");
        this.setBounds(100, 100, 450, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);

        contentPane = new JPanel();
        contentPane.setSize(this.getSize());
        contentPane.setPreferredSize(this.getSize());
        this.setContentPane(contentPane);
        
        contentPane.setLayout(null);

        JButton btnReportLoss = new JButton("挂失");
        btnReportLoss.setBounds(305, 173, 113, 27);
        btnReportLoss.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) { 
                ReportLossFrame reportlossframe = new ReportLossFrame(mainmenuframe,user);
                reportlossframe.setVisible(true);
                mainmenuframe.setVisible(false);
            }
        });
        contentPane.add(btnReportLoss);

        JButton btnTransfer = new JButton("转账");
        btnTransfer.setBounds(10, 86, 113, 27);
        btnTransfer.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                TransferAccountFrame transferaccountframe = new TransferAccountFrame(mainmenuframe,user);
                transferaccountframe.setVisible(true);
                mainmenuframe.setVisible(false);
            }
        });
        contentPane.add(btnTransfer);

        JButton btnWithdraw = new JButton("取款");
        btnWithdraw.setBounds(10, 46, 113, 27);
        btnWithdraw.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                DrawMoneyFrame drawmoneyframe = new DrawMoneyFrame(mainmenuframe,user);
                drawmoneyframe.setVisible(true);
                mainmenuframe.setVisible(false);
            }
        });
        contentPane.add(btnWithdraw);

        JButton btnDeposit = new JButton("存款");
        btnDeposit.setBounds(305, 46, 113, 27);
        btnDeposit.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                DepositMoneyFrame depositmoneyframe = new DepositMoneyFrame(mainmenuframe,user);
                depositmoneyframe.setVisible(true);
                mainmenuframe.setVisible(false);
            }
        });
        contentPane.add(btnDeposit);

        JButton btnChangePassword = new JButton("修改密码");
        btnChangePassword.setBounds(10, 133, 113, 27);
        btnChangePassword.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                ChangePasswordFrame changepasswordframe = new ChangePasswordFrame(mainmenuframe,user);
                changepasswordframe.setVisible(true);
                mainmenuframe.setVisible(false);
            }
        });
        contentPane.add(btnChangePassword);

        JButton btnGetBalance = new JButton("查看余额");
        btnGetBalance.setBounds(305, 86, 113, 27);
        btnGetBalance.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                GetBalanceFrame getbalanceframe = new GetBalanceFrame(mainmenuframe,user);
                getbalanceframe.setVisible(true);
                mainmenuframe.setVisible(false);
            }
        });
        contentPane.add(btnGetBalance);

        JButton btnViewHistory = new JButton("查看历史");
        btnViewHistory.setBounds(305, 133, 113, 27);
        btnViewHistory.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                ViewHistoryFrame viewhistoryframe = new ViewHistoryFrame(mainmenuframe,user);
                viewhistoryframe.addItem(SQLProcess.queryHistory(user.getAccount()));
                viewhistoryframe.setVisible(true);
                mainmenuframe.setVisible(false);
            }
        });
        contentPane.add(btnViewHistory);

        JButton btnCloseAccount = new JButton("销户");
        btnCloseAccount.setBounds(10, 173, 113, 27);
        btnCloseAccount.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                CloseAccountFrame closeaccountframe = new CloseAccountFrame(mainmenuframe,user);
                closeaccountframe.setVisible(true);
                mainmenuframe.setVisible(false);
            }
        });
        contentPane.add(btnCloseAccount);

        JButton btnExit = new JButton("退出");
        btnExit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });
        btnExit.setBounds(10, 213, 113, 27);
        contentPane.add(btnExit);

        JButton btnFeedBack = new JButton("回馈");
        btnFeedBack.setBounds(305, 213, 113, 27);
        btnFeedBack.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                FeedBackFrame feedbackframe = new FeedBackFrame(mainmenuframe,user);
                feedbackframe.setVisible(true);
                mainmenuframe.setVisible(false);
            }
        });
        contentPane.add(btnFeedBack);

        
        //添加背景
        File f = new File("C:\\Users\\chen\\Desktop\\back.jpg");
        BufferedImage  bufimage = new BufferedImage(300, 300,BufferedImage.SCALE_DEFAULT);
        Image image;
        try {
            bufimage = ImageIO.read(f);
           
        } catch (IOException e) {
            Graphics g  = bufimage.getGraphics();
            g.setColor(new Color(0,0,0,0));
        }finally {
            image = bufimage.getScaledInstance(this.contentPane.getWidth(), this.contentPane.getHeight(), Image.SCALE_DEFAULT);
        }
        
        BackgroundPanel backgroundPane = new BackgroundPanel(image,BackgroundPanel.SCALED,1.0f,0.5f);
        backgroundPane.setBounds(0,0,this.contentPane.getWidth(),this.contentPane.getHeight());
        backgroundPane.setSize(this.contentPane.getSize());
        backgroundPane.setPreferredSize(contentPane.getPreferredSize());
        contentPane.add(backgroundPane);
        
    }

}
