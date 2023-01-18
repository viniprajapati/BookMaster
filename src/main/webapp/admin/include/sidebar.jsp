  <!-- ======= Sidebar ======= -->
 <aside id="sidebar" class="sidebar">

	<ul class="sidebar-nav" id="sidebar-nav">

		<li class="nav-item">
			<a class="nav-link " href="${pageContext.request.contextPath}/admin/">
				<i class="bi bi-grid"></i>
				<span>Dashboard</span>
			</a>
		</li>
        <!-- End Dashboard Nav -->
		<%
			if(request.getSession().getAttribute("role") == "Admin"){
		%>
			<li class="nav-item">
	        	<a class="nav-link collapsed" href="${pageContext.request.contextPath}/admin/librarian?action=list">
	          		<i class="bi bi-person-check"></i>
	          		<span>Librarian</span>
	       		</a>
	      	</li>
		<% 
			}
		%>
      
      	<li class="nav-item">
     		<a class="nav-link collapsed" href="${pageContext.request.contextPath}/admin/user?action=list">
     			<i class="bi bi-people"></i>
     			<span>User List</span>
     		</a>
      	</li>
      
		<li class="nav-item">
	     	<a class="nav-link collapsed" href="${pageContext.request.contextPath }/admin/book?action=allBooks">
	     		<i class="bi bi-book"></i>
	     		<span>Book List</span>
	     	</a>
        </li>
        
        <li class="nav-item">
	     	<a class="nav-link collapsed" href="javaScript:void(0)">
	     		<i class="bi bi-book-half"></i>
	     		<span>Issued Book</span>
	     	</a>
        </li>
        
        <li class="nav-item">
	     	<a class="nav-link collapsed" href="javaScript:void(0)">
	     		<i class="bi bi-book-fill"></i>
	     		<span>Returned Book</span>
	     	</a>
        </li>
        
        <li class="nav-item">
	     	<a class="nav-link collapsed" href="javaScript:void(0)">
	     		<i class="bi bi-journal-x"></i>
	     		<span>Defaulter Book</span>
	     	</a>
        </li>
        
        <li class="nav-item">
	     	<a class="nav-link collapsed" href="${pageContext.request.contextPath }/admin/stream?action=list">
	     		<i class="bi bi-mortarboard"></i>
	     		<span>Streams</span>
	     	</a>
        </li>

    </ul>

  </aside><!-- End Sidebar-->