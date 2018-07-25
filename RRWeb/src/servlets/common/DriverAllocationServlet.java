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

public class DriverAllocationServlet extends HttpServlet {

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
		String msgTitle=null;
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
       
		String vehicleId="",driverName="",mobileNo="",fromDate="",toDate="",vehicleRegNo="",token="\ub006";
		 
		 	if(req.getParameter("vehicleRegNo")!=null)
			{
		 		vehicleRegNo=req.getParameter("vehicleRegNo");
			}
		 
		 	if(req.getParameter("vehicleId")!=null)
			{
		 		vehicleId=req.getParameter("vehicleId");
		 		
			}
		 	
		 	//ETHLogger.log(dbConnVar,"--------------------------->"+vehicleId);
			if(req.getParameter("driverName")!=null)
			{
				driverName=req.getParameter("driverName");
			}
			
			//ETHLogger.log(dbConnVar, driverName);  
			
			
			if(req.getParameter("mobileNo")!=null)
			{
				mobileNo=req.getParameter("mobileNo");
			}
			
			if(req.getParameter("fromDate")!=null)
			{
				fromDate=req.getParameter("fromDate");
			}
			
			
			if(req.getParameter("toDate")!=null)
			{
				toDate=req.getParameter("toDate");
			}
			  
			
			 
			 
			
			String data= vehicleId+"\ub006"+vehicleRegNo+"\ub006"+driverName+"\ub006"+mobileNo;
			
			//ETHLogger.log(dbConnVar,"data-->  " +  data);
			
			int vehicleMasterInsertion=0; 
			
			 vehicleMasterInsertion=bean.insertDriverAllocationDetails(data,token);
			 
			if(vehicleMasterInsertion==1){
				msg="Driver Allocated Successfully";
				msgTitle="Success!";
			}else if(vehicleMasterInsertion==2){
				msg="Driver Information Updated";
				msgTitle="Success!";
			}else{
				msg="Unable To Allocate Driver ";
				msgTitle="ERROR!";
			}  
		
			session.setAttribute("message", msg);
			session.setAttribute("messageTitle", msgTitle);
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
