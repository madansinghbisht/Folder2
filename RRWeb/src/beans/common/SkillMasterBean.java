package beans.common;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.util.Vector;

import org.apache.commons.collections.OrderedMap;
import org.apache.commons.collections.map.LinkedMap;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import eth.factory.ConnectionFactory;
import eth.i18n.ETHi18n;
import eth.logger.ETHLogger;

public class SkillMasterBean {

	private ConnectionFactory cf = null; 

	ETHi18n i18n = null; 
	String dbConnVar = "";

	
	public SkillMasterBean(String dbConnVar) {
		cf = new ConnectionFactory(dbConnVar);
		this.i18n = new ETHi18n(dbConnVar, true);
		this.dbConnVar = dbConnVar;
	}
	
	
 	public JsonArray getClassTeacher_ClassInfo(String uptoDate,String flag_check,int yearIdPre,String teacherLoginId,String teacherType){
			
			
			CallableStatement cstmt = null;
			ResultSet rs = null;
		 	Connection con=cf.getConnection();
	 	 
			JsonArray jsonData=new JsonArray();
			
		//	select fun_retrieve_class_teacher_class_strength('21/05/2018','N',29,'EMP112','C','c'); fetch all in c;
			try {
				cstmt = con.prepareCall("{ ? = call fun_retrieve_class_teacher_class_strength(?,?,?,?,?,?) }");
				cstmt.registerOutParameter(1,Types.OTHER);
				cstmt.setString(2, uptoDate);
				cstmt.setString(3, flag_check);
				cstmt.setInt(4,yearIdPre);
		 		cstmt.setString(5,teacherLoginId);
				cstmt.setString(6, teacherType);
	 		 	cstmt.setObject(7, rs);
				
				con.setAutoCommit(false); // without this the Result set is not returning
				cstmt.execute();
				rs = (ResultSet) cstmt.getObject(1);
				
			 
				while(rs.next() ) {
					JsonObject jsonObject = new JsonObject();	
					jsonObject.add("class_structure_id", new JsonPrimitive(rs.getString("class_structure_id")));
					jsonObject.add("standard_id", new JsonPrimitive(rs.getString("standard_id")));
					jsonObject.add("class_section_id", new JsonPrimitive(rs.getString("class_section_id")));
					jsonObject.add("standard_desc", new JsonPrimitive(rs.getString("standard_desc")));
					jsonObject.add("section", new JsonPrimitive(rs.getString("section")));
					jsonObject.add("strength", new JsonPrimitive(rs.getString("No. Of Students")));
					jsonObject.add("class_teacher_id", new JsonPrimitive(rs.getString("class_teacher_id")));
					
				 	jsonData.add(jsonObject);
			 			
				}
				ETHLogger.log(dbConnVar + "_dojo",jsonData.toString());
		 			
			} catch (Exception e) {
				ETHLogger.log(dbConnVar + "_dojo",e);

			} finally {
				try {
					if (rs != null) {
						rs.close();
					}
					if (cstmt != null) {
						cstmt.close();
					}
					if(con!=null)
					{
						cf.freeConnection(con);
					}
				} catch (Exception e) {
					ETHLogger.log(dbConnVar + "_dojo",e);
				}
			}
			
		return jsonData;
	}
	 
 	
 	public JsonArray getSubjectTeacher_ClassInfo(String uptoDate,String flag_check,int yearIdPre,String teacherLoginId){
		
		
		CallableStatement cstmt = null;
		ResultSet rs = null;
	 	Connection con=cf.getConnection();
 	 
		JsonArray jsonData=new JsonArray();
		
	//	select fun_retrieve_class_teacher_class_strength('21/05/2018','N',29,'EMP112','C','c'); fetch all in c;
		try {
			 
			cstmt = con.prepareCall("{ ? = call fun_retrieve_subject_teacher_class_strength(?,?,?,?,?) }");
			cstmt.registerOutParameter(1,Types.OTHER);
			cstmt.setString(2, uptoDate);
			cstmt.setString(3, flag_check);
			cstmt.setInt(4,yearIdPre);
	 		cstmt.setString(5,teacherLoginId);
	 
 		 	cstmt.setObject(6, rs);
			 
			
			/*
			cstmt = con.prepareCall("{ ? = call fun_retrieve_class_subject_list_for_specified_teacher(?,?) }");
			cstmt.registerOutParameter(1,Types.OTHER);
			cstmt.setString(2,teacherLoginId);
			
			*/
			con.setAutoCommit(false); // without this the Result set is not returning
			cstmt.execute();
			rs = (ResultSet) cstmt.getObject(1);
			
		 
			while(rs.next() ) {
				JsonObject jsonObject = new JsonObject();	
				jsonObject.add("class_structure_id", new JsonPrimitive(rs.getString("class_structure_id")));
				jsonObject.add("standard_id", new JsonPrimitive(rs.getString("standard_id")));
				jsonObject.add("class_section_id", new JsonPrimitive(rs.getString("class_section_id")));
				jsonObject.add("standard_desc", new JsonPrimitive(rs.getString("standard_desc")));
				jsonObject.add("section", new JsonPrimitive(rs.getString("section")));
				jsonObject.add("strength", new JsonPrimitive(rs.getString("No. Of Students")));
				//jsonObject.add("class_teacher_id", new JsonPrimitive(rs.getString("class_teacher_id")));
				
			 	jsonData.add(jsonObject);
		 			
			}
			ETHLogger.log(dbConnVar + "_dojo",jsonData.toString());
	 			
		} catch (Exception e) {
			ETHLogger.log(dbConnVar + "_dojo",e);

		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (cstmt != null) {
					cstmt.close();
				}
				if(con!=null)
				{
					cf.freeConnection(con);
				}
			} catch (Exception e) {
				ETHLogger.log(dbConnVar + "_dojo",e);
			}
		}
		
