<%@ include file="include/header.jsp"%>
<%@ page import="java.util.List"%>
<%@ page import="bookMaster.entity.Books"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<main id="main" class="main">

	<div class="pagetitle">
		<h1>List of Books</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a
					href="${pageContext.request.contextPath}/admin/">Home</a></li>
				<li class="breadcrumb-item">Books</li>
				<li class="breadcrumb-item active">List</li>
			</ol>
		</nav>
	</div>
	<!-- End Page Title -->


	<div>
		<a
			href="${pageContext.request.contextPath }/admin/book?action=addBook"
			class="btn btn-primary">Add Book</a>
	</div>
	<br>
	<section class="section">
		<div class="row">
			<div>
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">List of Books</h5>

						<!-- Default Table -->
						<table class="table">
							<thead>
								<tr>
									<th scope="col">#</th>
									<th scope="col">Photo</th>
									<th scope="col">Book Name</th>
									<th scope="col">Author</th>
									<th scope="col">Publisher</th>
									<th scope="col">Stream</th>
									<th scope="col">Total Books</th>
									<th scope="col">Issued Books</th>
									<th scope="col">Left Books</th>
									<th scope="col">Lost Books</th>
									<th scope="col">Status</th>
									<th scope="col">Created Date</th>
									<th scope="col">Actions</th>
								</tr>
							</thead>
							<tbody>
								<c:set var="jinddex" value="1"></c:set>

								<c:forEach items="${bookList }" var="book">

									<c:url var="updateURL" value="/admin/book">
										<c:param name="action" value="updateBook"></c:param>
										<c:param name="book_id" value="${ book.bookId }"></c:param>
									</c:url>

									<c:url var="deleteURL" value="/admin/book">
										<c:param name="action" value="deleteBook"></c:param>
										<c:param name="book_id" value="${ book.bookId }"></c:param>
									</c:url>

									<tr>
										<td>${jinddex }</td>
										<td><img src="G:/JAVA/upload_img/book_img/${book.bookPhoto }" alt="img" /></td>
										<td>${book.bookName }</td>
										<td>${book.author }</td>
										<td>${book.publisher }</td>
										<td>${book.streamId }</td>
										<td>${book.totalBook }</td>
										<td>${book.issuedBook }</td>
										<td>${book.leftBook }</td>
										<td>${book.lostBook }</td>
										<td><c:if test="${book.status == 1}">
												<span class="badge bg-success"> <i
													class="bi bi-check-circle me-1"></i> Active
												</span>
											</c:if> <c:if test="${book.status == 0 }">
												<span class="badge bg-secondary"> <i
													class="bi bi-exclamation-triangle me-11"></i> In-Active
												</span>
											</c:if></td>
										<td>${book.createdAt }</td>
										<td><a href="${updateURL }"> <i
												class="bi bi-pencil-square text-info"></i>
										</a> <a href=${deleteURL }
											onclick="if(!confirm('Are you sure to delete the book?')) return false">
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