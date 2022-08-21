<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
  pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<link rel="stylesheet"
  href='<c:url value="/resources/css/adminlte.css"/>'>
<style>
.btn-reset {
  background-color: #28a745 !important;
}
</style>
<div class="content-wrapper">
  <section class="content">
    <div class="row">
      <div class="col-12">
        <div class="forms-mr">
          <div class="col-sm-6 col-md-6 form-detail">
            <c:url var="updatePerson" value="/editUser"></c:url>
            <form:form class="form-create" action="editUser"
              method="POST" id="form" modelAttribute="editedPersonForm">
              <input type="hidden" name="id"
                value="${editedPersonForm.id }" />
              <div class="card card-primary card-outline">
                <div class="card-body box-profile">
                  <h4 class="text-center forms-header">
                    <b>User Edition</b>
                  </h4>
                  <c:if test="${errorMsg != null }">
                    <div class="alert alert-danger">
                      <strong>${errorMsg }</strong>
                    </div>
                  </c:if>

                  <div class="form-group">
                    <label for="username">Name</label> <input
                      class="form-control" name="username"
                      value="${editedPersonForm.username }">
                    <form:errors path="username" class="text-danger" />
                  </div>

                  <div class="form-group">
                    <label for="password">Password</label> <input
                      class="form-control" name="password"
                      value="${editedPersonForm.password }" />
                    <form:errors path="password" class="text-danger" />
                  </div>
                  <div class="form-group">
                    <label for="email">Email</label> <input
                      class="form-control" name="email"
                      value="${editedPersonForm.email }" />
                    <form:errors path="password" class="text-danger" />
                  </div>
                  <div class="form-group">
                    <label for="authority">Authority Role</label>
                    <form:select path="authority.id"
                      value="${editedPersonForm.authority.id}"
                      class="form-select">
                      <c:forEach items="${AuthorityList}"
                        var="authority" varStatus="loop">
                        <option value="${authority.id }"
                          <c:if test="${editedPersonForm.authority.id == authority.id }"> selected</c:if>>
                          ${authority.name}</option>
                      </c:forEach>
                    </form:select>
                  </div>
                </div>

                <button type="submit" class="btn btn-info" name="update">Update</button>
            </form:form>
          </div>
        </div>

      </div>
    </div>
</div>
</div>
</section>
</div>