	return jsonData;
}
 
	public JsonArray getClassWiseStudentData(int yearIdPre,int classStructureId){
		
		
		CallableStatement cstmt = null;
		ResultSet rs = null;
	 	Connection con=cf.getConnection();
 	 
		JsonArray jsonData=new JsonArray();
		
	//	select fun_retrieve_class_teacher_class_strength('21/05/2018','N',29,'EMP112','C','c'); fetch all in c;
		try {
			cstmt = con.prepareCall("{ ? = call fun_retrive_classwise_student_data(?,?,?) }");
			cstmt.registerOutParameter(1,Types.OTHER);
			cstmt.setInt(2, yearIdPre);
			cstmt.setInt(3, classStructureId);
			cstmt.setObject(4, rs);
			
			con.setAutoCommit(false); // without this the Result set is not returning
			cstmt.execute();
			rs = (ResultSet) cstmt.getObject(1);
			
		 
			while(rs.next() ) {
				JsonObject jsonObject = new JsonObject();	
				jsonObject.add("enrollment_no", new JsonPrimitive(rs.getString("Enrollment No")));
				jsonObject.add("roll_no", new JsonPrimitive(rs.getString("Roll No")));
				jsonObject.add("name", new JsonPrimitive(rs.getString("Student Name")));
				jsonObject.add("photoPath", new JsonPrimitive(rs.getString("PhotoPath")));
				jsonObject.add("negative_points", new JsonPrimitive(rs.getString("negative_points")));
				jsonObject.add("positive_points", new JsonPrimitive(rs.getString("positive_points")));
				jsonObject.add("total_points", new JsonPrimitive(rs.getString("total_points")));
				
			 	jsonData.add(jsonObject);
		 			
			}
			ETHLogger.log(dbConnVar + "_dojo",jsonData.toString());
	 			
		} catch (Exception e) {
			ETHLogger.log(dbConnVar + "_dojo",e);

		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (cstmt != null) {
					cstmt.close();
				}
				if(con!=null)
				{
					cf.freeConnection(con);
				}
			} catch (Exception e) {
				ETHLogger.log(dbConnVar + "_dojo",e);
			}
		}
		
	return jsonData;
}
 	
