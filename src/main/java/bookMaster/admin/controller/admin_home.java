package bookMaster.admin.controller;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class admin_home
 */
@WebServlet("/admin")
public class admin_home extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String encode = response.encodeURL(request.getContextPath());
		if(request.getSession().getAttribute("username") == null) {
			response.sendRedirect(encode+"/admin/login");
		}else {
			
			String action = request.getParameter("action");
			switch (action) {
			case "logout":
				request.getSession().invalidate();
				response.sendRedirect(encode+"/admin/login");
				break;

			default:				
				request.setAttribute("title","Dashboard");
				request.getRequestDispatcher("index.jsp").forward(request, response);
				break;
			}
		}

	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
