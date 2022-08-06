<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="content-wrapper">
  <section class="content">
    <div class="row">
      <div class="col-12">
        <div class="forms-mr">
          <div class="col-sm-6 col-md-6 form-detail">
            <c:url var="createPerson" value="/createStudentConfirm"></c:url>
            <form:form class="form-detail" action="insertPerson"
              method="POST" id="form"
              modelAttribute="rollBackPersonForm">
              <div class="card card-primary card-outline">
                <div class="card-body box-profile">
                  <h4 class="text-center forms-header">
                    <b>Person Create</b>
                  </h4>
                  <c:if test="${errorMsg != null }">
                    <div class="alert alert-danger">
                      <strong>${errorMsg }</strong>
                    </div>
                  </c:if>
                  <div class="form-group">
                    <label for="name">Name</label>
                    <form:input path="name"
                      value="${rollBackPersonForm.name }"
                      class="form-control" placeholder="Enter Name" />
                    <form:errors path="name" class="text-danger" />
                  </div>
                  <div class="form-group">
                    <label for="country">Country</label>
                    <form:input path="country"
                      value="${rollBackPersonForm.country }"
                      class="form-control" placeholder="Enter country" />
                    <form:errors path="country" class="text-danger" />
                  </div>

                </div>
                <button type="submit" class="btn btn-info"
                  name="addStudent">Add</button>
              </div>
            </form:form>
          </div>
        </div>
      </div>
    </div>
  </section>
</div>