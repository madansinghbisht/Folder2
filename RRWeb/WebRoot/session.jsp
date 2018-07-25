<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page language="java" import= "org.apache.commons.collections.OrderedMap,eth.logger.*,beans.common.AccessControlBean,java.util.Vector"%>
<jsp:directive.page import="beans.common.CommonBean"/>


<%
	String dbConnVar=null;
	try{
		String loginid=null;
		if(request.getParameter("loginid")!=null){
		  	loginid=request.getParameter("loginid");
		  	session.setAttribute("loginid",loginid);
		}
		
		if(request.getParameter("dbConnVar")!=null){
		  	dbConnVar=request.getParameter("dbConnVar");
		  	session.setAttribute("dbConnVar",dbConnVar);
		}
		String userName=null;
		if(request.getParameter("userName")!=null){
		  	userName=request.getParameter("userName").toString();
		  	session.setAttribute("userName",userName);
		}
		String academicStartDate=null;
		if(request.getParameter("academicStartDate")!=null){
		  	academicStartDate=request.getParameter("academicStartDate");
		  	session.setAttribute("academicStartDate",academicStartDate);
		}
		String academicEndDate=null;
		if(request.getParameter("academicEndDate")!=null){
		  	academicEndDate=request.getParameter("academicEndDate");
		  	session.setAttribute("academicEndDate",academicEndDate);
		}
		String academicYearId=null;
		if(request.getParameter("academicYearId")!=null){
		  	academicYearId=request.getParameter("academicYearId");
		  	session.setAttribute("academicYearId",academicYearId);
		}
		String academicYear=null;
		if(request.getParameter("academicYear")!=null){
			academicYear=request.getParameter("academicYear");
		  	session.setAttribute("academicYear",academicYear);
		}
		String localLang=null;
		if(request.getParameter("localLang")!=null){
			localLang=request.getParameter("localLang").toString();
			session.setAttribute("localLang",localLang);
		}
		String photoPath=null;
		if(request.getParameter("photoPath")!=null){
			photoPath=request.getParameter("photoPath").toString();
			session.setAttribute("photoPath",photoPath);
		}
		String isadmin=null;
		if(request.getParameter("isadmin")!=null){
			isadmin=request.getParameter("isadmin").toString();
			session.setAttribute("isadmin",isadmin);
		}
		String fin_id=null;
		if(request.getParameter("fin_id")!=null){
			fin_id=request.getParameter("fin_id").toString();
			session.setAttribute("fin_id",fin_id);
		}
		//ETHLogger.log(dbConnVar+"_VMS", "In VMS Session"+request.getParameter("fin_id"));
		 
		
		
		//AccessControlBean aBean = new AccessControlBean(dbConnVar);
		//Vector activityVec = aBean.getAccessControlList(loginid);
		
		CommonBean bean=new CommonBean(dbConnVar);
		
		Vector activityVec=bean.getAccessControlList(loginid);
		
		OrderedMap activityOm = null, dashboardOm = null;
		 
		if(activityVec!=null && activityVec.size()>0){
			activityOm = (OrderedMap)activityVec.get(0);
			dashboardOm = (OrderedMap)activityVec.get(1);
		}
		 
		session.setAttribute("activityOm",activityOm);
		session.setAttribute("dashboardOm",dashboardOm);
		
		response.sendRedirect("/VMSWeb/");
		
		
		
	}catch(Exception e){
		ETHLogger.log(dbConnVar, e);
	}
	
%>