public JsonArray getSkillData(String loginId,String type){
		
		
		CallableStatement cstmt = null;
		ResultSet rs = null;
	 	Connection con=cf.getConnection();
 	 
		JsonArray jsonData=new JsonArray();
		
	 	ETHLogger.log(dbConnVar+"_VMS","select fun_retrive_dojo_skill_info("+loginId+","+type+",'c'); fetch all in c");
		try {
			cstmt = con.prepareCall("{ ? = call fun_retrive_dojo_skill_info(?,?,?) }");
			cstmt.registerOutParameter(1,Types.OTHER);
			cstmt.setString(2, loginId);
			cstmt.setString(3, type);
	 	 	cstmt.setObject(4, rs);
	 		con.setAutoCommit(false); // without this the Result set is not returning
			cstmt.execute();
			rs = (ResultSet) cstmt.getObject(1);
			
		 
			while(rs.next() ) {
				JsonObject jsonObject = new JsonObject();	
				jsonObject.add("id", new JsonPrimitive(rs.getString("id")));
				jsonObject.add("icon", new JsonPrimitive(rs.getString("icon")));
				jsonObject.add("description", new JsonPrimitive(rs.getString("description")));
				jsonObject.add("points", new JsonPrimitive(rs.getString("points")));
				 
				
			 	jsonData.add(jsonObject);
		 			
			}
			ETHLogger.log(dbConnVar + "_dojo",jsonData.toString());
	 			
		} catch (Exception e) {
			ETHLogger.log(dbConnVar + "_dojo",e);

		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (cstmt != null) {
					cstmt.close();
				}
				if(con!=null)
				{
					cf.freeConnection(con);
				}
			} catch (Exception e) {
				ETHLogger.log(dbConnVar + "_dojo",e);
			}
		}
		
	return jsonData;
}	
	
public JsonArray getSkillDataUserWise(String loginId,String type){
	
	
	CallableStatement cstmt = null;
	ResultSet rs = null;
 	Connection con=cf.getConnection();
	 
	JsonArray jsonData=new JsonArray();
	
 	ETHLogger.log(dbConnVar+"_VMS","select fun_retrive_userwise_dojo_skill_info("+loginId+","+type+",'c'); fetch all in c");
	try {
		cstmt = con.prepareCall("{ ? = call fun_retrive_userwise_dojo_skill_info(?,?,?) }");
		cstmt.registerOutParameter(1,Types.OTHER);
		cstmt.setString(2, loginId);
		cstmt.setString(3, type);
 	 	cstmt.setObject(4, rs);
 		con.setAutoCommit(false); // without this the Result set is not returning
		cstmt.execute();
		rs = (ResultSet) cstmt.getObject(1);
		
	 
		while(rs.next() ) {
			JsonObject jsonObject = new JsonObject();	
			jsonObject.add("id", new JsonPrimitive(rs.getString("id")));
			jsonObject.add("icon", new JsonPrimitive(rs.getString("icon")));
			jsonObject.add("description", new JsonPrimitive(rs.getString("description")));
			jsonObject.add("points", new JsonPrimitive(rs.getString("points")));
			 
			
		 	jsonData.add(jsonObject);
	 			
		}
		ETHLogger.log(dbConnVar + "_dojo",jsonData.toString());
 			
	} catch (Exception e) {
		ETHLogger.log(dbConnVar + "_dojo",e);

	} finally {
		try {
			if (rs != null) {
				rs.close();
			}
			if (cstmt != null) {
				cstmt.close();
			}
			if(con!=null)
			{
				cf.freeConnection(con);
			}
		} catch (Exception e) {
			ETHLogger.log(dbConnVar + "_dojo",e);
		}
	}
	
return jsonData;
}


public int insertUpdateSkillDetails(String insertUpdateFlag,String data,String token) {
	Connection con = null;
	CallableStatement cs = null;
	ResultSet rs = null;
	int result = 0; 

	try {
		con = cf.getConnection();
		ETHLogger.log(dbConnVar,"select fun_insert_update_dojo_skill_details('"+insertUpdateFlag+"',"+data+","+token);  
		cs = con.prepareCall("{? = call fun_insert_update_dojo_skill_details(?,?,?)}"); 
		cs.registerOutParameter(1, Types.INTEGER); 
		cs.setString(2, insertUpdateFlag);
		cs.setString(3, data);
		cs.setString(4, token);  
		cs.execute();
		result = cs.getInt(1);

	} catch (Exception e) {
		ETHLogger.log(dbConnVar + "_VMS",e);
	} finally {
		try {
			if (rs != null) {
				rs.close();
			}
			if (cs != null) {
				cs.close();
			}
			if (con != null) {
				cf.freeConnection(con);
			}
		} catch (Exception e) {
			ETHLogger.log(dbConnVar + "_dojo", e);
		} 
	}// end of finally
	return result;
}// end of insertUpdateSkillDetails() method
	
