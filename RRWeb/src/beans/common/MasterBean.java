package beans.common;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;


import org.apache.commons.collections.OrderedMap;
import org.apache.commons.collections.map.LinkedMap;

import vms.pojo.PlateType;
import eth.factory.ConnectionFactory;
import eth.logger.ETHLogger;

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Vector;
public class MasterBean {

	
	
	public ArrayList<PlateType> getPlateTypes(String dbConnVar)
	{
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		String query=null;
		String returnValue=null;
		ConnectionFactory cf =null;
		ArrayList<PlateType> plateTypesAL=null;
		try
		{
			cf= new ConnectionFactory(dbConnVar);
			con=cf.getConnection();
			if(con==null)
			{
				return null; 
			}
			stmt=con.createStatement();
			query=" SELECT * from vms_plate_type_master where status='V'";
			rs=stmt.executeQuery(query);
			ETHLogger.log(dbConnVar, "Before->"+query);
			plateTypesAL=new ArrayList<PlateType>();
			if(rs.next())
			{
				ETHLogger.log(dbConnVar, "After->"+query);
				PlateType plateType=new PlateType();
				plateType.setPlate_type_id(Integer.parseInt(rs.getString("plate_type_id")));
				plateType.setPlate_type_code(rs.getString("plate_type_id"));
				plateType.setStatus(rs.getString("plate_type_id"));
            	plateTypesAL.add(plateType);
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
		return plateTypesAL;
	}//End of getPwdForLoginid method
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
