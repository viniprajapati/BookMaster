<%@ include file="include/header.jsp"%>
<%@ page import="java.util.List"%>
<%@ page import="bookMaster.entity.Users"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<main id="main" class="main">

	<div class="pagetitle">
		<h1>List of Users</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a
					href="${pageContext.request.contextPath}/admin/">Home</a></li>
				<li class="breadcrumb-item">Users</li>
				<li class="breadcrumb-item active">List</li>
			</ol>
		</nav>
	</div>
	<!-- End Page Title -->


	<div>
		<a
			href="${pageContext.request.contextPath }/admin/user?action=addUser"
			class="btn btn-primary">Add User</a>
	</div>
	<br>
	<section class="section">
		<div class="row">
			<div>
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">List of User</h5>

						<!-- Default Table -->
						<table class="table">
							<thead>
								<tr>
									<th scope="col">#</th>
									<th scope="col">Profile Photo</th>
									<th scope="col">User Name</th>
									<th scope="col">Email</th>
									<th scope="col">Status</th>
									<th scope="col">Created Date</th>
									<th scope="col">Actions</th>
								</tr>
							</thead>
							<tbody>
								<c:set var="jinddex" value="1"></c:set>

								<c:forEach items="${listUsers }" var="user">
									<c:url var="statusURL" value="/admin/user">
										<c:param name="action" value="statusUser"></c:param>
										<c:param name="user_id" value="${ user.userId }"></c:param>
									</c:url>

									<c:url var="updateURL" value="/admin/user">
										<c:param name="action" value="updateUser"></c:param>
										<c:param name="user_id" value="${ user.userId }"></c:param>
									</c:url>

									<c:url var="deleteURL" value="/admin/user">
										<c:param name="action" value="deleteUser"></c:param>
										<c:param name="user_id" value="${ user.userId }"></c:param>
									</c:url>




									<tr>
										<td>${jinddex }</td>
										<td></td>
										<td>${user.userName }</td>
										<td>${user.email }</td>
										<td><c:if test="${user.status == 1}">
												<span class="badge bg-success"> <i
													class="bi bi-check-circle me-1"></i> Active
												</span>
											</c:if> <c:if test="${user.status == 0 }">
												<span class="badge bg-secondary"> <i
													class="bi bi-exclamation-triangle me-11"></i> In-Active
												</span>
											</c:if></td>
										<td>${user.createdAt }</td>
										<td><a href="${updateURL }"> <i
												class="bi bi-pencil-square text-info"></i>
										</a> <a href=${deleteURL }
											onclick="if(!confirm('Are you sure to delete the user?')) return false">
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