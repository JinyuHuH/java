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
 private JFrame frame = new JFrame("图书馆管理系统 ");
 private JPanel imagePanel;
 private ImageIcon background;

 
 public welcome() {
	 

	 background = new ImageIcon(this.getClass().getResource("back.jpg"));// 背景图片
	 JLabel label = new JLabel(background);// 把背景图片显示在一个标签里面
	 label.setBounds(0, 0, background.getIconWidth(),background.getIconHeight());// 把标签的大小位置设置为图片刚好填充整个面板
	 imagePanel = (JPanel) frame.getContentPane(); // 把内容窗格转化为JPanel，否则不能用方法setOpaque()来使内容窗格透明
	 frame.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE)); // 把背景图片添加到分层窗格的最底层作为背景
	 
	 imagePanel.setOpaque(false);	 
	 imagePanel.setLayout(null);//无布局
	 frame.getLayeredPane().setLayout(null);
	 
	 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭操作
	 frame.setBounds(500, 200, 450, 300);//窗体大小
	 frame.setResizable(false);//不可更改尺寸
	 frame.setVisible(true);//窗体可见
	 
	    JLabel title = new JLabel("山东大学图书馆管理系统");
		title.setBounds(124, 10, 184, 33);
		title.setFont(new Font("",Font.ITALIC+Font.BOLD,15));
		imagePanel.add(title);
		
		JButton ManagerEnterButton = new JButton("管理员入口");
		imagePanel.add(ManagerEnterButton);
		ManagerEnterButton.setBounds(124, 66, 184, 40);
		ManagerEnterButton.addActionListener(this);
		
		
		JButton ReaderEnterButton = new JButton("读者入口");
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



