import java.awt.*;
import java.awt.event.*;

import java.sql.*;

import javax.swing.*;


public class BookBack extends JPanel 
{
	 static String rnum=ReaderEnter.name;
	 private static BorrowTableModel borrowTableModel;
	 private static JTable borrowTable; 	
	 JButton button1 = new JButton("续借");
	 JButton button2 = new JButton("还书");
	 JScrollPane scrollPane = new JScrollPane();

	public BookBack()  throws SQLException
	{
		setLayout(null);
		setSize(800,400);
		
		
		add(button1);
		button1.setBounds(243, 337, 93, 23);
		button1.addActionListener(new listener()); 
				
		add(button2);
		button2.setBounds(445, 337, 93, 23);
		button2.addActionListener(new listener());
		
		borrowTableModel=new BorrowTableModel();
		
		borrowTable=new JTable(borrowTableModel);
		
		borrowTable.getColumn("学号").setPreferredWidth(100);
		borrowTable.getColumn("书号").setPreferredWidth(100);
		borrowTable.getColumn("借书时间").setPreferredWidth(150);
		borrowTable.getColumn("应还书时间").setPreferredWidth(150);
		
		scrollPane=new JScrollPane(borrowTable);
		
		add(scrollPane);	
		scrollPane.setBounds(33, 27, 742, 300);	
		
		
		this.setBorder(BorderFactory.createEtchedBorder());

	}
	
	public static void refreshTable()
	  { 
	    BorrowTableModel searchBook; 
	    try 
	    { 
	      searchBook = new BorrowTableModel("SELECT * FROM borrow where Rnum = '"+rnum+"'"); 
	      
	      borrowTable.setModel(searchBook);
	      
	      borrowTable.getColumn("学号").setPreferredWidth(100);
		  borrowTable.getColumn("书号").setPreferredWidth(100);
		  borrowTable.getColumn("借书时间").setPreferredWidth(150);
		  borrowTable.getColumn("应还书时间").setPreferredWidth(150);
		  
	      borrowTableModel=searchBook; 
	      
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
				 int rowNum=borrowTable.getSelectedRow(); 
			      if(rowNum<0||rowNum>borrowTable.getRowCount())
			      {        
			        JOptionPane.showMessageDialog(null,"未选中", "提示",JOptionPane.PLAIN_MESSAGE); 
			      } 
			      else
			      {  
			        int n = JOptionPane.showConfirmDialog(null, "确认续借吗?", "确认框", JOptionPane.YES_NO_OPTION); 
			        if (n == JOptionPane.YES_OPTION) 
			        { 
			          String readerNum=(String) borrowTable.getValueAt(rowNum, 0); 
			          String bookNum=(String) borrowTable.getValueAt(rowNum, 1); 
			          String bookTime=(String)borrowTable.getValueAt(rowNum, 2);
			          
			          String sql="UPDATE borrow set BorrowTime=now(),ReturnTime=date_add(now(),interval 30 day) where "
			          		+ "Rnum='"+readerNum+"' "+ "and Bnum "+" = '"+bookNum+"'"+" and borrowTime "+" = '"+bookTime+"'"; 			          
			          
			          borrowTableModel.returnBook(sql); 			          
			          
			          refreshTable();
			          
			          JOptionPane.showMessageDialog(null,"续借成功", "提示",JOptionPane.PLAIN_MESSAGE); 
			        } 
			        else if (n == JOptionPane.NO_OPTION) 
			        { 
			          return; 
			        } 
			      } 
				
							
		    }
			else if (e.getSource()==button2)
			{
				  int rowNum=borrowTable.getSelectedRow(); 
			      if(rowNum<0||rowNum>borrowTable.getRowCount())
			      {        
			        JOptionPane.showMessageDialog(null,"未选中", "提示",JOptionPane.PLAIN_MESSAGE); 
			      } 
			      else
			      {  
			        int n = JOptionPane.showConfirmDialog(null, "确认还书吗?", "确认框", JOptionPane.YES_NO_OPTION); 
			        if (n == JOptionPane.YES_OPTION) 
			        { 
			          String readerNum=(String) borrowTable.getValueAt(rowNum, 0); 
			          String bookNum=(String) borrowTable.getValueAt(rowNum, 1); 
			          String bookTime=(String)borrowTable.getValueAt(rowNum, 2);
			          
			          String sql="DELETE FROM borrow WHERE Rnum='"+readerNum+"' "+ "and Bnum "+" = '"+bookNum+"'"+" and borrowTime "+" = '"+bookTime+"'"; 			          
			          String sql1="UPDATE book set book_left=book_left+1 WHERE book_num = '"+bookNum+"'"; 
			          
			          borrowTableModel.returnBook(sql); 			          
			          borrowTableModel.returnBook(sql1); 
			          
			          refreshTable();
			          
			          JOptionPane.showMessageDialog(null,"还书成功", "提示",JOptionPane.PLAIN_MESSAGE); 
			        } 
			        else if (n == JOptionPane.NO_OPTION) 
			        { 
			          return; 
			        } 
			      } 
			}				
	     }	
	}
}


