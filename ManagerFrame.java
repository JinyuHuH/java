import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.sql.*;

public class ManagerFrame  extends JFrame implements ActionListener{
	private JFrame frame = new JFrame("图书馆管理系统 ");
	private JPanel ManagerPane;
	private ImageIcon background,background1;
    BookSeeking bookseeking=new BookSeeking();
  
    AddReader addreader=new AddReader();
    ReaderSeeking readerseeking =new ReaderSeeking();
    DeleteReader deletereader =new DeleteReader();
    
    ManagerMessage mainpanel=new ManagerMessage();
    

	public ManagerFrame() throws SQLException{
		//背景
		background = new ImageIcon(this.getClass().getResource("back.jpg"));
		JLabel label = new JLabel(background);
		label.setBounds(0, 200, 1000,700);
		 
		 background1 = new ImageIcon(this.getClass().getResource("top1.jpg"));
		 JLabel label1 = new JLabel(background1);
		 label1.setBounds(0, 0, 1000,200);		 
		
		ManagerPane = (JPanel) frame.getContentPane();
		ManagerPane.setOpaque(false);//非透明
		ManagerPane.setLayout(null);//无布局
		 frame.getLayeredPane().setLayout(null);

		 
		 //frame.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
		 frame.getLayeredPane().add(label1, new Integer(Integer.MIN_VALUE));

		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 frame.setBounds(200, 20, 1000, 680);
		 frame.setResizable(true);
		 frame.setVisible(true);
		
		JButton BookSeeking= new JButton("图书查询");
		ManagerPane.add(BookSeeking);
		BookSeeking.setBounds(25, 246, 90, 30);
		BookSeeking.addActionListener(this);
		
		JButton AddBook = new JButton("图书添加");
		ManagerPane.add(AddBook);
		AddBook.setBounds(25, 299, 90, 30);		
		AddBook.addActionListener(this);
		
		JButton DeleteBook = new JButton("图书编辑");
		ManagerPane.add(DeleteBook);
		DeleteBook.setBounds(25, 352, 90, 30);
		DeleteBook.addActionListener(this);
				
		JButton ReaderSeeking= new JButton("读者信息查询");
		ManagerPane.add(ReaderSeeking);
		ReaderSeeking.setBounds(25, 407, 112, 30);		
		ReaderSeeking.addActionListener(this);
		
		JButton AddReader = new JButton("读者信息添加");
		ManagerPane.add(AddReader);
		AddReader.setBounds(25, 466, 112, 30);		
		AddReader.addActionListener(this);
		
		JButton DeleteReader = new JButton("读者信息编辑");
		ManagerPane.add(DeleteReader);
		DeleteReader.setBounds(25, 524, 112, 30);		
		DeleteReader.addActionListener(this);
		

		frame.getContentPane().add(mainpanel);
		mainpanel.setBounds(170, 230, 800, 400);
		
		
		JButton Message = new JButton("返回个人信息");
		frame.getContentPane().add(Message);
		Message.setBounds(25, 575, 112, 30);
		Message.addActionListener(this);

		
			
		BookSeeking.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				mainpanel.removeAll();
				mainpanel.add(bookseeking);
				mainpanel.repaint();							
			}			
		});
		
		AddBook.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{				
				// TODO Auto-generated method stub
			    AddBook addbook = null;
				try {
					addbook = new AddBook();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				mainpanel.removeAll();
				mainpanel.add(addbook);
				mainpanel.repaint();			
			}			
		});
		
		DeleteBook.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{				
				// TODO Auto-generated method stub
				  DeleteBook deletebook = null;
				try {
					deletebook = new DeleteBook();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				mainpanel.removeAll();
				mainpanel.add(deletebook);
				mainpanel.repaint();		
			}			
		});
		
		ReaderSeeking.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{				
				// TODO Auto-generated method stub
				mainpanel.removeAll();
				mainpanel.add(readerseeking);
				mainpanel.repaint();				
			}			
		});
		
		AddReader.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{				
				// TODO Auto-generated method stub
				mainpanel.removeAll();
				mainpanel.add(addreader);
				mainpanel.repaint();	
			}			
		});
		
		DeleteReader.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{				
				// TODO Auto-generated method stub
				mainpanel.removeAll();
				mainpanel.add(deletereader);
				mainpanel.repaint();
			}			
		});
				
		Message.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				ManagerMessage mainpanel1=new ManagerMessage();
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
