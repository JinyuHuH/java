

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.JTableHeader;
class Returns
{
    Vector redata;
	String resql;
	String resqls;
}
public class Search
{
	
	String stucolumn[]={"书名","作者","出版社"};
	String stucolumns[]={"book_name","book_writer","publish_house"};
	
	String sql;
	String sqls;
	static String[] re;
	static String from;
	Vector colvalue=new Vector();
	public Search() 
	{}
	
	public Returns ns(String enter)
	{
		Returns rs=new Returns();
		 
		String encodingFlag = "-g";
		Segmenter s1=new Segmenter();
		Segmenter.loadWordLibs(encodingFlag);
		String abc=enter;
		String result = Segmenter.segmentString(abc," ");
		//System.out.println(abc);
		//System.out.println(result);	
		int len=result.length();
		
		Vector select=new Vector();
		//Vector from=new Vector();
		Vector where=new Vector();
		re = result.split(" ");//用split()函数直接分割	
		
			int flag=0;
			for(int j=0;j<re.length;j++)
			{
				for(int k=0;k<stucolumn.length;k++)
				{
					 if(re[j].equals(stucolumn[k]))
					{
						String column=" and "+stucolumns[k]+"='"+re[j+2]+"'";
						where.add(column);
					}	 	
				}
			}
		
		
			sql="select * ";
			sqls="select count(*) ";
			sql+=" from book where 1=1";
			sqls+=" from book where 1=1";
			for(int m=0;m<where.size();m++)
			{
				String te=(String) where.elementAt(m);
				sql+=""+te+"";
				sqls+=""+te+"";
			}
			rs.redata=colvalue;
			rs.resql=sql;
			rs.resqls=sqls;
			return rs;
	}		
}