package uidesign;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewHistoryFrame extends JFrameDemo {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private DefaultTableModel tablemodel;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ViewHistoryFrame window = new ViewHistoryFrame();
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
    public ViewHistoryFrame() {
        setTitle("查询历史");
        init();
    }
    
    public ViewHistoryFrame(JFrame pframe) {
        super(pframe);
        this.parentFrame = pframe;
        init();
    }
    
    public void init() {
        
        setBounds(100, 100, 450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        
        JPanel contentPane = new JPanel();
        contentPane.setBounds(0, 0, 432, 253);
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JButton btnReturn = new JButton("返回");
        btnReturn.addMouseListener(new JButtonReturnListener());
        btnReturn.setBounds(305, 213, 113, 27);
        contentPane.add(btnReturn);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 0, 432, 192);
        
        
        contentPane.add(scrollPane);
        this.setBackgroundImg(contentPane);
        
        tablemodel = new DefaultTableModel(
                new Object[][] {
                    {null, null, null},
                    {null, null, null},
                    {null, null, null},
                    {null, null, null},
                    {null, null, null},
                    {null, null, null},
                    {null, null, null},
                    {null, null, null},
                    {null, null, null},
                    {null, null, null},
                    {null, null, null},
                    {null, null, null},
                    {null, null, null},
                    {null, null, null},
                    {null, null, null},
                    {null, null, null},
                },
                new String[] {
                    "\u65F6\u95F4", "\u64CD\u4F5C", "\u4F59\u989D"
                }
            );
        
        JTable table =new JTable(tablemodel);
        
       
        scrollPane.setViewportView(table);
        
    }
    
    

}
