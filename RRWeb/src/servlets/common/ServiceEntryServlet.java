package servlets.common;

import java.io.IOException;
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

public class ServiceEntryServlet extends HttpServlet {

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
	String communication_success = prb.getString("communication_success");
	
	CommonBean bean=new CommonBean(dbConnVar);

	String serviceNo="",serviceDate="",lastMeterReading="",nextMeterReading="",serviceDetails="",serviceRemark="",serviceCost="",taxAmount="",vendorName="",
	acChangeHrs="",serviceLocation="",vehicleStatus="",serviceInvoiceNo="",serviceInvoiceDate="",vehicleRegeNo="",vehicleId=""; 
	String token="\ub006";
	 int lastMeterReadingParse=0;
	String loginid="";
	    
	 	 if(session.getAttribute("loginid")!=null)
		 {
			loginid=session.getAttribute("loginid").toString();
		 }
	  
	    if(req.getParameter("serviceNo")!=null && !req.getParameter("serviceNo").equalsIgnoreCase(""))
		{
			serviceNo=req.getParameter("serviceNo");
		}
		
		if(req.getParameter("serviceDate")!=null && !req.getParameter("serviceDate").equalsIgnoreCase(""))
		{
			serviceDate=req.getParameter("serviceDate");
		}
		
		if(req.getParameter("vehicleRegeNo")!=null && !req.getParameter("vehicleRegeNo").equalsIgnoreCase(""))
		{
			vehicleRegeNo=req.getParameter("vehicleRegeNo");
		}
		
		if(req.getParameter("vehicleId")!=null && !req.getParameter("vehicleId").equalsIgnoreCase(""))
		{
			vehicleId=req.getParameter("vehicleId");
		}
		 
		if(req.getParameter("lastMeterReading")!=null && !req.getParameter("lastMeterReading").equalsIgnoreCase("") )
		{
			lastMeterReading= req.getParameter("lastMeterReading");
		}else
		{
			 
			lastMeterReading="0"; 
		} 
		 			
		if(req.getParameter("nextMeterReading")!=null && !req.getParameter("nextMeterReading").equalsIgnoreCase(""))
		{
			nextMeterReading=req.getParameter("nextMeterReading");
		}else
		{
			 
			nextMeterReading="0"; 
		}
		
		if(req.getParameter("serviceDetails")!=null && !req.getParameter("serviceDetails").equalsIgnoreCase("") )
		{
			serviceDetails=req.getParameter("serviceDetails");
		}
		 
		 if(req.getParameter("serviceRemark")!=null && !req.getParameter("serviceRemark").equalsIgnoreCase(""))
		{
			serviceRemark=req.getParameter("serviceRemark");
		}
		
		if(req.getParameter("serviceCost")!=null && !req.getParameter("serviceCost").equalsIgnoreCase(""))
		{
			serviceCost=req.getParameter("serviceCost");
		}else
		{
			 serviceCost="0"; 
		}
					
		if(req.getParameter("taxAmount")!=null && !req.getParameter("taxAmount").equalsIgnoreCase(""))
		{
			taxAmount=req.getParameter("taxAmount");
		}else
		{
			 
			taxAmount="0"; 
		}
					
		if(req.getParameter("vendorName" )!=null && !req.getParameter("vendorName").equalsIgnoreCase(""))
		{
			vendorName=req.getParameter("vendorName");
		}
					
		if(req.getParameter("acChangeHrs")!=null && !req.getParameter("acChangeHrs").equalsIgnoreCase(""))
		{
			acChangeHrs=req.getParameter("acChangeHrs");
		}
		
		if(req.getParameter("serviceLocation")!=null && !req.getParameter("serviceLocation").equalsIgnoreCase(""))
		{
			serviceLocation=req.getParameter("serviceLocation");
		}
		
		if(req.getParameter("vehicleStatus")!=null && !req.getParameter("vehicleStatus").equalsIgnoreCase(""))
		{
			vehicleStatus=req.getParameter("vehicleStatus");
		}
		 
		if(req.getParameter("serviceInvoiceNo")!=null && !req.getParameter("serviceInvoiceNo").equalsIgnoreCase(""))
		{
			serviceInvoiceNo=req.getParameter("serviceInvoiceNo");
		}
		 	
		 
		if(req.getParameter("serviceInvoiceDate")!=null && !req.getParameter("serviceInvoiceDate").equalsIgnoreCase(""))
		{
			serviceInvoiceDate=req.getParameter("serviceInvoiceDate");
		}
		  
		String insertUpdateFlag="I"; 
		if(req.getParameter("insertUpdateFlag")!=null && !req.getParameter("insertUpdateFlag").equalsIgnoreCase("")) 
		{
			insertUpdateFlag=req.getParameter("insertUpdateFlag");
		}
		
		  
		
		String data= serviceNo+"\ub006"+serviceDate+"\ub006"+vehicleRegeNo+"\ub006"+vehicleId+"\ub006"+lastMeterReading+"\ub006"+nextMeterReading+"\ub006"+serviceDetails+"\ub006"+serviceRemark+"\ub006"+serviceCost+"\ub006"+taxAmount+"\ub006"+vendorName+"\ub006"+acChangeHrs+"\ub006"+serviceLocation+"\ub006"+vehicleStatus+"\ub006"+serviceInvoiceNo+"\ub006"+serviceInvoiceDate+"\ub006"+loginid;
		 
		//ETHLogger.log(dbConnVar,"data"+data);     
		int vehicleMasterInsertion=0; 
		 
		 vehicleMasterInsertion=bean.insertServiceEntryDetails(data,insertUpdateFlag,token);      
		 	if(vehicleMasterInsertion==1){
				
				msg="Service Data Inserted Successfully"; 
				
			}
			else if(vehicleMasterInsertion==2){
				
				msg="Service Data Updated Successfully"; 
			}
			else if(vehicleMasterInsertion==3)
			{
				
					msg="Service Data Deleted Successfully"; 
					session.setAttribute("message", msg);
					res.getWriter().write("<html><head></head><body>"+msg+"</body></html>");
					res.getWriter().close();
			
			}
			else
			{
					msg="Error In Service  Data Insertion "; 
			}  
	
		session.setAttribute("message", msg);
		
		if( insertUpdateFlag.equalsIgnoreCase("I"))
		{
			res.sendRedirect(req.getContextPath()+"/form/jsp_master/vms_VehicleMaster.jsp"); 
		}
		else if(insertUpdateFlag.equalsIgnoreCase("U"))
		{
			res.sendRedirect(req.getContextPath()+"/form/jsp_master/ServiceEntryPostToFinance.jsp"); 
		}
		
	
	
	}catch(Exception e){
		ETHLogger.log(dbConnVar,e);
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
