import java.awt.*;
import java.awt.event.*;

import java.sql.*;

import javax.swing.*;

public class AddReader extends JPanel 
{
	private JTextField text1;
	private JTextField text3;
	private JTextField text4;
	private JTextField text2;
	private JTextField text5;
	private JTextField text6;
	JButton Button1 = new JButton("添加");
	JButton Button2 = new JButton("取消");
	
	public AddReader() throws SQLException
	{
		setLayout(null);
		setSize(800,400);
		
		add(Button1);
		Button1.setBounds(216, 306, 93, 23);
		Button1.addActionListener(new listener()); 
		
		
		add(Button2);
		Button2.setBounds(420, 306, 93, 23);
		Button2.addActionListener(new listener()); 
		
		JLabel Label= new JLabel("请输入读者信息");
		Label.setFont(new Font("宋体",Font.BOLD, 20));
		Label.setBounds(299, 29, 163, 23);
		add(Label);
		
		JLabel Label1 = new JLabel("姓名：");
		Label1.setBounds(234, 87, 83, 15);
		add(Label1);
				
		JLabel label2 = new JLabel("ID号：");
		label2.setBounds(234, 115, 83, 15);
		add(label2);
		
		JLabel label3 = new JLabel("性别：");
		label3.setBounds(234, 146, 83, 15);
		add(label3);
		
		JLabel label4 = new JLabel("年龄：");
		label4.setBounds(234, 176, 83, 15);
		add(label4);

		
		JLabel label5 = new JLabel("电话号：");
		label5.setBounds(234, 207, 83, 15);
		add(label5);
		
		JLabel label6 = new JLabel("邮箱：");
		label6.setBounds(234, 237, 83, 15);
		add(label6);
		
		text1 = new JTextField();
		text1.setBounds(350, 87, 163, 21);
		add(text1);
		text1.setColumns(10);
		
		text2 = new JTextField();
		text2.setColumns(10);
		text2.setBounds(350, 115, 163, 21);
		add(text2);
		
		text3 = new JTextField();
		text3.setColumns(10);
		text3.setBounds(350, 146, 163, 21);
		add(text3);
		
		text4 = new JTextField();
		text4.setColumns(10);
		text4.setBounds(350, 176, 163, 21);
		add(text4);
		
		
		
		text5 = new JTextField();
		text5.setColumns(10);
		text5.setBounds(350, 207, 163, 21);
		add(text5);
		
		text6 = new JTextField();
		text6.setColumns(10);
		text6.setBounds(350, 237, 163, 21);
		add(text6);
		
		this.setBorder(BorderFactory.createEtchedBorder());		
		
		JLabel pw123 = new JLabel("默认密码为:123456");
		pw123.setBounds(543, 115, 144, 15);
		add(pw123);

	}

	class listener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			if (e.getSource()==Button1)
			{
				if(text1.getText().equals("")||text3.getText().equals("")|| 
						text4.getText().equals("")||text2.getText().equals("")|| 
						text5.getText().equals(""))
				{ 
				        JOptionPane.showMessageDialog(null,"除邮箱外输入不能有空", "提示",JOptionPane.PLAIN_MESSAGE); 
			    }
				else
			    {  
					String sql0="select count(*) from reader where id ='"+text2.getText()+"'";
					
					int n=new ConnectDB().Return(sql0);
					if(n!=0)
					{
						JOptionPane.showMessageDialog(null,"该学生已添加，请重新添加", "提示",JOptionPane.PLAIN_MESSAGE); 
												
					}
					else
					{

				        String sql="insert into "
				            + "reader(name,id,sex,age,telephone,email,password)"
				            + "values('"+text1.getText()+"','"+text2.getText()+"','"+text3.getText()
				            +"','"+text4.getText()+"','"+text5.getText()+"','"+text6.getText()+"','"+"123456"+"')"; 

	                    new ConnectDB().Update(sql);
				        			        	
				        text1.setText("");
				        text3.setText("");
				        text4.setText("");
				        text2.setText("");
				        text5.setText("");
				        text6.setText("");

				        JOptionPane.showMessageDialog(null,"添加成功", "提示",JOptionPane.PLAIN_MESSAGE); 
				        
					}
			    
			      } 
		}
		if (e.getSource()==Button2)
		{
			setVisible(false);
		}		
	}
}
}
