package com.luv2code.web.jdbc;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
//Resource injection
    @Resource(name="jdbc/web_student_tracker")
    private DataSource dataSource;
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//1. PrintWriter
	    PrintWriter out =response.getWriter();
		response.setContentType("text/plain");
		
		//2.getting Connection
		
		Connection myconn=null;
		Statement mystmt=null;
		ResultSet myRs=null;
		
		try
	    {
			//3.Creating Sql Connection
			myconn=dataSource.getConnection();

			//4.Creating sql Statement 
			String sql="Select * from student";
			mystmt=myconn.createStatement();
			
			//Execution
			myRs=mystmt.executeQuery(sql);
			
			
			//5.result
			
			while(myRs.next())
			{
				String email =myRs.getString("email");
				out.println(email);
			}
			
			
			
	    }catch(Exception ex)
		{
	    	ex.printStackTrace();
	    	
		}
		
		
		
		
		
	}

}
