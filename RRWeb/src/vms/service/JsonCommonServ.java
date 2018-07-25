package vms.service;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.OrderedMap;
import org.apache.commons.collections.map.LinkedMap;

import vms.pojo.FuelType;
import vms.pojo.MakeCompany;
import vms.pojo.PlateType;
import vms.pojo.VehicleData;
import vms.pojo.VehicleType;

import beans.common.CommonBean;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import eth.logger.ETHLogger;

public class JsonCommonServ extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public JsonCommonServ() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out
				.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
String dbConnVar = "";
try 
{
	request.setCharacterEncoding("UTF-8");
	response.setContentType("application/json; charset=UTF8");
	HttpSession session = request.getSession(false);
	String action = "";
	PrintWriter out = response.getWriter();
	Gson gson = new Gson();
	
	if(session.getAttribute("dbConnVar")!=null)
	{
		dbConnVar=session.getAttribute("dbConnVar").toString();
	}
	String loginid ="";
	if(session.getAttribute("loginid")!=null){
		loginid = (String)session.getAttribute("loginid");
	}
	if(request.getParameter("action")!=null && request.getParameter("action")!="")
	{
		action=request.getParameter("action");
	}
	
	String memCacheKey = (dbConnVar+"_"+loginid+"_"+action).toUpperCase();
	
	if(action.equalsIgnoreCase("getPlateTypes"))
	{
		CommonBean bean=new CommonBean(dbConnVar);
		String result = "";
		
			ArrayList<PlateType> output = bean.getPlateTypes(dbConnVar);
			
			Gson gson1 = new Gson();
			JsonElement element = gson.toJsonTree(output, new TypeToken<List<PlateType>>() {}.getType());

			JsonArray jsonArray = element.getAsJsonArray();
			response.setContentType("application/json");
			response.getWriter().print(jsonArray);
			
			
	//	ETHLogger.log(dbConnVar+"_VMS","getPlateTypes = "+jsonArray.toString());
		//out.print(result.toString());
	}
	else if(action.equalsIgnoreCase("getVehicleTypes"))
	{
		CommonBean bean=new CommonBean(dbConnVar);
		String result = "";
		
			ArrayList<VehicleType> output = bean.getVehicleTypes(dbConnVar);
			
			Gson gson1 = new Gson();
			JsonElement element = gson.toJsonTree(output, new TypeToken<List<VehicleType>>() {}.getType());

			JsonArray jsonArray = element.getAsJsonArray();
			
			response.setContentType("application/json");
			response.getWriter().print(jsonArray);
			
			
	//	ETHLogger.log(dbConnVar+"_VMS","getVehicleTypes = "+jsonArray.toString());
		//out.print(result.toString());
	}
	else if(action.equalsIgnoreCase("getFuelTypes"))
	{
		CommonBean bean=new CommonBean(dbConnVar);
		String result = "";
		
			ArrayList<FuelType> output = bean.getFuelTypes(dbConnVar);
			
			Gson gson1 = new Gson();
			JsonElement element = gson.toJsonTree(output, new TypeToken<List<FuelType>>() {}.getType());

			JsonArray jsonArray = element.getAsJsonArray();
			response.setContentType("application/json");
			response.getWriter().print(jsonArray);
			
			
	//	ETHLogger.log(dbConnVar+"_VMS","getFuelTypes = "+jsonArray.toString());
		//out.print(result.toString());
	}
	else if(action.equalsIgnoreCase("getMakeCompany"))
	{
		CommonBean bean=new CommonBean(dbConnVar);
		String result = "";
		
			ArrayList<MakeCompany> output = bean.getMakeCompany(dbConnVar);
			
			Gson gson1 = new Gson();
			JsonElement element = gson.toJsonTree(output, new TypeToken<List<MakeCompany>>() {}.getType());

			JsonArray jsonArray = element.getAsJsonArray();
			response.setContentType("application/json");
			response.getWriter().print(jsonArray);
			
			
	//	ETHLogger.log(dbConnVar+"_VMS","getMakeCompany = "+jsonArray.toString());
		//out.print(result.toString());
	}
	else if(action.equalsIgnoreCase("getVehicleData"))
	{
		CommonBean bean=new CommonBean(dbConnVar);
		String result = "";
		
			ArrayList<VehicleData> output = bean.getVehicleData(dbConnVar);
		
			Gson gson1 = new Gson();
			JsonElement element = gson.toJsonTree(output, new TypeToken<List<VehicleData>>() {}.getType());

			JsonArray jsonArray = element.getAsJsonArray();
			response.setContentType("application/json");
			response.getWriter().print(jsonArray);
			
			
		//ETHLogger.log(dbConnVar+"_VMS","getVehicleData = "+jsonArray.toString());
		//out.print(result.toString());
	}
	else if(action.equalsIgnoreCase("getExpenseAccounts"))
	{
		CommonBean bean=new CommonBean(dbConnVar);
		String result = "";
		int company_id=1;
		int statement_code2=22;
		
		JsonArray jsonArray= bean.getExpenseAccounts(dbConnVar, company_id, statement_code2);
		 			
	
					response.setContentType("application/json");
					response.getWriter().print(jsonArray);
			
			
		//ETHLogger.log(dbConnVar+"_VMS","getVehicleData = "+jsonArray.toString());
		//out.print(result.toString());
	}
	else if(action.equalsIgnoreCase("checkVehicleRegNo"))
	{
		CommonBean bean=new CommonBean(dbConnVar);
			
		ArrayList<VehicleData> output = bean.getVehicleByRegNo(dbConnVar,request.getParameter("vehicleRegNo"));
		
		Gson gson1 = new Gson();
		JsonElement element = gson.toJsonTree(output, new TypeToken<List<VehicleData>>() {}.getType());

		JsonArray jsonArray = element.getAsJsonArray();
		response.setContentType("application/json");
		response.getWriter().print(jsonArray);
			
			
		//ETHLogger.log(dbConnVar+"_VMS","getVehicleByRegNo = "+jsonArray.toString());
		//out.print(result.toString());
	}
	else if(action.equalsIgnoreCase("getMulkiyaTypes"))
	{
		CommonBean bean=new CommonBean(dbConnVar);
		
		
		JsonArray jsonArray= bean.getMulkiyaTypes(dbConnVar);
		
		response.setContentType("application/json");
		response.getWriter().print(jsonArray);
		//ETHLogger.log(dbConnVar+"_VMS","getMulkiyaTypes = "+jsonArray.toString());
		
	}
	else if(action.equalsIgnoreCase("getEmirates"))
	{
		CommonBean bean=new CommonBean(dbConnVar);
		
		JsonArray jsonArray= bean.getEmirates(dbConnVar);
		
		response.setContentType("application/json");
		response.getWriter().print(jsonArray);
		//ETHLogger.log(dbConnVar+"_VMS","getMulkiyaTypes = "+jsonArray.toString());
		
	}
	else if(action.equalsIgnoreCase("getFormNo"))
	{
		
		String prefix="",acadId="";
		CommonBean bean=new CommonBean(dbConnVar);
		
		 acadId=session.getAttribute("academicYearId").toString();;
		if(request.getParameter("prefix")!=null && request.getParameter("prefix")!="")
		{
			prefix=request.getParameter("prefix");
		}
		//ETHLogger.log(dbConnVar + "_VMS",acadId+"/"+prefix);
		String formNo= bean.getNextFormNo(dbConnVar,acadId,prefix);
		
		response.setContentType("text");
		response.getWriter().print(formNo);
		
		//ETHLogger.log(dbConnVar+"_VMS","formNo========"+formNo);
		
	}
	else if(action.equalsIgnoreCase("getVendors"))
	{
		CommonBean bean=new CommonBean(dbConnVar);
		
		
		JsonArray jsonArray= bean.getVendors(dbConnVar);
		 			
	    response.setContentType("application/json");
		response.getWriter().print(jsonArray);
			
			
		//ETHLogger.log(dbConnVar+"_VMS","getVehicleData = "+jsonArray.toString());
		//out.print(result.toString());
	}
	else if(action.equalsIgnoreCase("getVehicleDetailReportData"))
	{
		CommonBean bean=new CommonBean(dbConnVar);
		String result = "";
		
			JsonArray jsonArray = bean.getVehicleDetailReportData(dbConnVar);
		
			response.setContentType("application/json");
			response.getWriter().print(jsonArray);
			
			
		//ETHLogger.log(dbConnVar+"_VMS","getVehicleDetailReportData = "+jsonArray.toString());
		//out.print(result.toString());
	}
	else if(action.equalsIgnoreCase("getFuelDetailReportData"))
	{
		CommonBean bean=new CommonBean(dbConnVar);
		String result = "";
		
			JsonArray jsonArray = bean.getFuelDetailReportData(dbConnVar);
		
			response.setContentType("application/json");
			response.getWriter().print(jsonArray);
			
			
		//ETHLogger.log(dbConnVar+"_VMS","getFuelDetailReportData = "+jsonArray.toString());
		//out.print(result.toString());
	}
	else if(action.equalsIgnoreCase("getAccidentDetailReportData"))
	{
		CommonBean bean=new CommonBean(dbConnVar);
		String result = "";
		
			JsonArray jsonArray = bean.getAccidentDetailReportData(dbConnVar);
		
			response.setContentType("application/json");
			response.getWriter().print(jsonArray);
			
			
		//ETHLogger.log(dbConnVar+"_VMS","getAccidentDetailReportData = "+jsonArray.toString());
		//out.print(result.toString());
	}
	else if(action.equalsIgnoreCase("getServiceDetailReportData"))
	{
		CommonBean bean=new CommonBean(dbConnVar);
		String result = "";
		
			JsonArray jsonArray = bean.getServiceDetailReportData(dbConnVar);
		
			response.setContentType("application/json");
			response.getWriter().print(jsonArray);
			
			
		//ETHLogger.log(dbConnVar+"_VMS","getServiceDetailReportData = "+jsonArray.toString());
		//out.print(result.toString());
	}
	else if(action.equalsIgnoreCase("getInsuranceDetailReportData"))
	{
		CommonBean bean=new CommonBean(dbConnVar);
		String result = "";
		
			JsonArray jsonArray = bean.getInsuranceDetailReportData(dbConnVar);
		
			response.setContentType("application/json");
			response.getWriter().print(jsonArray);
			
			
		//ETHLogger.log(dbConnVar+"_VMS","getInsuranceDetailReportData = "+jsonArray.toString());
		//out.print(result.toString());
	}
	else if(action.equalsIgnoreCase("getVehicleRegistrationDetailReportData"))
	{
		CommonBean bean=new CommonBean(dbConnVar);
		String result = "";
		
			JsonArray jsonArray = bean.getVehicleRegistrationDetailReportData(dbConnVar);
		
			response.setContentType("application/json");
			response.getWriter().print(jsonArray);
			
			
		//ETHLogger.log(dbConnVar+"_VMS","getVehicleRegistrationDetailReportData = "+jsonArray.toString());
		//out.print(result.toString());
	}
	else if(action.equalsIgnoreCase("getRepairDetailReportData"))
	{
		CommonBean bean=new CommonBean(dbConnVar);
		String result = "";
		
			JsonArray jsonArray = bean.getRepairDetailReportData(dbConnVar);
		
			response.setContentType("application/json");
			response.getWriter().print(jsonArray);
			
			
		//ETHLogger.log(dbConnVar+"_VMS","getRepairDetailReportData = "+jsonArray.toString());
		//out.print(result.toString());
	}
	else if(action.equalsIgnoreCase("isVehicleRegNoExist"))
	{
		CommonBean bean=new CommonBean(dbConnVar);
		String result = "";
		String newVehicleRegNo="";
		if(request.getParameter("vehicleRegNo")!=null && request.getParameter("vehicleRegNo")!="")
		{
			newVehicleRegNo=request.getParameter("vehicleRegNo");
			ETHLogger.log(dbConnVar+"_VMS","newVehicleRegNo = "+newVehicleRegNo);
		}
				result = bean.isVehicleRegNoExist(dbConnVar,newVehicleRegNo);
				ETHLogger.log(dbConnVar+"_VMS","newVehicleRegNo--result = "+result);
				response.setContentType("text");
				response.getWriter().print(result);
	 		
		//ETHLogger.log(dbConnVar+"_VMS","getRepairDetailReportData = "+jsonArray.toString());
		//out.print(result.toString());
	}
	else
	{
		response.setStatus(204); 
	}

}catch (Exception e) 
{
	//ETHLogger.log(dbConnVar+"_VMS",e);
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
