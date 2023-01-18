<%@ include file="include/header.jsp"%>
<%@ page import="java.util.List"%>
<%@ page import="bookMaster.entity.Stream"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<main id="main" class="main">

	<div class="pagetitle">
		<h1>List of Stream</h1>
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a
					href="${pageContext.request.contextPath}/admin/">Home</a></li>
				<li class="breadcrumb-item">Stream</li>
				<li class="breadcrumb-item active">List</li>
			</ol>
		</nav>
	</div>
	<!-- End Page Title -->


	<%-- <div>
		<a
			href="${pageContext.request.contextPath }/admin/stream?action=addStream"
			class="btn btn-primary">Add Stream</a>
	</div> --%>
	<div>
		<button type="button" class="btn btn-primary" data-bs-toggle="modal"
			data-bs-target="#addStream">Add Stream</button>
	</div>

	<br>
	<section class="section">
		<div class="row">
			<div>
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">List of Stream</h5>

						<!-- Default Table -->
						<table class="table">
							<thead>
								<tr>
									<th scope="col">#</th>
									<th scope="col">Stream Name</th>
									<th scope="col">Status</th>
									<th scope="col">Created Date</th>
									<th scope="col">Actions</th>
								</tr>
							</thead>
							<tbody>
								<c:set var="jinddex" value="1"></c:set>

								<c:forEach items="${listStream }" var="stream">
									<c:url var="statusURL" value="/admin/stream">
										<c:param name="action" value="statusStream"></c:param>
										<c:param name="stream_id" value="${ stream.streamId }"></c:param>
									</c:url>

									<c:url var="updateURL" value="/admin/stream">
										<c:param name="action" value="updateStream"></c:param>
										<c:param name="stream_id" value="${ stream.streamId }"></c:param>
									</c:url>

									<c:url var="deleteURL" value="/admin/stream">
										<c:param name="action" value="deleteStream"></c:param>
										<c:param name="stream_id" value="${ stream.streamId }"></c:param>
									</c:url>

									<tr>
										<td>${jinddex }</td>
										<td>${stream.name }</td>
										
										<td><c:if test="${stream.status == 1}">
												<span class="badge bg-success"> <i
													class="bi bi-check-circle me-1"></i> Active
												</span>
											</c:if> <c:if test="${stream.status == 0 }">
												<span class="badge bg-secondary"> <i
													class="bi bi-exclamation-triangle me-11"></i> In-Active
												</span>
											</c:if></td>
										<td>${stream.createdAt }</td>
										<td><a href="javaScript:void(0)" onclick="editStream(${stream.streamId})" class="editStream" data-stream-id="${stream.streamId }"> <i
												class="bi bi-pencil-square text-info"></i>
										</a> <a href=${deleteURL }
											onclick="if(!confirm('Are you sure to delete the stream?')) return false">
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
<div class="modal fade" id="addStream" tabindex="-1">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">Add Stream</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<form action="" method="post" id="addStreamForm" name="addStream" class="row g-3">
					<div class="col-md-12">
						<label for="validationCustom01" class="form-label">Stream Name</label> 
							<input type="text" name="streamName" value=""
							class="form-control" id="validationCustom01">
					</div>
					<div class="col-md-12">
						<label for="validationCustom03" class="form-label">Status</label>
						<select name="status" class="form-select" id="validationCustom03">
							<option selected value="1">Active</option>
							<option value="0">In-Active</option>
						</select>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary"
					data-bs-dismiss="modal">Close</button>
				<button type="button" class="btn btn-primary addStream">Save changes</button>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="updateStream" tabindex="-1">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">Add Stream</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<form action="" method="post" id="updateStreamForm" name="updateStream" class="row g-3">
					<input type="hidden" name="streamId" value="">
					<div class="col-md-12">
						<label class="form-label">Stream Name</label> 
							<input type="text" name="streamName" value="" class="form-control" >
					</div>
					<div class="col-md-12">
						<label class="form-label">Status</label>
						<select name="status" class="form-select selectOpt">
							<option value="1">Active</option>
							<option value="0">In-Active</option>
						</select>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary"
					data-bs-dismiss="modal">Close</button>
				<button type="button" class="btn btn-primary updateStream">Save changes</button>
			</div>
		</div>
	</div>
</div>
<%@ include file="include/footer.jsp"%>
<script type="text/javascript">

	$("form[name='addStream']").validate({
		rules: {
			streamName: "required",
			status: "required"
		},
		messages: {
			streamName : "Please enter the stream name.",
			status : "Please select stream status."
		},
		errorPlacement: function(error, element){
			var elem = $(element);
			error.insertAfter(element);
		},
		submitHandler: function(form){}
	});

	$(".addStream").on("click", function(){
		if($("#addStreamForm").valid()){
			
			$.ajax({
				type: "post",
				url : "/BookMaster/admin/stream?action=addStream",
				data : $("#addStreamForm").serialize(),
				
				success: function(resp){
					console.log(resp);
					
					$("#addStream").modal("hide");
					location.reload();
				},
				error: function(err){
					alert("error");
					console.log(err);
				}
			});
		}
	});
	
	function editStream(streamId) {
		alert("editStream");
		if(streamId != ""){
			
			$.ajax({
				type: "get",
				url: "/BookMaster/admin/stream?action=updateStream",
				data: {streamId : streamId},
				dataType: "json",
				success: function(resp){
					
					var result = jQuery.parseJSON(resp.streamData[0]);
					
					if(resp.status == "true"){
						
						$('#updateStream').find("input[type='hidden']").val(result.streamId);
						$('#updateStream').find("input[type='text']").val(result.name);
						
						if(result.status == 1){		
						
							$(".selectOpt option[value='1']").attr("selected",true);
						}else{
						
							$(".selectOpt option[value='0']").attr("selected",true);
						}
						$('#updateStream').modal("show");
					}
				},
				error: function(err){
					alert("error");
					console.log(err);
				}
			})
		}
	}
	
	$("form[name='updateStream']").validate({
		rules: {
			streamName: "required",
			status: "required"
		},
		messages: {
			streamName : "Please enter the stream name.",
			status : "Please select stream status."
		},
		errorPlacement: function(error, element){
			var elem = $(element);
			error.insertAfter(element);
		},
		submitHandler: function(form){}
	});

	$(".updateStream").on("click", function(){
		if($("#updateStreamForm").valid()){
			
			$.ajax({
				type: "post",
				url : "/BookMaster/admin/stream?action=updateStreamForm",
				data : $("#updateStreamForm").serialize(),
				
				success: function(resp){
					console.log(resp);
					
					$("#updateStream").modal("hide");
					location.reload();
				},
				error: function(err){
					alert("error");
					console.log(err);
				}
			});
		}
	});
</script>