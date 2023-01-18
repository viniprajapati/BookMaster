package bookMaster.admin.controller;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import bookMaster.admin.model.admin_m;
import bookMaster.admin.model.admin_stream_m;
import bookMaster.entity.Admin;
import bookMaster.entity.Stream;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class admin_librarian
 */
@WebServlet("/admin/librarian")
public class admin_librarian extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DataSource dataSource;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getSession().getAttribute("username") == null) {
			String encode = response.encodeURL(request.getContextPath());
			response.sendRedirect(encode+"/admin/login");
		}else {
			String action = request.getParameter("action");
			String encode = response.encodeURL(request.getContextPath());
			switch (action) {
			case "list":
				listLibrarian(request, response);
				break;
			case "addLibrarian" :
				addLibrarianListing(request, response);
				break;
			case "updateLibrarian":
				updateLibrarianListing(request, response);
				break;
			case "deleteLibrarian":
				deleteLibrarian(request, response);
				response.sendRedirect(encode+"/admin/librarian?action=list");
				break;
			default:
				listLibrarian(request, response);
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
			case "addLibrarianForm":
				addLibrarianForm(request, response);
				response.sendRedirect(encode+"/admin/librarian?action=list");
				break;
				
			case "updateLibrarianForm":
				updateLibrarianForm(request, response);
				response.sendRedirect(encode+"/admin/librarian?action=list");
				break;
				
			default:
				response.sendRedirect(encode+"/admin/librarian?action=list");
				break;
			}
		}
	}

	private void listLibrarian(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		
		List<Admin> listLibrarian = new ArrayList<>();
		listLibrarian = new admin_m().listLibrarian(dataSource);
		request.setAttribute("listLibrarian", listLibrarian);
		request.setAttribute("title", "Librarian");
		
		try {
//			response.sendRedirect("librarian_list.jsp");
			request.getRequestDispatcher("librarian_list.jsp").forward(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void addLibrarianListing(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("title", "Add Librarian");
		request.getRequestDispatcher("librarian_add.jsp").forward(request, response);
		
	}
	
	private void addLibrarianForm(HttpServletRequest request, HttpServletResponse response) {
		
		Admin admin = new Admin();
		admin.setUserName(request.getParameter("userName"));
		admin.setPassword(request.getParameter("password"));
		admin.setRole(Integer.parseInt(request.getParameter("role")));
		admin.setStatus(Integer.parseInt(request.getParameter("status")));
	
		new admin_m().addLibrarian(admin,dataSource);
		return;
	}
	
	private void updateLibrarianListing(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int adminId = Integer.parseInt(request.getParameter("admin_id"));
		Admin admin = new admin_m().getLibrarianById(adminId);
		System.out.println(admin);
		request.setAttribute("librarianData", admin);
		request.setAttribute("title", "Update Librarian");
		
		request.getRequestDispatcher("librarian_edit.jsp").forward(request, response);
	}
	
	private void updateLibrarianForm(HttpServletRequest request, HttpServletResponse response) {
//		Admin admin = new Admin();
//		admin.setAdminId(Integer.parseInt(request.getParameter("admin_id")));
//		admin.setUserName(request.getParameter("userName"));
////		admin.setPassword(request.getParameter("password"));
//		admin.setRole(Integer.parseInt(request.getParameter("role")));
//		admin.setStatus(Integer.parseInt(request.getParameter("status")));
//		
		new admin_m().updateLibrarian(request,dataSource);
		return;
	}
	
	private void deleteLibrarian(HttpServletRequest request, HttpServletResponse response) {
		
		int lib_id = Integer.parseInt(request.getParameter("admin_id"));
		new admin_m().deleteLibrarian(lib_id);
		return;
	}

}
