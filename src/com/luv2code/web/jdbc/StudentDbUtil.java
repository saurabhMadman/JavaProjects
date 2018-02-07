package com.luv2code.web.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.mysql.jdbc.PreparedStatement;

public class StudentDbUtil
{
	private static DataSource dataSource;

	public StudentDbUtil(DataSource thedataSource)
	{
		super();
		dataSource =thedataSource;
	}
	
	
	public List<Student> getStudent() throws Exception
	{
	    List<Student> students =new ArrayList<>();
	    
	    Connection con=null;
	    Statement stmt=null;
	    ResultSet rs=null;
	    
	    try
	    {
	    	con=dataSource.getConnection();
	    	
	    	String sql="Select * from student order by last_Name";
	    	stmt=con.createStatement();
	    	
	    	rs=stmt.executeQuery(sql);
	    	
	    	while(rs.next())
	    	{
	    		int id=rs.getInt("id");
	    		String firstName=rs.getString("first_Name");
	    		String lastName=rs.getString("last_Name");
	    		String email=rs.getString("email");
	    		
	    		//creating student object
	    		Student tempstudent=new Student(id,firstName,lastName,email);
	    		
	    		//adding it to the student list
	    		students.add(tempstudent);
	    		
	    	}
	    	

			return students;
	    }
	    finally
	    {
	    	close(con,stmt,rs);
	    }	    
		
	}


	private static void close(Connection con, Statement stmt, ResultSet rs) 
	{
	   try
	   {
		   if(con!=null)
		   {
			   con.close();
		   }
		   if(stmt!=null)
		   {
			   stmt.close();
		   }
		   if(rs!=null)
		   {
			   rs.close();
		   }
	   }
		catch(Exception ex)
	   {
			ex.printStackTrace();
	   }
	}


	public static void addStudent(Student thestudent) throws SQLException
	{
		Connection con=null;
		java.sql.PreparedStatement stmt=null;

		
		try{
	       con=dataSource.getConnection();
		//crete sql  for insertion
		 String sql="insert into student"
				    +"(first_name,last_name,email)"
                    +"values (?,?,?)";
		  
		stmt=con.prepareStatement(sql);
		//set param values to the student
		
		 stmt.setString (1,thestudent.getFirstName());
		 stmt.setString (2,thestudent.getLastName());
		 stmt.setString (3,thestudent.getEmail());
		
		//excute sql
		 
		 stmt.execute();
		}
		finally 
		{
		//clean jdbc
			close(con,stmt,null);
		}
	}


	public static Student getstudent(String studentid) throws Exception {
		
		String firstname=null;
		String lastname=null;
		String  email=null;
	    int sid;
		Student thestudent=null;
		
		
		Connection con=null;
	    ResultSet rs =null;
		java.sql.PreparedStatement pr=null;
		int studentId;
		
		
			studentId=Integer.parseInt(studentid);try{
		
			
			con=dataSource.getConnection();
			
			String query="select * from student where id = ?";
			
			pr=con.prepareStatement(query);
			
			pr.setInt(1, studentId);
			
			rs= pr.executeQuery();
			
			if(rs.next())
			{
				 sid=rs.getInt("id");
				 firstname=rs.getString("first_name");
				 lastname=rs.getString("last_name");
				 email=rs.getString("email");
			    
				 thestudent=new Student(sid,firstname,lastname,email);
			}
			else
			{
				throw new Exception("Could not found the id" +studentId);
			}
			return thestudent;
		
			
		   }finally
    		{
			  close(con,pr,rs);
		    }	
	
	}

// dbupdate
	
	public static void updateStudent(Student theStudent) throws Exception 
	{
		Connection con=null;
		java.sql.PreparedStatement ptr=null;
		
		try
		{
		con=dataSource.getConnection();
		
		String query= "update student "
		              + "set first_name=?, last_name=?, email=? "
				      + "where id=?";
		
		ptr=con.prepareStatement(query);
		
		ptr.setString(1, theStudent.getFirstName());
		ptr.setString(2, theStudent.getLastName());
		ptr.setString(3, theStudent.getEmail());
		ptr.setInt(4, theStudent.getId());
		
		ptr.execute();
		}
		finally
		{
			close(con,ptr,null);
		}		
	}

//dbdelete
	public static void deletestudent(String id) throws SQLException 
	{
	   Connection con=null;
	   java.sql.PreparedStatement ptr=null;
	   
	   try
	   {
		  //getting connection. 
		  con=dataSource.getConnection();
		  
		  //converting id into int
		  
		  int sid=Integer.parseInt(id);
		  
		  String query="delete from student where id=?";
		  
		  ptr=con.prepareStatement(query);
		  
		  ptr.setInt(1, sid);
		  ptr.execute();
		  
		   
	   }
	   finally
	   {
		   close(con,ptr,null);
	   }
		
	}
	
}
