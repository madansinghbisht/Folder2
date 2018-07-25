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

public class VehicleRegistrationDetailsServlet extends HttpServlet {

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

	String  plateTypeNo="",mulkiyaType="",issuePlace="",mulkiyaRenewDate="",tcNo="",owner="",totalAmount="",mulkiyaExpDate="",procDate="",mulkiyaIssueDate=""; 
	String token="\ub006",vehicleId="";
	 
	String loginid="";
	  
		 if(session.getAttribute("loginid")!=null)
		 {
			loginid=session.getAttribute("loginid").toString();
		 }
	 
		 if( req.getParameter("vehicleId")!=null){
	         vehicleId=req.getParameter("vehicleId"); 
	       }
		 
	    
	    if(req.getParameter("plateTypeNo")!=null && !req.getParameter("plateTypeNo").equalsIgnoreCase("") )
		{
	    	plateTypeNo=req.getParameter("plateTypeNo");
		}
	 	
		if(req.getParameter("mulkiyaType")!=null && !req.getParameter("mulkiyaType").equalsIgnoreCase(""))
		{
			mulkiyaType=req.getParameter("mulkiyaType");
		}
		
		if(req.getParameter("issuePlace")!=null && !req.getParameter("issuePlace").equalsIgnoreCase(""))
		{
			issuePlace=req.getParameter("issuePlace");
		}
		
		if(req.getParameter("mulkiyaRenewDate")!=null && !req.getParameter("mulkiyaRenewDate").equalsIgnoreCase(""))
		{
			mulkiyaRenewDate=req.getParameter("mulkiyaRenewDate");
		}
		
		if(req.getParameter("tcNo")!=null && !req.getParameter("tcNo").equalsIgnoreCase(""))
		{
			tcNo=req.getParameter("tcNo");
		} 
					
		if(req.getParameter("owner")!=null && !req.getParameter("owner").equalsIgnoreCase(""))
		{
			owner=req.getParameter("owner");
		}
		   
	
		if(req.getParameter("totalAmount")!=null && !req.getParameter("totalAmount").equalsIgnoreCase(""))
		{
			totalAmount=req.getParameter("totalAmount");
		}else
		{
			 
			totalAmount="0"; 
		}
		 
			 
		if(req.getParameter("mulkiyaExpDate")!=null && !req.getParameter("mulkiyaExpDate").equalsIgnoreCase(""))
		{
			mulkiyaExpDate=req.getParameter("mulkiyaExpDate");
		}
		
		
		if(req.getParameter("procDate")!=null && !req.getParameter("procDate").equalsIgnoreCase(""))
		{
			procDate=req.getParameter("procDate");
		}
			
		if(req.getParameter("mulkiyaIssueDate")!=null && !req.getParameter("mulkiyaIssueDate").equalsIgnoreCase(""))
		{
			mulkiyaIssueDate=req.getParameter("mulkiyaIssueDate");
		}
		 
		  
		String insertUpdateFlag="I"; 
		if(req.getParameter("insertUpdateFlag")!=null && !req.getParameter("insertUpdateFlag").equalsIgnoreCase("")) 
		{
			insertUpdateFlag=req.getParameter("insertUpdateFlag");
		}
		
		  
		String data= vehicleId+"\ub006"+plateTypeNo+"\ub006"+mulkiyaType+"\ub006"+issuePlace+"\ub006"+mulkiyaRenewDate+"\ub006"+tcNo+"\ub006"+mulkiyaIssueDate+"\ub006"+owner+"\ub006"+totalAmount+"\ub006"+mulkiyaExpDate+"\ub006"+procDate; 
		  
		//ETHLogger.log(dbConnVar,"data"+data);     
		int vehicleMasterInsertion=0;  
		   
		 vehicleMasterInsertion=bean.insertVehiclRegistrationDetails(data,token,insertUpdateFlag);        
		if(vehicleMasterInsertion>0){
			
			msg="Vehicle Registration Details Inserted Successfully"; 
		}else{
			msg="Error In Vehicle Registration Details Insertion "; 
		}  
	
		session.setAttribute("message", msg);
		 
		res.sendRedirect(req.getContextPath()+"/form/jsp_master/vms_VehicleMaster.jsp"); 
	
	
	}catch(Exception e)
	{
	
	}
	
}

}