public int assignSkillToStudent(String skillData,String token) {
	Connection con = null;
	CallableStatement cs = null;
	ResultSet rs = null;
	int result = 0; 
//acdId,classStructureId,classSubjectId,skillId,enrollNo,points,createdDate,createdBy
	try {
		con = cf.getConnection();
		ETHLogger.log(dbConnVar,"select fun_assign_skill_to_student('"+skillData+"','"+token+"'");  
		cs = con.prepareCall("{? = call fun_assign_skill_to_student(?,?)}"); 
		cs.registerOutParameter(1, Types.INTEGER); 
		cs.setString(2, skillData);
		cs.setString(3, token);
	 	cs.execute();
		result = cs.getInt(1);

	} catch (Exception e) {
		ETHLogger.log(dbConnVar + "_dojo",e);
	} finally {
		try {
			if (rs != null) {
				rs.close();
			}
			if (cs != null) {
				cs.close();
			}
			if (con != null) {
				cf.freeConnection(con);
			}
		} catch (Exception e) {
			ETHLogger.log(dbConnVar + "_dojo", e);
		} 
	}// end of finally
	return result;
}// end of assignSkilltostudent() method





public int checkSkillName(String skillName,String type) {
	Connection con = null;
	CallableStatement cs = null;
	ResultSet rs = null;
	int result = 0; 
//acdId,classStructureId,classSubjectId,skillId,enrollNo,points,createdDate,createdBy
	try {
		con = cf.getConnection();
		ETHLogger.log(dbConnVar,"select fun_check_skill_name_exist_or_not('"+skillName+"','"+type+"'");  
		cs = con.prepareCall("{? = call fun_check_skill_name_exist_or_not(?,?)}"); 
		cs.registerOutParameter(1, Types.INTEGER); 
		cs.setString(2, skillName);
		cs.setString(3, type);
	 	cs.execute();
		result = cs.getInt(1);

	} catch (Exception e) {
		ETHLogger.log(dbConnVar + "_dojo",e);
	} finally {
		try {
			if (rs != null) {
				rs.close();
			}
			if (cs != null) {
				cs.close();
			}
			if (con != null) {
				cf.freeConnection(con);
			}
		} catch (Exception e) {
			ETHLogger.log(dbConnVar + "_dojo", e);
		} 
	}// end of finally
	return result;
}// end of checkSkillName() method

public JsonObject getStudentSkillStat(String enrollNo) {
	Connection con = null;
	CallableStatement cs = null;
	ResultSet rs = null;
	int result = 0; 
	JsonObject jsonObject = new JsonObject();	
//acdId,classStructureId,classSubjectId,skillId,enrollNo,points,createdDate,createdBy
	try {
		con = cf.getConnection();
		ETHLogger.log(dbConnVar,"select fun_retrive_student_skill_stat('"+enrollNo+"','c'");  
		cs = con.prepareCall("{? = call fun_retrive_student_skill_stat(?,?)}"); 
		cs.registerOutParameter(1,Types.OTHER);
		cs.setString(2, enrollNo);
		cs.setObject(3, rs); 
		con.setAutoCommit(false); // without this the Result set is not returning
		cs.execute();
		rs = (ResultSet) cs.getObject(1);

		
		while(rs.next())
		{
			
			jsonObject.add("enrollment_no", new JsonPrimitive(rs.getString("enrollment_no")));
			jsonObject.add("positive_points", new JsonPrimitive(rs.getString("positive_points")));
			jsonObject.add("negative_points", new JsonPrimitive(rs.getString("negative_points")));
			jsonObject.add("total_points", new JsonPrimitive(rs.getString("total_points")));
		}
		
	 
	} catch (Exception e) {
		ETHLogger.log(dbConnVar + "_dojo",e);
	} finally {
		try {
			if (rs != null) {
				rs.close();
			}
			if (cs != null) {
				cs.close();
			}
			if (con != null) {
				cf.freeConnection(con);
			}
		} catch (Exception e) {
			ETHLogger.log(dbConnVar + "_dojo", e);
		} 
	}// end of finally
	return jsonObject;
}// end of checkSkillName() method


