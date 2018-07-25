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

public class FuelReadingServlet extends HttpServlet {

	
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)	throws ServletException, IOException {
			
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
			
			String token="\ub006",fuelCardNo="",fuelDate="",vehicleRegNo="",previousKilo="",ltrsGln="",currentKilom="",ltrsGlnRate="",fuelAccount="",fuelAmount="",fuelVat="",remark="",invoiceNo="",invoiceDate="",vehicleId="",fuelReadingNo=""; 
			String loginid="";
			  
			 if(session.getAttribute("loginid")!=null)
			 {
				loginid=session.getAttribute("loginid").toString();
			 }
			 
				if(req.getParameter("fuelCardNo")!=null && !req.getParameter("fuelCardNo").equalsIgnoreCase(""))
				{
					fuelCardNo=req.getParameter("fuelCardNo");
				}
				
				if(req.getParameter("fuelDate")!=null && !req.getParameter("fuelDate").equalsIgnoreCase("") )
				{
					fuelDate=req.getParameter("fuelDate");
				}
				
				if(req.getParameter("vehicleRegNo")!=null && !req.getParameter("vehicleRegNo").equalsIgnoreCase(""))
				{
					vehicleRegNo=req.getParameter("vehicleRegNo");
				}
							
				if(req.getParameter("previousKilo")!=null && !req.getParameter("previousKilo").equalsIgnoreCase(""))
				{
					previousKilo=req.getParameter("previousKilo");
				}else
				{
					 
					previousKilo="0"; 
				}
				
				if(req.getParameter("ltrsGln")!=null && !req.getParameter("ltrsGln").equalsIgnoreCase(""))
				{
					ltrsGln=req.getParameter("ltrsGln");
				}else
				{
					 
					ltrsGln="0"; 
				}
				
				if(req.getParameter("currentKilom")!=null && !req.getParameter("currentKilom").equalsIgnoreCase(""))
				{
					currentKilom=req.getParameter("currentKilom");
				}else
				{
					 
					currentKilom="0"; 
				}
				
				
				if(req.getParameter("ltrsGlnRate")!=null && !req.getParameter("ltrsGlnRate").equalsIgnoreCase(""))
				{
					ltrsGlnRate=req.getParameter("ltrsGlnRate");
				}else
				{
					 
					ltrsGlnRate="0"; 
				}
							
				if(req.getParameter("fuelAccount")!=null && !req.getParameter("fuelAccount").equalsIgnoreCase("") )
				{
					fuelAccount=req.getParameter("fuelAccount");
				}
							
				if(req.getParameter("fuelAmount")!=null && !req.getParameter("fuelAmount").equalsIgnoreCase(""))
				{
					fuelAmount=req.getParameter("fuelAmount");
				}else
				{
					 
					fuelAmount="0"; 
				}
							
				if(req.getParameter("fuelVat")!=null && !req.getParameter("fuelVat").equalsIgnoreCase(""))
				{
					fuelVat=req.getParameter("fuelVat");
				}else
				{
					 
					fuelVat="0"; 
				}
				
				if(req.getParameter("remark")!=null && !req.getParameter("remark").equalsIgnoreCase(""))
				{
					remark=req.getParameter("remark");
				}
				
				if(req.getParameter("invoiceNo")!=null && !req.getParameter("invoiceNo").equalsIgnoreCase(""))
				{
					invoiceNo=req.getParameter("invoiceNo");
				}
				
				if(req.getParameter("invoiceDate")!=null && !req.getParameter("invoiceDate").equalsIgnoreCase(""))
				{
					invoiceDate=req.getParameter("invoiceDate");
				}
				 			 
				  
				String insertUpdateFlag="I"; 
				if(req.getParameter("insertUpdateFlag")!=null && !req.getParameter("insertUpdateFlag").equalsIgnoreCase("")) 
				{
					insertUpdateFlag=req.getParameter("insertUpdateFlag");
				}
				
				if(req.getParameter("vehicleId")!=null && !req.getParameter("vehicleId").equalsIgnoreCase(""))
				{
					vehicleId=req.getParameter("vehicleId");
				}
				  
				if(req.getParameter("fuelReadingNo")!=null && !req.getParameter("fuelReadingNo").equalsIgnoreCase(""))
				{
					fuelReadingNo=req.getParameter("fuelReadingNo");
				}
				  
				 //ETHLogger.log(dbConnVar, "vehicleId--###-> "+fuelReadingNo); 
				 
				String data= vehicleRegNo+"\ub006"+fuelReadingNo+"\ub006"+fuelDate+"\ub006"+previousKilo+"\ub006"+ltrsGln+"\ub006"+currentKilom+"\ub006"+ltrsGlnRate+"\ub006"+fuelAccount+"\ub006"+fuelAmount+"\ub006"+fuelVat+"\ub006"+remark+"\ub006"+invoiceNo+"\ub006"+invoiceDate+"\ub006"+vehicleId+"\ub006"+loginid;
				
				//ETHLogger.log(dbConnVar,"data"+data);     
				
				int vehicleMasterInsertion=0; 
				
				 vehicleMasterInsertion=bean.insertFuelReadingDetails(data,insertUpdateFlag,token);
   
				 if(vehicleMasterInsertion==1){
						
						msg="Fuel Reading Data Inserted Successfully"; 
						
					}
					else if(vehicleMasterInsertion==2){
						
						msg="Fuel Reading Data Updated Successfully"; 
					}
					else if(vehicleMasterInsertion==3){
						
							msg="Fuel Reading Data Deleted Successfully"; 
							session.setAttribute("message", msg);
							res.getWriter().write("<html><head></head><body>"+msg+"</body></html>");
							res.getWriter().close();
					
					}else
					{
						msg="Error In Fuel Reading Data Insertion/Updation "; 
					} 
			
				
				
				session.setAttribute("message", msg);
				
				if( insertUpdateFlag.equalsIgnoreCase("I"))
				{
					res.sendRedirect(req.getContextPath()+"/form/jsp_master/vms_VehicleMaster.jsp"); 
				}
				else if(insertUpdateFlag.equalsIgnoreCase("U"))
				{
					res.sendRedirect(req.getContextPath()+"/form/jsp_master/FuelReadingPostToFinance.jsp"); 
				}
				
				
			
			}catch(Exception e){
				ETHLogger.log(dbConnVar,e);
			}
			
}

}