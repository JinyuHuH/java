import java.awt.*;
import java.awt.event.*;

import java.sql.*;

import javax.swing.*;
public class DeleteBook extends JPanel 
{
	 private static BookTableModel bookTableModel;
	 private static JTable bookTable; 	
	 JButton button1 = new JButton("�޸�");
	 JButton button2 = new JButton("ɾ��");
	 //JButton Button3 = new JButton("ˢ��");
	 JScrollPane scrollPane = new JScrollPane();
	 
	public DeleteBook() throws SQLException
	{
		setLayout(null);
		setSize(800,400);

		add(button1);
		button1.setBounds(243, 337, 93, 23);
		button1.addActionListener(new listener()); 
				
		add(button2);
		button2.setBounds(445, 337, 93, 23);
		button2.addActionListener(new listener());
		
		bookTableModel=new BookTableModel(); 
		
		bookTable=new JTable(bookTableModel); 
		
		bookTable.getColumn("���").setPreferredWidth(100);
		bookTable.getColumn("����").setPreferredWidth(130);
		bookTable.getColumn("����").setPreferredWidth(80);
		bookTable.getColumn("������").setPreferredWidth(90);
		bookTable.getColumn("����ʱ��").setPreferredWidth(150);
		bookTable.getColumn("�۸�").setPreferredWidth(40);
		bookTable.getColumn("������").setPreferredWidth(40);
		bookTable.getColumn("ʣ������").setPreferredWidth(40);
		scrollPane=new JScrollPane(bookTable);
		
		add(scrollPane);	
		scrollPane.setBounds(21, 23, 742, 287);	
		
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
		  bookTable.getColumn("����ʱ��").setPreferredWidth(150);
		  bookTable.getColumn("�۸�").setPreferredWidth(40);
		  bookTable.getColumn("������").setPreferredWidth(40);
		  bookTable.getColumn("ʣ������").setPreferredWidth(40);
		  
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
				  bookTable.setModel(bookTableModel); 
			      int rowNum=bookTable.getSelectedRow(); 
			      if(rowNum<0||rowNum>bookTable.getRowCount())
			      {        
			        JOptionPane.showMessageDialog(null,"δѡ��", "��ʾ",JOptionPane.PLAIN_MESSAGE); 
			      } 
			      else
			      { 
			        @SuppressWarnings("unused") 			        
			        ModifyBook modifyWin=new ModifyBook(null,"�޸���Ϣ",true,bookTableModel,rowNum); 			        			         
			      } 
			      refreshTable();
		    }
			else if (e.getSource()==button2)
			{
				  int rowNum=bookTable.getSelectedRow(); 
			      if(rowNum<0||rowNum>bookTable.getRowCount())
			      {        
			        JOptionPane.showMessageDialog(null,"δѡ��", "��ʾ",JOptionPane.PLAIN_MESSAGE); 
			      } 
			      else{  
			        int n = JOptionPane.showConfirmDialog(null, "ȷ��ɾ����?", "ȷ��ɾ����", JOptionPane.YES_NO_OPTION); 
			        if (n == JOptionPane.YES_OPTION) 
			        { 
			          String bookNum=(String) bookTable.getValueAt(rowNum, 0); 
			          String sql="DELETE FROM book WHERE book_num= '"+bookNum+"'"; 
			          String sql0="DELETE from borrow WHERE Bnum= '"+bookNum+"'";
			          
			          bookTableModel.deleteBook(sql); 
			          new ConnectDB().Update(sql0);
			          
			          refreshTable();
			          
			          JOptionPane.showMessageDialog(null,"ɾ���ɹ�", "��ʾ",JOptionPane.PLAIN_MESSAGE); 
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



class ModifyBook extends JDialog implements ActionListener{ 
	  //private JLabel bookNumLabel; 
	  private JLabel bookNameLabel; 
	  private JLabel bookWriterLabel; 
	  private JLabel bookPublishLabel; 
	  private JLabel bookPriceLabel; 
	  private JLabel bookTimeLabel; 
	  private JLabel bookSumLabel; 
	  private JLabel bookLeftLabel; 
	  
	  //private JTextField bookNumText; 
	  private JTextField bookNameText; 
	  private JTextField bookWriterText; 
	  private JTextField bookPublishText; 
	  private JTextField bookPriceText; 
	  private JTextField bookTimeText; 
	  private JTextField bookSumText; 
	  private JTextField bookLeftText; 
	  private JButton submitBut; 
	  private JButton cancelBut; 
	  private BookTableModel bookModel; 
	  private int rowNum; 
	  public ModifyBook(Frame owner,String title,boolean type,BookTableModel model,int row){ 
	 
	    bookModel=model; 
	    rowNum=row; 
	    //bookNumLabel=new JLabel("��  ��:"); 
	    bookNameLabel=new JLabel("��  ��:"); 
	    bookWriterLabel=new JLabel("��  ��:"); 
	    bookPublishLabel=new JLabel("������:"); 
	    bookPriceLabel=new JLabel("��  ��:"); 
	    bookTimeLabel=new JLabel("����ʱ��:"); 
	    bookSumLabel=new JLabel("������:"); 
	    bookLeftLabel=new JLabel("ʣ������:"); 
	    
	      
	    //bookNumText=new JTextField(15); 
	    bookNameText=new JTextField(15); 
	    bookWriterText=new JTextField(15); 
	    bookPublishText=new JTextField(15); 
	    bookPriceText=new JTextField(15); 
	    bookTimeText=new JTextField(15); 
	    bookSumText=new JTextField(15); 
	    bookLeftText=new JTextField(15); 
	      
	    submitBut=new JButton("ȷ���޸�"); 
	    cancelBut=new JButton("ȡ��"); 
	    submitBut.addActionListener(this); 
	    cancelBut.addActionListener(this); 
	    this.setBounds(350,150,400,420); 
	    this.setResizable(false); 
	    this.setLayout(new BorderLayout()); 
	    this.setValue(); 
	    this.initLayout(); 
	      
	  } 
	  public void initLayout(){ 
	    Container[] con1=new Container[7]; 
	    for(int i=0;i<7;i++) 
	    	con1[i]=new Container(); 
	    //con1[0].setLayout(new FlowLayout()); 
	    //con1[0].add(bookNumLabel); 
	    //con1[0].add(bookNumText); 
	      
	    con1[0].setLayout(new FlowLayout()); 
	    con1[0].add(bookNameLabel); 
	    con1[0].add(bookNameText); 
	      
	    con1[1].setLayout(new FlowLayout()); 
	    con1[1].add(bookWriterLabel); 
	    con1[1].add(bookWriterText); 
	      
	    con1[2].setLayout(new FlowLayout()); 
	    con1[2].add(bookPublishLabel); 
	    con1[2].add(bookPublishText); 
	      
	    con1[3].setLayout(new FlowLayout()); 
	    con1[3].add(bookPriceLabel); 
	    con1[3].add(bookPriceText); 
	      
	    con1[4].setLayout(new FlowLayout()); 
	    con1[4].add(bookTimeLabel); 
	    con1[4].add(bookTimeText); 
	    
	    con1[5].setLayout(new FlowLayout()); 
	    con1[5].add(bookSumLabel); 
	    con1[5].add(bookSumText);
	    
	    con1[6].setLayout(new FlowLayout()); 
	    con1[6].add(bookLeftLabel); 
	    con1[6].add(bookLeftText);
	    
	    Container con2=new Container(); 
	    con2.setLayout(new FlowLayout()); 
	    con2.add(submitBut); 
	    con2.add(cancelBut); 
	      
	    Container con3=new Container(); 
	    con3.setLayout(new GridLayout(9,1)); 
	          
	    con3.add(con1[0]); 
	    con3.add(con1[1]); 
	    con3.add(con1[2]); 
	    con3.add(con1[3]); 
	    con3.add(con1[4]); 
	    con3.add(con1[5]); 
	    con3.add(con1[6]); 
	    //con3.add(con1[7]); 
	    con3.add(con2); 

	    
	    this.add(con3); 
	    this.validate(); 
	    this.setVisible(true); 
	  } 
	  public void setValue(){ 
	    //this.bookNumText.setText((String) bookModel.getValueAt(rowNum, 0));      
	    this.bookNameText.setText((String) bookModel.getValueAt(rowNum, 1)); 
	    this.bookWriterText.setText((String) bookModel.getValueAt(rowNum, 2)); 
	    this.bookPublishText.setText((String) bookModel.getValueAt(rowNum, 3)); 
	    this.bookTimeText.setText((String) bookModel.getValueAt(rowNum, 4)); 
	    this.bookPriceText.setText((String) bookModel.getValueAt(rowNum, 5)); 
	    this.bookSumText.setText((String) bookModel.getValueAt(rowNum, 6)); 
	    this.bookLeftText.setText((String) bookModel.getValueAt(rowNum, 7)); 
	    this.validate(); 
	  } 
	  @Override
	  public void actionPerformed(ActionEvent e) { 
	  // System.out.println(bookPriceText.getText()); 
	    // TODO Auto-generated method stub 
	    if(e.getSource()==submitBut)
	    { 
	      if(bookNameText.getText().equals("")|| 
	          bookWriterText.getText().equals("")||bookPublishText.getText().equals("")|| 
	          bookPriceText.getText().equals("")||bookTimeText.getText().equals("")||
	          bookSumText.getText().equals("")||bookLeftText.getText().equals(""))
	      { 
	        //System.out.println("����ʧ��"); 
	        JOptionPane.showMessageDialog(this,"�޸Ĳ����п�", "��ʾ",JOptionPane.PLAIN_MESSAGE); 
	      } 
	      else
	      { 
	        int n = JOptionPane.showConfirmDialog(null, "ȷ���޸���?", "ȷ���޸Ŀ�", JOptionPane.YES_NO_OPTION); 
	        if (n == JOptionPane.YES_OPTION) 
	        { 
	          String sql="UPDATE book SET book_name ='"+bookNameText.getText()+"', book_writer= '"+bookWriterText.getText()
	          +"',publish_house='"+bookPublishText.getText()+"',book_price='"+bookPriceText.getText()
	          +"',book_sum='"+bookSumText.getText()+"',book_left='"+bookLeftText.getText()
	          +"',publish_time='"+bookTimeText.getText()+"' WHERE book_num = '"+(String) bookModel.getValueAt(rowNum, 0)+"' "; 

	          new ConnectDB().Update(sql); 
	          
	          DeleteBook.refreshTable();
	          
	          JOptionPane.showMessageDialog(this,"�޸ĳɹ�", "��ʾ",JOptionPane.PLAIN_MESSAGE); 


	          this.setVisible(false); 
	        }
	        else if (n == JOptionPane.NO_OPTION) 
	        { 
	          return; 
	        } 
	      } 
	    } 
	    if(e.getSource()==cancelBut)
	    { 
	      this.setVisible(false); 
	    } 
	  } 
	} 
