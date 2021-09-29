import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class ReaderRegister extends JFrame implements ActionListener
{
	private JFrame frame = new JFrame("图书馆管理系统 ");
	private JPanel ReaderRegisterPane;
	private ImageIcon background;
	private JTextField Name;
    private JTextField Number;
    private JTextField Password;
    private JTextField PasswordAgain;
    String name0,sex0,telephone0,email0,password0,checkpassword0;
    int number0,age0;
    private JTextField Sex;
    private JTextField Age;
    private JTextField Telephone;
    private JTextField Email;

	public ReaderRegister() 
	{
		background = new ImageIcon(this.getClass().getResource("back.jpg"));// 背景图片
   	    JLabel label = new JLabel(background);// 把背景图片显示在一个标签里面
   	    label.setBounds(0, 0, background.getIconWidth(),background.getIconHeight());// 把标签的大小位置设置为图片刚好填充整个面板
   	    ReaderRegisterPane = (JPanel) frame.getContentPane(); // 把内容窗格转化为JPanel，否则不能用方法setOpaque()来使内容窗格透明
   	    frame.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE)); // 把背景图片添加到分层窗格的最底层作为背景
   	    
    	ReaderRegisterPane.setOpaque(false);	 
    	ReaderRegisterPane.setLayout(null);//无布局
   	    frame.getLayeredPane().setLayout(null);
   	  
   	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭操作
   	    frame.setBounds(450, 200, 450, 430);//窗体大小
   	    frame.setResizable(false);//不可更改尺寸
   	    frame.setVisible(true);//窗体可见
   	    
   	    JLabel name = new JLabel("用户名");
		ReaderRegisterPane.add(name);
		name.setBounds(82, 24, 54, 15);
				
		Name = new JTextField();
		ReaderRegisterPane.add(Name);
		Name.setBounds(190, 21, 155, 21);
		Name.setColumns(10);
		
		JLabel number = new JLabel("学号");
		ReaderRegisterPane.add(number);
		number.setBounds(82, 64, 54, 15);
	
		Number = new JTextField();
		ReaderRegisterPane.add(Number);
		Number.setColumns(10);
		Number.setBounds(190, 61, 155, 21);
			
		JLabel password = new JLabel("密码");
		ReaderRegisterPane.add(password);
		password.setBounds(82, 258, 54, 15);
		
		Password = new JPasswordField();
		ReaderRegisterPane.add(Password);
		Password.setColumns(10);
		Password.setBounds(190, 255, 155, 21);
		
		
		JLabel passwordagain = new JLabel("确认密码");
		ReaderRegisterPane.add(passwordagain);
		passwordagain.setBounds(82, 298, 80, 15);
		
		PasswordAgain = new JPasswordField();
		ReaderRegisterPane.add(PasswordAgain);
		PasswordAgain.setColumns(10);
		PasswordAgain.setBounds(190, 295, 155, 21);
		
		JButton register = new JButton("注册");
		frame.getContentPane().add(register);
		register.setBounds(65, 338, 100, 30);
		register.addActionListener(this);
		
		
		JButton cancle = new JButton("取消");
		frame.getContentPane().add(cancle);
		cancle.setBounds(235, 338, 100, 30);
		
		JLabel sex = new JLabel("性别");
		sex.setBounds(82, 100, 54, 15);
		frame.getContentPane().add(sex);
		
		JLabel age = new JLabel("年龄");
		age.setBounds(82, 140, 54, 15);
		frame.getContentPane().add(age);
		
		JLabel telephone = new JLabel("电话号");
		telephone.setBounds(82, 180, 54, 15);
		frame.getContentPane().add(telephone);
		
		Sex = new JTextField();
		Sex.setBounds(190, 97, 155, 21);
		frame.getContentPane().add(Sex);
		Sex.setColumns(10);
		
		Age = new JTextField();
		Age.setColumns(10);
		Age.setBounds(190, 137, 155, 21);
		frame.getContentPane().add(Age);
		
		Telephone = new JTextField();
		Telephone.setColumns(10);
		Telephone.setBounds(190, 177, 155, 21);
		frame.getContentPane().add(Telephone);
		
		JLabel email = new JLabel("邮箱");
		email.setBounds(82, 220, 54, 15);
		frame.getContentPane().add(email);
		
		Email = new JTextField();
		Email.setColumns(10);
		Email.setBounds(190, 217, 155, 21);
		frame.getContentPane().add(Email);
		
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		register.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				// TODO Auto-generated method stub
				boolean b=false;
				if(e.getSource()==register)
				{    name0=Name.getText();
				     
				     String id=Number.getText().trim();
			         number0=Integer.parseInt(id);
			     
                     sex0=Sex.getText();
				     
				     String ag=Age.getText().trim();
				     age0=Integer.parseInt(ag);
				     
				     telephone0=Telephone.getText();	
				     
				     email0=Email.getText();
				     
				     password0=Password.getText();
				     checkpassword0=PasswordAgain.getText();	
				     
				    try
				    {
				    	b=new ConnectDB().testRname(name0);
				    }
				    catch(Exception e1)
				    {
				    	e1.printStackTrace();
				    }
				    if (b==false)
				    {	
				    	if(!password0.equals(checkpassword0))
				    	{				    		
				    		//您的密码不一致				    		
				    		JOptionPane.showMessageDialog(null,"您的密码不一致，请重新输入","错误",JOptionPane.INFORMATION_MESSAGE);
				    		Password.setText("");
				    		PasswordAgain.setText("");
				    	}
				    	else
				    	{
				    		if(Name.getText().length()>4)
				    		{
				    		  JOptionPane.showMessageDialog(null,"姓名输入错误","错误",JOptionPane.INFORMATION_MESSAGE);
				    		  Name.setText("");
				    		  }
				    		  else  if(Number.getText().length()!=8)
				    		  {
				    			JOptionPane.showMessageDialog(null,"学号输入错误","错误",JOptionPane.INFORMATION_MESSAGE);
				    			Number.setText("");
				    			}
				    		  else	if(!Sex.getText().equals("男")&&!Sex.getText().equals("女"))
				    		    {
				    		      JOptionPane.showMessageDialog(null,"性别输入错误","错误",JOptionPane.INFORMATION_MESSAGE);
				    		      Sex.setText("");
				    		      }
				    		  else  if(Age.getText().length()!=2)
				    		      {
				    			    JOptionPane.showMessageDialog(null,"年龄输入错误","错误",JOptionPane.INFORMATION_MESSAGE);
				    			    Age.setText("");
				    			    }
				    		  else  if (Telephone.getText().length()!=11)
						    		{
						    			JOptionPane.showMessageDialog(null,"电话号输入错误","错误",JOptionPane.INFORMATION_MESSAGE);
						    			Telephone.setText("");
						    			}
				    		  else
				    		  {
				    			    ConnectDB db=new ConnectDB();
							    	db.RRegister(name0,number0,sex0,age0,telephone0,email0, password0);
							    	frame.setVisible(false);
							    	JOptionPane.showMessageDialog(null,"您已注册成功","通知",JOptionPane.INFORMATION_MESSAGE);
				    		  }				    		
						 }				    					    						    								    						    		
				    	}				    	
				    }
				    else
				    {			
				    	//此用户名已被注册
				    	JOptionPane.showMessageDialog(null,"此用户名已被注册，请重新注册","错误",JOptionPane.INFORMATION_MESSAGE);
				    	frame.setVisible(false);
				    	//System.out.println(number0);
				    	new ReaderRegister();
				    }
				if (e.getSource()==cancle)
				{
					System.exit(0);
				}
				}			
		});
	
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		
	}
}
