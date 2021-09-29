import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;

public class ReaderEnter  extends JFrame implements ActionListener 
{
	
	private JFrame frame = new JFrame("图书馆管理系统 ");
	private JPanel ReaderEnterPane;
	public static String name,password;
	public static int number;
	private ImageIcon background;


	public ReaderEnter() 
	{
		 background = new ImageIcon(this.getClass().getResource("back.jpg"));// 背景图片
		 JLabel label = new JLabel(background);// 把背景图片显示在一个标签里面
		 label.setBounds(0, 0, background.getIconWidth(),background.getIconHeight());// 把标签的大小位置设置为图片刚好填充整个面板
		
		ReaderEnterPane = (JPanel) frame.getContentPane(); // 把内容窗格转化为JPanel，否则不能用方法setOpaque()来使内容窗格透明
		
		ReaderEnterPane.setOpaque(false);
		ReaderEnterPane.setLayout(null);//无布局
		 frame.getLayeredPane().setLayout(null);
		 
		 frame.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE)); // 把背景图片添加到分层窗格的最底层作为背景

		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭操作
		 frame.setBounds(500, 200, 450, 300);//窗体大小
		 frame.setResizable(false);//不可更改尺寸
		 frame.setVisible(true);//窗体可见

		
		JLabel ReaderNameLabel = new JLabel("学号:");
		ReaderEnterPane.add(ReaderNameLabel);
		ReaderNameLabel.setBounds(70, 37, 61, 37);
		ReaderNameLabel.setFont(new Font("",Font.BOLD,20));
		
		
		JLabel ReaderPasswordLabel = new JLabel("密码:");
		ReaderEnterPane.add(ReaderPasswordLabel);
		ReaderPasswordLabel.setBounds(70, 121, 61, 24);
		ReaderPasswordLabel.setFont(new Font("",Font.BOLD,20));
		
		
		JTextField ReaderNametextField=new JTextField();
		ReaderEnterPane.add(ReaderNametextField);
		ReaderNametextField.setBounds(156, 39, 178, 37);
		ReaderNametextField.setColumns(10);
		
		JPasswordField ReaderPasswordtextField;
		ReaderPasswordtextField = new JPasswordField();
		ReaderEnterPane.add(ReaderPasswordtextField);
		ReaderPasswordtextField.setBounds(156, 117, 178, 37);
		ReaderPasswordtextField.setColumns(10);
		
		JButton RegisterButton = new JButton("注册");
		ReaderEnterPane.add(RegisterButton);
		RegisterButton.setBounds(97, 187, 75, 37);
		RegisterButton.setFont(new Font("注册",Font.BOLD,15));
		RegisterButton.addActionListener(this);
		
		
		JButton LandButton = new JButton("登陆");
		ReaderEnterPane.add(LandButton);
		LandButton.setBounds(259, 187, 75, 37);
		LandButton.setFont(new Font("",Font.BOLD,15));
		LandButton.addActionListener(this);
		
		
		JLabel ForgetLabel = new JLabel("忘记密码？");
		ReaderEnterPane.add(ForgetLabel);
		ForgetLabel.setBounds(358, 123, 66, 24);
		ForgetLabel.setForeground(Color.blue);
				
		
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);;
		
		ForgetLabel.addMouseListener(new MouseListener() 
		{
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("+1s");
			}
			@SuppressWarnings("deprecation")
			@Override
			public void mouseEntered(MouseEvent arg0) {
				setCursor(HAND_CURSOR);
			}
			@SuppressWarnings("deprecation")
			@Override
			public void mouseExited(MouseEvent arg0) {
				setCursor(DEFAULT_CURSOR);
			}
			@Override
			public void mousePressed(MouseEvent arg0) {}
			@Override
			public void mouseReleased(MouseEvent arg0) {}				
			});
		
		RegisterButton.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						// TODO Auto-generated method stub					
								new ReaderRegister();
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
				    	    password=ReaderPasswordtextField.getText();
				    	    name=ReaderNametextField.getText();
						    try
						    {
						    	b=new ConnectDB().testRLogin(number, password);
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
									new ReaderFrame();
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
	public void actionPerformed(ActionEvent arg0) 
	{
		// TODO Auto-generated method stub
		
	}
}

