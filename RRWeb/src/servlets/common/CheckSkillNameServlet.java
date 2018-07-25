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

import beans.common.SkillMasterBean;

import eth.factory.ConnectionFactory;
import eth.i18n.ETHi18n;
import eth.logger.ETHLogger;

public class CheckSkillNameServlet extends HttpServlet {

	 
  
	public void doPost(HttpServletRequest req , HttpServletResponse res )
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
  		
  		
		String skillType="";
		if(req.getParameter("skillType")!=null)
		{
			skillType=req.getParameter("skillType");
		}
		
		
		String skillName="";
		if(req.getParameter("skillName")!=null)
		{
			skillName=req.getParameter("skillName");
		}
		
		 
		int skillCheckStatus=0; 
		
		SkillMasterBean bean=new SkillMasterBean(dbConnVar);
		 
		
		skillCheckStatus=bean.checkSkillName(skillName,skillType);
		
		
		if(skillCheckStatus==1)
		{
			res.getWriter().write("1");
			res.getWriter().close();
		}
		else
		{
			res.getWriter().write("0");
			res.getWriter().close();
		}
		
		
		
		
		
		
		 
		}catch(Exception e)
		{
			ETHLogger.log(dbConnVar + "_dojo", e);
		}
		
	}
	
}

