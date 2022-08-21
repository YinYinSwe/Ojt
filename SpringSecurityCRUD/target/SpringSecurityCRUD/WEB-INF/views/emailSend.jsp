<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

  <c:url value="/sendEmail" var="send">
  </c:url>

  <form:form action="${send }" modelAttribute="emailForm" method="post"
    style="max-width: 420px; margin: 0 auto;">
    <div class="border border-secondary rounded p-3">
      <div>
        <p>We will be sending a reset password link to your email.</p>
      </div>
      <div>
        <p>
          <form:input path="user_email" type="user_email" name="user_email"
            class="form-control" placeholder="Enter your e-mail" />
          <c:if test="${errorMsg != null}">
            <div>
              <span class="text-danger">${errorMsg }</span>
            </div>
          </c:if>
        <div class="text-center">
          <button type="submit" class="btn btn-primary" >Send</button>

        </div>
      </div>
    </div>
  </form:form>
</body>
</html>