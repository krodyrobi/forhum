<%@ page language="java" 
    contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
  <head>
    <title>Register - Foruhm</title>
  <head>
  
  <body>
    <div><h2>Register</h2></div>
    
    <c:if test="${not empty errors}">
      <ul>
      <c:forEach items="${errors}" var="error">
        <li><c:out value="${error}"/></li>
      </c:forEach>
      </ul>
    </c:if>

    <form method="POST" action="register">
      Username:<input type="text" name="username" value="${username}"/>
      Password:<input type="password" name="password" />
      
      <input type="submit" value="submit">
    </form>
  </body>
</html>
