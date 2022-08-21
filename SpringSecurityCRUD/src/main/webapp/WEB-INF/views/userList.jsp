<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="content-wrapper">
  <section class="content">
    <div class="row">
      <div class="col-12">
        <div class="card">
          <div class="card-header">
            <h3 class="card-title">User List</h3>
          </div>
          <div class="card-body">
            <div class="row">
              <div class="col-sm-12 col-md-12">
                <div class="search-sec">
                  <c:url var="addAction" value="/searchStudent"></c:url>
                  <a
                    href="${pageContext.request.contextPath}/createUser"
                    class="btn btn-info">Add</a>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-sm-12">
                <table id="example1"
                  class="table table-bordered table-striped">
                  <thead>
                    <tr>
                      <th>ID</th>
                      <th>Name</th>
                      <th>Email</th>
                      <th>Authorities</th>
                      <th>Action</th>
                    </tr>
                  </thead>
                  <tbody>
                    <c:forEach items="${PersonList}" var="person"
                      varStatus="loop">
                      <c:if test="${person.deleteAt == null }">
                      <tr>
                        <td>${person.id }</td>
                        <td>${person.username}</td>
                        <td>${person.email }</td>
                        <c:forEach items="${person.authorities}"
                          var="autho" varStatus="loop">
                          <td>${autho.name}</td>
                        </c:forEach>
                        <td class="text-right py-0 align-middle">
                          <div class="btn-group btn-group-sm">
                            <a
                              href="${pageContext.request.contextPath}/updateUser?id=${person.id}"
                              class="btn btn-secondary"><i
                              class="fas fa-edit"></i></a> <a href="#"
                              data-toggle="modal"
                              data-href="${pageContext.request.contextPath}/deleteUser?id=${person.id}"
                              data-target="#myModal"
                              class="btn btn-danger"><i
                              class="fas fa-trash"></i></a>
                          </div>
                        </td>
                      </tr>
                      </c:if>
                    </c:forEach>
                  </tbody>
                </table>

              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
  <form action="<%=request.getContextPath()%>/logout" method="POST">
    <input type="submit" value="Logout" /> <input type="hidden"
      name="${_csrf.parameterName}" value="${_csrf.token}" />
  </form>
</div>
<div class="modal fade" id="myModal" role="dialog">
  <div class="modal-dialog">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title">Delete Student Confirmation!</h4>
      </div>
      <div class="modal-body">
        <p>Are You Sure Want to Delete!</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default"
          data-dismiss="modal">Cancel</button>
        <a class="btn btn-danger btn-ok">OK</a>
      </div>
    </div>
  </div>
</div>
<script>
	$('#myModal').on(
			'show.bs.modal',
			function(e) {
				$(this).find('.btn-ok').attr('href',
						$(e.relatedTarget).data('href'));
				$('.debug-url').html(
						'Delete URL: <strong>'
								+ $(this).find('.btn-ok').attr('href')
								+ '</strong>');
			});
</script>