import java.awt.*; 
import java.awt.event.*; 
import java.sql.SQLException; 
import javax.swing.*; 
  
@SuppressWarnings("serial") 
public class ModifyRead extends JDialog implements ActionListener{ 
	
	private JTextField text1;
	private JTextField text3;
	private JTextField text4;
	private JTextField text5;
	private JTextField text6;
	
	private JLabel L1;
	private JLabel L3;
	private JLabel L4;
	private JLabel L5;
	private JLabel L6;
	
  
  private JButton submitBut; 
  private JButton cancelBut; 
  
  private ReaderTableModel readerModel; 
  
  private int rowNum; 
  
  public ModifyRead(Frame owner,String title,boolean type,ReaderTableModel model,int row){ 
 
    readerModel=model; 
    rowNum=row; 
    
    L1=new JLabel("姓 名:");  
    L3=new JLabel("性 别:"); 
    L4=new JLabel("年 龄:"); 
    L5=new JLabel("电话号:"); 
    L6=new JLabel("邮箱:"); 

       
    text1=new JTextField(15); 
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
    
    con3.add(con2); 
    
    this.add(con3); 
    this.validate(); 
    this.setVisible(true); 
  } 
  
  public void setValue()
  { 
    this.text1.setText((String) readerModel.getValueAt(rowNum, 0));      
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
        JOptionPane.showMessageDialog(this,"除邮箱外修改不能有空", "提示",JOptionPane.PLAIN_MESSAGE); 
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
