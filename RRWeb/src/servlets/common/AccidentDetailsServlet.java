package servlets.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.PropertyResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.common.CommonBean;
import eth.factory.ConnectionFactory;
import eth.i18n.ETHi18n;
import eth.logger.ETHLogger;

public class AccidentDetailsServlet extends HttpServlet {

	 public void doPost(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {

	String dbConnVar="";
	Connection con = null;
	ConnectionFactory ConnectionFactory = null;
	PreparedStatement stmt = null;
	Statement selectstmt = null;
	ResultSet rs = null;
	String str = null;
	String msg = null;
	ETHi18n  i18n =null; 
	 
	try
	{
		 
	req.setCharacterEncoding("UTF-8");
	res.setContentType("text/html; charset=UTF-8");
	HttpSession session = req.getSession();
	
	
	if(session.getAttribute("dbConnVar")!=null)
	 {
		dbConnVar=session.getAttribute("dbConnVar").toString();
	 }
	 
	ConnectionFactory = new ConnectionFactory(dbConnVar);
	
	
	String localLang="";
	 
	if(req.getParameter("localLang")!=null && !req.getParameter("localLang").equalsIgnoreCase("null") && !req.getParameter("localLang").equalsIgnoreCase(""))
	{
		localLang=req.getParameter("localLang");
		session.setAttribute("localLang",localLang);
	}
	else 
	{
		if(session!=null && session.getAttribute("localLang")!=null && !session.getAttribute("localLang").toString().equalsIgnoreCase("null") && !session.getAttribute("localLang").toString().equalsIgnoreCase(""))
		{
			localLang=session.getAttribute("localLang").toString();
		}
	}
	 
	i18n = new ETHi18n(dbConnVar,true,localLang);
	PropertyResourceBundle prb = i18n.getETHi18nVar("ParentPortal");
	 
	CommonBean bean=new CommonBean(dbConnVar);

	String  accidentNo="",accidentDate="",accidentVehicleNo="",accidentVehicleId="",vehicleRegisterNo="",driverName="",mobileNo="";
	String locationAccidentDetails="",emirate="",accidentCausedBy="",damagaeOccured="",accidentRemark="",vehicleId="",reasonOfAccident="";  
	  
	String loginid="" , insertUpdateFlag="I",timeOfAccident="",token="\ub006"; 
	  
		 if(session.getAttribute("loginid")!=null)
		 {
			loginid=session.getAttribute("loginid").toString();
		 }
	 
	   	 
	    if(req.getParameter("accidentNo")!=null)
		{
	    	accidentNo=req.getParameter("accidentNo");
		}
		
		if(req.getParameter("accidentDate")!=null)
		{
			accidentDate=req.getParameter("accidentDate");
		}
		
		if(req.getParameter("accidentVehicleNo")!=null)
		{
			accidentVehicleNo=req.getParameter("accidentVehicleNo");
		}
		
		if(req.getParameter("vehicleId")!=null)
		{
			accidentVehicleId=req.getParameter("vehicleId");
		}
		
		if(req.getParameter("vehicleRegisterNo")!=null)
		{
			vehicleRegisterNo=req.getParameter("vehicleRegisterNo");
		}
					
		if(req.getParameter("driverName")!=null)
		{
			driverName=req.getParameter("driverName");
		}
		
		if(req.getParameter("locationAccidentDetails")!=null)
		{
			locationAccidentDetails=req.getParameter("locationAccidentDetails");
		}
		 
			 
		if(req.getParameter("emirate")!=null)
		{
			emirate=req.getParameter("emirate");
		}
		
		if(req.getParameter("accidentCausedBy")!=null)
		{
			accidentCausedBy=req.getParameter("accidentCausedBy");
		}
					
		if(req.getParameter("damagaeOccured")!=null)
		{
			damagaeOccured=req.getParameter("damagaeOccured");
		}
					
		if(req.getParameter("accidentRemark")!=null)
		{
			accidentRemark=req.getParameter("accidentRemark");
		}
		    
		 
		
		if(req.getParameter("insertUpdateFlag")!=null) 
		{
			insertUpdateFlag=req.getParameter("insertUpdateFlag");
		}
		
		if(req.getParameter("timeOfAccident")!=null) 
		{
			timeOfAccident=req.getParameter("timeOfAccident");
		}
		
		if(req.getParameter("reasonOfAccident")!=null) 
		{
			reasonOfAccident=req.getParameter("reasonOfAccident");
		}
		
		
		 
		 
		String data= accidentNo+"\ub006"+accidentDate+"\ub006"+accidentVehicleId+"\ub006"+driverName+"\ub006"+locationAccidentDetails+"\ub006"+emirate
		+"\ub006"+accidentCausedBy+"\ub006"+damagaeOccured+"\ub006"+accidentRemark+"\ub006"+timeOfAccident+"\ub006"+reasonOfAccident;
		 
		//ETHLogger.log(dbConnVar,"data"+data);     
		int vehicleMasterInsertion=0; 
		 
		 vehicleMasterInsertion=bean.insertAccidentDetails(data,token);      
		if(vehicleMasterInsertion>0){
			
			msg="Accident Data Inserted Successfully"; 
		}else{ 
			msg="Error In Accident Insertion "; 
		}  
	
		session.setAttribute("message", msg);
		 
		res.sendRedirect(req.getContextPath()+"/form/jsp_master/vms_VehicleMaster.jsp"); 
	
	
	}catch(Exception e)
	{
	
	}
	
}

/**
* Initialization of the servlet. <br>
*
* @throws ServletException if an error occure
*/
public void init() throws ServletException {
	// Put your code here
}

}
