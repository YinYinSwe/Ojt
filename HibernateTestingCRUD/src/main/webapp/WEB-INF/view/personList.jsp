<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%-- <div align="center">
  <h1>Student List</h1>
  <table border="1">
    <tr>
      <th>ID</th>
      <th>Roll Number</th>
      <th>Name</th>
      <th>Major</th>
      <th>Year</th>
      <th>Email</th>
      <th>Date of Birth</th>
      <!-- <th>Action</th> -->
    </tr>
    <c:forEach var="student" items="${StudentList}">
      <tr>
        <td>${student.id }</td>
        <td>${student.roll_number }</td>
        <td>${student.name}</td>
        <td>${student.major }</td>
        <td>${student.year }</td>
        <td>${student.email}</td>
        <td>${student.dob}</td>
        <td><a href="editStudent?id=${student.id}">Edit</a>
          &nbsp;&nbsp;&nbsp;&nbsp; <a
          href="deleteStudent?id=${student.id}">Delete</a></td>
      </tr>
    </c:forEach>
  </table>
</div> --%>

<div class="content-wrapper">
	<section class="content">
		<div class="row">
			<div class="col-12">
				<div class="card">
					<div class="card-header">
						<h3 class="card-title">Person List</h3>
					</div>
					<div class="card-body">
						<div class="row">
							<div class="col-sm-12 col-md-12">
								<div class="search-sec">
									<c:url var="addAction" value="/searchStudent"></c:url>
									<%-- <form:form action="${addAction}"
                    commandName="studentForm" method="student" id="form"
                    class="searchForm-mr">
                    <label><input type="text"
                      class="form-control form-control-sm search-text-pd"
                      aria-controls="example1" name="search-input"
                      value="${searchData }"></label>
                    <input name="searchStudent" type="submit"
                      value="Search" class="btn btn-secondary">
                    
                    <a
                      href="${pageContext.request.contextPath}/uploadCSV"
                      class="btn btn-info">Upload</a>
                    <input type="submit" class="btn btn-info"
                      value="Download" name="downloadExcel">
                  </form:form> --%>
									<a href="${pageContext.request.contextPath}/createPerson"
										class="btn btn-info">Add</a>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<table id="example1" class="table table-bordered table-striped">
									<thead>
										<tr>
											<th>ID</th>
											<th>Name</th>
											<th>Country</th>
											<th>Action</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${PersonList}" var="person" varStatus="loop">
											<tr>
												<td>${person.id }</td>
												<td>${person.name}</td>
												<td>${person.country}</td>
												<td class="text-right py-0 align-middle">
													<div class="btn-group btn-group-sm">
														<a
															href="${pageContext.request.contextPath}/updatePerson?id=${person.id}"
															class="btn btn-secondary"><i class="fas fa-edit"></i></a>
														<a href="#" data-toggle="modal"
															data-href="${pageContext.request.contextPath}/deletePerson?id=${person.id}"
															data-target="#myModal" class="btn btn-danger"><i
															class="fas fa-trash"></i></a>
													</div>
												</td>
											</tr>
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
				<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
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