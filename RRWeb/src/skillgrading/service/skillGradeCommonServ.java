package skillgrading.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vms.pojo.FuelType;
import vms.pojo.MakeCompany;
import vms.pojo.PlateType;
import vms.pojo.VehicleData;
import vms.pojo.VehicleType;
import beans.common.CommonBean;
import beans.common.SkillMasterBean;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import eth.logger.ETHLogger;

public class skillGradeCommonServ extends HttpServlet {

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
	
	if(action.equalsIgnoreCase("getClassInfo"))
		{
			int academicYearId=0;
			String uptoDate="",criteria="";
			SkillMasterBean sb=new SkillMasterBean(dbConnVar);
		 	JsonObject jsonObject = new JsonObject();
			JsonArray ClassInfo=new JsonArray();
			JsonArray ResultClassInfo=new JsonArray();
			if(request.getParameter("academicYearId")!=null && request.getParameter("academicYearId")!="")
			{
				academicYearId=Integer.parseInt(request.getParameter("academicYearId"));			 
			}
			if(request.getParameter("uptoDate")!=null && request.getParameter("uptoDate")!="")
			{
				uptoDate= request.getParameter("uptoDate");			 
			}
			
			if(request.getParameter("criteria")!=null && request.getParameter("criteria")!="")
			{
				criteria= request.getParameter("criteria");			 
			}
			 		ClassInfo=sb.getMyClasses(uptoDate,"Y",academicYearId,loginid);
			 		response.setContentType("application/json");
					response.getWriter().print(ClassInfo);
	 
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

}
