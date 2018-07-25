package beans.common;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;


import org.apache.commons.collections.OrderedMap;
import org.apache.commons.collections.map.LinkedMap;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import vms.pojo.FuelType;
import vms.pojo.MakeCompany;
import vms.pojo.PlateType;
import vms.pojo.VehicleData;
import vms.pojo.VehicleType;
import eth.factory.ConnectionFactory;
import eth.i18n.ETHi18n;
import eth.logger.ETHLogger;

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Vector;
public class CommonBean {

	private ConnectionFactory cf = null; 

	ETHi18n i18n = null; 
	String dbConnVar = "";

	
	public CommonBean(String dbConnVar) {
		cf = new ConnectionFactory(dbConnVar);
		this.i18n = new ETHi18n(dbConnVar, true);
		this.dbConnVar = dbConnVar;
	}
	
	
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
			query=" SELECT plate_type_id,plate_type_code,status from vms_plate_type_master where status='V'";
			rs=stmt.executeQuery(query);
			//ETHLogger.log(dbConnVar, "Before->"+query);
			plateTypesAL=new ArrayList<PlateType>();
			while(rs.next())
			{
				//ETHLogger.log(dbConnVar, "After->"+query);
				PlateType plateType=new PlateType();
				plateType.setPlate_type_id(Integer.parseInt(rs.getString("plate_type_id")));
				plateType.setPlate_type_code(rs.getString("plate_type_code"));
				plateType.setStatus(rs.getString("status"));
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
				ETHLogger.log(dbConnVar + "_VMS",e);
			}
		}//End of finally block
		return plateTypesAL;
	}
	
	
	public ArrayList<VehicleType> getVehicleTypes(String dbConnVar)
	{
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		String query=null;
		String returnValue=null;
		ConnectionFactory cf =null;
		ArrayList<VehicleType> vehicleTypesAL=null;
		try
		{
			cf= new ConnectionFactory(dbConnVar);
			con=cf.getConnection();
			if(con==null)
			{
				return null; 
			} 
			stmt=con.createStatement();
			query=" SELECT vehicle_type_id,vehicle_type_name,vehicle_type_status from vms_vehicle_type_master where vehicle_type_status='V'";
			rs=stmt.executeQuery(query);
			//ETHLogger.log(dbConnVar, "Before->"+query);
			vehicleTypesAL=new ArrayList<VehicleType>();
			while(rs.next())
			{
			//	ETHLogger.log(dbConnVar, "After->"+query);
				VehicleType vehicleType=new VehicleType();
				vehicleType.setVehicle_type_id(Integer.parseInt(rs.getString("vehicle_type_id")));
				vehicleType.setVehicle_type_name(rs.getString("vehicle_type_name"));
				vehicleType.setVehicle_type_status(rs.getString("vehicle_type_status"));
				vehicleTypesAL.add(vehicleType);
			}
		}//end of try block
		catch(Exception e)
		{
			ETHLogger.log(dbConnVar + "_VMS",e);
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
				ETHLogger.log(dbConnVar + "_VMS",e);
			}
		}//End of finally block
		return vehicleTypesAL;
	}
	
	
	public ArrayList<FuelType> getFuelTypes(String dbConnVar)
	{
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		String query=null;
		String returnValue=null;
		ConnectionFactory cf =null;
		ArrayList<FuelType> fuelTypesAL=null;
		try
		{
			cf= new ConnectionFactory(dbConnVar);
			con=cf.getConnection();
			if(con==null)
			{
				return null; 
			} 
			stmt=con.createStatement();
			query=" SELECT fuel_type_id,fuel_type_desc from vms_fuel_type_master";
			rs=stmt.executeQuery(query);
			//ETHLogger.log(dbConnVar, "Before->"+query);
			fuelTypesAL=new ArrayList<FuelType>();
			while(rs.next())
			{
				//ETHLogger.log(dbConnVar, "After->"+query);
				FuelType fueltype=new FuelType();
				fueltype.setFuel_type_id(Integer.parseInt(rs.getString("fuel_type_id")));
				fueltype.setFuel_type_desc(rs.getString("fuel_type_desc"));
				
				fuelTypesAL.add(fueltype);
			}
		}//end of try block
		catch(Exception e)
		{
			ETHLogger.log(dbConnVar + "_VMS",e);
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
				ETHLogger.log(dbConnVar + "_VMS",e);
			}
		}//End of finally block
		return fuelTypesAL;
	}
	
	
	
	public ArrayList<MakeCompany> getMakeCompany(String dbConnVar)
	{
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		String query=null;
		String returnValue=null;
		ConnectionFactory cf =null;
		ArrayList<MakeCompany> makeCompanyAL=null;
		try
		{
			cf= new ConnectionFactory(dbConnVar);
			con=cf.getConnection();
			if(con==null)
			{
				return null; 
			} 
			stmt=con.createStatement();
			query=" SELECT maker_id,maker_name,status from vms_vehicle_maker_master";
			rs=stmt.executeQuery(query);
			//ETHLogger.log(dbConnVar, "Before->"+query);
			makeCompanyAL=new ArrayList<MakeCompany>();
			while(rs.next())
			{
				//ETHLogger.log(dbConnVar, "After->"+query);
				MakeCompany makeCompany=new MakeCompany();
				makeCompany.setMaker_id(Integer.parseInt(rs.getString("maker_id")));
				makeCompany.setMaker_name(rs.getString("maker_name"));
				makeCompany.setStatus(rs.getString("status"));
				
				makeCompanyAL.add(makeCompany);
			}
		}//end of try block
		catch(Exception e)
		{
			ETHLogger.log(dbConnVar + "_VMS",e);
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
				ETHLogger.log(dbConnVar + "_VMS",e);
			}
		}//End of finally block
		return makeCompanyAL;
	}
	
	
	
	
	public ArrayList<VehicleData> getVehicleData(String dbConnVar)
	{
		Connection con=null;
		CallableStatement cs = null;
		ResultSet rs = null;
		int result = 0;

		
		String returnValue=null;
		ConnectionFactory cf =null;
		ArrayList<VehicleData> vehicleDataAL=null;
		try
		{
			cf= new ConnectionFactory(dbConnVar);
			con=cf.getConnection();
			if(con==null)
			{
				return null; 
			} 
			//ETHLogger.log(dbConnVar + "_VMS","select fun_retrive_vms_vehicle_details" );
			cs = con.prepareCall("{? = call fun_retrive_vms_vehicle_details(?)}");
			
			cs.registerOutParameter(1, Types.OTHER); 
			cs.setObject(2, rs);
			con.setAutoCommit(false);
		    cs.execute();
			
			
			cs.execute();
			rs = (ResultSet) cs.getObject(1);
 
			vehicleDataAL=new ArrayList<VehicleData>();
			while(rs.next())
			{
				
				VehicleData vehicledata=new VehicleData();
				vehicledata.setVehicleId(rs.getInt("vehicle_id"));
				vehicledata.setVehicleRegNo(rs.getString("vehicle_reg_no"));
				vehicledata.setMakeCompany(rs.getString("vehicle_maker_company"));
				vehicledata.setModel(rs.getString("vehicle_model"));
				vehicledata.setPlateType(rs.getString("plate_type"));
				vehicledata.setChassisNo(rs.getString("chassis_no"));
				vehicledata.setEngineNo(rs.getString("engine_no"));
				vehicledata.setTrafficFileNo(rs.getString("traffic_file_no"));
				vehicledata.setIvmsNo(rs.getString("ivms_no"));
				vehicledata.setEngineCapacity(rs.getString("engine_capacity"));
				vehicledata.setFuelCardNo(rs.getString("fuel_card_no"));
				vehicledata.setFuelType(rs.getString("fuel_Type"));
				vehicledata.setVehicleType(rs.getString("vehicle_type_name"));
				vehicledata.setExpAccount(rs.getString("acc_ledger_desc"));
				
				
				//ETHLogger.log(dbConnVar, "After->"+vehicledata.getVehicleRegNo());
				
				vehicleDataAL.add(vehicledata);
			}
		}//end of try block
		catch(Exception e)
		{
			ETHLogger.log(dbConnVar + "_VMS",e);
		}
		finally
		{
			try
			{
				if(rs!=null)
				{
					rs.close();
				}
				if(cs!=null)
				{
					cs.close();
				}
				if(con!=null)
				{
					cf.freeConnection(con);
				}
			}
			catch(Exception e)
			{
				ETHLogger.log(dbConnVar + "_VMS",e);
			}
		}//End of finally block
		//ETHLogger.log(dbConnVar, "Return ->"+vehicleDataAL.toString());
		return vehicleDataAL;
	}
	
	 
	
	
	
	public int insertVehicleDetails(String data,String insertUpdateFlag,String token) {
		Connection con = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		int result = 0;

		try {
			con = cf.getConnection();
			//ETHLogger.log(dbConnVar,"select fun_insert_update_vms_vehicle_details("+data);  
			cs = con.prepareCall("{? = call fun_insert_update_vms_vehicle_details(?,?,?)}");
			cs.registerOutParameter(1, Types.INTEGER); 
			cs.setString(2, data);
			cs.setString(3, insertUpdateFlag);  
			cs.setString(4, token);  
			cs.execute();
			result = cs.getInt(1);

		} catch (Exception e) {
			ETHLogger.log(dbConnVar + "_VMS",e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (cs != null) {
					cs.close();
				}
				if (con != null) {
					cf.freeConnection(con);
				}
			} catch (Exception e) {
				ETHLogger.log(dbConnVar + "_VMS", e);
			} 
		}// end of finally
		return result;
	}// end of insertVehicleDetails() method
	
	public int insertDriverAllocationDetails(String data,String token) {
		Connection con = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		int result = 0; 

		try {
			con = cf.getConnection();
			//ETHLogger.log(dbConnVar,"select fun_insert_update_vms_driver_allocation_details("+data+","+token);  
			cs = con.prepareCall("{? = call fun_insert_update_vms_driver_allocation_details(?,?)}"); 
			cs.registerOutParameter(1, Types.INTEGER); 
			cs.setString(2, data);
			cs.setString(3, token);  
			cs.execute();
			result = cs.getInt(1);

		} catch (Exception e) {
			ETHLogger.log(dbConnVar + "_VMS",e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (cs != null) {
					cs.close();
				}
				if (con != null) {
					cf.freeConnection(con);
				}
			} catch (Exception e) {
				ETHLogger.log(dbConnVar + "_VMS", e);
			} 
		}// end of finally
		return result;
	}// end of insertDriverAllocationDetails() method
	
	public int insertFuelReadingDetails(String data,String insertUpdateFlag,String token ) { 
		Connection con = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		int result = 0; 

		try { 
			con = cf.getConnection();
			//ETHLogger.log(dbConnVar,"select fun_insert_update_vms_fuel_reading_details("+data+","+insertUpdateFlag);  
			cs = con.prepareCall("{? = call fun_insert_update_vms_fuel_reading_details(?,?,?)}"); 
			cs.registerOutParameter(1, Types.INTEGER); 
			cs.setString(2, data);
			cs.setString(3, insertUpdateFlag);
			cs.setString(4, token); 
			cs.execute();
			result = cs.getInt(1);

		} catch (Exception e) {
			ETHLogger.log(dbConnVar + "_VMS",e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (cs != null) {
					cs.close();
				}
				if (con != null) {
					cf.freeConnection(con);
				}
			} catch (Exception e) {
				ETHLogger.log(dbConnVar + "_VMS", e); 
			} 
		}// end of finally
		return result;
	}// end of insertFuelReadingDetails() method
	
	public OrderedMap  getVehicleDetails(String vehicleId){
        
	    Connection con = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;
		OrderedMap appOM = new LinkedMap();
		try{
			
			con = cf.getConnection();
			
			cstmt =con.prepareCall("{? = call fun_retrive_vms_edit_vehicle_details(?,?)}"); 
			cstmt.registerOutParameter(1,Types.OTHER);
			cstmt.setString(2, vehicleId );
			cstmt.setObject(3, rs );
			con.setAutoCommit(false);
		    cstmt.execute();
			  rs  = (ResultSet) cstmt.getObject(1);
			
			if(rs.next()){
				String vehicle_id  ="",vehicle_reg_no="",vehicle_type_id="",plate_type_id="",chassis_no="",engine_no="",
				traffic_file_no="",engine_capacity="",maker_id="",vehicle_model="",ivms_no="",vehicle_owner="",
				fuel_type_id="",fuel_capacity="",fuel_card_no="",seating_capacity="",vehicle_avg_km="",vehicle_expense_account="",fuel_limit=""
					,registration_expiry_date="",contract_expiry_date="",hired_date="",office_hired_date="",hiredRate="",contract_ref_no="";
									
				do{
					
					  
					vehicle_reg_no=rs.getString(1);
					vehicle_type_id=rs.getString(2);
					plate_type_id=rs.getString(3);
					chassis_no=rs.getString(4);
					engine_no=rs.getString(5);
					traffic_file_no=rs.getString(6);
					engine_capacity=rs.getString(7);
					maker_id=rs.getString(8);
					vehicle_model=rs.getString(9);
					ivms_no=rs.getString(10);
					vehicle_owner=rs.getString(11);
					fuel_type_id=rs.getString(12); 
					fuel_capacity=rs.getString(13);
					fuel_card_no=rs.getString(14);
					seating_capacity=rs.getString(15);
					vehicle_avg_km=rs.getString(16);
					vehicle_expense_account=rs.getString(17);
					
					fuel_limit=rs.getString(18);
					registration_expiry_date=rs.getString(19);
					contract_expiry_date=rs.getString(20);
					hired_date=rs.getString(21);
					office_hired_date=rs.getString(22);
					hiredRate=rs.getString(23);
					contract_ref_no=rs.getString(24);
					 
					 
					appOM.put(vehicle_reg_no,vehicle_reg_no+"$"+vehicle_type_id+"$"+plate_type_id+"$"+chassis_no+"$"+engine_no+"$"+
							traffic_file_no+"$"+engine_capacity+"$"+maker_id+"$"+vehicle_model+"$"+ivms_no+"$"+vehicle_owner
							+"$"+fuel_type_id+"$"+fuel_capacity+"$"+fuel_card_no+"$"+seating_capacity+"$"+vehicle_avg_km
							+"$"+vehicle_expense_account+"$"+fuel_limit+"$"+registration_expiry_date+"$"+contract_expiry_date
							+"$"+hired_date+"$"+office_hired_date+"$"+hiredRate+"$"+contract_ref_no 
							);
				}while(rs.next());
			}
			
		}catch(Exception e){

		ETHLogger.log(dbConnVar + "_VMS", e);
		 }
		finally {
			try {
				  if (cstmt != null){
					  cstmt.close();
				  }
				  if(con != null){
				  con.close();
				  }
				  
				  if(rs!=null){
					rs.close();  
					 }
			  } catch (Exception e ) {
				  ETHLogger.log(dbConnVar + "_VMS",e);
			  } 

		}
     
		return appOM;
	} 
	
	
	public OrderedMap getDriverDetails(String dbConnVar,String vehicleId)
	{
		Connection con = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;
			
		OrderedMap appOM = new LinkedMap();
	
		try
		{
			//ETHLogger.log(dbConnVar, "Before->"+ "Inside Try");
			con = cf.getConnection();
			cstmt =con.prepareCall("{? = call fun_retrive_vms_driver_allocation_details(?,?)}"); 
			cstmt.registerOutParameter(1,Types.OTHER);
			cstmt.setString(2, vehicleId );
			cstmt.setObject(3, rs );
			con.setAutoCommit(false);
		    cstmt.execute();
			rs  = (ResultSet) cstmt.getObject(1);
						
			
			if(rs.next()){
				  String vehicleRegno="",driverName="",mobileNo="";
									
				do{
					vehicleRegno=rs.getString(1);
					driverName=rs.getString(2);
					mobileNo=rs.getString(3);
				 
				    appOM.put(vehicleId,vehicleRegno+"$"+driverName+"$"+mobileNo);
				}while(rs.next());
			}
			
		}//end of try block
		catch(Exception e)
		{
			ETHLogger.log(dbConnVar + "_VMS",e);
		}
		finally
		{
			try
			{
				if(rs!=null)
				{
					rs.close();
				}
				if(cstmt!=null)
				{
					cstmt.close();
				}
				if(con!=null)
				{
					cf.freeConnection(con);
				}
			}
			catch(Exception e)
			{
				ETHLogger.log(dbConnVar + "_VMS",e);
			}
		}//End of finally block
		return appOM;
	}
	
	
	public JsonArray getExpenseAccounts(String dbConnVar,int company_id,int statement_code2)
	{
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		JsonArray jsonData=new JsonArray();
		
		String returnValue=null;
		ConnectionFactory cf =null;
		OrderedMap appOM = new LinkedMap();
		ArrayList<VehicleData> vehicleDataAL=null;
		try
		{
			cf= new ConnectionFactory(dbConnVar);
			con=cf.getConnection();
			if(con==null)
			{
				return null; 
			} 
			stmt=con.createStatement();
			StringBuilder query=new StringBuilder();
			query.append(" select acc_ledger_id,acc_ledger_desc from acc_ledger_master c,acc_group_master gm");
			query.append(" where c.group_id1=group_id and c.company_id="+company_id);
			query.append(" and statement_code2="+statement_code2+" and c.status='V'");
			query.append(" order by acc_ledger_desc");
		    //ETHLogger.log(dbConnVar, "Before->"+query.toString());
			rs=stmt.executeQuery(query.toString());
		while(rs.next() ) {
					JsonObject jsonObject = new JsonObject();	
					jsonObject.add("acc_ledger_id", new JsonPrimitive(rs.getString("acc_ledger_id")));
					jsonObject.add("acc_ledger_desc", new JsonPrimitive(rs.getString("acc_ledger_desc")));
					jsonData.add(jsonObject);
				}
				
		
		}//end of try block
		catch(Exception e)
		{
			ETHLogger.log(dbConnVar + "_VMS",e);
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
				ETHLogger.log(dbConnVar + "_VMS",e);
			}
		}//End of finally block
		return jsonData;
	}
	
	
	public ArrayList<VehicleData> getVehicleByRegNo(String dbConnVar,String vehicleRegNo)
	{
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		
		ConnectionFactory cf =null;
		ArrayList<VehicleData> vehicleDataAL=null;
		try
		{
			cf= new ConnectionFactory(dbConnVar);
			con=cf.getConnection();
			if(con==null)
			{
				return null; 
			} 
			stmt=con.createStatement();
			StringBuilder query=new StringBuilder();
			
			query.append(" select vm.*,ptm.plate_type_code,vtm.vehicle_type_name,ft.fuel_type_desc,vmm.maker_name from vms_vehicle_master vm ");
			query.append(" left join vms_plate_type_master ptm on vm.plate_type=ptm.plate_type_id ");
			query.append(" left join vms_vehicle_type_master vtm on vm.vehicle_type_id=vtm.vehicle_type_id ");
			query.append(" left join vms_fuel_type_master ft on  vm.fuel_type_id=ft.fuel_type_id ");
			query.append(" left join vms_vehicle_maker_master vmm on vm.maker_id=vmm.maker_id ");
			query.append(" where vehicle_reg_no="+vehicleRegNo);

 
			
 
			rs=stmt.executeQuery(query.toString());
			//ETHLogger.log(dbConnVar, "Before->"+query.toString());
			
			vehicleDataAL=new ArrayList<VehicleData>();
			if(rs.next())
			{
				
				VehicleData vehicledata=new VehicleData();
				vehicledata.setVehicleId(rs.getInt("vehicle_id"));
				vehicledata.setVehicleRegNo(rs.getString("vehicle_reg_no"));
				vehicledata.setMakeCompany(rs.getString("maker_name"));
				vehicledata.setModel(rs.getString("vehicle_model"));
				vehicledata.setPlateType(rs.getString("plate_type_code"));
				vehicledata.setChassisNo(rs.getString("chassis_no"));
				vehicledata.setEngineNo(rs.getString("engine_no"));
				vehicledata.setTrafficFileNo(rs.getString("traffic_file_no"));
				vehicledata.setIvmsNo(rs.getString("ivms_no"));
				vehicledata.setEngineCapacity(rs.getString("engine_capacity"));
				vehicledata.setFuelCardNo(rs.getString("fuel_card_no"));
				vehicledata.setFuelType(rs.getString("fuel_Type_desc"));
				vehicledata.setVehicleType(rs.getString("vehicle_type_name"));
				vehicledata.setExpAccount(rs.getString("vehicle_expense_account"));
				
				
				//ETHLogger.log(dbConnVar, "After->"+query+vehicledata.getVehicleRegNo());
				
				vehicleDataAL.add(vehicledata);
			}
		}//end of try block
		catch(Exception e)
		{
			ETHLogger.log(dbConnVar + "_VMS",e);
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
				ETHLogger.log(dbConnVar + "_VMS",e);
			}
		}//End of finally block
		return vehicleDataAL;
	}
	 public int insertServiceEntryDetails(String data,String insertUpdateFlag,String token) { 
			Connection con = null;
			CallableStatement cs = null;
			ResultSet rs = null;
			int result = 0; 

			try { 
				con = cf.getConnection();
				//ETHLogger.log(dbConnVar,"select fun_insert_vms_service_entry_details('"+data+"','"+insertUpdateFlag+"','"+token+"'");  
				cs = con.prepareCall("{? = call fun_insert_vms_service_entry_details(?,?,?)}"); 
				cs.registerOutParameter(1, Types.INTEGER); 
				cs.setString(2, data);
				cs.setString(3, insertUpdateFlag);
				cs.setString(4, token);  
				cs.execute();
				result = cs.getInt(1);

			} catch (Exception e) {
				ETHLogger.log(dbConnVar + "_VMS", e);
			} finally {
				try {
					if (rs != null) {
						rs.close();
					}
					if (cs != null) {
						cs.close();
					}
					if (con != null) {
						cf.freeConnection(con);
					}
				} catch (Exception e) {
					ETHLogger.log(dbConnVar + "_VMS", e);
				} 
			}// end of finally  
			 
			return result;
		}// end of insertServiceEntryDetails() method
	 
	 public int insertRepairEntryDetails(String data,String insertUpdateFlag,String token) { 
			Connection con = null;
			CallableStatement cs = null;
			ResultSet rs = null;
			int result = 0; 

			try {  
				con = cf.getConnection();
				//ETHLogger.log(dbConnVar,"select fun_insert_vms_repair_entry_details('"+data+"','"+insertUpdateFlag+"','"+token+"'");  
				cs = con.prepareCall("{? = call fun_insert_vms_repair_entry_details(?,?,?)}"); 
				cs.registerOutParameter(1, Types.INTEGER); 
				cs.setString(2, data);
				cs.setString(3, insertUpdateFlag);
				cs.setString(4, token);  
				cs.execute();
				result = cs.getInt(1);

			} catch (Exception e) {
				ETHLogger.log(dbConnVar + "_VMS", e);
			} finally {
				try {
					if (rs != null) {
						rs.close();
					}
					if (cs != null) {
						cs.close();
					}
					if (con != null) {
						cf.freeConnection(con);
					}
				} catch (Exception e) {
					ETHLogger.log(dbConnVar + "_VMS", e);
				} 
			}// end of finally  
			 
			return result;
		}// end of insertServiceEntryDetails() method
	 
	 public int insertAccidentDetails(String data,String token) { 
			Connection con = null;
			CallableStatement cs = null;
			ResultSet rs = null;
			int result = 0; 
 
			try { 
				con = cf.getConnection();
				//ETHLogger.log(dbConnVar,"select fun_insert_vms_accident_details('"+data+"','"+token+"'");  
				cs = con.prepareCall("{? = call fun_insert_vms_accident_details(?,?)}"); 
				cs.registerOutParameter(1, Types.INTEGER); 
				cs.setString(2, data);
				cs.setString(3, token);  
				cs.execute();
				result = cs.getInt(1);

			} catch (Exception e) {
				ETHLogger.log(dbConnVar + "_VMS", e);
			} finally {
				try {
					if (rs != null) {
						rs.close();
					}
					if (cs != null) {
						cs.close();
					}
					if (con != null) {
						cf.freeConnection(con);
					}
				} catch (Exception e) {
					ETHLogger.log(dbConnVar + "_VMS", e);
				} 
			}// end of finally  
			 
			return result;
		}// end of insertAccidentDetails() method
	 
	 public int insertVehiclRegistrationDetails(String data,String token,String insertUpdateFlag) { 
			Connection con = null;
			CallableStatement cs = null;
			ResultSet rs = null;
			int result = 0; 
 
			try { 
				con = cf.getConnection();
				//ETHLogger.log(dbConnVar,"select fun_insert_vms_vehicle_registration_details('"+data+"','"+token+"'");  
				cs = con.prepareCall("{? = call fun_insert_vms_vehicle_registration_details(?,?,?)}"); 
				cs.registerOutParameter(1, Types.INTEGER); 
				cs.setString(2, data);
				cs.setString(3, token);  
				cs.setString(4, insertUpdateFlag);  
				cs.execute();
				result = cs.getInt(1);

			} catch (Exception e) {
				ETHLogger.log(dbConnVar + "_VMS", e);
			} finally {
				try {
					if (rs != null) {
						rs.close();
					}
					if (cs != null) {
						cs.close();
					}
					if (con != null) {
						cf.freeConnection(con);
					}
				} catch (Exception e) {
					ETHLogger.log(dbConnVar + "_VMS", e);
				} 
			}// end of finally  
			 
			return result;
		}// end of insertVehiclRegistrationDetails() method
	 
	 public int insertVehiclInsuranceDetails(String data,String token, String insertUpdateFlag) { 
			Connection con = null;
			CallableStatement cs = null;
			ResultSet rs = null;
			int result = 0; 
 
			try { 
				con = cf.getConnection();
				//ETHLogger.log(dbConnVar,"select fun_insert_vms_vehicle_insurance_details('"+data+"','"+token+"'");  
				cs = con.prepareCall("{? = call fun_insert_vms_vehicle_insurance_details(?,?,?)}"); 
				cs.registerOutParameter(1, Types.INTEGER); 
				cs.setString(2, data);
				cs.setString(3, token);  
				cs.setString(4, insertUpdateFlag); 
				cs.execute();
				result = cs.getInt(1);

			} catch (Exception e) {
				ETHLogger.log(dbConnVar + "_VMS", e);
			} finally {
				try {
					if (rs != null) {
						rs.close();
					}
					if (cs != null) {
						cs.close();
					}
					if (con != null) {
						cf.freeConnection(con);
					}
				} catch (Exception e) {
					ETHLogger.log(dbConnVar + "_VMS", e);
				} 
			}// end of finally  
			 
			return result;
		}// end of insertVehiclInsuranceDetails() method
	
	 
	 public JsonArray getMulkiyaTypes(String dbConnVar)
		{
			Connection con=null;
			Statement stmt=null;
			ResultSet rs=null;
			JsonArray jsonData=new JsonArray();
			
			String returnValue=null;
			ConnectionFactory cf =null;
			OrderedMap appOM = new LinkedMap();
			
			try
			{
				cf= new ConnectionFactory(dbConnVar);
				con=cf.getConnection();
				if(con==null)
				{
					return null; 
				} 
				stmt=con.createStatement();
				StringBuilder query=new StringBuilder();
				query.append(" select vms_mulkiya_type_id,mulkiya_type from vms_mulkiya_type_master");
			    //ETHLogger.log(dbConnVar, "Before->"+query.toString());
				rs=stmt.executeQuery(query.toString());
			while(rs.next() ) {
						JsonObject jsonObject = new JsonObject();	
						jsonObject.add("vms_mulkiya_type_id", new JsonPrimitive(rs.getInt("vms_mulkiya_type_id")));
						jsonObject.add("mulkiya_type", new JsonPrimitive(rs.getString("mulkiya_type")));
						jsonData.add(jsonObject);
					}
					
			
			}//end of try block
			catch(Exception e)
			{
				ETHLogger.log(dbConnVar + "_VMS",e);
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
					ETHLogger.log(dbConnVar + "_VMS",e);
				}
			}//End of finally block
			return jsonData;
		}
		
	 
	 
	 
	public JsonObject  getVehicleDetailsJSON(String dbConnVar,String vehicleId){
	        
		    Connection con = null;
			CallableStatement cstmt = null;
			ResultSet rs = null;
			OrderedMap appOM = new LinkedMap();
		
			JsonObject jsonObject = new JsonObject();
			try{
				//ETHLogger.log(dbConnVar+"_VMS","Inside Bean vehicleId"+vehicleId );	
				
				con = cf.getConnection();
				//ETHLogger.log(dbConnVar+"_VMS","select fun_retrive_vms_edit_vehicle_details('"+vehicleId+"','c');");
				cstmt =con.prepareCall("{? = call fun_retrive_vms_edit_vehicle_details(?,?)}"); 
				cstmt.registerOutParameter(1,Types.OTHER);
				cstmt.setString(2, vehicleId );
				cstmt.setObject(3, rs );
				con.setAutoCommit(false);
			    cstmt.execute();
				  rs  = (ResultSet) cstmt.getObject(1);
					if(rs.next()) {
//{"vehicle_reg_no":"VH69693","vehicle_type_name":"JEEP","plate_type":"PT002     ","chassis_no":"","engine_no":"99","traffic_file_no":"9975","engine_capacity":"0","maker_company":"TOYOTA","vehicle_model":"QQQQ","ivms_no":"","vehicle_owner":"","fuel_type_id":"Diesel","fuel_capacity":"0","fuel_card_no":"997570","seating_capacity":"0","vehicle_avg_km":"0","vehicle_expense_account":"118","fuel_limit":"0","registration_expiry_date":"","contract_expiry_date":"","hired_date":"","office_hired_date":"30-05-2018","hiredRate":"0","contract_ref_no":"0"}					
							jsonObject.add("vehicle_reg_no",new JsonPrimitive(rs.getString(1)));
							jsonObject.add("vehicle_type_name", new JsonPrimitive(rs.getString(2)));
							jsonObject.add("plate_type", new JsonPrimitive(rs.getString(3)));
							jsonObject.add("chassis_no", new JsonPrimitive(rs.getString(4)));
							jsonObject.add("engine_no", new JsonPrimitive(rs.getString(5)));
							jsonObject.add("traffic_file_no", new JsonPrimitive(rs.getString(6)));
							jsonObject.add("engine_capacity", new JsonPrimitive(rs.getString(7)));
							jsonObject.add("maker_company", new JsonPrimitive(rs.getString(8)));
							jsonObject.add("vehicle_model", new JsonPrimitive(rs.getString(9)));
							jsonObject.add("ivms_no", new JsonPrimitive(rs.getString(10)));
							jsonObject.add("vehicle_owner", new JsonPrimitive(rs.getString(11)));
							jsonObject.add("fuel_type_id", new JsonPrimitive(rs.getString(12)));
							jsonObject.add("fuel_capacity", new JsonPrimitive(rs.getString(13)));
							jsonObject.add("fuel_card_no", new JsonPrimitive(rs.getString(14)));
							jsonObject.add("seating_capacity", new JsonPrimitive(rs.getString(15)));
							jsonObject.add("vehicle_avg_km", new JsonPrimitive(rs.getString(16)));
							jsonObject.add("vehicle_expense_account", new JsonPrimitive(rs.getString(17)));
							jsonObject.add("fuel_limit", new JsonPrimitive(rs.getString(18)));
							jsonObject.add("registration_expiry_date", new JsonPrimitive(rs.getString(19)));
							jsonObject.add("contract_expiry_date", new JsonPrimitive(rs.getString(20)));
							
							jsonObject.add("office_hired_date", new JsonPrimitive(rs.getString(21)));
							jsonObject.add("hired_date", new JsonPrimitive(rs.getString(22)));
							jsonObject.add("hiredRate", new JsonPrimitive(rs.getString(23)));
							jsonObject.add("contract_ref_no", new JsonPrimitive(rs.getString(24)));
							//jsonObject.add("vehicle_status", new JsonPrimitive(rs.getString(25)));
							ETHLogger.log(dbConnVar+"_VMS", "yyyy"+jsonObject.toString());
						
					}
						
			}catch(Exception e){

			ETHLogger.log(dbConnVar + "_VMS", e);
			 }
			finally {
				try {
					  if (cstmt != null){
						  cstmt.close();
					  }
					  if(con != null){
					  con.close();
					  }
					  
					  if(rs!=null){
						rs.close();  
						 }
				  } catch (Exception e ) {
					  ETHLogger.log(dbConnVar + "_VMS",e);
				  } 

			}
	     
			return jsonObject;
		} 
	

	
	

	public JsonArray getEmirates(String dbConnVar)
	{
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		JsonArray jsonData=new JsonArray();
		String returnValue=null;
		ConnectionFactory cf =null;
	
		
		try
		{
			cf= new ConnectionFactory(dbConnVar);
			con=cf.getConnection();
			if(con==null)
			{
				return null; 
			} 
			stmt=con.createStatement();
			StringBuilder query=new StringBuilder();
			query.append(" select state_id,state_name from state_master where status='V'");
		    //ETHLogger.log(dbConnVar, "Before->"+query.toString());
			rs=stmt.executeQuery(query.toString());
		while(rs.next() ) {
					JsonObject jsonObject = new JsonObject();	
					jsonObject.add("state_id", new JsonPrimitive(rs.getInt("state_id")));
					jsonObject.add("state_name", new JsonPrimitive(rs.getString("state_name")));
					jsonData.add(jsonObject);
				}
				
		
		}//end of try block
		catch(Exception e)
		{
			ETHLogger.log(dbConnVar + "_VMS",e);
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
				ETHLogger.log(dbConnVar + "_VMS",e);
			}
		}//End of finally block
		return jsonData;
	}
	
	
	public JsonObject getVehicleRegistrationDetails(String dbConnVar,String vehicleId )
	{
		Connection con = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;
		JsonArray jsonData=new JsonArray();
		ConnectionFactory cf =null;
		JsonObject jsonObject = new JsonObject();	
	
		
		//vehicle_registration_id,vehicle_id,traffic_plate_no,mulkiya_type,place_off_issue,mulkiya_renewal_date,
		//t_c_no,owner,cost,mulkiya_issue_date,mulkiya_expiry_date,mulkiya_processing_date 
		 
				
		try
		{
			cf= new ConnectionFactory(dbConnVar);
			con=cf.getConnection();
			//ETHLogger.log(dbConnVar+"_VMS","select fun_retrive_vms_vehicle_registration_details('"+vehicleId+"','c')");
			cstmt =con.prepareCall("{? = call fun_retrive_vms_vehicle_registration_details(?,?)}"); 
			cstmt.registerOutParameter(1,Types.OTHER);
			cstmt.setString(2, vehicleId );
			cstmt.setObject(3, rs );
			con.setAutoCommit(false);
		    cstmt.execute();
		    rs  = (ResultSet) cstmt.getObject(1);
			
		if(rs.next() ) {
				
			
					jsonObject.add("vehicle_registration_id", new JsonPrimitive(rs.getInt("vehicle_registration_id")));
					jsonObject.add("vehicle_id", new JsonPrimitive(rs.getInt("vehicle_id")));
					jsonObject.add("traffic_plate_no", new JsonPrimitive(rs.getString("traffic_plate_no")));
					
					jsonObject.add("mulkiya_type", new JsonPrimitive(rs.getString("mulkiya_type")));
					jsonObject.add("place_off_issue", new JsonPrimitive(rs.getString("place_off_issue")));
					jsonObject.add("mulkiya_renewal_date", new JsonPrimitive(rs.getString("mulkiya_renewal_date")));
					
					
					jsonObject.add("t_c_no", new JsonPrimitive(rs.getString("t_c_no")));
					jsonObject.add("owner", new JsonPrimitive(rs.getString("owner")));
					jsonObject.add("cost", new JsonPrimitive(rs.getFloat("cost")));
					
					jsonObject.add("mulkiya_issue_date", new JsonPrimitive(rs.getString("mulkiya_issue_date")));
					jsonObject.add("mulkiya_expiry_date", new JsonPrimitive(rs.getString("mulkiya_expiry_date")));
					jsonObject.add("mulkiya_processing_date", new JsonPrimitive(rs.getString("mulkiya_processing_date")));
					
				}
				
		
		}//end of try block
		catch(Exception e)
		{
			ETHLogger.log(dbConnVar + "_VMS",e);
		}
		finally
		{
			try
			{
				if(rs!=null)
				{
					rs.close();
				}
				if(cstmt!=null)
				{
					cstmt.close();
				}
				if(con!=null)
				{
					cf.freeConnection(con);
				}
			}
			catch(Exception e)
			{
				ETHLogger.log(dbConnVar + "_VMS",e);
			}
		}//End of finally block
		return jsonObject;
	}
	
	public JsonObject getInsuranceDetails(String dbConnVar,String vehicleId )
	{
		Connection con = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;
		JsonArray jsonData=new JsonArray();
		ConnectionFactory cf =null;
		JsonObject jsonObject = new JsonObject();	
		try
		{
			cf= new ConnectionFactory(dbConnVar);
			con=cf.getConnection();
			//ETHLogger.log(dbConnVar+"_VMS","select fun_retrive_vms_vehicle_insurance_details('"+vehicleId+"','c')");
			cstmt =con.prepareCall("{? = call fun_retrive_vms_vehicle_insurance_details(?,?)}"); 
			cstmt.registerOutParameter(1,Types.OTHER);
			cstmt.setString(2, vehicleId );
			cstmt.setObject(3, rs );
			con.setAutoCommit(false);
		    cstmt.execute();
		    rs  = (ResultSet) cstmt.getObject(1);
		    
		if(rs.next() ) {
				
			
					jsonObject.add("vehicle_insurance_id", new JsonPrimitive(rs.getInt("vehicle_insurance_id")));
					jsonObject.add("vehicle_id", new JsonPrimitive(rs.getInt("vehicle_id")));
					jsonObject.add("policy_no", new JsonPrimitive(rs.getString("policy_no")));
					
					jsonObject.add("insurance_company", new JsonPrimitive(rs.getString("insurance_company")));
					jsonObject.add("issue_date", new JsonPrimitive(rs.getString("issue_date")));
					jsonObject.add("insurance_value", new JsonPrimitive(rs.getString("insurance_value")));
					
					
					jsonObject.add("insurance_amount", new JsonPrimitive(rs.getString("insurance_amount")));
					jsonObject.add("payment_mode", new JsonPrimitive(rs.getString("payment_mode")));
					jsonObject.add("expiry_date", new JsonPrimitive(rs.getString("expiry_date")));
					
					jsonObject.add("insurance_class", new JsonPrimitive(rs.getString("insurance_class")));
					jsonObject.add("insurance_agent", new JsonPrimitive(rs.getString("insurance_agent")));
					
					
				}
				
		
		}//end of try block
		catch(Exception e)
		{
			ETHLogger.log(dbConnVar + "_VMS",e);
		}
		finally
		{
			try
			{
				if(rs!=null)
				{
					rs.close();
				}
				if(cstmt!=null)
				{
					cstmt.close();
				}
				if(con!=null)
				{
					cf.freeConnection(con);
				}
			}
			catch(Exception e)
			{
				ETHLogger.log(dbConnVar + "_VMS",e);
			}
		}//End of finally block
		return jsonObject;
	}
	
	
	
	
	
	 public String getNextFormNo(String dbConnVar,String acadId,String prefix)
		{
		 	Connection con = null;
			CallableStatement cstmt = null;
			ResultSet rs = null;
			JsonArray jsonData=new JsonArray();
			//ConnectionFactory cf =null;
			String nextFormNo="";
			
			try
			{
				//cf= new ConnectionFactory(dbConnVar);
				con=cf.getConnection();
				
				//ETHLogger.log(dbConnVar+"_VMS"," fun_generate_form_no("+acadId+",'"+prefix+"','admission_form_no',0,'V','')"); 
				//ETHLogger.log(dbConnVar + "_VMS",acadId+"/"+prefix);
				
				cstmt =con.prepareCall("{? = call fun_generate_vms_form_no(?,?,?,?,?,?)}"); 
				cstmt.registerOutParameter(1,Types.VARCHAR);
				cstmt.setInt(2, Integer.parseInt(acadId));
				cstmt.setString(3, prefix );
				cstmt.setString(4, "admission_form_no" );
				cstmt.setInt(5, 0);
				cstmt.setString(6, "V" );
				cstmt.setString(7, "" );
				
				
			    cstmt.execute();
			    nextFormNo  = cstmt.getString(1);
			   
			    
					
			
			}//end of try block
			catch(Exception e)
			{
				ETHLogger.log(dbConnVar + "_VMS",e);
			}
			finally
			{
				try
				{
					if(rs!=null)
					{
						rs.close();
					}
					if(cstmt!=null)
					{
						cstmt.close();
					}
					if(con!=null)
					{
						cf.freeConnection(con);
					}
				}
				catch(Exception e)
				{
					ETHLogger.log(dbConnVar + "_VMS",e);
				}
			}//End of finally block
			//ETHLogger.log(dbConnVar + "_VMS","nextFormNo---------"+nextFormNo);
			return nextFormNo;
		}
	 
	 
	 public JsonArray getVendors(String dbConnVar)
		{
			Connection con=null;
			Statement stmt=null;
			ResultSet rs=null;
			JsonArray jsonData=new JsonArray();
			
			String returnValue=null;
			ConnectionFactory cf =null;
			
			try
			{
				cf= new ConnectionFactory(dbConnVar);
				con=cf.getConnection();
				if(con==null)
				{
					return null; 
				} 
				stmt=con.createStatement();
				StringBuilder query=new StringBuilder();
				query.append(" select vendor_id,vendor_name from vendor_master where vms_applicable='Y'");
				
			    //ETHLogger.log(dbConnVar, "Before->"+query.toString());
				rs=stmt.executeQuery(query.toString());
			while(rs.next() ) {
						JsonObject jsonObject = new JsonObject();	
						jsonObject.add("vendor_id", new JsonPrimitive(rs.getInt("vendor_id")));
						jsonObject.add("vendor_name", new JsonPrimitive(rs.getString("vendor_name")));
						jsonData.add(jsonObject);
					}
					
			
			}//end of try block
			catch(Exception e)
			{
				ETHLogger.log(dbConnVar + "_VMS",e);
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
					ETHLogger.log(dbConnVar + "_VMS",e);
				}
			}//End of finally block
			return jsonData;
		}
	 
	 public JsonArray getVehicleDetailReportData(String dbConnVar)
		{
		 	Connection con = null;
			CallableStatement cstmt = null;
			ResultSet rs = null;
			JsonArray jsonData=new JsonArray();
			
			
			
			try
			{
					con=cf.getConnection();
					cstmt =con.prepareCall("{? = call fun_retrive_vms_vehicle_details_report(?)}"); 
					cstmt.registerOutParameter(1,Types.OTHER);
					cstmt.setObject(2, rs );
					con.setAutoCommit(false);
				    cstmt.execute();
				    rs  = (ResultSet) cstmt.getObject(1);
			while(rs.next() ) {
						JsonObject jsonObject = new JsonObject();	
						jsonObject.add("vehicle_reg_no", new JsonPrimitive(rs.getString("vehicle_reg_no")));
						jsonObject.add("seating_capacity", new JsonPrimitive(rs.getInt("seating_capacity")));
						jsonObject.add("driver_name", new JsonPrimitive(rs.getString("driver_name")));
						jsonObject.add("driver_mobile_no", new JsonPrimitive(rs.getString("driver_mobile_no")));
						jsonObject.add("vehicle_type_name", new JsonPrimitive(rs.getString("vehicle_type_name")));
						jsonObject.add("vehicle_maker_company", new JsonPrimitive(rs.getString("vehicle_maker_company")));
						
						jsonData.add(jsonObject);
					}
					
			
			}//end of try block
			catch(Exception e)
			{
				ETHLogger.log(dbConnVar + "_VMS",e);
			}
			finally
			{
				try
				{
					if(rs!=null)
					{
						rs.close();
					}
					if(cstmt!=null)
					{
						cstmt.close();
					}
					if(con!=null)
					{
						cf.freeConnection(con);
					}
				}
				catch(Exception e)
				{
					ETHLogger.log(dbConnVar + "_VMS",e);
				}
			}//End of finally block
			return jsonData;
		}
	 	
	 public JsonArray getFuelDetailReportData(String dbConnVar)
		{
		 	Connection con = null;
			CallableStatement cstmt = null;
			ResultSet rs = null;
			JsonArray jsonData=new JsonArray();
			
			
			
			try
			{
					con=cf.getConnection();
					cstmt =con.prepareCall("{? = call fun_retrive_vms_fuel_details_report(?)}"); 
					cstmt.registerOutParameter(1,Types.OTHER);
					cstmt.setObject(2, rs );
					con.setAutoCommit(false);
				    cstmt.execute();
				    rs  = (ResultSet) cstmt.getObject(1);
			while(rs.next() ) {
						JsonObject jsonObject = new JsonObject();
						
						jsonObject.add("vehicle_id", new JsonPrimitive(rs.getString("vehicle_id")));
						jsonObject.add("vehicle_reg_no", new JsonPrimitive(rs.getString("vehicle_reg_no")));
						jsonObject.add("vehicle_type_name", new JsonPrimitive(rs.getString("vehicle_type_name")));
						jsonObject.add("vm_fuel_reading_id", new JsonPrimitive(rs.getString("vm_fuel_reading_id")));
						jsonObject.add("fuel_reading_no", new JsonPrimitive(rs.getString("fuel_reading_no")));
						
						jsonObject.add("vehicle_owner", new JsonPrimitive(rs.getString("vehicle_owner")));
						jsonObject.add("driver_name", new JsonPrimitive(rs.getString("driver_name")));
						jsonObject.add("fill_date", new JsonPrimitive(rs.getString("fill_date")));
						jsonObject.add("new_meter_reading", new JsonPrimitive(rs.getString("new_meter_reading")));
						
						jsonObject.add("old_meter_reading", new JsonPrimitive(rs.getString("old_meter_reading")));
						
						jsonObject.add("total_kilometers", new JsonPrimitive(rs.getString("total_kilometers")));
						
						
						jsonObject.add("quantity", new JsonPrimitive(rs.getString("quantity")));
						jsonObject.add("rate_per_ltr", new JsonPrimitive(rs.getString("rate_per_ltr")));
						jsonObject.add("total_amount", new JsonPrimitive(rs.getString("total_amount")));
						
						jsonObject.add("tax_amount", new JsonPrimitive(rs.getString("tax_amount")));
						jsonObject.add("fuel_account", new JsonPrimitive(rs.getString("fuel_account")));
						jsonObject.add("vendor_name", new JsonPrimitive(rs.getString("vendor_name")));
						jsonObject.add("average", new JsonPrimitive(rs.getString("average")));
						jsonObject.add("voucher_id", new JsonPrimitive(rs.getString("voucher_id")));
						jsonObject.add("postToFinanceStatus", new JsonPrimitive(rs.getString("postToFinanceStatus")));
						
						jsonData.add(jsonObject);
					}
			
			
			}//end of try block
			catch(Exception e)
			{
				ETHLogger.log(dbConnVar + "_VMS",e);
			}
			finally
			{
				try
				{
					if(rs!=null)
					{
						rs.close();
					}
					if(cstmt!=null)
					{
						cstmt.close();
					}
					if(con!=null)
					{
						cf.freeConnection(con);
					}
				}
				catch(Exception e)
				{
					ETHLogger.log(dbConnVar + "_VMS",e);
				}
			}//End of finally block
			return jsonData;
		}
	 public JsonArray getAccidentDetailReportData(String dbConnVar)
		{
		 	Connection con = null;
			CallableStatement cstmt = null;
			ResultSet rs = null;
			JsonArray jsonData=new JsonArray();
			
			
			
			try
			{
					con=cf.getConnection();
					cstmt =con.prepareCall("{? = call fun_retrive_vms_accident_details_report(?)}"); 
					cstmt.registerOutParameter(1,Types.OTHER);
					cstmt.setObject(2, rs );
					con.setAutoCommit(false);
				    cstmt.execute();
				    rs  = (ResultSet) cstmt.getObject(1);
			while(rs.next() ) {
						JsonObject jsonObject = new JsonObject();	
						jsonObject.add("vehicle_reg_no", new JsonPrimitive(rs.getString("vehicle_reg_no")));
						jsonObject.add("accident_date", new JsonPrimitive(rs.getString("accident_date")));
						
						jsonObject.add("driver_name", new JsonPrimitive(rs.getString("driver_name")));
						jsonObject.add("accident_location", new JsonPrimitive(rs.getString("accident_location")));
						jsonObject.add("emirate", new JsonPrimitive(rs.getString("emirate")));
						jsonObject.add("accident_caused_by", new JsonPrimitive(rs.getString("accident_caused_by")));
								
						
						jsonData.add(jsonObject);
					}
					
			
			}//end of try block
			catch(Exception e)
			{
				ETHLogger.log(dbConnVar + "_VMS",e);
			}
			finally
			{
				try
				{
					if(rs!=null)
					{
						rs.close();
					}
					if(cstmt!=null)
					{
						cstmt.close();
					}
					if(con!=null)
					{
						cf.freeConnection(con);
					}
				}
				catch(Exception e)
				{
					ETHLogger.log(dbConnVar + "_VMS",e);
				}
			}//End of finally block
			return jsonData;
		}
	 
	 public JsonArray getServiceDetailReportData(String dbConnVar)
		{
		 	Connection con = null;
			CallableStatement cstmt = null;
			ResultSet rs = null;
			JsonArray jsonData=new JsonArray();
			
			try
			{
					con=cf.getConnection();
					cstmt =con.prepareCall("{? = call fun_retrive_vms_service_details_report(?)}"); 
					cstmt.registerOutParameter(1,Types.OTHER);
					cstmt.setObject(2, rs );
					con.setAutoCommit(false);
				    cstmt.execute();
				    rs  = (ResultSet) cstmt.getObject(1);
			while(rs.next() ) {
						JsonObject jsonObject = new JsonObject();
						jsonObject.add("vehicle_id", new JsonPrimitive(rs.getString("vehicle_id")));
						jsonObject.add("vehicle_reg_no", new JsonPrimitive(rs.getString("vehicle_reg_no")));
						jsonObject.add("service_id", new JsonPrimitive(rs.getString("service_id")));
						jsonObject.add("service_no", new JsonPrimitive(rs.getString("service_no")));
						jsonObject.add("vehicle_owner", new JsonPrimitive(rs.getString("vehicle_owner")));
						jsonObject.add("vehicle_type_name", new JsonPrimitive(rs.getString("vehicle_type_name")));
						jsonObject.add("driver_name", new JsonPrimitive(rs.getString("driver_name")));
						
						jsonObject.add("service_date", new JsonPrimitive(rs.getString("service_date")));
						
						jsonObject.add("last_meter_reading", new JsonPrimitive(rs.getString("last_meter_reading")));
						jsonObject.add("next_meter_reading", new JsonPrimitive(rs.getString("next_meter_reading")));
						jsonObject.add("location", new JsonPrimitive(rs.getString("location")));
						
						jsonObject.add("ac_change_hrs", new JsonPrimitive(rs.getString("ac_change_hrs")));
						
						jsonObject.add("cost", new JsonPrimitive(rs.getString("cost")));
						jsonObject.add("tax_amount", new JsonPrimitive(rs.getString("tax_amount")));
						jsonObject.add("vehicle_status", new JsonPrimitive(rs.getString("vehicle_status")));
						
						jsonObject.add("remark", new JsonPrimitive(rs.getString("remark")));
						jsonObject.add("service_details", new JsonPrimitive(rs.getString("service_details")));
						jsonObject.add("vendor_name", new JsonPrimitive(rs.getString("vendor_name")));
						
						jsonObject.add("voucher_id", new JsonPrimitive(rs.getString("voucher_id")));
						jsonObject.add("postToFinanceStatus", new JsonPrimitive(rs.getString("postToFinanceStatus")));							
						
						jsonData.add(jsonObject);
					}
					
			
			}//end of try block
			catch(Exception e)
			{
				ETHLogger.log(dbConnVar + "_VMS",e);
			}
			finally
			{
				try
				{
					if(rs!=null)
					{
						rs.close();
					}
					if(cstmt!=null)
					{
						cstmt.close();
					}
					if(con!=null)
					{
						cf.freeConnection(con);
					}
				}
				catch(Exception e)
				{
					ETHLogger.log(dbConnVar + "_VMS",e);
				}
			}//End of finally block
			return jsonData;
		}
	 public JsonArray getInsuranceDetailReportData(String dbConnVar)
		{
		 	Connection con = null;
			CallableStatement cstmt = null;
			ResultSet rs = null;
			JsonArray jsonData=new JsonArray();
			
			
			
			try
			{
					con=cf.getConnection();
					cstmt =con.prepareCall("{? = call fun_retrive_vms_insurance_details_report(?)}"); 
					cstmt.registerOutParameter(1,Types.OTHER);
					cstmt.setObject(2, rs );
					con.setAutoCommit(false);
				    cstmt.execute();
				    rs  = (ResultSet) cstmt.getObject(1);
			while(rs.next() ) {
						JsonObject jsonObject = new JsonObject();
						jsonObject.add("policy_no", new JsonPrimitive(rs.getString("policy_no")));
						jsonObject.add("vehicle_reg_no", new JsonPrimitive(rs.getString("vehicle_reg_no")));
						jsonObject.add("insurance_company", new JsonPrimitive(rs.getString("insurance_company")));
						
						
						jsonObject.add("insurance_value", new JsonPrimitive(rs.getString("insurance_value")));
						jsonObject.add("insurance_amount", new JsonPrimitive(rs.getString("insurance_amount")));
						jsonObject.add("issue_date", new JsonPrimitive(rs.getString("issue_date")));
						
						jsonObject.add("expiry_date", new JsonPrimitive(rs.getString("expiry_date")));
						
						jsonObject.add("status", new JsonPrimitive(rs.getString("status")));
						
						
													
						
						jsonData.add(jsonObject);
					}
					
			
			}//end of try block
			catch(Exception e)
			{
				ETHLogger.log(dbConnVar + "_VMS",e);
			}
			finally
			{
				try
				{
					if(rs!=null)
					{
						rs.close();
					}
					if(cstmt!=null)
					{
						cstmt.close();
					}
					if(con!=null)
					{
						cf.freeConnection(con);
					}
				}
				catch(Exception e)
				{
					ETHLogger.log(dbConnVar + "_VMS",e);
				}
			}//End of finally block
			return jsonData;
		}
	 public JsonArray getVehicleRegistrationDetailReportData(String dbConnVar)
		{
		 	Connection con = null;
			CallableStatement cstmt = null;
			ResultSet rs = null;
			JsonArray jsonData=new JsonArray();
			
			
			
			try
			{		//ETHLogger.log(dbConnVar + "_VMS","Inside getVehicleRegistrationDetailReportData ");
					con=cf.getConnection();
					cstmt =con.prepareCall("{? = call fun_retrive_vms_vehicle_registration_details_report(?)}"); 
					cstmt.registerOutParameter(1,Types.OTHER);
					cstmt.setObject(2, rs );
					con.setAutoCommit(false);
				    cstmt.execute();
				    rs  = (ResultSet) cstmt.getObject(1);
			while(rs.next() ) {
						JsonObject jsonObject = new JsonObject();
						jsonObject.add("vehicle_id", new JsonPrimitive(rs.getString("vehicle_id")));
						jsonObject.add("vehicle_reg_no", new JsonPrimitive(rs.getString("vehicle_reg_no")));
						jsonObject.add("t_c_no", new JsonPrimitive(rs.getString("t_c_no")));
						jsonObject.add("vehicle_type_name", new JsonPrimitive(rs.getString("vehicle_type_name")));
						jsonObject.add("mulkiya_issue_date", new JsonPrimitive(rs.getString("mulkiya_issue_date")));
						jsonObject.add("mulkiya_expiry_date", new JsonPrimitive(rs.getString("mulkiya_expiry_date")));
						jsonObject.add("mulkiya_renewal_date", new JsonPrimitive(rs.getString("mulkiya_renewal_date")));
						jsonObject.add("status", new JsonPrimitive(rs.getString("status")));
						//ETHLogger.log(dbConnVar + "_VMS","Inside getVehicleRegistrationDetailReportData--> "+jsonObject.toString());
						jsonData.add(jsonObject);
					}
					
			
			}//end of try block
			catch(Exception e)
			{
				ETHLogger.log(dbConnVar + "_VMS",e);
			}
			finally
			{
				try
				{
					if(rs!=null)
					{
						rs.close();
					}
					if(cstmt!=null)
					{
						cstmt.close();
					}
					if(con!=null)
					{
						cf.freeConnection(con);
					}
				}
				catch(Exception e)
				{
					ETHLogger.log(dbConnVar + "_VMS",e);
				}
			}//End of finally block
			return jsonData;
		}
	 
	 public JsonArray getVehicleDocuments(String dbConnVar)
		{
		 	Connection con = null;
			CallableStatement cstmt = null;
			ResultSet rs = null;
			JsonArray jsonData=new JsonArray();
			
			
			
			try
			{		//ETHLogger.log(dbConnVar + "_VMS","Inside getVehicleDocuments ");
					con=cf.getConnection();
					cstmt =con.prepareCall("{? = call fun_retrive_vms_documents(?)}"); 
					cstmt.registerOutParameter(1,Types.OTHER);
					cstmt.setObject(2, rs );
					con.setAutoCommit(false);
				    cstmt.execute();
				    rs  = (ResultSet) cstmt.getObject(1);
			while(rs.next() ) {
						JsonObject jsonObject = new JsonObject();
						jsonObject.add("document_id", new JsonPrimitive(rs.getString("document_id")));
						jsonObject.add("document_name", new JsonPrimitive(rs.getString("document_name")));
						jsonObject.add("document_desc", new JsonPrimitive(rs.getString("document_desc")));
						jsonObject.add("document_code", new JsonPrimitive(rs.getString("document_code")));
						jsonObject.add("display_order", new JsonPrimitive(rs.getString("display_order")));
						
						//ETHLogger.log(dbConnVar + "_VMS","Inside getVehicleDocuments--> "+jsonObject.toString());
						jsonData.add(jsonObject);
					}
					
			
			}//end of try block
			catch(Exception e)
			{
				ETHLogger.log(dbConnVar + "_VMS",e);
			}
			finally
			{
				try
				{
					if(rs!=null)
					{
						rs.close();
					}
					if(cstmt!=null)
					{
						cstmt.close();
					}
					if(con!=null)
					{
						cf.freeConnection(con);
					}
				}
				catch(Exception e)
				{
					ETHLogger.log(dbConnVar + "_VMS",e);
				}
			}//End of finally block
			return jsonData;
		}
	 
	 
	 public JsonArray getDocumentAttributes(String dbConnVar,String docId)
		{
		 	Connection con = null;
			CallableStatement cstmt = null;
			ResultSet rs = null;
			JsonArray jsonData=new JsonArray();
			try
			{		//ETHLogger.log(dbConnVar + "_VMS","Inside fun_retrive_vms_documents ");
					con=cf.getConnection();
					cstmt =con.prepareCall("{? = call fun_retrive_vms_documents_attributes(?,?)}"); 
					cstmt.registerOutParameter(1,Types.OTHER);
					cstmt.setString(2, docId );
					cstmt.setObject(3, rs );
					con.setAutoCommit(false);
				    cstmt.execute();
				    rs  = (ResultSet) cstmt.getObject(1);
			while(rs.next() ) {
						JsonObject jsonObject = new JsonObject();
						jsonObject.add("document_id", new JsonPrimitive(rs.getString("document_id")));
						jsonObject.add("document_name", new JsonPrimitive(rs.getString("document_name")));
						jsonObject.add("attribute_id", new JsonPrimitive(rs.getString("attribute_id")));
						jsonObject.add("attribute_name", new JsonPrimitive(rs.getString("attribute_name")));
						jsonObject.add("attribute_type", new JsonPrimitive(rs.getString("attribute_type")));
						jsonObject.add("display_order1", new JsonPrimitive(rs.getString("display_order1")));
						jsonObject.add("display_order2", new JsonPrimitive(rs.getString("display_order2")));
						//ETHLogger.log(dbConnVar + "_VMS","Inside getVehicleDocuments--> "+jsonObject.toString());
						jsonData.add(jsonObject);
					}
					
			
			}//end of try block
			catch(Exception e)
			{
				ETHLogger.log(dbConnVar + "_VMS",e);
			}
			finally
			{
				try
				{
					if(rs!=null)
					{
						rs.close();
					}
					if(cstmt!=null)
					{
						cstmt.close();
					}
					if(con!=null)
					{
						cf.freeConnection(con);
					}
				}
				catch(Exception e)
				{
					ETHLogger.log(dbConnVar + "_VMS",e);
				}
			}//End of finally block
			return jsonData;
		}
	 

	 public int insertUpdateVehicleDocumentDetails(String vehicleId,String documentId,String dataStr,String token) {
			Connection con = null;
			CallableStatement cs = null;
			ResultSet rs = null;
			int result = 0;

			try {
				con = cf.getConnection();
				//ETHLogger.log(dbConnVar,"select vms_insert_update_vehicle_document_details('"+vehicleId+","+documentId+","+dataStr+","+token+"'");  
				cs = con.prepareCall("{? = call vms_insert_update_vehicle_document_details(?,?,?,?)}");
				cs.registerOutParameter(1, Types.INTEGER); 
				cs.setString(2, vehicleId);
				cs.setString(3, documentId);  
				cs.setString(4, dataStr);  
				cs.setString(5, token);  
				cs.execute();
				result = cs.getInt(1);

			} catch (Exception e) {
				ETHLogger.log(dbConnVar + "_VMS",e);
			} finally {
				try {
					if (rs != null) {
						rs.close();
					}
					if (cs != null) {
						cs.close();
					}
					if (con != null) {
						cf.freeConnection(con);
					}
				} catch (Exception e) {
					ETHLogger.log(dbConnVar + "_VMS", e);
				} 
			}// end of finally
			return result;
		}// end of insertUpdateVehicleDocumentDetails() method
	 
	 
	 public JsonObject  getDocumentAttributeValues(String vehicleId,String docId,String attrId){
	        
		    Connection con = null;
			CallableStatement cstmt = null;
			ResultSet rs = null;
			OrderedMap appOM = new LinkedMap();
		
			JsonObject jsonObject = new JsonObject();
			try{
				//ETHLogger.log(dbConnVar+"_VMS","Inside Bean vehicleId"+vehicleId );	
				
				con = cf.getConnection();
				//ETHLogger.log(dbConnVar+"_VMS","select fun_retrive_vms_document_attribute_values('"+vehicleId+"','"+docId+"','"+attrId+"','c');");
				cstmt =con.prepareCall("{? = call fun_retrive_vms_document_attribute_values(?,?,?,?)}"); 
				cstmt.registerOutParameter(1,Types.OTHER);
				cstmt.setString(2, vehicleId );
				cstmt.setObject(3, docId );
				cstmt.setObject(4, attrId );
				cstmt.setObject(5, rs );
				con.setAutoCommit(false);
			    cstmt.execute();
				  rs  = (ResultSet) cstmt.getObject(1);
					if(rs.next()) {
						
							jsonObject.add("vms_document_id",new JsonPrimitive(rs.getString("vms_document_id")));
							jsonObject.add("document_id", new JsonPrimitive(rs.getString("document_id")));
							jsonObject.add("document_desc", new JsonPrimitive(rs.getString("document_desc")));
							jsonObject.add("attribute_id", new JsonPrimitive(rs.getString("attribute_id")));
							jsonObject.add("attribute_value", new JsonPrimitive(rs.getString("attribute_value")));
					
							//ETHLogger.log(dbConnVar+"_VMS", "yyyy"+jsonObject.toString());
						
					}
						
			}catch(Exception e){

			ETHLogger.log(dbConnVar + "_VMS", e);
			 }
			finally {
				try {
					  if (cstmt != null){
						  cstmt.close();
					  }
					  if(con != null){
					  con.close();
					  }
					  
					  if(rs!=null){
						rs.close();  
						 }
				  } catch (Exception e ) {
					  ETHLogger.log(dbConnVar + "_VMS",e);
				  } 

			}
	     
			return jsonObject;
		}
	 
	 public JsonObject getFuelReadingDetails(String vehicleId,String fuelReadingNo)
		{
		 	Connection con = null;
			CallableStatement cstmt = null;
			ResultSet rs = null;
			JsonArray jsonData=new JsonArray();
		    JsonObject jsonObject = new JsonObject();	
			
			
			
			try
			{
					con=cf.getConnection();
					cstmt =con.prepareCall("{? = call fun_retrive_vms_fuel_reading_details(?,?)}"); 
					cstmt.registerOutParameter(1,Types.OTHER);
					//cstmt.setObject(2, vehicleId );
					cstmt.setString(2, fuelReadingNo );
					cstmt.setObject(3, rs );
					con.setAutoCommit(false);
				    cstmt.execute();
				    rs  = (ResultSet) cstmt.getObject(1);
			if(rs.next() ) {
				
			
				jsonObject.add("vehicle_reg_no", new JsonPrimitive(rs.getString("vehicle_reg_no")));
				jsonObject.add("vehicle_type_name", new JsonPrimitive(rs.getString("vehicle_type_name")));
				jsonObject.add("fuel_reading_no", new JsonPrimitive(rs.getString("fuel_reading_no")));
				
				jsonObject.add("vehicle_owner", new JsonPrimitive(rs.getString("vehicle_owner")));
				jsonObject.add("driver_name", new JsonPrimitive(rs.getString("driver_name")));
				jsonObject.add("fill_date", new JsonPrimitive(rs.getString("fill_date")));
				
				jsonObject.add("new_meter_reading", new JsonPrimitive(rs.getString("new_meter_reading")));
				
				jsonObject.add("old_meter_reading", new JsonPrimitive(rs.getString("old_meter_reading")));
				
				jsonObject.add("total_kilometers", new JsonPrimitive(rs.getString("total_kilometers")));
				
				
				jsonObject.add("quantity", new JsonPrimitive(rs.getString("quantity")));
				jsonObject.add("rate_per_ltr", new JsonPrimitive(rs.getString("rate_per_ltr")));
				jsonObject.add("total_amount", new JsonPrimitive(rs.getString("total_amount")));
				
				jsonObject.add("tax_amount", new JsonPrimitive(rs.getString("tax_amount")));
				jsonObject.add("remark", new JsonPrimitive(rs.getString("remark")));
				jsonObject.add("fuel_account", new JsonPrimitive(rs.getString("fuel_account")));
				jsonObject.add("average", new JsonPrimitive(rs.getString("average")));
				jsonObject.add("voucher_id", new JsonPrimitive(rs.getString("voucher_id")));
				jsonObject.add("postToFinanceStatus", new JsonPrimitive(rs.getString("postToFinanceStatus")));
				
				jsonObject.add("vendor_id", new JsonPrimitive(rs.getString("vendor_id")));
				jsonObject.add("invoice_no", new JsonPrimitive(rs.getString("invoice_no")));
				jsonObject.add("invoice_date", new JsonPrimitive(rs.getString("invoice_date")));
				
				
				//ETHLogger.log(dbConnVar + "_VMS",rs.getString("new_meter_reading") + jsonObject.toString());
					}
			
			
			}//end of try block
			catch(Exception e)
			{
				ETHLogger.log(dbConnVar + "_VMS",e);
			}
			finally
			{
				try
				{
					if(rs!=null)
					{
						rs.close();
					}
					if(cstmt!=null)
					{
						cstmt.close();
					}
					if(con!=null)
					{
						cf.freeConnection(con);
					}
				}
				catch(Exception e)
				{
					ETHLogger.log(dbConnVar + "_VMS",e);
				}
			}//End of finally block
			return jsonObject;
		}
	 public Vector getAccessControlList(String loginid)
		throws Exception {
		// ETHLogger.log(dbConnVar,"11111111111111111111111111111111"+loginid); 
		
		ResultSet rs = null;
		Connection con = null;
		CallableStatement cstmt = null;
		 
		
		OrderedMap activityOm = new LinkedMap();
		OrderedMap privilegeOm = new LinkedMap();
		OrderedMap privilegeGroupOm = new LinkedMap();
		 
		OrderedMap dashboardOm  = new LinkedMap();
		Vector dashboardAccessLevelPrivilege = new Vector();
		Vector activityVec = new Vector();
		
		 
		try{
			
			con = cf.getConnection();	 
			// ETHLogger.log(dbConnVar+"_VMS", "Innnnnnnnnnnnnnnnnnnnvms_fun_retrieve_access_control_list"+loginid);
			cstmt = con.prepareCall("{call vms_fun_retrieve_access_control_list(?)}"); 
			cstmt.setString(1, loginid); 
			
			cstmt.execute();
			rs = cstmt.getResultSet();
			
			
			
			if(rs.next()){
				String activityId = null;
				String activityName = null;
				String privilegeId = null;
				String privilegeName = null, privilegeData=null , privilegeGroup = null;
				String privilegeUrlData = null, privilegeUrl = null;
				String activityNameLang2 = null;
				String privilegeNameLang2 = null,privilegeIcon=null;
				String accessLevel = "-",accessLevel1="-";
				
				String activityKey = null;
				
				StringTokenizer token = null,token1 = null;
				
				do{
					 activityId = rs.getString(1);
					 activityName = rs.getString(2);	
					 privilegeId = rs.getString(3);
					 privilegeName = rs.getString(4);
					 privilegeData = rs.getString(5);
					 activityNameLang2= rs.getString(6);
					 privilegeNameLang2= rs.getString(7);
					 privilegeIcon = rs.getString(8);
					 
					 /* privilegeData looks like  DashBoard~/form/jsp_aaa/Masters.jsp$/form/jsp_result/PreExamActivity/ResultConfiguration.jsp#accessType=ST,CT
					  privilegeGroup 	: DashBoard
					  privilegeUrlData  : /form/jsp_aaa/Masters.jsp$/form/jsp_result/PreExamActivity/ResultConfiguration.jsp#accessType=ST,CT
					  privilegeUrl 		: /form/jsp_aaa/Masters.jsp$/form/jsp_result/PreExamActivity/ResultConfiguration.jsp
					  accessLevel 		: accessType=ST,CT
					 */
					 //ETHLogger.log(dbConnVar+"_GradeBook", "privilegeData : "+privilegeData);
					 token = new StringTokenizer(privilegeData, "~");
					 privilegeGroup = token.nextToken();
					 privilegeUrlData = token.nextToken();
					 if(privilegeUrlData!=null && privilegeUrlData.contains("^")){
						 token = new StringTokenizer(privilegeUrlData, "^");
						 privilegeUrl = token.nextToken();   				 
						 accessLevel = token.nextToken(); // accessType=ST,CT					 
						 token = new StringTokenizer(accessLevel, "=");					 
						 token.nextToken();//accessType
						 accessLevel = token.nextToken(); //ST,CT	
					 
						 if(accessLevel!=null && !"".equalsIgnoreCase(accessLevel) && accessLevel.contains(",")){
							token1 = new StringTokenizer(accessLevel,","); 
							while(token1.hasMoreTokens()){
								 accessLevel1 = token1.nextToken();
								 if(dashboardOm!=null && dashboardOm.containsKey(accessLevel1)){
									 dashboardAccessLevelPrivilege = (Vector)dashboardOm.get(accessLevel1);					  				 
								 }else{
									 dashboardAccessLevelPrivilege = new Vector();	
								 }
							 dashboardAccessLevelPrivilege.add(privilegeId+"\uB006"+privilegeName+"\uB006"+privilegeUrl+"\uB006"+privilegeIcon+"\uB006"+privilegeNameLang2);							  
							 dashboardOm.put(accessLevel1, dashboardAccessLevelPrivilege);
							}
						 }else{
							 accessLevel1 = accessLevel;
							 if(dashboardOm!=null && dashboardOm.containsKey(accessLevel1)){
								 dashboardAccessLevelPrivilege = (Vector)dashboardOm.get(accessLevel1);					  				 
							 }else{
								 dashboardAccessLevelPrivilege = new Vector();	
							 }	
							 dashboardAccessLevelPrivilege.add(privilegeId+"\uB006"+privilegeName+"\uB006"+privilegeUrl+"\uB006"+ privilegeIcon+"\uB006"+privilegeNameLang2);							  
							 dashboardOm.put(accessLevel1, dashboardAccessLevelPrivilege);
						 }
					}else{
						 privilegeUrl = privilegeUrlData;
					}
					 
					
						 activityKey = activityId+"\uB006"+activityName+"\uB006"+activityNameLang2;
						// ETHLogger.log(dbConnVar+"_VMS","activityKey-->"+activityKey );
						 if(activityOm!=null && activityOm.containsKey(activityKey)){ 
							 privilegeGroupOm = (OrderedMap)activityOm.get(activityKey);
							 if(privilegeGroupOm!=null && privilegeGroupOm.containsKey(privilegeGroup)){
								 privilegeOm = (OrderedMap)privilegeGroupOm.get(privilegeGroup);
							 }else{
								 privilegeOm = new LinkedMap();
							 }
						 }else{
							 privilegeGroupOm = new LinkedMap();
							 privilegeOm = new LinkedMap();
						 }
						 privilegeOm.put(privilegeId,privilegeName+"\uB006"+privilegeUrl+"\uB006"+ privilegeIcon+"\uB006"+privilegeNameLang2);  
						 privilegeGroupOm.put(privilegeGroup,privilegeOm); 				
						 activityOm.put(activityKey, privilegeGroupOm); 					
					 				
				}while(rs.next());
			}//end of if 
					
			activityVec.add(activityOm);	
			activityVec.add(dashboardOm);
		}catch(Exception e ){
			 ETHLogger.log(dbConnVar+"_VMS", e);
		}finally {
			try {
				if (rs != null){
					rs.close();
				}
				if (cstmt != null){
					cstmt.close();
				}
				if(con != null){
					cf.freeConnection(con);
				}
			} catch (Exception e) {}
		}//end of finally
		return activityVec;		
	}//end of method getServiceList
	 
	 public JsonArray getRepairDetailReportData(String dbConnVar)
		{
		 	Connection con = null;
			CallableStatement cstmt = null;
			ResultSet rs = null;
			JsonArray jsonData=new JsonArray();
			
			
			
			try
			{
					con=cf.getConnection();
					cstmt =con.prepareCall("{? = call fun_retrive_vms_repair_details_report(?)}"); 
					cstmt.registerOutParameter(1,Types.OTHER);
					cstmt.setObject(2, rs );
					con.setAutoCommit(false);
				    cstmt.execute();
				    rs  = (ResultSet) cstmt.getObject(1);
			while(rs.next() ) {
						JsonObject jsonObject = new JsonObject();
						jsonObject.add("vehicle_id", new JsonPrimitive(rs.getString("vehicle_id")));
						jsonObject.add("vehicle_reg_no", new JsonPrimitive(rs.getString("vehicle_reg_no")));
						jsonObject.add("repair_id", new JsonPrimitive(rs.getString("repair_id")));
						jsonObject.add("repair_no", new JsonPrimitive(rs.getString("repair_no")));
						jsonObject.add("vehicle_owner", new JsonPrimitive(rs.getString("vehicle_owner")));
						jsonObject.add("vehicle_type_name", new JsonPrimitive(rs.getString("vehicle_type_name")));
						jsonObject.add("driver_name", new JsonPrimitive(rs.getString("driver_name")));
						jsonObject.add("repair_date", new JsonPrimitive(rs.getString("repair_date")));
						jsonObject.add("last_meter_reading", new JsonPrimitive(rs.getString("last_meter_reading")));
						jsonObject.add("complaint_type", new JsonPrimitive(rs.getString("complaint_type")));
						jsonObject.add("complaint_details", new JsonPrimitive(rs.getString("complaint_details")));
						jsonObject.add("repair_details", new JsonPrimitive(rs.getString("repair_details")));
						jsonObject.add("remark", new JsonPrimitive(rs.getString("remark")));
						jsonObject.add("workshop", new JsonPrimitive(rs.getString("workshop")));
						jsonObject.add("cost", new JsonPrimitive(rs.getString("cost")));
						jsonObject.add("tax_amount", new JsonPrimitive(rs.getString("tax_amount")));
						jsonObject.add("quotation_amount", new JsonPrimitive(rs.getString("quotation_amount")));
						jsonObject.add("vendor_id", new JsonPrimitive(rs.getString("vendor_id")));
						jsonObject.add("vendor_name", new JsonPrimitive(rs.getString("vendor_name")));
						jsonObject.add("created_by", new JsonPrimitive(rs.getString("created_by")));
						jsonObject.add("creation_date", new JsonPrimitive(rs.getString("creation_date")));
						jsonObject.add("invoice_no", new JsonPrimitive(rs.getString("invoice_no")));
						jsonObject.add("invoice_date", new JsonPrimitive(rs.getString("invoice_date")));
						jsonObject.add("voucher_id", new JsonPrimitive(rs.getString("voucher_id")));
						jsonObject.add("postToFinanceStatus", new JsonPrimitive(rs.getString("postToFinanceStatus")));							
						
						jsonData.add(jsonObject);
					}
					
			
			}//end of try block
			catch(Exception e)
			{
				ETHLogger.log(dbConnVar + "_VMS",e);
			}
			finally
			{
				try
				{
					if(rs!=null)
					{
						rs.close();
					}
					if(cstmt!=null)
					{
						cstmt.close();
					}
					if(con!=null)
					{
						cf.freeConnection(con);
					}
				}
				catch(Exception e)
				{
					ETHLogger.log(dbConnVar + "_VMS",e);
				}
			}//End of finally block
			return jsonData;
		}
	 
	 public JsonObject getServiceEntryDetails(String serviceNo)
		{
		 	Connection con = null;
			CallableStatement cstmt = null;
			ResultSet rs = null;
			
			JsonObject jsonObject = new JsonObject();
			
			
			try
			{
					con=cf.getConnection();
					cstmt =con.prepareCall("{? = call fun_retrive_vms_service_entry_details(?,?)}"); 
					cstmt.registerOutParameter(1,Types.OTHER);
					cstmt.setString(2,serviceNo);
					cstmt.setObject(3, rs );
					con.setAutoCommit(false);
				    cstmt.execute();
				    rs  = (ResultSet) cstmt.getObject(1);
			while(rs.next() ) {
					
						jsonObject.add("vehicle_id", new JsonPrimitive(rs.getString("vehicle_id")));
						jsonObject.add("vehicle_reg_no", new JsonPrimitive(rs.getString("vehicle_reg_no")));
						jsonObject.add("service_no", new JsonPrimitive(rs.getString("service_no")));
						jsonObject.add("vehicle_owner", new JsonPrimitive(rs.getString("vehicle_owner")));
						jsonObject.add("vehicle_type_name", new JsonPrimitive(rs.getString("vehicle_type_name")));
						jsonObject.add("driver_name", new JsonPrimitive(rs.getString("driver_name")));
						
						jsonObject.add("service_date", new JsonPrimitive(rs.getString("service_date")));
						
						
						jsonObject.add("last_meter_reading", new JsonPrimitive(rs.getString("last_meter_reading")));
						jsonObject.add("next_meter_reading", new JsonPrimitive(rs.getString("next_meter_reading")));
						jsonObject.add("location", new JsonPrimitive(rs.getString("location")));
						
						jsonObject.add("ac_change_hrs", new JsonPrimitive(rs.getString("ac_change_hrs")));
						
						jsonObject.add("cost", new JsonPrimitive(rs.getString("cost")));
						jsonObject.add("tax_amount", new JsonPrimitive(rs.getString("tax_amount")));
						jsonObject.add("vehicle_status", new JsonPrimitive(rs.getString("vehicle_status")));
						jsonObject.add("invoice_no", new JsonPrimitive(rs.getString("invoice_no")));
						jsonObject.add("invoice_date", new JsonPrimitive(rs.getString("invoice_date")));
						
						jsonObject.add("remark", new JsonPrimitive(rs.getString("remark")));
						jsonObject.add("service_details", new JsonPrimitive(rs.getString("service_details")));
						jsonObject.add("vendor_name", new JsonPrimitive(rs.getString("vendor_name")));
						
						jsonObject.add("voucher_id", new JsonPrimitive(rs.getString("voucher_id")));
						jsonObject.add("postToFinanceStatus", new JsonPrimitive(rs.getString("postToFinanceStatus")));							
						
					
					}
					
			
			}//end of try block
			catch(Exception e)
			{
				ETHLogger.log(dbConnVar + "_VMS",e);
			}
			finally
			{
				try
				{
					if(rs!=null)
					{
						rs.close();
					}
					if(cstmt!=null)
					{
						cstmt.close();
					}
					if(con!=null)
					{
						cf.freeConnection(con);
					}
				}
				catch(Exception e)
				{
					ETHLogger.log(dbConnVar + "_VMS",e);
				}
			}//End of finally block
			return jsonObject;
		}	 
	 
	 public JsonObject getRepairEntryDetails(String repairEntryNo)
		{
		 	Connection con = null;
			CallableStatement cstmt = null;
			ResultSet rs = null;
			
			JsonObject jsonObject = new JsonObject();
			
			
			try
			{
					con=cf.getConnection();
					cstmt =con.prepareCall("{? = call fun_retrive_vms_repair_entry_details(?,?)}"); 
					cstmt.registerOutParameter(1,Types.OTHER);
					cstmt.setString(2,repairEntryNo);
					cstmt.setObject(3, rs );
					con.setAutoCommit(false);
				    cstmt.execute();
				    rs  = (ResultSet) cstmt.getObject(1);
			while(rs.next() ) {
						
						jsonObject.add("vehicle_id", new JsonPrimitive(rs.getString("vehicle_id")));
						jsonObject.add("vehicle_reg_no", new JsonPrimitive(rs.getString("vehicle_reg_no")));
						jsonObject.add("repair_no", new JsonPrimitive(rs.getString("repair_no")));
						jsonObject.add("vehicle_owner", new JsonPrimitive(rs.getString("vehicle_owner")));
						jsonObject.add("vehicle_type_name", new JsonPrimitive(rs.getString("vehicle_type_name")));
						jsonObject.add("driver_name", new JsonPrimitive(rs.getString("driver_name")));
						jsonObject.add("repair_date", new JsonPrimitive(rs.getString("repair_date")));
						jsonObject.add("last_meter_reading", new JsonPrimitive(rs.getString("last_meter_reading")));
						jsonObject.add("repair_status", new JsonPrimitive(rs.getString("repair_status")));
						
						jsonObject.add("complaint_type", new JsonPrimitive(rs.getString("complaint_type")));
						jsonObject.add("complaint_details", new JsonPrimitive(rs.getString("complaint_details")));
						
						jsonObject.add("repair_details", new JsonPrimitive(rs.getString("repair_details")));
						jsonObject.add("remark", new JsonPrimitive(rs.getString("remark")));
						
						jsonObject.add("workshop", new JsonPrimitive(rs.getString("workshop")));
						
						jsonObject.add("cost", new JsonPrimitive(rs.getString("cost")));
						jsonObject.add("tax_amount", new JsonPrimitive(rs.getString("tax_amount")));
						jsonObject.add("quotation_amount", new JsonPrimitive(rs.getString("quotation_amount")));
						jsonObject.add("invoice_no", new JsonPrimitive(rs.getString("invoice_no")));
						jsonObject.add("invoice_date", new JsonPrimitive(rs.getString("invoice_date")));
						jsonObject.add("vendor_id", new JsonPrimitive(rs.getString("vendor_id")));
						jsonObject.add("created_by", new JsonPrimitive(rs.getString("created_by")));
						jsonObject.add("creation_date", new JsonPrimitive(rs.getString("creation_date")));
						jsonObject.add("voucher_id", new JsonPrimitive(rs.getString("voucher_id")));
						jsonObject.add("postToFinanceStatus", new JsonPrimitive(rs.getString("postToFinanceStatus")));							
						
				
					}
					
			
			}//end of try block
			catch(Exception e)
			{
				ETHLogger.log(dbConnVar + "_VMS",e);
			}
			finally
			{
				try
				{
					if(rs!=null)
					{
						rs.close();
					}
					if(cstmt!=null)
					{
						cstmt.close();
					}
					if(con!=null)
					{
						cf.freeConnection(con);
					}
				}
				catch(Exception e)
				{
					ETHLogger.log(dbConnVar + "_VMS",e);
				}
			}//End of finally block
			return jsonObject;
		}
	 
	 
	 public int postingToFinance(String companyId,String fin_id,String voucherDate,String postingToFinanceStr,String expenseType,String createdBy)
	 {
		 
		 
		 Connection con = null;
			CallableStatement cstmt = null;
			ResultSet rs = null;
		    int result=0;
			try
			{
					con=cf.getConnection();
					//ETHLogger.log(dbConnVar+"_VMS","select vms_post_vehicle_expense_to_finance("+companyId+","+fin_id+",'"+voucherDate+"','"+postingToFinanceStr+"','"+expenseType+"','"+createdBy+"'");
					cstmt =con.prepareCall("{? = call vms_post_vehicle_expense_to_finance(?,?,?,?,?,?)}"); 
					cstmt.registerOutParameter(1,Types.INTEGER);
					cstmt.setInt(2,Integer.parseInt(companyId));
					cstmt.setInt(3,Integer.parseInt(fin_id));
					cstmt.setString(4,voucherDate);
					cstmt.setString(5,postingToFinanceStr);
					cstmt.setString(6,expenseType);
					cstmt.setString(7,createdBy);
					//con.setAutoCommit(false);
					cstmt.execute();
					result = cstmt.getInt(1);
			}
			catch(Exception e)
			{
				ETHLogger.log(dbConnVar + "_VMS",e);
			}
			finally
			{
				try
				{
					if(rs!=null)
					{
						rs.close();
					}
					if(cstmt!=null)
					{
						cstmt.close();
					}
					if(con!=null)
					{
						cf.freeConnection(con);
					}
				}
				catch(Exception e)
				{
					ETHLogger.log(dbConnVar + "_VMS",e);
				}
			}//End of finally block
			return result;
		 
	 }
	 
	 
	 public String  isVehicleRegNoExist(String dbConnVar,String vehicleRegNo)
		{
			Connection con=null;
			Statement stmt=null;
			ResultSet rs=null;
			String result="0";
			ConnectionFactory cf =null;
		 
			try
			{
				cf= new ConnectionFactory(dbConnVar);
				con=cf.getConnection();
			 
				stmt=con.createStatement();
				
				StringBuilder query=new StringBuilder();
				
				query.append("select * from vms_vehicle_master where vehicle_reg_no='"+ vehicleRegNo + "'");

	     		rs=stmt.executeQuery(query.toString());
				ETHLogger.log(dbConnVar, "Before->"+query.toString());
			 
				if(rs.next())
				{
					result="1";
		 		}
			}//end of try block
			catch(Exception e)
			{
				ETHLogger.log(dbConnVar + "_VMS",e);
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
					ETHLogger.log(dbConnVar + "_VMS",e);
				}
			}//End of finally block
			ETHLogger.log(dbConnVar, "Result->"+result);
			return result;
		}
	 
	 
}

