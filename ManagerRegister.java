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

public class ManagerRegister extends JFrame implements ActionListener
{
	private JFrame frame = new JFrame("图书馆管理系统 ");
	private JPanel ManagerRegisterPane;
    private JTextField Name;
    private JTextField Number;
    private JTextField SerialNumber;
    private JTextField Password;
    private JTextField PasswordAgain;
    private ImageIcon background;
    String name0,sex0,password0,checkpassword0,telephone0;
    int number0,age0;
    private JTextField Sex;
    private JTextField Age;
    private JTextField Telephone;

	public ManagerRegister() {
		background = new ImageIcon(this.getClass().getResource("back.jpg"));// 背景图片
   	    JLabel label = new JLabel(background);// 把背景图片显示在一个标签里面
   	    label.setBounds(0, 0, background.getIconWidth(),background.getIconHeight());// 把标签的大小位置设置为图片刚好填充整个面板
   	    ManagerRegisterPane = (JPanel) frame.getContentPane(); // 把内容窗格转化为JPanel，否则不能用方法setOpaque()来使内容窗格透明
   	    frame.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE)); // 把背景图片添加到分层窗格的最底层作为背景
   	    
    	ManagerRegisterPane.setOpaque(false);	 
    	ManagerRegisterPane.setLayout(null);//无布局
   	    frame.getLayeredPane().setLayout(null);
   	  
   	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭操作
   	    frame.setBounds(450, 200, 450, 430);//窗体大小
   	    frame.setResizable(false);//不可更改尺寸
   	    frame.setVisible(true);//窗体可见
		
		
		JLabel name = new JLabel("姓名");
		ManagerRegisterPane.add(name);
		name.setBounds(93, 26, 54, 15);
				
		Name = new JTextField();
		ManagerRegisterPane.add(Name);
		Name.setBounds(193, 26, 155, 21);
		Name.setColumns(10);
		
		JLabel number = new JLabel("学工号");
		ManagerRegisterPane.add(number);
		number.setBounds(93, 56, 54, 15);

		
		Number = new JTextField();
		ManagerRegisterPane.add(Number);
		Number.setColumns(10);
		Number.setBounds(193, 56, 155, 21);
		
		
		JLabel  serialnumber= new JLabel("注册通行证");
		ManagerRegisterPane.add(serialnumber);
		serialnumber.setBounds(93, 176, 80, 15);

		
		SerialNumber = new JTextField();
		ManagerRegisterPane.add(SerialNumber);
		SerialNumber.setColumns(10);
		SerialNumber.setBounds(193, 176, 155, 21);
		
		
		JLabel password = new JLabel("密码");
		ManagerRegisterPane.add(password);
		password.setBounds(93, 206, 54, 15);
		
		Password = new JPasswordField();
		ManagerRegisterPane.add(Password);
		Password.setColumns(10);
		Password.setBounds(193, 206, 155, 21);
		
		
		JLabel passwordagain = new JLabel("确认密码");
		ManagerRegisterPane.add(passwordagain);
		passwordagain.setBounds(93, 236, 80, 15);
		
		PasswordAgain = new JPasswordField();
		ManagerRegisterPane.add(PasswordAgain);
		PasswordAgain.setColumns(10);
		PasswordAgain.setBounds(193, 236, 155, 21);
		
		JButton register = new JButton("注册");
		frame.getContentPane().add(register);
		register.setBounds(79, 299, 100, 30);
		
		
		JButton cancle = new JButton("取消");
		frame.getContentPane().add(cancle);
		cancle.setBounds(236, 299, 100, 30);
		
		JLabel sex = new JLabel("性别");
		sex.setBounds(93, 86, 54, 15);
		frame.getContentPane().add(sex);
		
		JLabel age = new JLabel("年龄");
		age.setBounds(93, 116, 54, 15);
		frame.getContentPane().add(age);
		
		JLabel telephone = new JLabel("手机号");
		telephone.setBounds(93, 146, 54, 15);
		frame.getContentPane().add(telephone);
		
		Sex = new JTextField();
		Sex.setBounds(193, 86, 155, 21);
		frame.getContentPane().add(Sex);
		Sex.setColumns(10);
		
		Age = new JTextField();
		Age.setColumns(10);
		Age.setBounds(193, 116, 155, 21);
		frame.getContentPane().add(Age);
		
		Telephone = new JTextField();
		Telephone.setColumns(10);
		Telephone.setBounds(193, 146, 155, 21);
		frame.getContentPane().add(Telephone);
		
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		register.addActionListener(new ActionListener(){

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
				     				     
				     password0=Password.getText();
				     checkpassword0=PasswordAgain.getText();				     				   	
				    try
				    {
				    	b=new ConnectDB().testMname(name0);
				    }
				    catch(Exception e1)
				    {
				    	e1.printStackTrace();
				    }
				    if (b==false)
				    {	
				      if(SerialNumber.getText().equals("111111"))
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
				    			JOptionPane.showMessageDialog(null,"学工号输入错误","错误",JOptionPane.INFORMATION_MESSAGE);
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
							    	db.MRegister(name0,number0,sex0,age0,telephone0, password0);					    	
							    	frame.setVisible(false);
							    	JOptionPane.showMessageDialog(null,"您已注册成功","通知",JOptionPane.INFORMATION_MESSAGE);
				    		  }				    		
						 }
				      }
				    }
				    else
				    {	
				    	//此用户名已被注册
				    	JOptionPane.showMessageDialog(null,"此用户名不可用，请重新注册","错误",JOptionPane.INFORMATION_MESSAGE);
				    	frame.setVisible(false);
				    	//System.out.println(number0);
				    	new ManagerRegister();
				    }
				}
				if (e.getSource()==cancle){
					System.exit(0);
				}
			}			
		});		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
