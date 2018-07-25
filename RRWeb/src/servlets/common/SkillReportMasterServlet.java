  	package servlets.common;

	import java.io.IOException;
	import java.io.PrintWriter;
	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.Statement;
import java.text.SimpleDateFormat;

	import javax.servlet.ServletException;
	import javax.servlet.http.HttpServlet;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;
	import javax.servlet.http.HttpSession;
	import java.util.Calendar;
import java.util.Date;

import com.google.gson.JsonArray;
	import com.google.gson.JsonObject;

	import beans.common.SkillMasterBean;

	import eth.factory.ConnectionFactory;
	import eth.i18n.ETHi18n;
import eth.logger.ETHLogger;

	public class SkillReportMasterServlet extends HttpServlet {

		   
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
	  	   	 
	  		int academicYearId=0;
	  		 		
	  		 		if(req.getParameter("academicYearId")!=null && !req.getParameter("academicYearId").equalsIgnoreCase("null") && !req.getParameter("academicYearId").equalsIgnoreCase(""))
	  		 		{
	  		 			academicYearId=Integer.parseInt(req.getParameter("academicYearId"));
	  		 	 
	  		 		}		
	  		 		
	  		 			
	  		 	int classStructureId=0;
	  		 		
	  		 		if(req.getParameter("classStructureId")!=null && !req.getParameter("classStructureId").equalsIgnoreCase("null") && !req.getParameter("classStructureId").equalsIgnoreCase(""))
	  		 		{
	  		 			classStructureId=Integer.parseInt(req.getParameter("classStructureId"));
	  		 	 
	  		 		}	
			 
			 
			 
			String enrollNo="";
			if(req.getParameter("enrollNo")!=null)
			{
				enrollNo=req.getParameter("enrollNo");
			}
			
			 
			
			String rangeType="";
			if(req.getParameter("rangeType")!=null)
			{
				rangeType=req.getParameter("rangeType");
			}
			
		    String fromDate="",toDate="";
		    String strDate="";
		    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		    Calendar currentDate = Calendar.getInstance();
		    if(rangeType.equalsIgnoreCase("today"))
		    {
		    	 
		    	strDate=sdf.format(currentDate.getTime());
		    	fromDate=strDate;
		        toDate=strDate;
		        ETHLogger.log(dbConnVar+"_dojo","Today----------------------------");
		        ETHLogger.log(dbConnVar+"_dojo","fromDate---"+ fromDate);
		        ETHLogger.log(dbConnVar+"_dojo","toDate---"+ toDate);
		    }
		    else if(rangeType.equalsIgnoreCase("yesterday"))
		    {
		     
		        Calendar previousDay = (Calendar) currentDate.clone();
		        previousDay.add(Calendar.DAY_OF_YEAR, -1);
		        strDate = sdf.format(previousDay.getTime());
		        
		       
		        fromDate=strDate;
		        toDate=strDate;
		        ETHLogger.log(dbConnVar+"_dojo","Yesterday---------------------------");
		        ETHLogger.log(dbConnVar+"_dojo","fromDate---"+ fromDate);
		        ETHLogger.log(dbConnVar+"_dojo","toDate---"+ toDate);
		    }
		    else if(rangeType.equalsIgnoreCase("currentWeek"))
		    {
		    	Calendar c = Calendar.getInstance();
		        while (c.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
		            c.add(Calendar.DATE, -1);
		        }
		        strDate = sdf.format(c.getTime());
		        fromDate=strDate;
		          c = Calendar.getInstance();
		        while (c.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
		            c.add(Calendar.DATE, 1);
		        }
		        strDate=sdf.format(c.getTime());
		        toDate=strDate;
		    	
		    	
		    	
		         ETHLogger.log(dbConnVar+"_dojo","Current Week----------------------------");
		         ETHLogger.log(dbConnVar+"_dojo","fromDate---"+ fromDate);
			     ETHLogger.log(dbConnVar+"_dojo","toDate---"+ toDate);
		    	
		    	
		    }
		    else if(rangeType.equalsIgnoreCase("lastWeek"))
		    {
		    	Calendar c = (Calendar) currentDate.clone();
		         // last week
		         c.add(Calendar.WEEK_OF_YEAR, -1);
		         // first day
		         c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
		         strDate = sdf.format(c.getTime());
		         fromDate=strDate;
		         
		         c=(Calendar) currentDate.clone();
		         c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
		         // last day of previous week
		         c.add(Calendar.DAY_OF_MONTH, -1);
		         strDate = sdf.format(c.getTime());
		         
		         toDate=strDate;
		         ETHLogger.log(dbConnVar+"_dojo","Last Week----------------------------");
		         ETHLogger.log(dbConnVar+"_dojo","fromDate---"+ fromDate);
			     ETHLogger.log(dbConnVar+"_dojo","toDate---"+ toDate);
		    }
		    else if(rangeType.equalsIgnoreCase("currentMonth"))
		    {
		    	Calendar c = Calendar.getInstance();
		        int year = c.get(Calendar.YEAR);
		        int month = c.get(Calendar.MONTH);
		        int day = 1;
		        c.set(year, month, day);
		        int numOfDaysInMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		        strDate = sdf.format(c.getTime());
		        fromDate=strDate;
		        
		        c.add(Calendar.DAY_OF_MONTH, numOfDaysInMonth-1);
		        strDate = sdf.format(c.getTime());
		        toDate=strDate;
		         
		      
		         ETHLogger.log(dbConnVar+"_dojo","Current Month----------------------------");
		         ETHLogger.log(dbConnVar+"_dojo","fromDate---"+ fromDate);
			     ETHLogger.log(dbConnVar+"_dojo","toDate---"+ toDate);
		    }
		    else if(rangeType.equalsIgnoreCase("lastMonth"))
		    {
		    	Calendar c = Calendar.getInstance();
		    	c.set(Calendar.DATE, 1);
		    	c.add(Calendar.DAY_OF_MONTH, -1);
		     	strDate = sdf.format(c.getTime());
		     	toDate=strDate;
		        
		    	c.set(Calendar.DATE, 1);
		    	strDate = sdf.format(c.getTime());
		    	fromDate=strDate;
		         
		      
		         ETHLogger.log(dbConnVar+"_dojo","Last Month----------------------------");
		         ETHLogger.log(dbConnVar+"_dojo","fromDate---"+ fromDate);
			     ETHLogger.log(dbConnVar+"_dojo","toDate---"+ toDate);
		    }
		    else if(rangeType.equalsIgnoreCase("customRange"))
		    {
		    	 
		    	if(req.getParameter("fromDate")!=null)
				{
		    		fromDate=req.getParameter("fromDate");
				}
		    	if(req.getParameter("toDate")!=null)
				{
		    		toDate=req.getParameter("toDate");
				}
		    	 
		     
		         ETHLogger.log(dbConnVar+"_dojo","Date Range  ----------------------------");
		         ETHLogger.log(dbConnVar+"_dojo","fromDate---"+ fromDate);
			     ETHLogger.log(dbConnVar+"_dojo","toDate---"+ toDate);
		    }
		    
	 		int assignSkillToStudent=0; 
			
			SkillMasterBean bean=new SkillMasterBean(dbConnVar);
		 	 
				JsonObject jsonObject = new JsonObject();	
 				JsonArray jsonData= bean.getStudentSkillPercentile(academicYearId,classStructureId,enrollNo,fromDate,toDate);
 				ETHLogger.log(dbConnVar+"_dojo",jsonData.toString());
 				res.setContentType("application/json");
 				res.getWriter().print(jsonData);
 				res.getWriter().close();
	 	//res.sendRedirect(req.getContextPath()+"/form/jsp_skill/student_details.jsp"); 
			 
			}catch(Exception e)
			{
				ETHLogger.log(dbConnVar + "_dojo", e);
			}
			
		}
		
		
	}