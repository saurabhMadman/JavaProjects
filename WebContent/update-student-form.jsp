<!DOCTYPE html>

<html>
<head>
<title>Add Student</title>

<link type="text/css" rel="stylesheet" href="css/style.css">
<link type="text/css" rel="stylesheet" href="css/add-student-style.css">
<script>
function validateForm() {
    var x = document.forms["myForm"]["firstname"].value;
    var y = document.forms["myForm"]["lastname"].value;
    var z = document.forms["myForm"]["email"].value;
    if (x == "" ||y==""||z=="") {
        alert("Form Must be filled out");
        return false;
    }
}
</script>
</head>
<body>
      <div id="wrapper">
         <div id="header">
             <h2>NEXTGEN STUDENT TRACKER</h2>
         </div>   
       </div>  
      
      <div="Container">
        <h3>UPDATE STUDENT</h3>
        
        <form action="StudentControllerServlet" method="GET"  onsubmit="return validateForm()" name="myForm">
        
        <input type="hidden" name="command" value="UPDATE"/>
        <input type="hidden" name="studentid" value="${THE_STUDENT.id}"/>
           <table>
              <tbody>
                  <tr>
                      <td><label>FIRST NAME:</label></td>
                      <td><input type="text" name="firstname" value="${THE_STUDENT.firstName}" />
                  </tr>
                   <tr>
                      <td><label>LAST NAME:</label></td>
                      <td><input type="text" name="lastname" value="${THE_STUDENT.lastName}"/>
                  </tr>
                   <tr>
                      <td><label>Email:</label></td>
                      <td><input type="text" name="email" value="${THE_STUDENT.email}"/>
                  </tr> 
                   <tr>
                      <td><label></label></td>
                      <td><input type="submit" value="Save" class="save"/>
                  </tr>              
              </tbody>
            </table>
        </form>
        
        <div style="clear:both;" > </div>
          <p><a href="StudentControllerServlet">Back to list</a></p>
       
      </div>
      
</body>
</html>