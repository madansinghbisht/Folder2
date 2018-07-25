package beans.common;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


import org.apache.commons.collections.OrderedMap;
import org.apache.commons.collections.map.LinkedMap;
 
import eth.factory.ConnectionFactory;
import eth.logger.ETHLogger;
import java.util.StringTokenizer;
import java.util.Vector;

public class AccessControlBean {
	
	private ConnectionFactory cf = null;
	private String dbConnVar =null;    
	
	public AccessControlBean(String dbConnVar){
		cf= new ConnectionFactory(dbConnVar);
		this.dbConnVar=dbConnVar;
	}
	
	/*
	 Used in header.jsp
	 */
	public Vector getAccessControlList(String loginid)
	throws Exception {
	
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
		 //ETHLogger.log(dbConnVar+"_VMS", "Innnnnnnnnnnnnnnnnnnnvms_fun_retrieve_access_control_list"+loginid);
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
					 ETHLogger.log(dbConnVar+"_VMS","activityKey-->"+activityKey );
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

 
	
}//end of AccessControlBean
