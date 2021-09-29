import java.awt.*;
import java.awt.event.*;

import java.sql.*;

import javax.swing.*;


public class BookLend extends JPanel
{
	 private JTextField text;
	 private static BookTableModel bookTableModel;
	 private static JTable bookTable; 
	 JLabel table = new JLabel("请输入书名");
	 JButton button1 = new JButton("查询");
	 JButton button2 = new JButton("刷新");
	 JButton button3 = new JButton("借阅");
	 JScrollPane scrollPane = new JScrollPane();
	
	 
	public BookLend()  throws SQLException
	{
		setLayout(null);
		setSize(800,400);
		
		add(table);
		table.setBounds(104, 37, 89, 18);
		
		text = new JTextField();
		add(text);
		text.setBounds(203, 35, 144, 21);
		text.setColumns(10);
				
		add(button1);
		button1.setBounds(363, 35, 93, 23);
		button1.addActionListener(new listener()); 
		
		
		add(button2);
		button2.setBounds(483, 35, 93, 23);
		button2.addActionListener(new listener());
		
		add(button3);
		button3.setBounds(603,35,93,23);
		button3.addActionListener(new listener());
		
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
		scrollPane.setBounds(35, 90, 742, 300);	
		
		this.setBorder(BorderFactory.createEtchedBorder());

	}
	
	public static void refreshTable()
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
				if(e.getSource()==button3)
				{
				    int rowNum=bookTable.getSelectedRow(); 
				    String rnum=ReaderEnter.name;	
				    if(rowNum<0||rowNum>bookTable.getRowCount())
					{        
					  JOptionPane.showMessageDialog(null,"未选中", "提示",JOptionPane.PLAIN_MESSAGE); 
					} 
					else
					{  
					  int n = JOptionPane.showConfirmDialog(null, "确认借阅吗?", "确认框", JOptionPane.YES_NO_OPTION); 
					  if (n == JOptionPane.YES_OPTION) 
					  { 					     				          
					     String bookNum=(String) bookTable.getValueAt(rowNum, 0); 
					     String bookLeft=(String) bookTable.getValueAt(rowNum, 7); 
					     
					     if(bookLeft.equals("0"))
					     {
					       JOptionPane.showMessageDialog(null,"当前书籍已全部借出", "提示",JOptionPane.PLAIN_MESSAGE);					        	  
					     }
					     else					    	 
					     {					      
					       String sql="insert into borrow values('"+rnum+"','"+bookNum+"',now(),date_add(now(),interval 30 day))"; 
					       String sql1="update book set book_left=book_left-1 where book_num='"+bookNum+"'";
					       new ConnectDB().Update(sql);
					       new ConnectDB().Update(sql1);		
					        	  
					       refreshTable();
					        	  
					       JOptionPane.showMessageDialog(null,"借阅成功", "提示",JOptionPane.PLAIN_MESSAGE); 
					        	  					        
					     }
					          					        
					   } 
					   else if (n == JOptionPane.NO_OPTION) 
					   { 
					       return; 
					   } 
					} 
				}	
				else if (e.getSource()==button2)
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
					
	     }
	  }
}
