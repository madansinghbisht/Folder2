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

public class RepairEntryServlet extends HttpServlet {
	 public void doPost(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {

	String dbConnVar="";
	 
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
 
	String  repairNo="",repairDate="",repairVehicleRegeNo="",repairVehicleId="",repairLastMeterReading="0",repairStatus="",repairInvoiceNo="",repairInvoiceDate="",complaintsType="",complaintsDetails="",repairDetails="",repairRemark="",repairWorkshop="",
	repairCost="0",taxAmount="0",quotationAmount="0",repairVendorName=""; 
	String token="\ub006";
	 
	String loginid="";
	  
		 if(session.getAttribute("loginid")!=null)
		 {
			loginid=session.getAttribute("loginid").toString();
		 }
	  
	    if(req.getParameter("repairNo")!=null && !req.getParameter("repairNo").equalsIgnoreCase("") )
		{
	    	repairNo=req.getParameter("repairNo");
		}
	 	
		if(req.getParameter("repairDate")!=null && !req.getParameter("repairDate").equalsIgnoreCase(""))
		{
			repairDate=req.getParameter("repairDate");
		}
		
		if(req.getParameter("vehicleRegNo")!=null && !req.getParameter("vehicleRegNo").equalsIgnoreCase(""))
		{
			repairVehicleRegeNo=req.getParameter("vehicleRegNo");
		}
		
		if(req.getParameter("vehicleId")!=null && !req.getParameter("vehicleId").equalsIgnoreCase(""))
		{
			repairVehicleId=req.getParameter("vehicleId");
		}
		
		if(req.getParameter("repairLastMeterReading")!=null && !req.getParameter("repairLastMeterReading").equalsIgnoreCase(""))
		{
			repairLastMeterReading=req.getParameter("repairLastMeterReading");
		}else
		{
			 
			repairLastMeterReading="0"; 
		} 
					
		if(req.getParameter("repairStatus")!=null && !req.getParameter("repairStatus").equalsIgnoreCase(""))
		{
			repairStatus=req.getParameter("repairStatus");
		}
		   
	
		if(req.getParameter("repairInvoiceNo")!=null && !req.getParameter("repairInvoiceNo").equalsIgnoreCase(""))
		{
			repairInvoiceNo=req.getParameter("repairInvoiceNo");
		}
		 
			 
		if(req.getParameter("repairInvoiceDate")!=null && !req.getParameter("repairInvoiceDate").equalsIgnoreCase(""))
		{
			repairInvoiceDate=req.getParameter("repairInvoiceDate");
		}
		
		if(req.getParameter("complaintsType")!=null && !req.getParameter("complaintsType").equalsIgnoreCase(""))
		{
			complaintsType=req.getParameter("complaintsType");
		}
					
		if(req.getParameter("complaintsDetails")!=null && !req.getParameter("complaintsDetails").equalsIgnoreCase(""))
		{
			complaintsDetails=req.getParameter("complaintsDetails");
		}
					
		if(req.getParameter("repairDetails")!=null && !req.getParameter("repairDetails").equalsIgnoreCase(""))
		{
			repairDetails=req.getParameter("repairDetails");
		}
					
		if(req.getParameter("repairRemark")!=null && !req.getParameter("repairRemark").equalsIgnoreCase(""))
		{
			repairRemark=req.getParameter("repairRemark");
		}
		
		if(req.getParameter("repairWorkshop")!=null && !req.getParameter("repairWorkshop").equalsIgnoreCase(""))
		{
			repairWorkshop=req.getParameter("repairWorkshop");
		}
		 
		if(req.getParameter("repairCost")!=null && !req.getParameter("repairCost").equalsIgnoreCase(""))
		{
			repairCost=req.getParameter("repairCost");
		}else
		{
			 
			repairCost="0"; 
		} 
		 
		if(req.getParameter("taxAmount")!=null && !req.getParameter("taxAmount").equalsIgnoreCase(""))
		{
			taxAmount=req.getParameter("taxAmount");
		}else
		{
			 
			taxAmount="0"; 
		} 
		 	
		
		if(req.getParameter("quotationAmount")!=null && !req.getParameter("quotationAmount").equalsIgnoreCase(""))
		{
			quotationAmount=req.getParameter("quotationAmount");
		}else
		{
			 
			quotationAmount="0"; 
		}
		
		if(req.getParameter("repairVendorName")!=null && !req.getParameter("repairVendorName").equalsIgnoreCase(""))
		{
			repairVendorName=req.getParameter("repairVendorName");
		}
		  
		String insertUpdateFlag="I"; 
		if(req.getParameter("insertUpdateFlag")!=null && !req.getParameter("insertUpdateFlag").equalsIgnoreCase("")) 
		{
			insertUpdateFlag=req.getParameter("insertUpdateFlag");
		}
		
		 
		String data= repairNo+"\ub006"+repairDate+"\ub006"+repairVehicleRegeNo+"\ub006"+repairVehicleId+"\ub006"+repairLastMeterReading+"\ub006"
	                    	+repairStatus+"\ub006"+repairInvoiceNo+"\ub006"+repairInvoiceDate+"\ub006"+complaintsType+"\ub006"+complaintsDetails+"\ub006"+repairDetails+"\ub006"+repairRemark+"\ub006"+repairWorkshop+"\ub006"+
			                repairCost+"\ub006"+taxAmount+"\ub006"+quotationAmount+"\ub006"+repairVendorName+"\ub006"+loginid; 
		  
		//ETHLogger.log(dbConnVar+"_VMS","Repair Entry data"+data);     
		int vehicleMasterInsertion=0;  
		  
		 vehicleMasterInsertion=bean.insertRepairEntryDetails(data,insertUpdateFlag,token);    
		 //ETHLogger.log(dbConnVar+"_VMS","vehicleMasterInsertion-->"+vehicleMasterInsertion);
		if(vehicleMasterInsertion==1){
			
			msg="Repair Data Inserted Successfully"; 
			
		}
		else if(vehicleMasterInsertion==2){
			
			msg="Repair Data Updated Successfully"; 
		}
		else if(vehicleMasterInsertion==3){
			
				msg="Repair Data Deleted Successfully"; 
				session.setAttribute("message", msg);
				res.getWriter().write("<html><head></head><body>"+msg+"</body></html>");
				res.getWriter().close();
		
		}else{
			msg="Error In Repair Data Insertion/Updation "; 
		}  
	
		session.setAttribute("message", msg);
		 
		 
		if( insertUpdateFlag.equalsIgnoreCase("I"))
		{
			res.sendRedirect(req.getContextPath()+"/form/jsp_master/vms_VehicleMaster.jsp"); 
		}
		else if(insertUpdateFlag.equalsIgnoreCase("U"))
		{
			res.sendRedirect(req.getContextPath()+"/form/jsp_master/RepairEntryPostToFinance.jsp"); 
		}
		
		
		
	
	
	}catch(Exception e){
		ETHLogger.log(dbConnVar,e);
	}
	
}
 
}
 