import java.awt.event.*;
import java.sql.SQLException;
import java.awt.*;
import javax.swing.*;

public class ManagerEnter extends JFrame implements ActionListener 
{
	private JFrame frame = new JFrame("图书馆管理系统 ");
	 private ImageIcon background;
	private JPanel ManagerEnterPane;
	private JTextField ReaderNametextField;
	private JTextField ReaderPasswordtextField;
	public static String name,password;
	public static int number;


	public ManagerEnter() 
	{
		 background = new ImageIcon(this.getClass().getResource("back.jpg"));// 背景图片
		 JLabel label = new JLabel(background);// 把背景图片显示在一个标签里面
		 label.setBounds(0, 0, background.getIconWidth(),background.getIconHeight());// 把标签的大小位置设置为图片刚好填充整个面板
		 
		 ManagerEnterPane = (JPanel) frame.getContentPane(); // 把内容窗格转化为JPanel，否则不能用方法setOpaque()来使内容窗格透明
		 
		 ManagerEnterPane.setOpaque(false);	 
		 ManagerEnterPane.setLayout(null);//无布局
		 frame.getLayeredPane().setLayout(null);
		 
		 frame.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE)); // 把背景图片添加到分层窗格的最底层作为背景
		 
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭操作
		 frame.setBounds(500, 200, 450, 300);//窗体大小
		 frame.setResizable(false);//不可更改尺寸
		 frame.setVisible(true);//窗体可见
		
		
		
		JLabel ReaderNameLabel = new JLabel("用户名:");
		ManagerEnterPane.add(ReaderNameLabel);
		ReaderNameLabel.setBounds(70, 37, 61, 37);
		ReaderNameLabel.setFont(new Font("",Font.BOLD,15));
		
		
		JLabel ReaderPasswordLabel = new JLabel("密码:");
		ManagerEnterPane.add(ReaderPasswordLabel);
		ReaderPasswordLabel.setBounds(70, 121, 61, 24);
		ReaderPasswordLabel.setFont(new Font("",Font.BOLD,15));
		
		
		ReaderNametextField = new JTextField();
		ManagerEnterPane.add(ReaderNametextField);
		ReaderNametextField.setBounds(156, 39, 178, 37);		
		ReaderNametextField.setColumns(10);
		
		ReaderPasswordtextField = new JPasswordField();
		ManagerEnterPane.add(ReaderPasswordtextField);
		ReaderPasswordtextField.setForeground(Color.BLACK);
		ReaderPasswordtextField.setBounds(156, 117, 178, 37);		
		ReaderPasswordtextField.setColumns(10);
		
		JButton RegisterButton = new JButton("注册");
		ManagerEnterPane.add(RegisterButton);
		RegisterButton.setBounds(96, 187, 88, 37);
		RegisterButton.setFont(new Font("",Font.BOLD,15));
		RegisterButton.addActionListener(this);
		
		
		JLabel ForgetLabel = new JLabel("忘记密码？");
		ManagerEnterPane.add(ForgetLabel);
		ForgetLabel.setBounds(358, 123, 66, 40);
		ForgetLabel.setForeground(Color.blue);
		
		
		JButton LandButton = new JButton("登陆");
		ManagerEnterPane.add(LandButton);
		LandButton.setFont(new Font("", Font.BOLD, 15));
		LandButton.setBounds(246, 187, 88, 37);
		
		

		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	
	
	RegisterButton.addActionListener(new ActionListener()
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			// TODO Auto-generated method stub
			if(e.getSource()==RegisterButton)
			{
	    		new ManagerRegister();
	    	}				
		}			
	});
	
	LandButton.addActionListener(new ActionListener()
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			// TODO Auto-generated method stub
			boolean b=false;
	    	if(e.getSource()==LandButton)
	    	{
	    		String id=ReaderNametextField.getText().trim();
			    number=Integer.parseInt(id);	
			    name=ReaderNametextField.getText();
	    	    password=ReaderPasswordtextField.getText();
			    try
			    {
			    	b=new ConnectDB().testMLogin(number, password);
			    }
			    catch(Exception e1)
			    {
			    	e1.printStackTrace();
			    	System.out.println("失败");
			    }
			    if (b==true)
			    {
			    	frame.dispose();					    		
					try {
						new ManagerFrame();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}								
			    }
			    else
			    	JOptionPane.showMessageDialog(null,"用户名或密码错误","通知",JOptionPane.INFORMATION_MESSAGE);
	    	}
		 }			
	});		
	
}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	}


