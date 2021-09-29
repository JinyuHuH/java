import java.awt.*;
import java.awt.event.*;

import java.sql.*;

import javax.swing.*;

public class AddBook extends JPanel 
{
	private JTextField text1;
	private JTextField text2;
	private JTextField text3;
	private JTextField text4;
	private JTextField text5;
	private JTextField text6;
	private JTextField text7;
	private JTextField text8;
	JButton Button1 = new JButton("添加");
	JButton Button2 = new JButton("取消");
	public AddBook() throws SQLException
	{
		setLayout(null);
		setSize(800,400);
		
				
		add(Button1);
		Button1.setBounds(216, 348, 93, 23);
		Button1.addActionListener(new listener()); 
		
		
		add(Button2);
		Button2.setBounds(420, 348, 93, 23);
		Button2.addActionListener(new listener()); 
		
		JLabel Label= new JLabel("请输入图书信息");
		Label.setFont(new Font("宋体",Font.BOLD, 20));
		Label.setBounds(299, 29, 163, 23);
		add(Label);
		
		JLabel Label1 = new JLabel("书号：");
		Label1.setBounds(234, 87, 83, 15);
		add(Label1);
		
		JLabel label2 = new JLabel("书名：");
		label2.setBounds(234, 117, 83, 15);
		add(label2);
		
		JLabel label3 = new JLabel("作者：");
		label3.setBounds(234, 147, 83, 15);
		add(label3);
		
		JLabel label4 = new JLabel("出版社：");
		label4.setBounds(234, 177, 83, 15);
		add(label4);
		
		JLabel label5 = new JLabel("出版时间：");
		label5.setBounds(234, 207, 83, 15);
		add(label5);
		
		JLabel label6 = new JLabel("价格：");
		label6.setBounds(234, 237, 83, 15);
		add(label6);
		
		JLabel label7 = new JLabel("总数量：");
		label7.setBounds(234, 267, 83, 15);
		add(label7);
		
		JLabel label8 = new JLabel("剩余数量：");
		label8.setBounds(234, 297, 83, 15);
		add(label8);
		
		text1 = new JTextField();
		text1.setBounds(350, 87, 163, 21);
		add(text1);
		text1.setColumns(10);
		
		text2 = new JTextField();
		text2.setColumns(10);
		text2.setBounds(350, 117, 163, 21);
		add(text2);
		
		text3 = new JTextField();
		text3.setColumns(10);
		text3.setBounds(350, 147, 163, 21);
		add(text3);
		
		text4 = new JTextField();
		text4.setColumns(10);
		text4.setBounds(350, 177, 163, 21);
		add(text4);
		
		text5 = new JTextField();
		text5.setColumns(10);
		text5.setBounds(350, 207, 163, 21);
		add(text5);
		
		text6 = new JTextField();
		text6.setColumns(10);
		text6.setBounds(350, 237, 163, 21);
		add(text6);
		
		text7 = new JTextField();
		text7.setColumns(10);
		text7.setBounds(350, 267, 163, 21);
		add(text7);
		
		text8 = new JTextField();
		text8.setColumns(10);
		text8.setBounds(350, 297, 163, 21);
		add(text8);
		
		this.setBorder(BorderFactory.createEtchedBorder());		
	}
	
	class listener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			if (e.getSource()==Button1)
			{
				if(text1.getText().equals("")||text2.getText().equals("")|| 
						text3.getText().equals("")||text4.getText().equals("")|| 
						text5.getText().equals("")||text6.getText().equals("")
						||text7.getText().equals("")||text8.getText().equals(""))
				{ 
				        JOptionPane.showMessageDialog(null,"输入不能有空", "提示",JOptionPane.PLAIN_MESSAGE); 
			    }
				else
			      { 
					String sql0="select count(*) from book where book_num ='"+text1.getText()+"'";
					int n=new ConnectDB().Return(sql0);
					if(n!=0)
					{
						JOptionPane.showMessageDialog(null,"该书籍已添加，请重新添加", "提示",JOptionPane.PLAIN_MESSAGE); 
												
					}
					else
					{
						 //System.out.println("输入成功"); 
				        String sql="insert into "
				            + "book(book_num,book_name,book_writer,publish_house,publish_time,book_price,book_sum,book_left)"
				            + "values('"+text1.getText()+"','"+text2.getText()+"','"+text3.getText()
				            +"','"+text4.getText()+"','"+text5.getText()+"','"+text6.getText()
				            +"','"+text7.getText()+"','"+text8.getText()+"')"; 
				        
				        new ConnectDB().Update(sql);
				        				       
				        text1.setText("");
				        text2.setText("");
				        text3.setText("");
				        text4.setText("");
				        text5.setText("");
				        text6.setText("");
				        text7.setText("");
				        text8.setText("");
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
