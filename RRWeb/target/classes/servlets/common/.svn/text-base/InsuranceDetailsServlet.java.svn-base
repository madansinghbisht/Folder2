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

public class InsuranceDetailsServlet extends HttpServlet {

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

	String   vehicleRegistrationNo="",policyNo="",insuranceCompany="",insuranceIssueDate="",insuranceExpiryDate="",insuranceValue="",insuranceAmount="",modeOfPayment="",insuranceClass="",insuranceAgent=""; 
	String token="\ub006",vehicleId="";
	 
	String loginid="";
	  
		 if(session.getAttribute("loginid")!=null)
		 {
			loginid=session.getAttribute("loginid").toString();
		 }
	 
		 if( req.getParameter("vehicleId")!=null){
	         vehicleId=req.getParameter("vehicleId"); 
	       }
		 
	    	 
	    if(req.getParameter("vehicleRegistrationNo")!=null && !req.getParameter("vehicleRegistrationNo").equalsIgnoreCase("") )
		{
	    	vehicleRegistrationNo=req.getParameter("vehicleRegistrationNo");
		}
	 	
		if(req.getParameter("policyNo")!=null && !req.getParameter("policyNo").equalsIgnoreCase(""))
		{
			policyNo=req.getParameter("policyNo");
		}
		
		if(req.getParameter("insuranceCompany")!=null && !req.getParameter("insuranceCompany").equalsIgnoreCase(""))
		{
			insuranceCompany=req.getParameter("insuranceCompany");
		}
		
		if(req.getParameter("insuranceIssueDate")!=null && !req.getParameter("insuranceIssueDate").equalsIgnoreCase(""))
		{
			insuranceIssueDate=req.getParameter("insuranceIssueDate");
		}
		
		if(req.getParameter("insuranceExpiryDate")!=null && !req.getParameter("insuranceExpiryDate").equalsIgnoreCase(""))
		{
			insuranceExpiryDate=req.getParameter("insuranceExpiryDate");
		} 
					
		if(req.getParameter("insuranceValue")!=null && !req.getParameter("insuranceValue").equalsIgnoreCase(""))
		{
			insuranceValue=req.getParameter("insuranceValue");
		}
		   
	
		if(req.getParameter("insuranceAmount")!=null && !req.getParameter("insuranceAmount").equalsIgnoreCase(""))
		{
			insuranceAmount=req.getParameter("insuranceAmount");
		}else
		{
			 
			insuranceAmount="0"; 
		}
		 
			 
		if(req.getParameter("modeOfPayment")!=null && !req.getParameter("modeOfPayment").equalsIgnoreCase(""))
		{
			modeOfPayment=req.getParameter("modeOfPayment");
		}
		
		if(req.getParameter("insuranceClass")!=null && !req.getParameter("insuranceClass").equalsIgnoreCase(""))
		{
			insuranceClass=req.getParameter("insuranceClass");
		}


		if(req.getParameter("insuranceAgent")!=null && !req.getParameter("insuranceAgent").equalsIgnoreCase(""))
		{
			insuranceAgent=req.getParameter("insuranceAgent");
		}
		  
		String insertUpdateFlag="I"; 
		if(req.getParameter("insertUpdateFlag")!=null && !req.getParameter("insertUpdateFlag").equalsIgnoreCase("")) 
		{
			insertUpdateFlag=req.getParameter("insertUpdateFlag");
		}
		
		  
		String data=vehicleId+"\ub006"+vehicleRegistrationNo+"\ub006"+policyNo+"\ub006"+insuranceCompany+"\ub006"+insuranceIssueDate+"\ub006"+insuranceExpiryDate+"\ub006"+insuranceValue+"\ub006"+insuranceAmount+"\ub006"+modeOfPayment+"\ub006"+insuranceClass+"\ub006"+insuranceAgent; 
		  
		//ETHLogger.log(dbConnVar,"data"+data);     
		int vehicleMasterInsertion=0;  
		   
		 vehicleMasterInsertion=bean.insertVehiclInsuranceDetails(data,token,insertUpdateFlag);         
		if(vehicleMasterInsertion>0){
			
			msg="Vehicle Insurance Details Inserted Successfully"; 
		}else{
			msg="Error In Vehicle Insurance Details Insertion "; 
		}  
	
		session.setAttribute("message", msg);
		 
		res.sendRedirect(req.getContextPath()+"/form/jsp_master/vms_VehicleMaster.jsp"); 
	
	
	}catch(Exception e)
	{
	
	}
	
}

}
