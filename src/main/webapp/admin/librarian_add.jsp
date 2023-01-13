<%@ include file="include/header.jsp"%>
<main id="main" class="main">

	<div class="pagetitle">
		<h1>Form Elements</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a
					href="${pageContext.request.contextPath}/admin/">Home</a></li>
				<li class="breadcrumb-item"><a
					href="${pageContext.request.contextPath}/admin/librarian?action=list">Librarian</a>
				</li>
				<li class="breadcrumb-item active">Add Librarian</li>
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
						<form class="row g-3" action="${pageContext.request.contextPath }/admin/librarian?action=addLibrarianForm" method="post">

							<div class="col-md-6">
								<label for="validationCustom01" class="form-label">Username</label>
							 	<input type="text" name="userName" value="" class="form-control" id="validationCustom01">
							</div>
							<div class="col-md-6">
								<label for="validationCustom02" class="form-label">Password</label>
								<input type="password" name="password" value="" class="form-control" id="validationCustom02">
							</div>
							<div class="col-md-6">
								<label class="col-form-label col-sm-2 pt-0">Role</label>
								<div class="col-sm-10">
				                    <div class="form-check">
				                      <input class="form-check-input" type="radio" name="role" id="gridRadios1" value="2" checked>
				                      <label class="form-check-label" for="gridRadios1">
				                        Librarian
				                      </label>
				                    </div>
				                    <div class="form-check">
				                      <input class="form-check-input" type="radio" name="role" id="gridRadios2" value="1">
				                      <label class="form-check-label" for="gridRadios2">
				                        Admin
				                      </label>
				                    </div>
			                    </div>
							</div>
							<div class="col-md-6">
								<label for="validationCustom03" class="form-label">Status</label>
								<select name="status" class="form-select" id="validationCustom03">
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