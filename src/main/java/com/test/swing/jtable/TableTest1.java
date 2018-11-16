package com.test.swing.jtable;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class TableTest1 {
    public static void main(String[] args) {
        UserTable ut = new UserTable();
        ut.init();
    }
}

class UserTable extends JFrame {
    private static final long serialVersionUID = 1L;
    
    private JPanel panel;
    private JScrollPane tableScroll;
    private JTable table;
    private JButton add;
    private JButton remove;
    
    private JButton close;
    
    private UserAdd addPanel;
    
    public UserTable(){}
    
    public void init(){
        this.initComponent();
        
        this.initTable();
        
        this.initTableEvent();
        
        this.initEvent();
        
        this.showFrame();
    }

    private void initEvent(){
        final UserTable frame = this;
        final JTable table = this.table;
        this.add.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0){
                frame.getAddPanel().showAddPanel();
            }
        });
        
        this.remove.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                int rowNum = table.getSelectedRow();
                if(rowNum == -1){
                    JOptionPane.showMessageDialog(frame, "请选择数据");      
                    return;
                }
                frame.removeItem(rowNum);
            }
        });
        
        this.close.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0){
                if(JOptionPane.showConfirmDialog(frame, "确认关闭吗？") == 0){
                    System.exit(0);
                }
            }
        });
    }
    
    private void initTable(){
        TableModel tm = new DefaultTableModel(this.getDatas(), this.getNames());
        this.table.setModel(tm);
    }
    
    private void initTableEvent(){
        final JTable table = this.table;
        this.table.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                int rowNum = e.getFirstRow();
                String val0 = table.getValueAt(rowNum, 0).toString();
                String val1 = table.getValueAt(rowNum, 1).toString();
                String val2 = table.getValueAt(rowNum, 2).toString();
                String val3 = table.getValueAt(rowNum, 3).toString();
                System.out.println(val0 +";"+val1+";"+val2+";"+val3);
            }
        });
    }
    
    private void initComponent(){
        panel = new JPanel(new BorderLayout());
        table = new JTable();
        tableScroll = new JScrollPane(table);
        
        JPanel btnPanel = new JPanel(new FlowLayout());
        add = new JButton("增加");
        btnPanel.add(add);
        
        remove = new JButton("删除");
        btnPanel.add(remove);
        close = new JButton("关闭");
        close.setName("closeBtn");
        btnPanel.add(close);
        
        panel.add(tableScroll, BorderLayout.CENTER);
        panel.add(btnPanel, BorderLayout.SOUTH);
        this.add(panel, BorderLayout.CENTER);
        
        this.addPanel = new UserAdd(this);
        this.addPanel.init();
        
    }
    
    private void showFrame(){
        try{
            this.setSize(400,300);
            this.setVisible(true);
            this.setLocation(300, 200);
            this.setName("测试");
            this.setTitle("测试123");
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            SwingUtilities.updateComponentTreeUI(this);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException e){
            //e.printStackTrace();
        }
    }
    
    private String[] getNames(){
        return new String[]{"id","名称","年龄","邮箱"};
    }

    private String[][] getDatas(){
        return new String[][]{
                {"1","aaa","10",""},
                {"2","bbb","11","bbb@qq.com"},
                {"3","ccc","12","吃吃吃@cc.com"},
                {"4","ddd","13","规定@yy.com"},
                {"4","ddd","13","规定@yy.com"},
                {"4","ddd","13","规定@yy.com"},
                {"4","ddd","13","规定@yy.com"},
                {"4","ddd","13","规定@yy.com"},
                {"4","ddd","13","规定@yy.com"},
                {"4","ddd","13","规定@yy.com"},
                {"4","ddd","13","规定@yy.com"},
                {"4","ddd","13","规定@yy.com"},
                {"4","ddd","13","规定@yy.com"},
                {"4","ddd","13","规定@yy.com"},
                {"4","ddd","13","规定@yy.com"},
                {"4","ddd","13","规定@yy.com"},
                {"5","eee","14",null}
        };
    }

    public UserAdd getAddPanel(){
        return this.addPanel;
    }
    
    public void addItem(String[] data){
        ((DefaultTableModel)this.table.getModel()).addRow(data);
        this.table.repaint();
    }
    
    public void removeItem(int idx){
        try{
            ((DefaultTableModel)this.table.getModel()).removeRow(idx);
        }catch(Exception e){
            e.printStackTrace();
        }
        this.table.repaint();
    }
    
}

