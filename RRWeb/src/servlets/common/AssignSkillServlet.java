package servlets.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import beans.common.SkillMasterBean;

import eth.factory.ConnectionFactory;
import eth.i18n.ETHi18n;
import eth.logger.ETHLogger;

public class AssignSkillServlet extends HttpServlet {

	   
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
		
		String loginid ="",sessionLoginid="";
	  	if(session.getAttribute("loginid")!=null && !session.getAttribute("loginid").equals(""))
	  	{
		  	loginid = session.getAttribute("loginid").toString();
		  	sessionLoginid = loginid;
  		}
  		String isadmin = "false";
  		//sessionLoginid = "SYSADMIN";
  		if(session.getAttribute("isadmin")!=null){
  				isadmin = session.getAttribute("isadmin").toString();	
  		}
  		/*
  		data:{acadId:acadId,classStructureId:classStructureId,
			  classSubjectId:classSubjectId,skillId:skillId,
			  enrollNo:enrollNo,points:points,
			  createdDate:createdDate,createdBy:createdBy},  */
			  
  		String token="\ub006";	  
		String acadId="";
		if(req.getParameter("acadId")!=null)
		{
			acadId=req.getParameter("acadId");
		}
		String classStructureId="";
		if(req.getParameter("classStructureId")!=null)
		{
			classStructureId=req.getParameter("classStructureId");
		}
		String classSubjectId="";
		if(req.getParameter("classSubjectId")!=null)
		{
			classSubjectId=req.getParameter("classSubjectId");
		}
		String skillId="";
		if(req.getParameter("skillId")!=null)
		{
			skillId=req.getParameter("skillId");
		}
		String enrollNo="";
		if(req.getParameter("enrollNo")!=null)
		{
			enrollNo=req.getParameter("enrollNo");
		}
		String points="";
		if(req.getParameter("points")!=null)
		{
			points=req.getParameter("points");
		}
		
		
		String createdDate="";
		if(req.getParameter("createdDate")!=null)
		{
			createdDate=req.getParameter("createdDate");
		}
		
		String createdBy="";
		if(req.getParameter("createdBy")!=null)
		{
			createdBy=req.getParameter("createdBy");
		}
		
		String classwiseFlag="";
		if(req.getParameter("classwiseFlag")!=null)
		{
			classwiseFlag=req.getParameter("classwiseFlag");
		}
		
		
		int assignSkillToStudent=0,assignSkillToClass=0; 
		
		SkillMasterBean bean=new SkillMasterBean(dbConnVar);
		
		 
		if(classwiseFlag.equalsIgnoreCase("true"))
		{
			String skillData=acadId+token+classStructureId+token+classSubjectId+token+skillId+token+points+token+createdBy;
			assignSkillToClass=bean.assignSkillToClass(skillData,token);
		}
		else
		{
			String skillData=acadId+token+classStructureId+token+classSubjectId+token+skillId+token+points+token+enrollNo+token+createdBy;
			assignSkillToStudent=bean.assignSkillToStudent(skillData,token);
		}
		
		 
		
		ETHLogger.log(dbConnVar+"_dojo",classwiseFlag+"/"+assignSkillToStudent+"/"+assignSkillToClass);
		
		if(assignSkillToStudent==1){
			 
			JsonArray result=new JsonArray();
		 	JsonObject jsonObject = new JsonObject();	
		
		 	jsonObject=bean.getStudentSkillStat(enrollNo);
			result.add(jsonObject);
			
			jsonObject = bean.getClasswiseSkillStat(Integer.parseInt(acadId),Integer.parseInt(classStructureId));
			result.add(jsonObject);
			
			ETHLogger.log(dbConnVar+"_dojo",result.toString());
			res.setContentType("application/json");
			res.getWriter().print(result);
			res.getWriter().close();
	 
		}else if(assignSkillToClass==1){
			 
			JsonArray result=new JsonArray();
			result  = bean.getClassWiseStudentData(Integer.parseInt(acadId),Integer.parseInt(classStructureId));
			JsonObject jsonObject = bean.getClasswiseSkillStat(Integer.parseInt(acadId),Integer.parseInt(classStructureId));
			result.add(jsonObject);
			 
			ETHLogger.log(dbConnVar+"_dojo",result.toString());
			res.setContentType("application/json");
			res.getWriter().print(result);
			res.getWriter().close();
			
		}else{
			msg="Unable To Add/Update Skill ";
			msgTitle="ERROR!";
			 
		}  
	
		
		//res.sendRedirect(req.getContextPath()+"/form/jsp_skill/student_details.jsp"); 
		 
		}catch(Exception e)
		{
			ETHLogger.log(dbConnVar + "_dojo", e);
		}
		
	}
	
	
}