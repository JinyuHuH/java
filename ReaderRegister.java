import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class ReaderRegister extends JFrame implements ActionListener
{
	private JFrame frame = new JFrame("ͼ��ݹ���ϵͳ ");
	private JPanel ReaderRegisterPane;
	private ImageIcon background;
	private JTextField Name;
    private JTextField Number;
    private JTextField Password;
    private JTextField PasswordAgain;
    String name0,sex0,telephone0,email0,password0,checkpassword0;
    int number0,age0;
    private JTextField Sex;
    private JTextField Age;
    private JTextField Telephone;
    private JTextField Email;

	public ReaderRegister() 
	{
		background = new ImageIcon(this.getClass().getResource("back.jpg"));// ����ͼƬ
   	    JLabel label = new JLabel(background);// �ѱ���ͼƬ��ʾ��һ����ǩ����
   	    label.setBounds(0, 0, background.getIconWidth(),background.getIconHeight());// �ѱ�ǩ�Ĵ�Сλ������ΪͼƬ�պ�����������
   	    ReaderRegisterPane = (JPanel) frame.getContentPane(); // �����ݴ���ת��ΪJPanel���������÷���setOpaque()��ʹ���ݴ���͸��
   	    frame.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE)); // �ѱ���ͼƬ��ӵ��ֲ㴰�����ײ���Ϊ����
   	    
    	ReaderRegisterPane.setOpaque(false);	 
    	ReaderRegisterPane.setLayout(null);//�޲���
   	    frame.getLayeredPane().setLayout(null);
   	  
   	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//�رղ���
   	    frame.setBounds(450, 200, 450, 430);//�����С
   	    frame.setResizable(false);//���ɸ��ĳߴ�
   	    frame.setVisible(true);//����ɼ�
   	    
   	    JLabel name = new JLabel("�û���");
		ReaderRegisterPane.add(name);
		name.setBounds(82, 24, 54, 15);
				
		Name = new JTextField();
		ReaderRegisterPane.add(Name);
		Name.setBounds(190, 21, 155, 21);
		Name.setColumns(10);
		
		JLabel number = new JLabel("ѧ��");
		ReaderRegisterPane.add(number);
		number.setBounds(82, 64, 54, 15);
	
		Number = new JTextField();
		ReaderRegisterPane.add(Number);
		Number.setColumns(10);
		Number.setBounds(190, 61, 155, 21);
			
		JLabel password = new JLabel("����");
		ReaderRegisterPane.add(password);
		password.setBounds(82, 258, 54, 15);
		
		Password = new JPasswordField();
		ReaderRegisterPane.add(Password);
		Password.setColumns(10);
		Password.setBounds(190, 255, 155, 21);
		
		
		JLabel passwordagain = new JLabel("ȷ������");
		ReaderRegisterPane.add(passwordagain);
		passwordagain.setBounds(82, 298, 80, 15);
		
		PasswordAgain = new JPasswordField();
		ReaderRegisterPane.add(PasswordAgain);
		PasswordAgain.setColumns(10);
		PasswordAgain.setBounds(190, 295, 155, 21);
		
		JButton register = new JButton("ע��");
		frame.getContentPane().add(register);
		register.setBounds(65, 338, 100, 30);
		register.addActionListener(this);
		
		
		JButton cancle = new JButton("ȡ��");
		frame.getContentPane().add(cancle);
		cancle.setBounds(235, 338, 100, 30);
		
		JLabel sex = new JLabel("�Ա�");
		sex.setBounds(82, 100, 54, 15);
		frame.getContentPane().add(sex);
		
		JLabel age = new JLabel("����");
		age.setBounds(82, 140, 54, 15);
		frame.getContentPane().add(age);
		
		JLabel telephone = new JLabel("�绰��");
		telephone.setBounds(82, 180, 54, 15);
		frame.getContentPane().add(telephone);
		
		Sex = new JTextField();
		Sex.setBounds(190, 97, 155, 21);
		frame.getContentPane().add(Sex);
		Sex.setColumns(10);
		
		Age = new JTextField();
		Age.setColumns(10);
		Age.setBounds(190, 137, 155, 21);
		frame.getContentPane().add(Age);
		
		Telephone = new JTextField();
		Telephone.setColumns(10);
		Telephone.setBounds(190, 177, 155, 21);
		frame.getContentPane().add(Telephone);
		
		JLabel email = new JLabel("����");
		email.setBounds(82, 220, 54, 15);
		frame.getContentPane().add(email);
		
		Email = new JTextField();
		Email.setColumns(10);
		Email.setBounds(190, 217, 155, 21);
		frame.getContentPane().add(Email);
		
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		register.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				// TODO Auto-generated method stub
				boolean b=false;
				if(e.getSource()==register)
				{    name0=Name.getText();
				     
				     String id=Number.getText().trim();
			         number0=Integer.parseInt(id);
			     
                     sex0=Sex.getText();
				     
				     String ag=Age.getText().trim();
				     age0=Integer.parseInt(ag);
				     
				     telephone0=Telephone.getText();	
				     
				     email0=Email.getText();
				     
				     password0=Password.getText();
				     checkpassword0=PasswordAgain.getText();	
				     
				    try
				    {
				    	b=new ConnectDB().testRname(name0);
				    }
				    catch(Exception e1)
				    {
				    	e1.printStackTrace();
				    }
				    if (b==false)
				    {	
				    	if(!password0.equals(checkpassword0))
				    	{				    		
				    		//�������벻һ��				    		
				    		JOptionPane.showMessageDialog(null,"�������벻һ�£�����������","����",JOptionPane.INFORMATION_MESSAGE);
				    		Password.setText("");
				    		PasswordAgain.setText("");
				    	}
				    	else
				    	{
				    		if(Name.getText().length()>4)
				    		{
				    		  JOptionPane.showMessageDialog(null,"�����������","����",JOptionPane.INFORMATION_MESSAGE);
				    		  Name.setText("");
				    		  }
				    		  else  if(Number.getText().length()!=8)
				    		  {
				    			JOptionPane.showMessageDialog(null,"ѧ���������","����",JOptionPane.INFORMATION_MESSAGE);
				    			Number.setText("");
				    			}
				    		  else	if(!Sex.getText().equals("��")&&!Sex.getText().equals("Ů"))
				    		    {
				    		      JOptionPane.showMessageDialog(null,"�Ա��������","����",JOptionPane.INFORMATION_MESSAGE);
				    		      Sex.setText("");
				    		      }
				    		  else  if(Age.getText().length()!=2)
				    		      {
				    			    JOptionPane.showMessageDialog(null,"�����������","����",JOptionPane.INFORMATION_MESSAGE);
				    			    Age.setText("");
				    			    }
				    		  else  if (Telephone.getText().length()!=11)
						    		{
						    			JOptionPane.showMessageDialog(null,"�绰���������","����",JOptionPane.INFORMATION_MESSAGE);
						    			Telephone.setText("");
						    			}
				    		  else
				    		  {
				    			    ConnectDB db=new ConnectDB();
							    	db.RRegister(name0,number0,sex0,age0,telephone0,email0, password0);
							    	frame.setVisible(false);
							    	JOptionPane.showMessageDialog(null,"����ע��ɹ�","֪ͨ",JOptionPane.INFORMATION_MESSAGE);
				    		  }				    		
						 }				    					    						    								    						    		
				    	}				    	
				    }
				    else
				    {			
				    	//���û����ѱ�ע��
				    	JOptionPane.showMessageDialog(null,"���û����ѱ�ע�ᣬ������ע��","����",JOptionPane.INFORMATION_MESSAGE);
				    	frame.setVisible(false);
				    	//System.out.println(number0);
				    	new ReaderRegister();
				    }
				if (e.getSource()==cancle)
				{
					System.exit(0);
				}
				}			
		});
	
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		
	}
}