public JsonArray getStudentSkillPercentile(int acadId,int classStructureId,String enrollNo,String fromDate,String toDate) {
	Connection con = null;
	CallableStatement cs = null;
	ResultSet rs = null;
	int result = 0; 
	JsonObject jsonObject = new JsonObject();	
	JsonArray jsonData=new JsonArray();
//acdId,classStructureId,classSubjectId,skillId,enrollNo,points,createdDate,createdBy
	try {
		con = cf.getConnection();
		ETHLogger.log(dbConnVar,"select fun_retrive_student_skill_percentile('"+acadId+"','"+classStructureId+"','"+enrollNo+"','"+fromDate+"','"+toDate+"','c'");  
		cs = con.prepareCall("{? = call fun_retrive_student_skill_percentile(?,?,?,?,?,?)}"); 
		cs.registerOutParameter(1,Types.OTHER);
		cs.setInt(2, acadId);
		cs.setInt(3, classStructureId);
		cs.setString(4, enrollNo);
		cs.setString(5, fromDate);
		cs.setString(6, toDate);
		cs.setObject(7, rs); 
		con.setAutoCommit(false); // without this the Result set is not returning
		cs.execute();
		rs = (ResultSet) cs.getObject(1);

		
		while(rs.next())
		{
			jsonObject = new JsonObject();	
			jsonObject.add("description", new JsonPrimitive(rs.getString("description")));
			jsonObject.add("type", new JsonPrimitive(rs.getString("type")));
			jsonObject.add("skill_total", new JsonPrimitive(rs.getString("skill_total")));
			
			//jsonObject.add("percentile", new JsonPrimitive(rs.getString("percentile")));
			
			jsonData.add(jsonObject); 
			ETHLogger.log(dbConnVar,jsonObject.toString());
		}
		
	 
	} catch (Exception e) {
		ETHLogger.log(dbConnVar + "_dojo",e);
	} finally {
		try {
			if (rs != null) {
				rs.close();
			}
			if (cs != null) {
				cs.close();
			}
			if (con != null) {
				cf.freeConnection(con);
			}
		} catch (Exception e) {
			ETHLogger.log(dbConnVar + "_dojo", e);
		} 
	}// end of finally
	return jsonData;
}// end of checkSkillName() method

public JsonObject getClasswiseSkillStat(int acadId,int classStuctureId) {
	Connection con = null;
	CallableStatement cs = null;
	ResultSet rs = null;
	int result = 0; 
	JsonObject jsonObject = new JsonObject();	
//acdId,classStructureId,classSubjectId,skillId,enrollNo,points,createdDate,createdBy
	try {
		con = cf.getConnection();
		ETHLogger.log(dbConnVar,"select fun_retrive_classwise_skill_stat("+acadId+","+classStuctureId+",'c'");  
		cs = con.prepareCall("{? = call fun_retrive_classwise_skill_stat(?,?,?)}"); 
		cs.registerOutParameter(1,Types.OTHER);
		cs.setInt(2, acadId);
		cs.setInt(3, classStuctureId);
		cs.setObject(4, rs); 
		con.setAutoCommit(false); // without this the Result set is not returning
		cs.execute();
		rs = (ResultSet) cs.getObject(1);

		
		while(rs.next())
		{
			
			jsonObject.add("class_structure_id", new JsonPrimitive(rs.getString("class_structure_id")));
			jsonObject.add("positive_points", new JsonPrimitive(rs.getString("positive_points")));
			jsonObject.add("negative_points", new JsonPrimitive(rs.getString("negative_points")));
			jsonObject.add("total_points", new JsonPrimitive(rs.getString("total_points")));
		}
		
	 
	} catch (Exception e) {
		ETHLogger.log(dbConnVar + "_dojo",e);
	} finally {
		try {
			if (rs != null) {
				rs.close();
			}
			if (cs != null) {
				cs.close();
			}
			if (con != null) {
				cf.freeConnection(con);
			}
		} catch (Exception e) {
			ETHLogger.log(dbConnVar + "_dojo", e);
		} 
	}// end of finally
	return jsonObject;
}// end of checkSkillName() method


