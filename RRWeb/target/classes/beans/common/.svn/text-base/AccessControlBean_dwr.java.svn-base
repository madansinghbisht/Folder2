package beans.common;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;


import org.apache.commons.collections.OrderedMap;
import org.apache.commons.collections.map.LinkedMap;
 
import eth.factory.ConnectionFactory;
import eth.logger.ETHLogger;
import java.util.StringTokenizer;
import java.util.Vector;

public class AccessControlBean_dwr {
	
	 
	
	public int savePerspective(String loginId, String Perspective, String dbConnVar) {	 
		CallableStatement cstmt = null;
		ResultSet rs = null;
		Connection con = null;
		ConnectionFactory cf = null;
		 int result = 0;
		try {
			cf = new ConnectionFactory(dbConnVar);
			con = cf.getConnection();
			cstmt = con.prepareCall("{?=call gb_fun_save_perspective(?,?)}");
			cstmt.registerOutParameter(1, Types.INTEGER);
			cstmt.setString(2, loginId);
			cstmt.setString(3, Perspective); 
			
			cstmt.executeUpdate();
			result = cstmt.getInt(1);
		} catch (Exception e) {
			ETHLogger.log(dbConnVar,e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (cstmt != null) {
					cstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (Exception e) {
				ETHLogger.log(dbConnVar,e);
			}
		} // End of finally
		return  result;
	}  	
	public String gtPd4Lgn(String strUserLogin,String dbConnVar )
	{
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		String query=null;
		String returnValue=null;
		ConnectionFactory cf =null;
		try
		{
			cf= new ConnectionFactory(dbConnVar);
			con=cf.getConnection();
			if(con==null)
			{
				return returnValue;
			}
			stmt=con.createStatement();
			query=" SELECT "+
						   " password "+
				  " FROM   "+
						   " users"+
				  " WHERE  "+
						   " loginid = '"+strUserLogin+"'" +
						   " and status='V'";
			rs=stmt.executeQuery(query);
			if(rs.next())
			{
				returnValue=rs.getString(1);	  		   		   		
			}
		}//end of try block
		catch(Exception e)
		{
			ETHLogger.log(dbConnVar,e);
		}
		finally
		{
			try
			{
				if(rs!=null)
				{
					rs.close();
				}
				if(stmt!=null)
				{
					stmt.close();
				}
				if(con!=null)
				{
					cf.freeConnection(con);
				}
			}
			catch(Exception e)
			{
				ETHLogger.log(dbConnVar,e);
			}
		}//End of finally block
		return returnValue;
	}//End of getPwdForLoginid method
	
}//end of AccessControlBean
