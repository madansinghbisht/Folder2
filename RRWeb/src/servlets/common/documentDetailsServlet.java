package servlets.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.common.CommonBean;
import eth.factory.ConnectionFactory;
import eth.i18n.ETHi18n;
import eth.logger.ETHLogger;

public class documentDetailsServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public documentDetailsServlet() {
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
 
i18n = new ETHi18n(dbConnVar,true,localLang);
PropertyResourceBundle prb = i18n.getETHi18nVar("ParentPortal");
String communication_success = prb.getString("communication_success");

CommonBean bean=new CommonBean(dbConnVar);

String vehicleId="",documentId="",mobileNo="",fromDate="",toDate="",vehicleRegNo="",token="\ub006",data="";
 
 	if(req.getParameter("vehicleRegNo")!=null)
	{
 		vehicleRegNo=req.getParameter("vehicleRegNo");
	}
 
 	if(req.getParameter("vehicleId")!=null)
	{
 		vehicleId=req.getParameter("vehicleId");
 		
	}
 	if(req.getParameter("documentId")!=null)
	{
 		documentId=req.getParameter("documentId");
 		
	}
 	
 	
    Map m=req.getParameterMap();
    Set s = m.entrySet();
    Iterator it = s.iterator();
    String val="";
    while(it.hasNext()){
    		Map.Entry<String,String[]> entry = (Map.Entry<String,String[]>)it.next();
    		String key= entry.getKey();
    		String[] value= entry.getValue();
    		
    		 	if (key.startsWith("attr_")) 
    		 	{
    	           // do what ever you want here 
    			 	if(value.length>1)
    			 	{
    			 		for (int i = 0; i < value.length; i++) 
    	    			{
    			 			val = val+ value[i].toString()+',';
    	    			}
    	    		}else
    	    			val=value[0].toString();
    			 	data= data + key.replace("attr_", "")+"$"+val+"\ub006";
    	    	}
    		 	
    		 	
    	   }
   String dataStr=data.substring(0, data.length() - 1);
   
    //ETHLogger.log(dbConnVar,"vehicleId-->  " +  vehicleId);
    //ETHLogger.log(dbConnVar,"documentId-->  " +  documentId);
	//ETHLogger.log(dbConnVar,"data-->  " +  dataStr);
	
	int vehicleMasterInsertion=0; 
	
	 vehicleMasterInsertion=bean.insertUpdateVehicleDocumentDetails(vehicleId,documentId,dataStr,token);
	 
	if(vehicleMasterInsertion==1){
		msg=" Documents Details Inserted Successfully";
		msgTitle="Success!";
	}else if(vehicleMasterInsertion==2){
		msg="Documents Details Updated Successfully ";
		msgTitle="Success!";
	}else{
		msg="Unable Update/Insert Documents Details  ";
		msgTitle="ERROR!";
	}  

	session.setAttribute("message", msg);
	session.setAttribute("messageTitle", msgTitle);
	res.sendRedirect(req.getContextPath()+"/form/jsp_master/vms_VehicleMaster.jsp"); 


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
