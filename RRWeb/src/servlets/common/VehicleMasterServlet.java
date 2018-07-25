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

public class VehicleMasterServlet extends HttpServlet {

	
	
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
		String messageTitle=null;
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
		//ETHLogger.log(dbConnVar,"In my vehiclemaster Servlet");
		i18n = new ETHi18n(dbConnVar,true,localLang);
		PropertyResourceBundle prb = i18n.getETHi18nVar("ParentPortal");
		String communication_success = prb.getString("communication_success");
       
		String vehicleRegNo="",plateTypeId="",chassisNo="",engineNo="",trafficFileNo="",engineCapacity="",makeCompanyList="",model="",ivmsNo="",vehicleTypeList=""
			,ownerName="",fuelTypeList="",fuelCapacity="",fuelCardNo="",fuelLimit="",mileage="",expAccount="",seatingCapacity="",regExpDate="",contractExpDate="",hiredDate="",hiredRate="",offHiredDate="",contractRefNo="" ;
		String token="\ub006";
		
		vehicleRegNo=req.getParameter("vehicleRegNo")!=null && !req.getParameter("vehicleRegNo").equalsIgnoreCase("") ?req.getParameter("vehicleRegNo"):"";
		plateTypeId=req.getParameter("plateTypeId")!=null && !req.getParameter("plateTypeId").equalsIgnoreCase("") ?req.getParameter("plateTypeId"):"";
		chassisNo=req.getParameter("chassisNo")!=null && !req.getParameter("chassisNo").equalsIgnoreCase("") ?req.getParameter("chassisNo"):"";
		engineNo=req.getParameter("engineNo")!=null && !req.getParameter("engineNo").equalsIgnoreCase("") ?req.getParameter("engineNo"):"";
		
		trafficFileNo=req.getParameter("trafficFileNo")!=null && !req.getParameter("trafficFileNo").equalsIgnoreCase("") ?req.getParameter("trafficFileNo"):"";
		engineCapacity=req.getParameter("engineCapacity")!=null && !req.getParameter("engineCapacity").equalsIgnoreCase("") ?req.getParameter("engineCapacity"):"";
		makeCompanyList=req.getParameter("makeCompanyList")!=null && !req.getParameter("makeCompanyList").equalsIgnoreCase("") ?req.getParameter("makeCompanyList"):"-";
		model=req.getParameter("model")!=null && !req.getParameter("model").equalsIgnoreCase("") ?req.getParameter("model"):"";
		
		ivmsNo=req.getParameter("ivmsNo")!=null && !req.getParameter("ivmsNo").equalsIgnoreCase("") ?req.getParameter("ivmsNo"):"";
		vehicleTypeList=req.getParameter("vehicleTypeList")!=null && !req.getParameter("vehicleTypeList").equalsIgnoreCase("") ?req.getParameter("vehicleTypeList"):"";
		ownerName=req.getParameter("ownerName")!=null && !req.getParameter("ownerName").equalsIgnoreCase("") ?req.getParameter("ownerName"):"";
		fuelTypeList=req.getParameter("fuelTypeList")!=null && !req.getParameter("fuelTypeList").equalsIgnoreCase("") ?req.getParameter("fuelTypeList"):"";
		
		fuelCapacity=req.getParameter("fuelCapacity")!=null && !req.getParameter("fuelCapacity").equalsIgnoreCase("") ?req.getParameter("fuelCapacity"):"";
		fuelCardNo=req.getParameter("fuelCardNo")!=null && !req.getParameter("fuelCardNo").equalsIgnoreCase("") ?req.getParameter("fuelCardNo"):"";
		mileage=req.getParameter("mileage")!=null && !req.getParameter("mileage").equalsIgnoreCase("") ?req.getParameter("mileage"):"";
		fuelLimit=req.getParameter("fuelLimit")!=null && !req.getParameter("fuelLimit").equalsIgnoreCase("") ?req.getParameter("fuelLimit"):"";
			
