<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student Tracker App</title>
</head>
<link type="text/css" rel="stylesheet" href="css/style.css">

<body>
   <div id="wrapper">
       <div id="header">
         <h2>Nextgen Student Tracker</h2> 
       </div>
    </div>
  
  
  
     <div id="Container">
        <div id="contain">
        
        <!-- add button -->
        
        <input type="button" value="Add Student" 
                onClick="window.location.href='add-student-form.jsp';return false;" 
                class="add-student-button"/>
        
          <table >
              <tr>
                  <th>FIRSTNAME</th>
                  <th>LASTNAME</th>
                  <th>EMAIL</th>
                  <th>ACTION</th>
               </tr>
               
                <c:forEach var="tempStudent" items="${Student_List}">
                  <c:url var="templink" value="StudentControllerServlet">
                     <c:param name="command" value="LOAD"/>
                     <c:param name="studentid" value="${tempStudent.id}"/>
                   </c:url>
                   
                   <c:url var="deletelink" value="StudentControllerServlet">
                     <c:param name="command" value="DELETE"/>
                     <c:param name="studentid" value="${tempStudent.id}"/>
                   </c:url>
                  <tr> 
                    <td>${tempStudent.firstName} </td>
                    <td>${tempStudent.lastName} </td>
                    <td>${tempStudent.email} </td>
                     <td>
                     <a href="${templink}">Update</a>
                      | <a href="${deletelink}" onClick="if(!(confirm('ARE YOU SURE YOU WANT TO DELETE?'))) return false">Delete
                      </a>
                     
                     </td>
                 </tr>
                 </c:forEach>
             
           </table>
        </div>
     </div>


</body>
</html>