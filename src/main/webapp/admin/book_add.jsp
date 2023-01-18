<%@ include file="include/header.jsp"%>
<%@ page import="java.util.List"%>
<%@ page import="bookMaster.entity.Stream"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<main id="main" class="main">

	<div class="pagetitle">
		<h1>Form Elements</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a
					href="${pageContext.request.contextPath}/admin/">Home</a></li>
				<li class="breadcrumb-item"><a
					href="${pageContext.request.contextPath}/admin/book?action=allBooks">Books</a>
				</li>
				<li class="breadcrumb-item active">Add Book</li>
			</ol>
		</nav>
	</div>
	<!-- End Page Title -->

	<section class="section">
		<div class="row">
			<div class="col-lg-12">

				<div class="card">
					<div class="card-body">
						<h5 class="card-title">General Form Elements</h5>

						<!-- General Form Elements -->
						<form class="row g-3" action="${pageContext.request.contextPath }/admin/book?action=addBookForm" method="post" enctype="multipart/form-data">

							<div class="col-md-6">
								<label for="validationCustom01" class="form-label">Book name</label>
							 	<input type="text" name="name" class="form-control" id="validationCustom01">
							</div>
							<div class="col-md-6">
			                  	<label for="validationCustom03" class="col-sm-2 col-form-label">File Upload</label>
			                  	<div class="col-sm-10">
			                   	 	<input type="file" name="book_img" value="" class="form-control" id="validationCustom03" accept="image/jpg">
			                  	</div>
			                </div>
							<div class="col-md-6">
								<label for="validationCustom02" class="form-label">Author</label>
							 	<input type="text" name="author" class="form-control" id="validationCustom02">
							</div>
							<div class="col-md-6">
								<label for="validationCustom02" class="form-label">Publisher</label>
							 	<input type="text" name="publisher" class="form-control" id="validationCustom02">
							</div>
							<div class="col-md-6">
								<label for="validationCustom05" class="form-label">Stream</label>
								<select name="stream_id" class="form-select" id="validationCustom05">
									<option disabled selected>Choose..</option>
									<c:forEach items="${streams }" var="stream">
										<option value="${stream.streamId }">${stream.name }</option>
									</c:forEach>
								</select>
							</div>
							<div class="col-md-6">
								<label for="validationCustom04" class="form-label">Total Book</label>
								<input type="number" name="total_book" value="" class="form-control" id="validationCustom04">
							</div>
							<div class="col-md-6">
								<label for="validationCustom06" class="form-label">Status</label>
								<select name="status" class="form-select" id="validationCustom06">
									<option selected value="1">Active</option>
									<option value="0">In-Active</option>
								</select>
							</div>
							<div class="text-center">
								<button type="submit" class="btn btn-primary">Submit</button>
								<button type="reset" class="btn btn-secondary">Reset</button>
							</div>

						</form>
						<!-- End General Form Elements -->
					</div>
				</div>
			</div>
		</div>
	</section>
</main>
<%@ include file="include/footer.jsp"%>