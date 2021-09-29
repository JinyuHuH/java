import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

public class ConnectDB {
	String Rname, Rpassword,Mname,Mpassword,Msex,Mtelephone,Rsex,Rtelephone,Remail;
	int Rnumber,Mnumber,Mage,Rage;
	Connection conn;
	Statement stmt;
	ResultSet rs;
	PreparedStatement ps;
	String word1, word2;

	public ConnectDB() {
		try{
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/book?useUnicode=true&characterEncoding=utf-8&useSSL=true";
			String user="root";
			String password="root";
			conn = DriverManager.getConnection(url,user,password);
			if(conn!=null){
				System.out.println("success");
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	// 读者注册
	public void RRegister(String name,int number,String sex,int age,String telephone, String email,String password) {
		this.Rnumber=number;
		this.Rname = name;
		this.Rpassword = password;
		this.Rsex=sex;
		this.Rage=age;
		this.Rtelephone=telephone;
		this.Remail=email;
		
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate("USE book;");
			stmt.executeUpdate("INSERT INTO reader(name,id,sex,age,telephone,email,password)  "
					+ "VALUES ('" +name+"','"+ number + "','"+ sex + "','"+ age + "','"+ telephone + "','"+ email + "','"+ password + "');");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	// 管理员注册
		public void MRegister(String name,int number,String sex,int age,String telephone, String password) {
			this.Mnumber=number;
			this.Mname = name;
			this.Msex=sex;
			this.Mage=age;
			this.Mtelephone=telephone;
			this.Mpassword = password;
			try {
				stmt = conn.createStatement();
				stmt.executeUpdate("USE book;");
				stmt.executeUpdate("INSERT INTO manager(name,id,sex,age,telephone,password)  "
						+ "VALUES ('" +name+"','"+ number + "','"+ sex + "','"+ age + "','"+ telephone + "','"+ password + "');");                                                                        
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		
//读者用户验证
	public boolean testRname(String name) {
		this.Rname = name;
		boolean b = false;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM reader");
			while (rs.next() && b == false) {
				if (name.equals(rs.getString(2)))
					b = true;
				else
					b = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}

	//管理员用户验证
		public boolean testMname(String name) {
			this.Mname = name;
			boolean b = false;
			try {
				stmt = conn.createStatement();
				rs = stmt.executeQuery("SELECT * FROM manager");
				while (rs.next() && b == false) {
					if (name.equals(rs.getString(2)))
						b = true;
					else
						b = false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return b;
		}
//读者密码验证	
	public boolean testRLogin(int number, String password) {
		this.Rnumber = number;
		this.Rpassword = password;
		boolean b = false;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from reader");
			while (rs.next() && b == false) {
				if (number==rs.getInt(2)
						&& password.equals(rs.getString(7))) {
					b = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.close();
		return b;
	}

	//管理员密码验证	
		public boolean testMLogin(int number, String password) {
			this.Mnumber = number;
			this.Mpassword = password;
			boolean b = false;
			try {
				stmt = conn.createStatement();
				rs = stmt.executeQuery("select * from manager");
				while (rs.next() && b == false)
				{
					if (number==rs.getInt(2)
							&& password.equals(rs.getString(6))) 
					{
						b = true;
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			this.close();
			return b;
		}
		
		//图书借阅
	public void Update(String sql)
	{
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate("USE book;");
			stmt.executeUpdate(sql);                                                                        
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//返回数目
	public int Return(String sql)
	{
		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate("USE book;");
			rs=stmt.executeQuery(sql);			
			int rss=-1;
			if(rs.next())
				rss=rs.getInt(1);
			return rss;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return -1;
	}
	
	public int num(String sql)
	{
		try 
		{
			stmt = conn.createStatement();
			stmt.executeUpdate("USE book;");
			rs=stmt.executeQuery(sql);		
			
			int rss=-1;
			if(rs.next())
				rss=rs.getInt(1);
			return rss;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return -1;
	}
	
	public String[][] Select(String sql)
	{
		String a[][] = null;
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate("USE book;");

			int row=0,col=0;
			
    		stmt = conn.prepareStatement(sql); 
    	    rs=stmt.executeQuery(sql);
    	    while(rs.next())
    	    	row++;
    	    col=rs.getMetaData().getColumnCount();
    	    a=new String[row][col];
    	    rs=stmt.executeQuery(sql);
    	   	for(int i=0;i<row&&rs.next();i++)
    	   		for(int j=0;j<col;j++)
    	   			a[i][j]=rs.getString(j+1);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
		
	}
	
	// 关闭数据库

	public void close() {
		try {
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
	
