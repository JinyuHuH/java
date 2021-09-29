import java.awt.*;
import java.awt.event.*;

import java.sql.*;

import javax.swing.*;


public class BookLend extends JPanel
{
	 private JTextField text;
	 private static BookTableModel bookTableModel;
	 private static JTable bookTable; 
	 JLabel table = new JLabel("����������");
	 JButton button1 = new JButton("��ѯ");
	 JButton button2 = new JButton("ˢ��");
	 JButton button3 = new JButton("����");
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
		bookTable.getColumn("���").setPreferredWidth(100);
		bookTable.getColumn("����").setPreferredWidth(130);
		bookTable.getColumn("����").setPreferredWidth(80);
		bookTable.getColumn("������").setPreferredWidth(90);
		bookTable.getColumn("����ʱ��").setPreferredWidth(140);
		bookTable.getColumn("�۸�").setPreferredWidth(40);
		bookTable.getColumn("������").setPreferredWidth(40);
		bookTable.getColumn("ʣ������").setPreferredWidth(50);
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
	      
	      bookTable.getColumn("���").setPreferredWidth(100);
		  bookTable.getColumn("����").setPreferredWidth(130);
		  bookTable.getColumn("����").setPreferredWidth(80);
		  bookTable.getColumn("������").setPreferredWidth(90);
		  bookTable.getColumn("����ʱ��").setPreferredWidth(140);
		  bookTable.getColumn("�۸�").setPreferredWidth(40);
		  bookTable.getColumn("������").setPreferredWidth(40);
		  bookTable.getColumn("ʣ������").setPreferredWidth(50);
		  
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
			        JOptionPane.showMessageDialog(null,"���벻��Ϊ��", "��ʾ",JOptionPane.PLAIN_MESSAGE); 
			    } 			
		    }
			else 							
				if(e.getSource()==button3)
				{
				    int rowNum=bookTable.getSelectedRow(); 
				    String rnum=ReaderEnter.name;	
				    if(rowNum<0||rowNum>bookTable.getRowCount())
					{        
					  JOptionPane.showMessageDialog(null,"δѡ��", "��ʾ",JOptionPane.PLAIN_MESSAGE); 
					} 
					else
					{  
					  int n = JOptionPane.showConfirmDialog(null, "ȷ�Ͻ�����?", "ȷ�Ͽ�", JOptionPane.YES_NO_OPTION); 
					  if (n == JOptionPane.YES_OPTION) 
					  { 					     				          
					     String bookNum=(String) bookTable.getValueAt(rowNum, 0); 
					     String bookLeft=(String) bookTable.getValueAt(rowNum, 7); 
					     
					     if(bookLeft.equals("0"))
					     {
					       JOptionPane.showMessageDialog(null,"��ǰ�鼮��ȫ�����", "��ʾ",JOptionPane.PLAIN_MESSAGE);					        	  
					     }
					     else					    	 
					     {					      
					       String sql="insert into borrow values('"+rnum+"','"+bookNum+"',now(),date_add(now(),interval 30 day))"; 
					       String sql1="update book set book_left=book_left-1 where book_num='"+bookNum+"'";
					       new ConnectDB().Update(sql);
					       new ConnectDB().Update(sql1);		
					        	  
					       refreshTable();
					        	  
					       JOptionPane.showMessageDialog(null,"���ĳɹ�", "��ʾ",JOptionPane.PLAIN_MESSAGE); 
					        	  					        
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
					      
					      bookTable.getColumn("���").setPreferredWidth(100);
						  bookTable.getColumn("����").setPreferredWidth(130);
						  bookTable.getColumn("����").setPreferredWidth(80);
						  bookTable.getColumn("������").setPreferredWidth(90);
						  bookTable.getColumn("����ʱ��").setPreferredWidth(140);
						  bookTable.getColumn("�۸�").setPreferredWidth(40);
						  bookTable.getColumn("������").setPreferredWidth(40);
						  bookTable.getColumn("ʣ������").setPreferredWidth(50);
					      
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