		expAccount=req.getParameter("expAccount")!=null && !req.getParameter("expAccount").equalsIgnoreCase("") ?req.getParameter("expAccount"):"";
		seatingCapacity=req.getParameter("seatingCapacity")!=null && !req.getParameter("seatingCapacity").equalsIgnoreCase("") ?req.getParameter("seatingCapacity"):"";
		regExpDate=req.getParameter("regExpDate")!=null && !req.getParameter("regExpDate").equalsIgnoreCase("") ?req.getParameter("regExpDate"):"";
		contractExpDate=req.getParameter("contractExpDate")!=null && !req.getParameter("contractExpDate").equalsIgnoreCase("") ?req.getParameter("contractExpDate"):"";
		
		
		hiredDate=req.getParameter("hiredDate")!=null && !req.getParameter("hiredDate").equalsIgnoreCase("") ?req.getParameter("hiredDate"):"";
		hiredRate=req.getParameter("hiredRate")!=null && !req.getParameter("hiredRate").equalsIgnoreCase("") ?req.getParameter("hiredRate"):"";
		offHiredDate=req.getParameter("offHiredDate")!=null && !req.getParameter("offHiredDate").equalsIgnoreCase("") ?req.getParameter("offHiredDate"):"";
		
		
		contractRefNo=req.getParameter("contractRefNo")!=null && !req.getParameter("contractRefNo").equalsIgnoreCase("") ?req.getParameter("contractRefNo"):"";
		
			
		//contractRefNo = contractRefNo.substring(0, contractRefNo.indexOf('.'));
		
		
			
			String insertUpdateFlag="I";
			
			if(req.getParameter("insertUpdateFlag")!=null) 
			{
				insertUpdateFlag=req.getParameter("insertUpdateFlag");
			}
			 
			 
		
			
		 //ETHLogger.log(dbConnVar, "vehicleRegNo--servlet-> "+vehicleRegNo);
			 
			CommonBean bean=new CommonBean(dbConnVar);
			
			String data= vehicleRegNo+"\ub006"+plateTypeId+"\ub006"+chassisNo+"\ub006"+engineNo+"\ub006"+trafficFileNo+"\ub006"+engineCapacity+"\ub006"+makeCompanyList+"\ub006"+model+"\ub006"+ivmsNo+"\ub006"+vehicleTypeList+"\ub006"+ownerName+"\ub006"+fuelTypeList+"\ub006"+fuelCapacity+"\ub006"+fuelCardNo+"\ub006"+fuelLimit+"\ub006"+seatingCapacity+"\ub006"+mileage+"\ub006"+expAccount +"\ub006"+regExpDate+"\ub006"+contractExpDate+"\ub006"+hiredDate+"\ub006"+offHiredDate+"\ub006"+hiredRate+"\ub006"+contractRefNo;
			ETHLogger.log(dbConnVar+ "_VMS","data"+data); 
			
			int vehicleMasterInsertion=bean.insertVehicleDetails(data,insertUpdateFlag,token); 
			messageTitle="Alert";
			if(vehicleMasterInsertion==1){
				 
				msg="Vehicle Details Inserted/Updated Successfully."; 
				messageTitle="Success";
			
			}else if(vehicleMasterInsertion==2){
			
				msg="Vehicle Reg No Already Exists."; 
			
			
			}
			else if(vehicleMasterInsertion==4)
			{
				msg="Vehicle is mapped somewhere else, you cannot delete it.";
				session.setAttribute("message", msg);
				session.setAttribute("messageTitle", messageTitle);
				res.getWriter().write("<html><head></head><body>"+msg+"</body></html>");
				res.getWriter().close();
			}
			else if(vehicleMasterInsertion==3){
				msg="Vehicle Data Deleted Successfully";
				session.setAttribute("message", msg);
				session.setAttribute("messageTitle", messageTitle);
				res.getWriter().write("<html><head></head><body>"+msg+"</body></html>");
				res.getWriter().close();
			}
			else{
				msg = "Error in inserting Vehicle Details. ";
				messageTitle="Alert"; 
			} 
		
			session.setAttribute("message", msg);
			session.setAttribute("messageTitle", messageTitle);
			res.sendRedirect(req.getContextPath()+"/form/jsp_master/vms_VehicleMaster.jsp");
		
		
		}catch(Exception e)
		{
		 ETHLogger.log(dbConnVar+"_VMS", e);
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
