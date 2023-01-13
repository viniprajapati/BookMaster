package bookMaster.admin.controller;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import bookMaster.admin.model.admin_login_m;
import bookMaster.entity.Admin;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class admin_login
 */
@WebServlet("/admin/login")
public class admin_login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DataSource dataSource;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getSession().getAttribute("username") != null) {
			String encode = response.encodeURL(request.getContextPath());
			response.sendRedirect(encode+"/admin/");
		}else {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Admin adminDetail = new Admin(request.getParameter("username"), request.getParameter("password"));
		ResultSet result =  new admin_login_m().login(adminDetail, dataSource);
		if(result != null) {
			// Invalidate existing session
			request.getSession().invalidate();
			HttpSession newSession = request.getSession(true);
			
			try {
				while(result.next()) {
					
					newSession.setAttribute("username", result.getString("userName"));
					if(result.getInt("role") == 1) {
						newSession.setAttribute("role", "Admin");
						request.setAttribute("role", "Admin");
					}else {
						newSession.setAttribute("role", "Librarian");
						request.setAttribute("role", "Librarian");
					}
				}
			} catch (SQLException e) {
				request.setAttribute("role", "Librarian");
			}
			request.setAttribute("title","Dashboard");
			
			String encode = response.encodeURL(request.getContextPath());
			response.sendRedirect(encode+"/admin/");
			
		}else {
			doGet(request, response);
		}
	}

}
