import java.awt.*;
import java.awt.event.*;

import java.sql.*;

import javax.swing.*;


public class BookSeeking extends JPanel
{
	 private JTextField text;
	 private BookTableModel bookTableModel;
	 private JTable bookTable; 
	 JLabel table = new JLabel("请输入书名");
	 JButton button1 = new JButton("查询");
	 JButton button2 = new JButton("刷新");
	 JButton button3 = new JButton("查询");
	 JScrollPane scrollPane = new JScrollPane();
	 private JTextField text1;
	 
	public BookSeeking()  throws SQLException
	{
		setLayout(null);
		setSize(800,400);
		
		add(table);
		table.setBounds(138, 12, 112, 18);
		
		text = new JTextField();
		add(text);
		text.setBounds(260, 11, 144, 21);
		text.setColumns(10);
				
		add(button1);
		button1.setBounds(420, 10, 93, 23);
		button1.addActionListener(new listener()); 
		
		
		add(button2);
		button2.setBounds(540, 10, 93, 23);
		button2.addActionListener(new listener());
		
		bookTableModel=new BookTableModel(); 	
		bookTable=new JTable(bookTableModel); 
		bookTable.getColumn("书号").setPreferredWidth(100);
		bookTable.getColumn("书名").setPreferredWidth(130);
		bookTable.getColumn("作者").setPreferredWidth(80);
		bookTable.getColumn("出版社").setPreferredWidth(90);
		bookTable.getColumn("出版时间").setPreferredWidth(140);
		bookTable.getColumn("价格").setPreferredWidth(40);
		bookTable.getColumn("总数量").setPreferredWidth(40);
		bookTable.getColumn("剩余数量").setPreferredWidth(50);
		scrollPane=new JScrollPane(bookTable);
		
		add(scrollPane);	
		scrollPane.setBounds(35, 90, 742, 287);	
		
		this.setBorder(BorderFactory.createEtchedBorder());
		
		JLabel table1 = new JLabel("请输入查询内容");
		table1.setBounds(138, 51, 112, 18);
		add(table1);
		
		text1 = new JTextField();
		text1.setColumns(10);
		text1.setBounds(260, 50, 253, 21);
		add(text1);
		

		button3.setBounds(540, 49, 93, 23);
		add(button3);
		button3.addActionListener(new listener());
	}
	
	  class listener implements ActionListener 
	  {
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource()==button1)
			{
				if(!text.getText().equals(""))
				{ 
			        String bookName=text.getText(); 
			        String sql="SELECT * FROM book WHERE book_name like'%"+bookName+"%'"; 
			        try 
			        { 
			        bookTableModel=new BookTableModel(sql); 
			        bookTable.setModel(bookTableModel); 
			        } 
			        catch (SQLException e1) 
			        { 
			        // TODO Auto-generated catch block 
			        e1.printStackTrace(); 
			        } 
			          
			      }
				else
				{ 
			        JOptionPane.showMessageDialog(null,"输入不能为空", "提示",JOptionPane.PLAIN_MESSAGE); 
			    } 			
		    }
			else 
				if (e.getSource()==button2)
			    {
				 BookTableModel searchBook; 
				    try 
				    { 
				      searchBook = new BookTableModel("SELECT * FROM book"); 
				      bookTable.setModel(searchBook); 
				      
				      bookTable.getColumn("书号").setPreferredWidth(100);
					  bookTable.getColumn("书名").setPreferredWidth(130);
					  bookTable.getColumn("作者").setPreferredWidth(80);
					  bookTable.getColumn("出版社").setPreferredWidth(90);
					  bookTable.getColumn("出版时间").setPreferredWidth(140);
					  bookTable.getColumn("价格").setPreferredWidth(40);
					  bookTable.getColumn("总数量").setPreferredWidth(40);
					  bookTable.getColumn("剩余数量").setPreferredWidth(50);
				      
				      bookTableModel=searchBook; 
				    } 
				    catch (SQLException e1) 
				    { 
				      // TODO Auto-generated catch block 
				      e1.printStackTrace(); 
				    } 
			}
				else if (e.getSource()==button3)
				{
					Search nls=new Search();
					Returns rs=nls.ns(text1.getText());
					System.out.println(rs.resql);
					try 
			        { 
			        bookTableModel=new BookTableModel(rs.resql); 
			        bookTable.setModel(bookTableModel); 
			        
			        bookTable.getColumn("书号").setPreferredWidth(100);
					  bookTable.getColumn("书名").setPreferredWidth(130);
					  bookTable.getColumn("作者").setPreferredWidth(80);
					  bookTable.getColumn("出版社").setPreferredWidth(90);
					  bookTable.getColumn("出版时间").setPreferredWidth(140);
					  bookTable.getColumn("价格").setPreferredWidth(40);
					  bookTable.getColumn("总数量").setPreferredWidth(40);
					  bookTable.getColumn("剩余数量").setPreferredWidth(50);
					  
			        } 
			        catch (SQLException e1) 
			        { 
			        // TODO Auto-generated catch block 
			        e1.printStackTrace(); 
			        } 
				}
	     }
	  }
}
