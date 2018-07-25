package servlets.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.PropertyResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import beans.aaa.AAAUtils;
import beans.common.CommonBean;
import eth.factory.ConnectionFactory;
import eth.i18n.ETHi18n;
import eth.logger.ETHLogger;

public class PostToFinance extends HttpServlet {

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
      
		String token="\ub006",vehicleId="",fin_id="",companyId="",acc_fin_id=""; 
		String loginid="",postingToFinanceStr="";
		
		/*
		AAAUtils bn= new AAAUtils(dbConnVar);
		String finarr[] = bn.getFinancialYear();
		if( finarr[0] != null ){
			session.setAttribute("fin_id",finarr[0]);
		}
		if( finarr[1] != null ){
			session.setAttribute("financial_year",finarr[1]);
		}
		if( finarr[2] != null ){
			session.setAttribute("fy_from_date",finarr[2]);
		}
		if( finarr[3] != null ){
			session.setAttribute("fy_to_date",finarr[3]);
		}
		
		*/
		
		 if(session.getAttribute("loginid")!=null)
		 {
			loginid=session.getAttribute("loginid").toString();
		 }
		 
		 if(req.getParameter("postingToFinanceStr")!=null && !req.getParameter("postingToFinanceStr").equalsIgnoreCase(""))
		 {
			 postingToFinanceStr=req.getParameter("postingToFinanceStr");
		 }
		 String expenseType="";
		 if(req.getParameter("expenseType")!=null && !req.getParameter("expenseType").equalsIgnoreCase(""))
		 {
			 expenseType=req.getParameter("expenseType");
		 }
		 
		 if(session.getAttribute("fin_id")!=null)
		 {
			 fin_id=session.getAttribute("fin_id").toString();
			 
		 }
		 
		 
		 
		 if(session.getAttribute("loginid")!=null)
		 {
			 loginid=session.getAttribute("loginid").toString();
		 }
		 
		 companyId="1";
		 if(session.getAttribute("company_id")!=null)
		 {
			 companyId=(String)session.getAttribute("company_id");
		 }
			
		
		 
		 
		 	Date date = new Date();  
		    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");  
		    
		    String voucherDate= formatter.format(date);  
		 
		   
			/// ETHLogger.log(dbConnVar, "postingToFinanceStr--###-> "+postingToFinanceStr); 
			 //ETHLogger.log(dbConnVar, "fin_id--###-> "+fin_id);
			
			// ETHLogger.log(dbConnVar, "loginid--###-> "+loginid);
			 //ETHLogger.log(dbConnVar, "company_id--###-> "+companyId);
			// postingToFinance(String companyId,String fin_id,String voucherDate,String postingToFinanceStr,String expenseType,String createdBy)
			 int postToFinanceStatus=bean.postingToFinance(companyId,fin_id,voucherDate,postingToFinanceStr,expenseType,loginid);
			
			// ETHLogger.log(dbConnVar, "postToFinanceStatus--###-> "+postToFinanceStatus);
			// ETHLogger.log(dbConnVar, "postToFinanceStatus--###-> "+expenseType);
			 
			if(postToFinanceStatus>0){
				msg="Vehicle Expense Posting Done Successfully"; 
			}else{
				msg="Error Vehicle Expense Posting ";
			}  
		
			
			session.setAttribute("message", msg);
			res.getWriter().write("<html><head></head><body>"+msg+"</body></html>");
			res.getWriter().close();
			
			
			/*
			if(expenseType.equalsIgnoreCase("Fuel"))
			{
				//res.sendRedirect(req.getContextPath()+"/form/jsp_master/FuelReadingPostToFinance.jsp?message="+msg);
				 //ETHLogger.log(dbConnVar, "postToFinanceStatus--###-> "+msg);
			}
			else if(expenseType.equalsIgnoreCase("Repair Entry"))
			{
				res.sendRedirect(req.getContextPath()+"/form/jsp_master/RepairEntryPostToFinance.jsp"); 
			}
			else
			{
			    res.sendRedirect(req.getContextPath()+"/form/jsp_master/ServiceEntryPostToFinance.jsp"); 
			}*/
		
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