public int assignSkillToClass(String skillData,String token) {
	Connection con = null;
	CallableStatement cs = null;
	ResultSet rs = null;
	int result = 0; 
//acdId,classStructureId,classSubjectId,skillId,enrollNo,points,createdDate,createdBy
	try {
		con = cf.getConnection();
		ETHLogger.log(dbConnVar,"select fun_assign_skill_to_class('"+skillData+"','"+token+"'");  
		cs = con.prepareCall("{? = call fun_assign_skill_to_class(?,?)}"); 
		cs.registerOutParameter(1, Types.INTEGER); 
		cs.setString(2, skillData);
		cs.setString(3, token);
	 	cs.execute();
		result = cs.getInt(1);

	} catch (Exception e) {
		ETHLogger.log(dbConnVar + "_dojo",e);
	} finally {
		try {
			if (rs != null) {
				rs.close();
			}
			if (cs != null) {
				cs.close();
			}
			if (con != null) {
				cf.freeConnection(con);
			}
		} catch (Exception e) {
			ETHLogger.log(dbConnVar + "_dojo", e);
		} 
	}// end of finally
	return result;
}// end of assignSkilltostudent() method



public JsonArray getAcademicYearDetails() {
	Connection con = null;
	CallableStatement cs = null;
	ResultSet rs = null;
	int result = 0; 
	 
	JsonArray jsonData=new JsonArray();
	
//acdId,classStructureId,classSubjectId,skillId,enrollNo,points,createdDate,createdBy
	try {
		con = cf.getConnection();
		ETHLogger.log(dbConnVar,"select fun_get_academic_year_for_skill_grading('c')");  
		cs = con.prepareCall("{? = call fun_get_academic_year_for_skill_grading(?)}"); 
		cs.registerOutParameter(1,Types.OTHER);
	 	cs.setObject(2, rs); 
		con.setAutoCommit(false); // without this the Result set is not returning
		cs.execute();
		rs = (ResultSet) cs.getObject(1);

		
		while(rs.next())
		{
			JsonObject jsonObject = new JsonObject();	
			jsonObject.add("academic_year_id", new JsonPrimitive(rs.getString("academic_year_id")));
			jsonObject.add("academic_year", new JsonPrimitive(rs.getString("academic_year")));
			jsonObject.add("status", new JsonPrimitive(rs.getString("status")));
			jsonObject.add("start_date", new JsonPrimitive(rs.getString("start_date")));
			jsonObject.add("end_date", new JsonPrimitive(rs.getString("end_date")));
			jsonData.add(jsonObject); 
			ETHLogger.log(dbConnVar,jsonData.toString());
		}
		
	 
	} catch (Exception e) {
		ETHLogger.log(dbConnVar + "_dojo",e);
	} finally {
		try {
			if (rs != null) {
				rs.close();
			}
			if (cs != null) {
				cs.close();
			}
			if (con != null) {
				cf.freeConnection(con);
			}
		} catch (Exception e) {
			ETHLogger.log(dbConnVar + "_dojo", e);
		} 
	}// end of finally
	return jsonData;
}// end of checkSkillName() method


