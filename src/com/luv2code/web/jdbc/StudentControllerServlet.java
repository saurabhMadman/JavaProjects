package com.luv2code.web.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class StudentControllerServlet
 */
@WebServlet("/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
    private StudentDbUtil studentutil;
    
	@Resource(name="jdbc/web_student_tracker" )
	private DataSource datasource;
  
	@Override
	public void init() throws ServletException
	{
		super.init();
		try
		{
		studentutil =new StudentDbUtil(datasource);
		}catch(Exception ex)
		{
			throw new ServletException(ex);
		}
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try {
			
			//read the command
			String theCommand =request.getParameter("command");
			
			//if the command is missing
			if(theCommand==null)
			{
				theCommand="LIST";
			}
		   
			switch(theCommand)
			{
			case "LIST":list(request,response);
			           break;
			case "ADD" :addStudent(request,response);
			           break;
			case "LOAD":loadStudent(request,response);
	                     break;  
			case "UPDATE":updateStudent(request,response);
                        break; 
			case "DELETE":deleteStudent(request,response);
                        break;             
			               
			default:list(request,response);
			          break;
			}	
		     list(request,response);
		     } catch (Exception e)
		{
		   throw new ServletException(e);
		}
		
		
		
	}

private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
	 
	String id=request.getParameter("studentid");
	
	StudentDbUtil.deletestudent(id);
	
	list(request,response);
		
	}


//update	
	
  private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
	  //retrive data from form
	  int id =Integer.parseInt(request.getParameter("studentid"));
	  String firstname=request.getParameter("firstname");
	  String lastname=request.getParameter("lastname");
	  String email=request.getParameter("email");
	 
	  //save it to object
	  Student theStudent =new Student(id,firstname,lastname,email);
	  
	  //update the db
	  StudentDbUtil.updateStudent(theStudent);
	  
	  list(request,response);	  
	}


//load data to update form
	
	private void loadStudent(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
	   //get the id
	    String studentId=request.getParameter("studentid");
		//get student from db
		Student thestudent = StudentDbUtil.getstudent(studentId);
	
		//place data in student request
		request.setAttribute("THE_STUDENT",thestudent);
		
		// forward it to jsp
		RequestDispatcher dispatcher =request.getRequestDispatcher("/update-student-form.jsp");
		dispatcher.forward(request,response);
	}


	private void addStudent(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
	   //read data from from
		 String firstName=request.getParameter("firstname");
		 String lastName=request.getParameter("lastname");
		 String email=request.getParameter("email");
		 
	   //create new student object
		 Student thestudent =new Student(firstName,lastName,email);
		 
		//add it to database
		 StudentDbUtil.addStudent(thestudent);
		 
		//send data back to list page
		 list(request,response);
	  
	}


	private void list(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		
			List<Student> student=studentutil.getStudent();
		    request.setAttribute("Student_List", student);
		   
		    //returning to View
		    RequestDispatcher dispatcher=request.getRequestDispatcher("/list-student.jsp");
		    dispatcher.forward(request,response);		
		  
		    
	}

	

}