class UserAdd extends JDialog {
    private static final long serialVersionUID = 1L;
    
    private JFrame parent;
    
    private JLabel idLabel;
    private JTextField id;

    private JLabel nameLabel;
    private JTextField name;
    
    private JLabel ageLabel;
    private JTextField age;
    
    private JLabel emailLabel;
    private JTextField email;
    
    private JButton ok;
    private JButton cancel;

    public UserAdd(JFrame parent){
        super(parent);
        this.parent = parent;
    }
    
    public void init(){
        this.initComponent();
        this.initFrame();
        this.initEvent();
    }
    
    public void showAddPanel(){
        this.clear();
        
        Point local = this.parent.getLocation();
        this.setLocation(local.x+20, local.y+20);
        this.setSize(300,250);
        this.setVisible(true);
    }
    
    private void initComponent(){
        JPanel panel = new JPanel(new BorderLayout());
        
        JPanel fieldPanel = new JPanel(new FlowLayout());
        
        this.idLabel = new JLabel("   id：");
        this.idLabel.setPreferredSize(new Dimension(40, 25));
        this.idLabel.setHorizontalAlignment(JLabel.RIGHT);
        this.id = new JTextField(20);
        this.id.setSize(100, 35);
        fieldPanel.add(idLabel);
        fieldPanel.add(id);
        
        this.nameLabel = new JLabel("名称：");
        this.nameLabel.setPreferredSize(new Dimension(40, 25));
        this.name = new JTextField(20);
        this.name.setSize(100, 35);
        fieldPanel.add(this.nameLabel);
        fieldPanel.add(this.name);
        
        this.ageLabel = new JLabel("年龄：");
        this.ageLabel.setPreferredSize(new Dimension(40, 25));
        this.age = new JTextField(20);
        this.age.setSize(100, 35);
        fieldPanel.add(this.ageLabel);
        fieldPanel.add(this.age);
        
        this.emailLabel = new JLabel("邮箱：");
        this.emailLabel.setPreferredSize(new Dimension(40, 25));
        this.email = new JTextField(20);
        this.email.setSize(100, 35);
        fieldPanel.add(this.emailLabel);
        fieldPanel.add(this.email);
        
        JPanel btnPanel = new JPanel(new FlowLayout());
        this.ok = new JButton("确定");
        this.cancel = new JButton("取消");
        btnPanel.add(ok);
        btnPanel.add(cancel);
        
        panel.add(fieldPanel, BorderLayout.CENTER);
        panel.add(btnPanel, BorderLayout.SOUTH);
        this.setContentPane(panel);
    }

    private void initEvent(){
        final UserAdd frame = this;
        this.cancel.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                frame.setVisible(false);
            }
        });
        
        this.ok.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                String[] data = {frame.getIdField().getText(), 
                        frame.getNameField().getText(),
                        frame.getAgeField().getText(),
                        frame.getEmailField().getText()};
                System.out.println(data);
                ((UserTable)frame.getParent()).addItem(data);
                frame.setVisible(false);
            }
        });
    }
    
    private void clear(){
        this.getIdField().setText("");
        this.getNameField().setText("");
        this.getAgeField().setText("");
        this.getEmailField().setText("");
    }
    
    private void initFrame(){
        this.setSize(300,400);
        this.setLocation(300, 200);
        this.setName("增加");
        this.setTitle("增加用户");
    }

    public JTextField getIdField()
    {
        return id;
    }

    public JTextField getNameField()
    {
        return name;
    }

    public JTextField getAgeField()
    {
        return age;
    }

    public JTextField getEmailField()
    {
        return email;
    }

}
