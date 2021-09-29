import java.awt.*;
import java.awt.event.*;

import java.sql.*;

import javax.swing.*;

public class DeleteReader extends JPanel 
{
	 private static ReaderTableModel readerTableModel;
	 private static JTable readerTable; 	
	 JButton button1 = new JButton("修改");
	 JButton button2 = new JButton("删除");
	 //JButton Button3 = new JButton("刷新");
	 JScrollPane scrollPane = new JScrollPane();
	
	public DeleteReader() throws SQLException
	{
		setLayout(null);
		setSize(800,400);

		add(button1);
		button1.setBounds(243, 337, 93, 23);
		button1.addActionListener(new listener()); 
				
		add(button2);
		button2.setBounds(445, 337, 93, 23);
		button2.addActionListener(new listener());
		
		readerTableModel=new ReaderTableModel(); 	
		readerTable=new JTable(readerTableModel); 
		readerTable.getColumn("姓名").setPreferredWidth(80);
		readerTable.getColumn("性别").setPreferredWidth(50);
		readerTable.getColumn("年龄").setPreferredWidth(50);
		readerTable.getColumn("ID号").setPreferredWidth(130);
		readerTable.getColumn("电话号").setPreferredWidth(130);
		readerTable.getColumn("邮箱").setPreferredWidth(130);

		scrollPane=new JScrollPane(readerTable);
		
		add(scrollPane);	
		scrollPane.setBounds(21, 23, 742, 287);	
		
		this.setBorder(BorderFactory.createEtchedBorder());
		
		//add(Button3);
		//Button3.setBounds(342, 337, 93, 23);
		
		/*Button3.addMouseListener(new MouseListener() 
		{
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				refreshTable();
			}
			@SuppressWarnings("deprecation")
			@Override
			public void mouseEntered(MouseEvent arg0) {		
			}
			@SuppressWarnings("deprecation")
			@Override
			public void mouseExited(MouseEvent arg0) {
			}
			@Override
			public void mousePressed(MouseEvent arg0) {}
			@Override
			public void mouseReleased(MouseEvent arg0) {}				
			});*/
		}
	

	public static void refreshTable()
	  { 
	    ReaderTableModel searchReader; 
	    try 
	    { 
	      searchReader = new ReaderTableModel("SELECT * FROM reader"); 
	      
	        readerTable.setModel(searchReader); 
	        readerTable.getColumn("姓名").setPreferredWidth(80);
			readerTable.getColumn("性别").setPreferredWidth(50);
			readerTable.getColumn("年龄").setPreferredWidth(50);
			readerTable.getColumn("ID号").setPreferredWidth(130);
			readerTable.getColumn("电话号").setPreferredWidth(130);
			readerTable.getColumn("邮箱").setPreferredWidth(130);
		  
	      readerTableModel=searchReader; 
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
				  readerTable.setModel(readerTableModel); 
			      int rowNum=readerTable.getSelectedRow(); 
			      if(rowNum<0||rowNum>readerTable.getRowCount()){        
			        JOptionPane.showMessageDialog(null,"未选中", "提示",JOptionPane.PLAIN_MESSAGE); 
			      } 
			      else{ 
			        @SuppressWarnings("unused") 
			        ModifyReader modifyWin=new ModifyReader(null,"修改信息",true,readerTableModel,rowNum); 
			        refreshTable(); 
			      } 
		    }
			else if (e.getSource()==button2)
			{
				  int rowNum=readerTable.getSelectedRow(); 
			      if(rowNum<0||rowNum>readerTable.getRowCount())
			      {        
			        JOptionPane.showMessageDialog(null,"未选中", "提示",JOptionPane.PLAIN_MESSAGE); 
			      } 
			      else{  
			        int n = JOptionPane.showConfirmDialog(null, "确认删除吗?", "确认删除框", JOptionPane.YES_NO_OPTION); 
			        if (n == JOptionPane.YES_OPTION) 
			        { 
			          String readerNum=(String) readerTable.getValueAt(rowNum, 1); 
			          
			          String sql="DELETE FROM reader WHERE id= '"+readerNum+"'"; 
			          String sql0="DELETE from borrow WHERE Rnum= '"+readerNum+"'";
			          
			          new ConnectDB().Update(sql0);			          
			          readerTableModel.deleteReader(sql); 
			          
			          refreshTable(); 
			          JOptionPane.showMessageDialog(null,"删除成功", "提示",JOptionPane.PLAIN_MESSAGE); 
			        } else if (n == JOptionPane.NO_OPTION) { 
			          return; 
			        } 
			      } 
			}				
	     }	
	}
}

class ModifyReader extends JDialog implements ActionListener{ 
	
	private JTextField text1;
	//private JTextField text2;
	private JTextField text3;
	private JTextField text4;
	private JTextField text5;
	private JTextField text6;
	
