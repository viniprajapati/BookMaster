package bookMaster.admin.controller;

import jakarta.servlet.http.HttpServlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import bookMaster.admin.model.admin_stream_m;
import bookMaster.admin.model.admin_user_m;
import bookMaster.entity.Stream;
import bookMaster.entity.Users;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

/**
 * Servlet implementation class admin_user
 */
@WebServlet("/admin/user")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, 
				maxFileSize = 1024 * 1024 * 5, 
				maxRequestSize = 1024 * 1024 * 5 * 5)
public class admin_user extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DataSource dataSource;
	String userImgPath = "G:\\JAVA\\upload_img\\user_profile_img";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getSession().getAttribute("username") == null) {
			String encode = response.encodeURL(request.getContextPath());
			response.sendRedirect(encode+"/admin/login");
		}else {
			String action = request.getParameter("action");
			switch (action) {
			case "list":
				listUser(request, response);
				break;
			case "addUser" :
				addUserListing(request, response);
				break;
			case "updateUser":
				updateUserListing(request, response);
				break;
			case "deleteUser":
				deleteUser(request, response);
				String encode = response.encodeURL(request.getContextPath());
				response.sendRedirect(encode+"/admin/user?action=list");
				break;
			default:
				listUser(request, response);
				break;
			}
		}	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getSession().getAttribute("username") == null) {
			String encode = response.encodeURL(request.getContextPath());
			response.sendRedirect(encode+"/admin/login");
		}else {
			String action = request.getParameter("action");
			String encode = response.encodeURL(request.getContextPath());
			switch (action) {
			case "addUserForm":
				addUserForm(request, response);
				response.sendRedirect(encode+"/admin/user?action=list");
				break;

			case "updateUserForm":
				updateUserForm(request, response);
				response.sendRedirect(encode+"/admin/user?action=list");
				break;
				
			default:
				response.sendRedirect(encode+"/admin/user?action=list");
				break;
			}
		}
	}

	private void listUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Users> listUsers = new ArrayList<>();
		listUsers = new admin_user_m().listUser(dataSource);
		request.setAttribute("listUsers", listUsers);
		request.setAttribute("title", "Users");
		request.getRequestDispatcher("users_list.jsp").forward(request, response);
	}
	
	private void addUserListing(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Stream> streamList = new ArrayList<>();
		streamList = new admin_stream_m().listOfStream();
		request.setAttribute("streams", streamList);
		System.out.println(streamList);

		request.setAttribute("title", "Add User");
		request.getRequestDispatcher("users_add.jsp").forward(request, response);
		
	}

	private void addUserForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		Users user = new Users();
		File uploadDir = new File(userImgPath);
		
		if(!uploadDir.exists()) uploadDir.mkdir();
		
		Part file = request.getPart("profile_img");
		String fileName = file.getSubmittedFileName();
		if(fileName.isEmpty()) {
			file.write(userImgPath+ File.separator+ fileName);
			user.setProfilePhoto(fileName);
		}
		user.setUserName(request.getParameter("userName"));
		user.setEmail(request.getParameter("email"));
		user.setPassword(request.getParameter("password"));
		user.setStatus(Integer.parseInt(request.getParameter("status")));
		user.setStreamId(Integer.parseInt(request.getParameter("streamId")));
		user.setCreatedAt(new Date());
		
		new admin_user_m().addUser(user, dataSource);
		return;
	}
	
	private void updateUserListing(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int userId = Integer.parseInt(request.getParameter("user_id"));
		Users user = new Users();
		user = new admin_user_m().getUserById(userId);
		
		List<Stream> streamList = new ArrayList<>();
		streamList = new admin_stream_m().listOfStream();
		
		request.setAttribute("title", "Update User");
		request.setAttribute("userData", user);
		request.setAttribute("streams", streamList);
		System.out.println(streamList);
		request.getRequestDispatcher("users_edit.jsp").forward(request, response);
		
	}

	private void updateUserForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		new admin_user_m().updateUser(request);
		return;
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) {
		
		int userId  = Integer.parseInt(request.getParameter("user_id"));
		new admin_user_m().deleteUser(userId);
		
		return;
		
	}




	



}
