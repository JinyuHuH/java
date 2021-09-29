import java.awt.event.*;
import java.sql.SQLException;
import java.awt.*;
import javax.swing.*;

public class ManagerEnter extends JFrame implements ActionListener 
{
	private JFrame frame = new JFrame("ͼ��ݹ���ϵͳ ");
	 private ImageIcon background;
	private JPanel ManagerEnterPane;
	private JTextField ReaderNametextField;
	private JTextField ReaderPasswordtextField;
	public static String name,password;
	public static int number;


	public ManagerEnter() 
	{
		 background = new ImageIcon(this.getClass().getResource("back.jpg"));// ����ͼƬ
		 JLabel label = new JLabel(background);// �ѱ���ͼƬ��ʾ��һ����ǩ����
		 label.setBounds(0, 0, background.getIconWidth(),background.getIconHeight());// �ѱ�ǩ�Ĵ�Сλ������ΪͼƬ�պ�����������
		 
		 ManagerEnterPane = (JPanel) frame.getContentPane(); // �����ݴ���ת��ΪJPanel���������÷���setOpaque()��ʹ���ݴ���͸��
		 
		 ManagerEnterPane.setOpaque(false);	 
		 ManagerEnterPane.setLayout(null);//�޲���
		 frame.getLayeredPane().setLayout(null);
		 
		 frame.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE)); // �ѱ���ͼƬ��ӵ��ֲ㴰�����ײ���Ϊ����
		 
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//�رղ���
		 frame.setBounds(500, 200, 450, 300);//�����С
		 frame.setResizable(false);//���ɸ��ĳߴ�
		 frame.setVisible(true);//����ɼ�
		
		
		
		JLabel ReaderNameLabel = new JLabel("�û���:");
		ManagerEnterPane.add(ReaderNameLabel);
		ReaderNameLabel.setBounds(70, 37, 61, 37);
		ReaderNameLabel.setFont(new Font("",Font.BOLD,15));
		
		
		JLabel ReaderPasswordLabel = new JLabel("����:");
		ManagerEnterPane.add(ReaderPasswordLabel);
		ReaderPasswordLabel.setBounds(70, 121, 61, 24);
		ReaderPasswordLabel.setFont(new Font("",Font.BOLD,15));
		
		
		ReaderNametextField = new JTextField();
		ManagerEnterPane.add(ReaderNametextField);
		ReaderNametextField.setBounds(156, 39, 178, 37);		
		ReaderNametextField.setColumns(10);
		
		ReaderPasswordtextField = new JPasswordField();
		ManagerEnterPane.add(ReaderPasswordtextField);
		ReaderPasswordtextField.setForeground(Color.BLACK);
		ReaderPasswordtextField.setBounds(156, 117, 178, 37);		
		ReaderPasswordtextField.setColumns(10);
		
		JButton RegisterButton = new JButton("ע��");
		ManagerEnterPane.add(RegisterButton);
		RegisterButton.setBounds(96, 187, 88, 37);
		RegisterButton.setFont(new Font("",Font.BOLD,15));
		RegisterButton.addActionListener(this);
		
		
		JLabel ForgetLabel = new JLabel("�������룿");
		ManagerEnterPane.add(ForgetLabel);
		ForgetLabel.setBounds(358, 123, 66, 40);
		ForgetLabel.setForeground(Color.blue);
		
		
		JButton LandButton = new JButton("��½");
		ManagerEnterPane.add(LandButton);
		LandButton.setFont(new Font("", Font.BOLD, 15));
		LandButton.setBounds(246, 187, 88, 37);
		
		

		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	
	
	RegisterButton.addActionListener(new ActionListener()
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			// TODO Auto-generated method stub
			if(e.getSource()==RegisterButton)
			{
	    		new ManagerRegister();
	    	}				
		}			
	});
	
	LandButton.addActionListener(new ActionListener()
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			// TODO Auto-generated method stub
			boolean b=false;
	    	if(e.getSource()==LandButton)
	    	{
	    		String id=ReaderNametextField.getText().trim();
			    number=Integer.parseInt(id);	
			    name=ReaderNametextField.getText();
	    	    password=ReaderPasswordtextField.getText();
			    try
			    {
			    	b=new ConnectDB().testMLogin(number, password);
			    }
			    catch(Exception e1)
			    {
			    	e1.printStackTrace();
			    	System.out.println("ʧ��");
			    }
			    if (b==true)
			    {
			    	frame.dispose();					    		
					try {
						new ManagerFrame();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}								
			    }
			    else
			    	JOptionPane.showMessageDialog(null,"�û������������","֪ͨ",JOptionPane.INFORMATION_MESSAGE);
	    	}
		 }			
	});		
	
}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	}