public JsonArray getMyClasses(String uptoDate,String flag_check,int yearIdPre,String teacherLoginId){
	
	
	CallableStatement cstmt = null;
	ResultSet rs = null;
 	Connection con=cf.getConnection();
	 
	JsonArray jsonData=new JsonArray();
	
//	select fun_retrieve_class_teacher_class_strength('21/05/2018','N',29,'EMP112','C','c'); fetch all in c;
	try {
		cstmt = con.prepareCall("{ ? = call fun_retrieve_my_classes(?,?,?,?,?) }");
		cstmt.registerOutParameter(1,Types.OTHER);
		cstmt.setString(2, uptoDate);
		cstmt.setString(3, flag_check);
		cstmt.setInt(4,yearIdPre);
 		cstmt.setString(5,teacherLoginId);
 
		 	cstmt.setObject(6, rs);
		
		con.setAutoCommit(false); // without this the Result set is not returning
		cstmt.execute();
		rs = (ResultSet) cstmt.getObject(1);
		
	 
		while(rs.next() ) {
			JsonObject jsonObject = new JsonObject();	
			jsonObject.add("class_structure_id", new JsonPrimitive(rs.getString("class_structure_id")));
			jsonObject.add("standard_id", new JsonPrimitive(rs.getString("standard_id")));
			jsonObject.add("class_section_id", new JsonPrimitive(rs.getString("class_section_id")));
			jsonObject.add("standard_desc", new JsonPrimitive(rs.getString("standard_desc")));
			jsonObject.add("section", new JsonPrimitive(rs.getString("section")));
			jsonObject.add("strength", new JsonPrimitive(rs.getString("No. Of Students")));
			//jsonObject.add("class_teacher_id", new JsonPrimitive(rs.getString("class_teacher_id")));
			
		 	jsonData.add(jsonObject);
	 			
		}
		ETHLogger.log(dbConnVar + "_dojo",jsonData.toString());
 			
	} catch (Exception e) {
		ETHLogger.log(dbConnVar + "_dojo",e);

	} finally {
		try {
			if (rs != null) {
				rs.close();
			}
			if (cstmt != null) {
				cstmt.close();
			}
			if(con!=null)
			{
				cf.freeConnection(con);
			}
		} catch (Exception e) {
			ETHLogger.log(dbConnVar + "_dojo",e);
		}
	}
	
return jsonData;
}

public JsonArray getTeacherwiseClasswiseSubjects(String teacherLoginId,int classStructureId,int acadId){
	
	
	CallableStatement cstmt = null;
	ResultSet rs = null;
 	Connection con=cf.getConnection();
	 
	JsonArray jsonData=new JsonArray();
	 
 
	try {
		cstmt = con.prepareCall("{ ? = call fun_retrieve_teacher_wise_class_wise_subjects(?,?,?,?,?) }");
		cstmt.registerOutParameter(1,Types.OTHER);
		cstmt.setString(2, teacherLoginId);
		cstmt.setInt(3, classStructureId);
		cstmt.setInt(4,acadId);
   	 	cstmt.setObject(5, rs);
		
		con.setAutoCommit(false); // without this the Result set is not returning
		cstmt.execute();
		rs = (ResultSet) cstmt.getObject(1);
		
	 
		while(rs.next() ) {
			JsonObject jsonObject = new JsonObject();	
			jsonObject.add("course_id", new JsonPrimitive(rs.getString("course_id")));
			jsonObject.add("course_name", new JsonPrimitive(rs.getString("course_name")));
	 	 	jsonData.add(jsonObject);
	 			
		}
		ETHLogger.log(dbConnVar + "_dojo",jsonData.toString());
 			
	} catch (Exception e) {
		ETHLogger.log(dbConnVar + "_dojo",e);

	} finally {
		try {
			if (rs != null) {
				rs.close();
			}
			if (cstmt != null) {
				cstmt.close();
			}
			if(con!=null)
			{
				cf.freeConnection(con);
			}
		} catch (Exception e) {
			ETHLogger.log(dbConnVar + "_dojo",e);
		}
	}
	
return jsonData;
}


