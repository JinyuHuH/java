import java.awt.*;
import java.awt.event.*;

import java.sql.*;

import javax.swing.*;


public class BookSeeking extends JPanel
{
	 private JTextField text;
	 private BookTableModel bookTableModel;
	 private JTable bookTable; 
	 JLabel table = new JLabel("����������");
	 JButton button1 = new JButton("��ѯ");
	 JButton button2 = new JButton("ˢ��");
	 JButton button3 = new JButton("��ѯ");
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
		scrollPane.setBounds(35, 90, 742, 287);	
		
		this.setBorder(BorderFactory.createEtchedBorder());
		
		JLabel table1 = new JLabel("�������ѯ����");
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
			        JOptionPane.showMessageDialog(null,"���벻��Ϊ��", "��ʾ",JOptionPane.PLAIN_MESSAGE); 
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
				else if (e.getSource()==button3)
				{
					Search nls=new Search();
					Returns rs=nls.ns(text1.getText());
					System.out.println(rs.resql);
					try 
			        { 
			        bookTableModel=new BookTableModel(rs.resql); 
			        bookTable.setModel(bookTableModel); 
			        
			        bookTable.getColumn("���").setPreferredWidth(100);
					  bookTable.getColumn("����").setPreferredWidth(130);
					  bookTable.getColumn("����").setPreferredWidth(80);
					  bookTable.getColumn("������").setPreferredWidth(90);
					  bookTable.getColumn("����ʱ��").setPreferredWidth(140);
					  bookTable.getColumn("�۸�").setPreferredWidth(40);
					  bookTable.getColumn("������").setPreferredWidth(40);
					  bookTable.getColumn("ʣ������").setPreferredWidth(50);
					  
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
