import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.sql.*;

public class ReaderMessage extends JPanel 
{
	JButton Button1 = new JButton("修改个人信息");
	JButton Button2 = new JButton("修改密码");
	//JButton Button3 = new JButton("刷新");

	static String rnum=ReaderEnter.name;
	//System.out.println(rnum);
	static String sql="SELECT * FROM reader where id ='"+rnum+"'";
	static String message[][]=new ConnectDB().Select(sql);
	
	static JLabel La1 = new JLabel(message[0][0]);
	static JLabel La2 = new JLabel(message[0][1]);
	static JLabel La3 = new JLabel(message[0][2]);
	static JLabel La4 = new JLabel(message[0][3]);
	static JLabel La5 = new JLabel(message[0][4]);
	static JLabel La6 = new JLabel(message[0][5]);
	
	public ReaderMessage() 
	{
		setLayout(null);
		setSize(800,400);
		
		//System.out.println(message[0].length);
		//System.out.println(message.length);
		
		
		JLabel L1 = new JLabel("欢迎进入图书馆管理系统！");
		L1.setFont(new Font("宋体",Font.BOLD,25));
		L1.setBounds(244, 34, 312, 52);
		add(L1);
		
		JLabel L2 = new JLabel("姓名：");
		L2.setFont(new Font("宋体", Font.PLAIN, 16));
		L2.setBounds(100, 132, 65, 27);
		add(L2);
		
		JLabel L3 = new JLabel("ID号：");
		L3.setFont(new Font("宋体", Font.PLAIN, 16));
		L3.setBounds(100, 186, 65, 27);
		add(L3);
		
		JLabel L4 = new JLabel("性别：");
		L4.setFont(new Font("宋体", Font.PLAIN, 16));
		L4.setBounds(100, 243, 65, 27);
		add(L4);
		
		JLabel L5 = new JLabel("年龄：");
		L5.setFont(new Font("宋体", Font.PLAIN, 16));
		L5.setBounds(423, 132, 54, 27);
		add(L5);
		
		JLabel L6 = new JLabel("电话号：");
		L6.setFont(new Font("宋体", Font.PLAIN, 16));
		L6.setBounds(423, 186, 65, 27);
		add(L6);
		
		JLabel L7 = new JLabel("邮箱：");
		L7.setFont(new Font("宋体", Font.PLAIN, 16));
		L7.setBounds(423, 243, 54, 27);
		add(L7);
		

		La1.setFont(new Font("宋体", Font.PLAIN, 16));
		La1.setForeground(Color.DARK_GRAY);
		La1.setBackground(Color.WHITE);
		La1.setBounds(202, 132, 124, 27);
		add(La1);
		

		La2.setFont(new Font("宋体", Font.PLAIN, 16));
		La2.setForeground(Color.DARK_GRAY);
		La2.setBackground(Color.WHITE);
		La2.setBounds(202, 186, 124, 27);
		add(La2);
		

		La3.setFont(new Font("宋体", Font.PLAIN, 16));
		La3.setForeground(Color.DARK_GRAY);
		La3.setBackground(Color.WHITE);
		La3.setBounds(202, 243, 124, 27);
		add(La3);
		
		
		La4.setFont(new Font("宋体", Font.PLAIN, 16));
		La4.setForeground(Color.DARK_GRAY);
		La4.setBackground(Color.WHITE);
		La4.setBounds(513, 132, 135, 27);
		add(La4);
		

		La5.setFont(new Font("宋体", Font.PLAIN, 16));
		La5.setForeground(Color.DARK_GRAY);
		La5.setBackground(Color.WHITE);
		La5.setBounds(513, 186, 135, 27);
		add(La5);
		

		La6.setFont(new Font("宋体", Font.PLAIN, 16));
		La6.setForeground(Color.DARK_GRAY);
		La6.setBackground(Color.WHITE);
		La6.setBounds(513, 243, 135, 27);
		add(La6);
		
		//System.out.println(ManagerEnter.name);
				
		this.setBorder(BorderFactory.createEtchedBorder());
		
		
		add(Button1);
		Button1.setFont(new Font("宋体", Font.PLAIN, 16));
		Button1.setBounds(116, 302, 140, 36);
		Button1.addActionListener(new listener());
		
		

		add(Button2);
		Button2.setFont(new Font("宋体", Font.PLAIN, 16));
		Button2.setBounds(441, 302, 140, 36);
		Button2.addActionListener(new listener());
		

		//add(Button3);

				
	}
	
	
	 class listener implements ActionListener 
	  {
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource()==Button1)
			{
				ReaderModify dialog = new ReaderModify();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
		    }
			else 
				if (e.getSource()==Button2)
			    {
					ReaderpwModify dialog0=new ReaderpwModify();
					dialog0.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog0.setVisible(true);					
			    }				
	     }	
	}
	
}