public JsonArray getSubjectListForTeacher(String teacherLoginId){
	
	
	CallableStatement cstmt = null;
	ResultSet rs = null;
 	Connection con=cf.getConnection();
	 
	JsonArray jsonData=new JsonArray();
	
//	select fun_retrieve_class_teacher_class_strength('21/05/2018','N',29,'EMP112','C','c'); fetch all in c;
	try {
		 
	 	cstmt = con.prepareCall("{ ? = call fun_retrieve_class_subject_list_for_specified_teacher(?,?) }");
		cstmt.registerOutParameter(1,Types.OTHER);
		cstmt.setString(2,teacherLoginId);
		cstmt.setObject(3, rs);
	 	con.setAutoCommit(false); // without this the Result set is not returning
		cstmt.execute();
		rs = (ResultSet) cstmt.getObject(1);
		
	 
		while(rs.next() ) {
			JsonObject jsonObject = new JsonObject();	
			jsonObject.add("class_structure_id", new JsonPrimitive(rs.getString("class_structure_id")));
			jsonObject.add("standard_id", new JsonPrimitive(rs.getString("standard_id")));
			jsonObject.add("class_section_id", new JsonPrimitive(rs.getString("class_section_id")));
			jsonObject.add("standard_desc", new JsonPrimitive(rs.getString("standard_desc")));
			jsonObject.add("section", new JsonPrimitive(rs.getString("section")));
			
			//jsonObject.add("subject_list", new JsonPrimitive(rs.getString("subject_list")));
			
			jsonObject.add("subject_id", new JsonPrimitive(rs.getString("subject_id")));
			jsonObject.add("course_name", new JsonPrimitive(rs.getString("course_name")));
 			jsonObject.add("strength", new JsonPrimitive(rs.getString("strength")));
			 
			
		 	jsonData.add(jsonObject);
	 			
		}
		ETHLogger.log(dbConnVar + "_dojo",jsonData.toString());
 			
	} catch (Exception e) {
		ETHLogger.log(dbConnVar + "_dojo",e);

	} finally {
		try {
			if (rs != null) {
				rs.close();
			}
			if (cstmt != null) {
				cstmt.close();
			}
			if(con!=null)
			{
				cf.freeConnection(con);
			}
		} catch (Exception e) {
			ETHLogger.log(dbConnVar + "_dojo",e);
		}
	}
	
return jsonData;
}

public JsonArray getSubjectStudentData(int yearIdPre,int classStructureId,int in_subject_id){
	
	
	CallableStatement cstmt = null;
	ResultSet rs = null;
 	Connection con=cf.getConnection();
	 
	JsonArray jsonData=new JsonArray();
	
//	select fun_retrieve_class_teacher_class_strength('21/05/2018','N',29,'EMP112','C','c'); fetch all in c;
	 
	try {
		cstmt = con.prepareCall("{ ? = call fun_retrive_student_list_subjectwise_DOJO(?,?,?,?) }");
		cstmt.registerOutParameter(1,Types.OTHER);
		cstmt.setInt(2, yearIdPre);
		cstmt.setInt(3, classStructureId);
		cstmt.setInt(4, in_subject_id);
		cstmt.setObject(5, rs);
		
		con.setAutoCommit(false); // without this the Result set is not returning
		cstmt.execute();
		rs = (ResultSet) cstmt.getObject(1);
		
	 
		while(rs.next() ) {
			JsonObject jsonObject = new JsonObject();	
			jsonObject.add("enrollment_no", new JsonPrimitive(rs.getString("Enrollment No")));
			jsonObject.add("roll_no", new JsonPrimitive(rs.getString("Roll No")));
			jsonObject.add("name", new JsonPrimitive(rs.getString("Student Name")));
			jsonObject.add("photoPath", new JsonPrimitive(rs.getString("PhotoPath")));
			jsonObject.add("negative_points", new JsonPrimitive(rs.getString("negative_points")));
			jsonObject.add("positive_points", new JsonPrimitive(rs.getString("positive_points")));
			jsonObject.add("total_points", new JsonPrimitive(rs.getString("total_points")));
			
		 	jsonData.add(jsonObject);
	 			
		}
		ETHLogger.log(dbConnVar + "_dojo",jsonData.toString());
 			
	} catch (Exception e) {
		ETHLogger.log(dbConnVar + "_dojo",e);

	} finally {
		try {
			if (rs != null) {
				rs.close();
			}
			if (cstmt != null) {
				cstmt.close();
			}
			if(con!=null)
			{
				cf.freeConnection(con);
			}
		} catch (Exception e) {
			ETHLogger.log(dbConnVar + "_dojo",e);
		}
	}
	
return jsonData;
}









}
