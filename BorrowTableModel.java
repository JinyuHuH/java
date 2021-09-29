import java.sql.*; 
import java.util.*; 
/* 
 * 图书表模型 
 * */ 
import javax.swing.table.*; 
@SuppressWarnings("serial") 
public class BorrowTableModel extends AbstractTableModel
{
	  static String rnum=ReaderEnter.name;
	//表的元素 
	  private Vector<Vector<String>> rowData; 
	  private Vector<String> colName; 
	  // 数据库 
	  private PreparedStatement stmt; 
	  private ResultSet result; 
	  public BorrowTableModel(String sql) throws SQLException
	  { 
	    this.initData(sql); 
	  } 
	  public BorrowTableModel() throws SQLException
	  { 
	    this.initData("SELECT * FROM borrow where Rnum = '"+rnum+"'"); 
	  } 
	  public void initData(String sql) throws SQLException{ 
		    setRowData(new Vector<Vector<String>>()); 
		    setColName(new Vector<String>()); 
		    getColName().add("学号"); 
		    getColName().add("书号"); 
		    getColName().add("借书时间"); 
		    getColName().add("应还书时间"); 
		    /* 
		     * 数据库的导入 
		     * */
		    try 
		    { 
		      Class.forName("com.mysql.jdbc.Driver"); 
		      String url="jdbc:mysql://localhost:3306/book?useUnicode=true&characterEncoding=utf-8&useSSL=true";
				String user="root";
				String password="root";
				Connection con=DriverManager.getConnection(url,user,password); 
				stmt = con.prepareStatement(sql); 
			    result=stmt.executeQuery(); 
			    importSQL(); 
		    } catch (ClassNotFoundException e) 
		    { 
		      // TODO Auto-generated catch block 
		      e.printStackTrace(); 
		    } 
		  } 
	  void importSQL() throws SQLException{ 
		    // TODO Auto-generated method stub 
		    @SuppressWarnings("unused") 
		    boolean signNull=true; 
		    while(result.next())
		    { 
		      Vector<String> item=new Vector<String>(); 
		      //Vector<Date> date=new Vector<Date> ();
		      for(int i=1;i<5;i++)
		      { 
		        item.add(result.getString(i)); 
		      } 		      		    	
		      getRowData().add(item); 
		      signNull=false; 
		    } 
		    result.close(); 
		  } 
	  
	@Override
	public int getColumnCount() 
	{
		//得到列数 
		// TODO Auto-generated method stub
		return this.colName.size(); 
	}

	@Override
	public int getRowCount() 
	{
		//得到行数 
		// TODO Auto-generated method stub
		return this.rowData.size(); 
	}

	@Override
	public Object getValueAt(int row, int col) 
	{
		//得到某行某列的数据 
		// TODO Auto-generated method stub
		return (this.rowData.get(row)).get(col); 
	}

	@Override
	  public String getColumnName(int column)
	{ 
	    // TODO Auto-generated method stub 
	    return this.colName.get(column); 
	  } 
	
	  public Vector<Vector<String>> getRowData() 
	  { 
		    return rowData; 
		  } 
		  public void setRowData(Vector<Vector<String>> rowData) 
		  { 
		    this.rowData = rowData; 
		  } 
		  public Vector<String> getColName() 
		  { 
		    return colName; 
		  } 
		  public void setColName(Vector<String> colName)
		  { 
		    this.colName = colName; 
		  } 	
		  public void returnBook(String sql)
		  { 
			    try { 
			      stmt.executeUpdate(sql); 
			    } catch (SQLException e1) { 
			      // TODO Auto-generated catch block 
			      e1.printStackTrace(); 
			    } 
			  } 
}