//修改个人信息

class ReaderModify extends JDialog implements ActionListener
{
	private final JPanel contentPanel = new JPanel();
	private JTextField t1;
	private JTextField t2;
	private JTextField t3;
	private JTextField t4;
	private JTextField t5;
	
	JButton b1 = new JButton("确认修改");
	JButton b2 = new JButton("取消");
	
	String rnum=ReaderEnter.name;
	//System.out.println(rnum);
	String sql="SELECT * FROM reader where id ='"+rnum+"'";
	String message[][]=new ConnectDB().Select(sql);
	
	public ReaderModify() 
	{
		setBounds(100, 100, 450, 300);	
		getContentPane().setLayout(null);
		
		JLabel l1 = new JLabel("姓名");
		getContentPane().add(l1);
		l1.setBounds(75, 20, 87, 21);
		
		JLabel l2 = new JLabel("性别");
		getContentPane().add(l2);
		l2.setBounds(75, 60, 87, 21);
		
		JLabel l3 = new JLabel("年龄");
		getContentPane().add(l3);
		l3.setBounds(75, 100, 87, 18);
		
		JLabel l4 = new JLabel("电话号");
		getContentPane().add(l4);
		l4.setBounds(75, 140, 87, 18);
				
		JLabel l5 = new JLabel("邮箱");
		getContentPane().add(l5);
		l5.setBounds(75, 180, 87, 18);
		
		t1 = new JTextField();
		t1.setBounds(207, 20, 148, 21);
		getContentPane().add(t1);
		t1.setColumns(10);
		
		t2 = new JTextField();
		t2.setColumns(10);
		t2.setBounds(207, 60, 148, 21);
		getContentPane().add(t2);
		
		t3 = new JTextField();
		t3.setColumns(10);
		t3.setBounds(207, 100, 148, 21);
		getContentPane().add(t3);
		
		t4 = new JTextField();
		t4.setColumns(10);
		t4.setBounds(207, 140, 148, 21);
		getContentPane().add(t4);
		
		t5 = new JTextField();
		t5.setColumns(10);
		t5.setBounds(207, 180, 148, 21);
		getContentPane().add(t5);
		

		b1.setBounds(55, 215, 107, 30);
		getContentPane().add(b1);
		b1.addActionListener(this);
		

		b2.setBounds(228, 215, 107, 30);
		getContentPane().add(b2);
		b2.addActionListener(this);
		
		 this.setValue(); 
		
	}
	
	public void setValue()
	  { 
	    this.t1.setText((message[0][0]));      
	    this.t2.setText((message[0][2])); 
	    this.t3.setText((message[0][3])); 
	    this.t4.setText((message[0][4])); 
	    this.t5.setText((message[0][5])); 	    

	    this.validate(); 
	  } 
		

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		if (e.getSource()==b1)
		{
			if(t1.getText().equals("")||t2.getText().equals("")|| 
		    		  t3.getText().equals("")||t4.getText().equals(""))
			{
				 //System.out.println("输入失败"); 
		        JOptionPane.showMessageDialog(this,"除邮箱外修改不能有空", "提示",JOptionPane.PLAIN_MESSAGE); 
			}
			else
		    { 
		        int n = JOptionPane.showConfirmDialog(null, "确认修改吗?", "确认修改框", JOptionPane.YES_NO_OPTION); 
		        if (n == JOptionPane.YES_OPTION) 
		        { 
		          String sql="UPDATE reader SET name ='"+t1.getText()+"', sex= '"+t2.getText()
		          +"',age='"+t3.getText()+"',telephone='"+t4.getText()+"',email='"+t5.getText()
		          +"' WHERE id = '"+rnum+"' "; 
		          
		          new ConnectDB().Update(sql); 
		          
		            String rnum0=ReaderEnter.name;
					//System.out.println(rnum);
					String sql1="SELECT * FROM reader where id ='"+rnum0+"'";
					String message[][]=new ConnectDB().Select(sql1);
					
					ReaderMessage.La1.setText(message[0][0]);
					ReaderMessage.La2.setText(message[0][1]);
					//La3.setText(message[0][0]);
					ReaderMessage.La4.setText(message[0][3]);
					ReaderMessage.La5.setText(message[0][4]);
					ReaderMessage.La6.setText(message[0][5]);
		          
		          JOptionPane.showMessageDialog(this,"修改成功", "提示",JOptionPane.PLAIN_MESSAGE); 
		          this.setVisible(false); 
		        }
		        else if (n == JOptionPane.NO_OPTION) 
		        { 
		          return; 
		        } 
		      }
		}
		if(e.getSource()==b2)
		{
			this.setVisible(false); 
		}
		
	}
}

