import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.sql.*;

public class ReaderFrame  extends JFrame implements ActionListener{
	private JFrame frame = new JFrame("图书馆管理系统 ");
	private JPanel ReaderPane;
	private ImageIcon background,background1;
    BookSeeking bookseeking=new BookSeeking();
    DeleteBook deletebook=new DeleteBook();
    AddReader addreader=new AddReader();
    ReaderSeeking readerseeking =new ReaderSeeking();
    DeleteReader deletereader =new DeleteReader();
   
    ReaderMessage mainpanel=new ReaderMessage();
   

	public ReaderFrame() throws SQLException{
		//背景
		background = new ImageIcon(this.getClass().getResource("back.jpg"));
		JLabel label = new JLabel(background);
		label.setBounds(0, 200, 1000,700);
		 
		 background1 = new ImageIcon(this.getClass().getResource("top1.jpg"));
		 JLabel label1 = new JLabel(background1);
		 label1.setBounds(0, 0, 1000,200);		 
		
		ReaderPane = (JPanel) frame.getContentPane();
		ReaderPane.setOpaque(false);//非透明
		ReaderPane.setLayout(null);//无布局
		 frame.getLayeredPane().setLayout(null);
		 frame.setLocationRelativeTo(null);

		 
		 //frame.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
		 frame.getLayeredPane().add(label1, new Integer(Integer.MIN_VALUE));

		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 frame.setBounds(200, 20, 1000, 680);
		 frame.setResizable(true);
		 frame.setVisible(true);
		
		JButton BookLend= new JButton("图书借阅");
		ReaderPane.add(BookLend);
		BookLend.setBounds(25, 336, 90, 30);
		BookLend.addActionListener(this);
		
		JButton BookBack = new JButton("图书归还");
		ReaderPane.add(BookBack);
		BookBack.setBounds(25, 436, 90, 30);		
		BookBack.addActionListener(this);
		
		JButton Message = new JButton("返回个人信息");
		frame.getContentPane().add(Message);
		Message.setBounds(25, 532, 112, 30);
		Message.addActionListener(this);
		
		
		frame.getContentPane().add(mainpanel);
		mainpanel.setBounds(170, 230, 800, 400);

		
			
		BookLend.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				 BookLend booklend = null;
				try {
					booklend = new BookLend();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// TODO Auto-generated method stub
				mainpanel.removeAll();
				mainpanel.add(booklend);
				mainpanel.repaint();							
			}			
		});
		
		BookBack.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{			
			    BookBack bookback = null;
				try {
					bookback = new BookBack();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// TODO Auto-generated method stub			    
				mainpanel.removeAll();
				mainpanel.add(bookback);
				mainpanel.repaint();			
			}			
		});
				
		Message.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				ReaderMessage mainpanel1=new ReaderMessage();
				// TODO Auto-generated method stub
				mainpanel.removeAll();
				mainpanel.add(mainpanel1);
				mainpanel.repaint();							
			}			
		});
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
