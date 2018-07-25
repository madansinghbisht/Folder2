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
import beans.common.SkillMasterBean;

import eth.factory.ConnectionFactory;
import eth.i18n.ETHi18n;
import eth.logger.ETHLogger;

public class SkillMasterServlet extends HttpServlet {

	   
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
  		
  		
		String type="";
		if(req.getParameter("type")!=null)
		{
			type=req.getParameter("type");
		}
		
		String skillName="",iconPath="",token="\ub006";
		String insertUpdateFlag="I";
		int pointWeight=0;
		if(req.getParameter("insertUpdateFlag")!=null)
		{
			insertUpdateFlag=req.getParameter("insertUpdateFlag");
		}
		String skillId="";
		if(req.getParameter("skillId")!=null)
		{
			skillId=req.getParameter("skillId");
		}
		if(req.getParameter("skillName")!=null)
		{
			skillName=req.getParameter("skillName");
		}
		 
		if(req.getParameter("iconPathInput")!=null)
		{
			iconPath= req.getParameter("iconPathInput");
		}
		if(req.getParameter("pointWeight")!=null)
		{
			pointWeight=Integer.parseInt(req.getParameter("pointWeight"));
		}
		
		
		
		/*
		i18n = new ETHi18n(dbConnVar,true,localLang);
		PropertyResourceBundle prb = i18n.getETHi18nVar("ParentPortal");
		String communication_success = prb.getString("communication_success");
		*/
		
		int skillMasterInsertion=0; 
		
		SkillMasterBean bean=new SkillMasterBean(dbConnVar);
		String data=skillId+token+type+token+skillName+token+iconPath+token+pointWeight+token+loginid;
		
		skillMasterInsertion=bean.insertUpdateSkillDetails(insertUpdateFlag,data,token);
		
		if(skillMasterInsertion==1){
			//msg="New Skill is Added Successfully";
			//msgTitle="Success!";
		}else if(skillMasterInsertion==2){
			//msg="Skill is Updated";
			//msgTitle="Success!";
		}else{
			msg="Unable To Add/Update Skill ";
			msgTitle="ERROR!";
			session.setAttribute("message", msg);
			session.setAttribute("messageTitle", msgTitle);
		}  
	
		
	 	res.sendRedirect(req.getContextPath()+"/form/jsp_skill/edit_skills.jsp"); 
		 
		}catch(Exception e)
		{
			ETHLogger.log(dbConnVar + "_dojo", e);
		}
		
	}
	
}
