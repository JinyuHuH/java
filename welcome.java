import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;


public class welcome implements ActionListener{
 private JFrame frame = new JFrame("ͼ��ݹ���ϵͳ ");
 private JPanel imagePanel;
 private ImageIcon background;

 
 public welcome() {
	 

	 background = new ImageIcon(this.getClass().getResource("back.jpg"));// ����ͼƬ
	 JLabel label = new JLabel(background);// �ѱ���ͼƬ��ʾ��һ����ǩ����
	 label.setBounds(0, 0, background.getIconWidth(),background.getIconHeight());// �ѱ�ǩ�Ĵ�Сλ������ΪͼƬ�պ�����������
	 imagePanel = (JPanel) frame.getContentPane(); // �����ݴ���ת��ΪJPanel���������÷���setOpaque()��ʹ���ݴ���͸��
	 frame.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE)); // �ѱ���ͼƬ��ӵ��ֲ㴰�����ײ���Ϊ����
	 
	 imagePanel.setOpaque(false);	 
	 imagePanel.setLayout(null);//�޲���
	 frame.getLayeredPane().setLayout(null);
	 
	 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//�رղ���
	 frame.setBounds(500, 200, 450, 300);//�����С
	 frame.setResizable(false);//���ɸ��ĳߴ�
	 frame.setVisible(true);//����ɼ�
	 
	    JLabel title = new JLabel("ɽ����ѧͼ��ݹ���ϵͳ");
		title.setBounds(124, 10, 184, 33);
		title.setFont(new Font("",Font.ITALIC+Font.BOLD,15));
		imagePanel.add(title);
		
		JButton ManagerEnterButton = new JButton("����Ա���");
		imagePanel.add(ManagerEnterButton);
		ManagerEnterButton.setBounds(124, 66, 184, 40);
		ManagerEnterButton.addActionListener(this);
		
		
		JButton ReaderEnterButton = new JButton("�������");
		imagePanel.add(ReaderEnterButton);
		ReaderEnterButton.setBounds(124, 148, 184, 40);
		ReaderEnterButton.addActionListener(this);
		
		
		ManagerEnterButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				// TODO Auto-generated method stub
				if(e.getSource()==ManagerEnterButton)
				{
					frame.dispose();
		    		new ManagerEnter();
		    		
		    	}				
			}			
		});
		
		ReaderEnterButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				// TODO Auto-generated method stub				
				if(e.getSource()==ReaderEnterButton)
				{
					frame.dispose();
		    		new ReaderEnter();
		    		
		    	}
			}			
		});
		
 }
 
 public static void main(String[] args) 
 {
	 new welcome();	
 	}

@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	
}
}



