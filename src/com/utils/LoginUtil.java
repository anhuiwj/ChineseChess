package com.utils;

import java.awt.Container;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.dao.UserDao;
import com.wyf.cgq.XiangQi;

public class LoginUtil extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4948696838766301907L;
	private JFrame frame;
	// 用户名
    private JTextField username;
    // 密码
    private JPasswordField password;
    // 小容器
    private JLabel jl1;
    private JLabel jl2;
    private JLabel jl3;
    private JLabel jl4;
 
    // 小按钮
    private JButton bu1;
    private JButton bu2;
 
    // 复选框
    private JCheckBox jc1;
    private JCheckBox jc2;
    
    private static UserDao dao = new UserDao();
 
    /*
     * 构造方法
     */
    public LoginUtil() {
        // 设置窗口标题
        this.setTitle("象棋大战");
        // 窗体组件初始化
        init();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 设置布局方式为绝对定位
        this.setLayout(null);
         
        this.setBounds(0, 0, 450, 300);
        // 设置窗体的标题图标
        String path = System.getProperty("user.dir")+"/timg.jpg";
        Image image = new ImageIcon(path).getImage();
        this.setIconImage(image);
         
        // 窗体大小不能改变
        this.setResizable(false);
         
        // 居中显示
        this.setLocationRelativeTo(null);
 
        // 窗体可见
        this.setVisible(true);
    }
 
    public void addListener(final LoginUtil frame){
        // 给按钮添加1个事件
        bu1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str=e.getActionCommand();
                if("登录".equals(str)){
                    String getName =username.getText();
                    String getPwd =password.getText();
                    if(getName.trim().length()==0||getPwd.trim().length()==0){
                     	 JOptionPane.showMessageDialog(null, "请输入用户名或密码", "提示", JOptionPane.ERROR_MESSAGE);
                    }else if(getPwd==null||getPwd.trim()==""){
                    	JOptionPane.showMessageDialog(null, "请输入密码", "提示", JOptionPane.ERROR_MESSAGE);
                    }else if(login(getName,getPwd)){
                    	LoginUtil.this.setVisible(false);
                    	 new XiangQi(getName);
                    }else{
                    	JOptionPane.showMessageDialog(null, "用户名或密码错误！", "提示", JOptionPane.ERROR_MESSAGE);
                    }
                  
                }
                 
            }
        });
        
     // 给注册按钮添加1个事件
        bu2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str=e.getActionCommand();
                if("注册".equals(str)){
                    String getName =username.getText();
                    String getPwd =password.getText();
                    
                   if(getName.trim().length()==0||getPwd.trim().length()==0){
                  	 JOptionPane.showMessageDialog(null, "请输入用户名或密码", "提示", JOptionPane.ERROR_MESSAGE);
                   }else if(getPwd==null||getPwd.trim()==""){
                	  JOptionPane.showMessageDialog(null, "请输入密码", "提示", JOptionPane.ERROR_MESSAGE);
                   }else {
                	   if(pdUser(getName)){
                		   	  int i =  dao.insert(getName, getPwd);
	                     	  if(i>0){
	                     		  JOptionPane.showConfirmDialog(null, "注册成功！");
	                     	  }
                      }else{
                    	  JOptionPane.showMessageDialog(null, "注册失败,当前用户已存在", "提示", JOptionPane.ERROR_MESSAGE);
                      }
                   }
                }
            }
        });
    }
    
    /*
     * 初始化方法
     */
    public void init() {
    	 // 创建一个容器
        Container con = this.getContentPane();
        jl1 = new JLabel();
        // 设置背景图片
        Image image1 = new ImageIcon(System.getProperty("user.dir")+"/background.jpg").getImage();
        jl1.setIcon(new ImageIcon(image1));
        jl1.setBounds(0, 0, 450, 300);
         
        // QQ登录头像设定
        jl2 = new JLabel();
        String path = System.getProperty("user.dir")+"/timg.jpg";
        ImageIcon icon = new ImageIcon(path);
        icon=new ImageIcon(icon.getImage().getScaledInstance(85, 85, Image.SCALE_DEFAULT));  
        jl2.setIcon(icon);
        jl2.setBounds(35, 75, 85, 85);
 
        // 用户号码登录输入框
        username = new JTextField();
        username.setBounds(100, 100, 150, 20);
 
        // 密码输入框
        password = new JPasswordField();
        password.setBounds(100, 130, 150, 20);
        
        // 按钮设定
        bu1 = new JButton("登录");
        bu1.setBounds(280, 200, 65, 20);
 
        // 输入框下方文字
        jc1 = new JCheckBox("记住密码");
        jc1.setBounds(105, 155, 80, 15);
        bu2 = new JButton("注册");
        bu2.setBounds(200, 200, 65, 20);
 
        // 所有组件用容器装载
        jl1.add(jl2);
        jl1.add(jc1);
        jl1.add(bu1);
        jl1.add(bu2);
        con.add(jl1);
        con.add(username);
        con.add(password);
    }
    public static boolean login(String username,String pwd){
    	String pas  = dao.findByName(username);
    	if(pas.length()>0 && pwd.equals(pas)){
    		return true;
    	}
		return false;
    }
    
    /**
     * 判断当前注册用户是否存在
     * @param username
     * @return
     */
    public boolean pdUser(String username){
    	if(dao.findByName(username).length()>0){
    		return false;
    	}
    	return true;
    }
    
    public static void main(String[] args) {
        // 实例化对象
    	LoginUtil  f= new LoginUtil();
    	f.addListener(f);
    }
 
}
