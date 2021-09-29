import java.awt.*;
import java.awt.event.*;

import java.sql.*;

import javax.swing.*;

public class ReaderSeeking extends JPanel 
{
	 private JTextField text;
	 private ReaderTableModel readerTableModel;
	 private JTable readerTable; 
	 JLabel table = new JLabel("请输入读者姓名");
	 JButton button1 = new JButton("查询");
	 JButton button2 = new JButton("刷新");
	 JScrollPane scrollPane = new JScrollPane();
	 
	public ReaderSeeking() throws SQLException
	{
		setLayout(null);
		setSize(800,400);
		
		add(table);
		table.setBounds(126, 37, 107, 18);
		
		text = new JTextField();
		add(text);
		text.setBounds(263, 36, 144, 21);
		text.setColumns(10);
				
		add(button1);
		button1.setBounds(423, 35, 93, 23);
		button1.addActionListener(new listener()); 
		
		
		add(button2);
		button2.setBounds(543, 35, 93, 23);
		button2.addActionListener(new listener());
		
		readerTableModel=new ReaderTableModel(); 	
		readerTable=new JTable(readerTableModel); 
		readerTable.getColumn("姓名").setPreferredWidth(80);
		readerTable.getColumn("ID号").setPreferredWidth(130);
		readerTable.getColumn("性别").setPreferredWidth(50);
		readerTable.getColumn("年龄").setPreferredWidth(50);
		readerTable.getColumn("电话号").setPreferredWidth(130);
		readerTable.getColumn("邮箱").setPreferredWidth(130);

		scrollPane=new JScrollPane(readerTable);

		add(scrollPane);	
		scrollPane.setBounds(35, 90, 742, 287);	
		
		this.setBorder(BorderFactory.createEtchedBorder());
	}
	
	class listener implements ActionListener 
	  {
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource()==button1)
			{
				if(!text.getText().equals(""))
				{ 
			        String readerName=text.getText(); 
			        String sql="SELECT * FROM reader WHERE name like'%"+readerName+"%'"; 
			        try 
			        { 
			        readerTableModel=new ReaderTableModel(sql); 
			        readerTable.setModel(readerTableModel); 
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
			else if (e.getSource()==button2)
			{
				 ReaderTableModel searchReader; 
				    try 
				    { 
				      searchReader = new ReaderTableModel("SELECT * FROM reader"); 
				      readerTable.setModel(searchReader); 
				      readerTableModel=searchReader; 
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
