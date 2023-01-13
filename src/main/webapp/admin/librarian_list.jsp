<%@ include file="include/header.jsp"%>
<%@ page import="java.util.List"%>
<%@ page import="bookMaster.entity.Admin"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<main id="main" class="main">

	<div class="pagetitle">
		<h1>List of Librarian</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a
					href="${pageContext.request.contextPath}/admin/">Home</a></li>
				<li class="breadcrumb-item">Librarian</li>
				<li class="breadcrumb-item active">List</li>
			</ol>
		</nav>
	</div>
	<!-- End Page Title -->


	<div>
		<a
			href="${pageContext.request.contextPath }/admin/librarian?action=addLibrarian"
			class="btn btn-primary">Add Librarian</a>
	</div>
	<br>
	<section class="section">
		<div class="row">
			<div>
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">List of Librarian</h5>

						<!-- Default Table -->
						<table class="table">
							<thead>
								<tr>
									<th scope="col">#</th>
									<th scope="col">User Name</th>
									<th scope="col">Position</th>
									<th scope="col">Status</th>
									<th scope="col">Start Date</th>
									<th scope="col">Actions</th>
								</tr>
							</thead>
							<tbody>
								<c:set var="jinddex" value="1"></c:set>

								<c:forEach items="${listLibrarian }" var="lib">
									<c:url var="statusURL" value="/admin/librarian">
										<c:param name="action" value="statusLibrarian"></c:param>
										<c:param name="admin_id" value="${ lib.adminId }"></c:param>
									</c:url>

									<c:url var="updateURL" value="/admin/librarian">
										<c:param name="action" value="updateLibrarian"></c:param>
										<c:param name="admin_id" value="${ lib.adminId }"></c:param>
									</c:url>

									<c:url var="deleteURL" value="/admin/librarian">
										<c:param name="action" value="deleteLibrarian"></c:param>
										<c:param name="admin_id" value="${ lib.adminId }"></c:param>
									</c:url>




									<tr>
										<td>${jinddex }</td>
										<td>${lib.userName }</td>
										<td>Librarian</td>
										<td><c:if test="${lib.status == 1}">
												<span class="badge bg-success"> <i
													class="bi bi-check-circle me-1"></i> Active
												</span>
											</c:if> <c:if test="${lib.status == 0 }">
												<span class="badge bg-secondary"> <i
													class="bi bi-exclamation-triangle me-11"></i> In-Active
												</span>
											</c:if></td>
										<td>${lib.createdAt }</td>
										<td><a href="${updateURL }"> <i
												class="bi bi-pencil-square text-info"></i>
										</a> <a href=${deleteURL }
											onclick="if(!confirm('Are you sure to delete the librarian?')) return false">
												<i class="bi bi-trash text-danger"></i>
										</a></td>
									</tr>
									<c:set var="jinddex" value="${jinddex+1 }"></c:set>
								</c:forEach>
							</tbody>
						</table>
						<!-- End Default Table Example -->
					</div>
				</div>
			</div>
		</div>
	</section>
</main>

<%@ include file="include/footer.jsp"%>