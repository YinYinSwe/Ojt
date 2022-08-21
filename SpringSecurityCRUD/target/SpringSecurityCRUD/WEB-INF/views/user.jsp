<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Home</title>
</head>
<body>
  <h3>User Page</h3>
  <form action="<%=request.getContextPath()%>/logout" method="POST">
    <input type="submit" value="Logout" /> 
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
  </form>
</body>
</html>