	private JLabel L1;
	//private JLabel L2;
	private JLabel L3;
	private JLabel L4;
	private JLabel L5;
	private JLabel L6;
	
  
  private JButton submitBut; 
  private JButton cancelBut; 
  
  private ReaderTableModel readerModel; 
  
  private int rowNum; 
  
  public ModifyReader(Frame owner,String title,boolean type,ReaderTableModel model,int row){ 
 
    readerModel=model; 
    rowNum=row; 
    
    L1=new JLabel("姓 名:"); 
    //L2=new JLabel("ID:"); 
    L3=new JLabel("性 别:"); 
    L4=new JLabel("年 龄:"); 
    L5=new JLabel("电话号:"); 
    L6=new JLabel("邮箱:"); 

       
    text1=new JTextField(15); 
    //text2=new JTextField(15); 
    text3=new JTextField(15); 
    text4=new JTextField(15); 
    text5=new JTextField(15); 
    text6=new JTextField(15); 

      
    submitBut=new JButton("确认修改"); 
    cancelBut=new JButton("取消"); 
    
    submitBut.addActionListener(this); 
    cancelBut.addActionListener(this); 
    
    this.setBounds(350,150,400,350); 
    this.setResizable(false); 
    this.setLayout(new BorderLayout()); 
    this.setValue(); 
    this.initLayout(); 
      
  } 
  
  public void initLayout(){ 
    Container[] con1=new Container[5]; 
    for(int i=0;i<5;i++) 
    	con1[i]=new Container(); 
    con1[0].setLayout(new FlowLayout()); 
    con1[0].add(L1); 
    con1[0].add(text1); 
      
    con1[1].setLayout(new FlowLayout()); 
    con1[1].add(L3); 
    con1[1].add(text3); 
      
    con1[2].setLayout(new FlowLayout()); 
    con1[2].add(L4); 
    con1[2].add(text4); 
      
    con1[3].setLayout(new FlowLayout()); 
    con1[3].add(L5); 
    con1[3].add(text5); 
      
    con1[4].setLayout(new FlowLayout()); 
    con1[4].add(L6); 
    con1[4].add(text6); 
      
    //con1[5].setLayout(new FlowLayout()); 
    //con1[5].add(L6); 
    //con1[5].add(text6); 
        
    Container con2=new Container(); 
    con2.setLayout(new FlowLayout()); 
    con2.add(submitBut); 
    con2.add(cancelBut); 
      
    Container con3=new Container(); 
    con3.setLayout(new GridLayout(6,1)); 
          
    con3.add(con1[0]); 
    con3.add(con1[1]); 
    con3.add(con1[2]); 
    con3.add(con1[3]); 
    con3.add(con1[4]); 
    //con3.add(con1[5]); 
    
    con3.add(con2); 
    
    this.add(con3); 
    this.validate(); 
    this.setVisible(true); 
  } 
  
  public void setValue()
  { 
    this.text1.setText((String) readerModel.getValueAt(rowNum, 0));      
    //this.text2.setText((String) readerModel.getValueAt(rowNum, 1)); 
    this.text3.setText((String) readerModel.getValueAt(rowNum, 2)); 
    this.text4.setText((String) readerModel.getValueAt(rowNum, 3)); 
    this.text5.setText((String) readerModel.getValueAt(rowNum, 4)); 
    this.text6.setText((String) readerModel.getValueAt(rowNum, 5)); 

    this.validate(); 
  } 
  @Override
  public void actionPerformed(ActionEvent e) { 
  // System.out.println(bookPriceText.getText()); 
    // TODO Auto-generated method stub 
    if(e.getSource()==submitBut)
    { 
      if(text1.getText().equals("")||text3.getText().equals("")|| 
    		  text4.getText().equals("")||text5.getText().equals(""))
      { 
        //System.out.println("输入失败"); 
        JOptionPane.showMessageDialog(this,"修改格式错误，除邮箱外不能有空", "提示",JOptionPane.PLAIN_MESSAGE); 
      } 
      else
      { 
        int n = JOptionPane.showConfirmDialog(null, "确认修改吗?", "确认修改框", JOptionPane.YES_NO_OPTION); 
        if (n == JOptionPane.YES_OPTION) 
        { 
          String sql="UPDATE reader SET name ='"+text1.getText()+"', sex= '"+text3.getText()
          +"',age='"+text4.getText()+"',telephone='"+text5.getText()+"',email='"+text6.getText()
          +"' WHERE id = '"+readerModel.getValueAt(rowNum, 1)+"' "; 
          
          new ConnectDB().Update(sql); 
          
          DeleteReader.refreshTable();
          
          JOptionPane.showMessageDialog(this,"修改成功", "提示",JOptionPane.PLAIN_MESSAGE); 
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

