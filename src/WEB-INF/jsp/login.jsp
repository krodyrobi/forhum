<%@ page language="java" 
    contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
  <head>
    <title>Login - Foruhm</title>
  <head>
  
  <body>
    <c:if test="${not empty errors}">
      <c:forEach items="${errors}" var="error">
        <p><c:out value="${error}"/></p><br/>
      </c:forEach>
    </c:if>

    <form method="POST" action="login">
      Username:<input type="text" name="username" value="${username}"/>
      Password:<input type"password" name="password" />
      
      <input type="submit" value="submit">
    </form>
  </body>
</html>
