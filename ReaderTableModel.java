
import java.sql.*; 
import java.util.*; 
/* 
 * ͼ���ģ�� 
 * */ 
import javax.swing.table.*; 
@SuppressWarnings("serial") 
public class ReaderTableModel extends AbstractTableModel
{ 
  //���Ԫ�� 
  private Vector<Vector<String>> rowData; 
  private Vector<String> colName; 
  // ���ݿ� 
  private PreparedStatement stmt; 
  private ResultSet result; 
  public ReaderTableModel(String sql) throws SQLException
  { 
    this.initData(sql); 
  } 
  public ReaderTableModel() throws SQLException
  { 
    this.initData("SELECT * FROM reader"); 
  } 
  public void initData(String sql) throws SQLException
  { 
    setRowData(new Vector<Vector<String>>()); 
    setColName(new Vector<String>()); 
    getColName().add("����"); 
    getColName().add("ID��"); 
    getColName().add("�Ա�"); 
    getColName().add("����"); 
    getColName().add("�绰��"); 
    getColName().add("����"); 
    /* 
     * ���ݿ�ĵ��� 
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
    } 
    catch (ClassNotFoundException e) 
    { 
      // TODO Auto-generated catch block 
      e.printStackTrace(); 
    } 
  } 
  void importSQL() throws SQLException
  { 
    // TODO Auto-generated method stub 
    @SuppressWarnings("unused") 
    boolean signNull=true; 
    while(result.next())
    { 
      Vector<String> item=new Vector<String>(); 
      for(int i=1;i<7;i++)
      { 
        item.add(result.getString(i)); 
      } 
      getRowData().add(item); 
      signNull=false; 
    } 
    result.close(); 
  } 
  @Override
  public int getColumnCount() {//�õ����� 
    // TODO Auto-generated method stub 
    return this.colName.size(); 
  } 
  
  @Override
  public int getRowCount() {//�õ����� 
    // TODO Auto-generated method stub 
    return this.rowData.size(); 
  } 
  
  @Override
  public Object getValueAt(int row, int col) {//�õ�ĳ��ĳ�е����� 
    // TODO Auto-generated method stub 
    return (this.rowData.get(row)).get(col); 
  } 
  
  @Override
  public String getColumnName(int column) { 
    // TODO Auto-generated method stub 
    return this.colName.get(column); 
  } 
    
  public Vector<Vector<String>> getRowData() { 
    return rowData; 
  } 
  public void setRowData(Vector<Vector<String>> rowData) { 
    this.rowData = rowData; 
  } 
  public Vector<String> getColName() { 
    return colName; 
  } 
  public void setColName(Vector<String> colName) { 
    this.colName = colName; 
  } 
  public void addReader(String sql){ 
    try { 
      stmt.executeUpdate(sql); 
    } catch (SQLException e) { 
      // TODO Auto-generated catch block 
      e.printStackTrace(); 
    } 

  } 
  public void deleteReader(String sql){ 
    try { 
      stmt.executeUpdate(sql); 
    } catch (SQLException e1) { 
      // TODO Auto-generated catch block 
      e1.printStackTrace(); 
    } 
  } 
} 
