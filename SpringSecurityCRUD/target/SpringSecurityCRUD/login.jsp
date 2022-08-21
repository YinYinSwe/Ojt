<html>
<body>
  <h3>Login Page</h3>
  <font color="red"> ${SPRING_SECURITY_LAST_EXCEPTION.message} </font>
  <form action="<%=request.getContextPath()%>/login" method="POST">
    Enter UserName: <input type="text" name="username" /><br />
    <br /> Enter Password: <input type="password" name="password" /> <br />
    <br /> <input type="submit" value="Login" /> <input type="hidden"
      name="${_csrf.parameterName}" value="${_csrf.token}" />

  </form>
  <a href="forgot_password">Forgot your password?</a>

</body>
</html>