//修改密码

class ReaderpwModify extends JDialog implements ActionListener
{
	private final JPanel contentPanel1 = new JPanel();
	private JPasswordField t1;
	private JPasswordField t2;
	private JPasswordField t3;
	
	JButton b1 = new JButton("确认修改");
	JButton b2 = new JButton("取消");
	
	String rnum=ReaderEnter.name;
	String sql="SELECT * FROM reader where id ='"+rnum+"'";
	String message[][]=new ConnectDB().Select(sql);
	
	public ReaderpwModify() 
	{
		setBounds(100, 100, 450, 300);	
		getContentPane().setLayout(null);
		
		JLabel l1 = new JLabel("原始密码：");
		getContentPane().add(l1);
		l1.setBounds(75, 20, 87, 21);
		
		JLabel l2 = new JLabel("新密码：");
		getContentPane().add(l2);
		l2.setBounds(75, 60, 87, 21);
		
		JLabel l3 = new JLabel("密码确认");
		getContentPane().add(l3);
		l3.setBounds(75, 100, 87, 18);		
		
		t1 = new JPasswordField();
		t1.setBounds(207, 20, 148, 21);
		getContentPane().add(t1);
		t1.setColumns(10);
		
		t2 = new JPasswordField();
		t2.setColumns(10);
		t2.setBounds(207, 57, 148, 21);
		getContentPane().add(t2);
		
		t3 = new JPasswordField();
		t3.setColumns(10);
		t3.setBounds(207, 97, 148, 21);
		getContentPane().add(t3);
				
		b1.setBounds(55, 140, 107, 30);
		getContentPane().add(b1);
		b1.addActionListener(this);
		

		b2.setBounds(228, 140, 107, 30);
		getContentPane().add(b2);
		b2.addActionListener(this);
		
		 this.setValue(); 
		
	}
	
	public void setValue()
	  { 
	    //this.t1.setText((message[0][6]));      
		this.t1.setText(null);   
	    this.t2.setText(null); 
	    this.t3.setText(null); 	    

	    this.validate(); 
	  } 
		

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		if (e.getSource()==b1)
		{
			if(t1.getText().equals("")||t2.getText().equals("")|| 
		    		  t3.getText().equals(""))
			{
				 //System.out.println("输入失败"); 
		        JOptionPane.showMessageDialog(this,"修改不能有空", "提示",JOptionPane.PLAIN_MESSAGE); 
			}
			else 
			{
				if(!t1.getText().equals((message[0][6])))
			    {
				//System.out.println("输入失败"); 
		        JOptionPane.showMessageDialog(this,"原始密码输入有误", "提示",JOptionPane.PLAIN_MESSAGE); 
		        t1.setText(null);
		        t2.setText(null);
		        t3.setText(null);
			    }
			   else 
			   {	   
				   if(!t2.getText().equals(t3.getText()))
			       {
					    JOptionPane.showMessageDialog(this,"密码不一致,请重新输入", "提示",JOptionPane.PLAIN_MESSAGE); 
				        t2.setText(null);
				        t3.setText(null);
			       }
				   else
		           { 
		             int n = JOptionPane.showConfirmDialog(null, "确认修改吗?", "确认修改框", JOptionPane.YES_NO_OPTION); 
		             if (n == JOptionPane.YES_OPTION) 
		             { 
		               String sql="UPDATE reader SET password ='"+t2.getText()+"' WHERE id = '"+rnum+"' "; 
		          
		               new ConnectDB().Update(sql); 
		          
		          
		               JOptionPane.showMessageDialog(this,"修改成功", "提示",JOptionPane.PLAIN_MESSAGE); 
		               this.setVisible(false); 
		             }
		             else if (n == JOptionPane.NO_OPTION) 
		             { 
		               return; 
		             } 
		           }
		       }
			}
		}
		if(e.getSource()==b2)
		{
			this.setVisible(false); 
		}		
	}
}


