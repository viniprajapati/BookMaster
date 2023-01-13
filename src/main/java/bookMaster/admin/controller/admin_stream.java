package bookMaster.admin.controller;

import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import bookMaster.admin.model.admin_stream_m;
import bookMaster.entity.Stream;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class admin_stream
 */
@WebServlet("/admin/stream")
public class admin_stream extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DataSource dataSource;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getSession().getAttribute("username") == null) {
			String encode = response.encodeURL(request.getContextPath());
			response.sendRedirect(encode+"/admin/login");
		}else {
			String action = request.getParameter("action");
			switch (action) {
			case "list":
				listAllStream(request, response);
				break;
			case "updateStream":
				updateStream(request, response);
				break;
			default:
				break;
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getSession().getAttribute("username") == null) {
			String encode = response.encodeURL(request.getContextPath());
			response.sendRedirect(encode+"/admin/login");
		}else {
			String action = request.getParameter("action");
			switch (action) {
			case "addStream":
				addStream(request, response);
				break;
			
			case "updateStreamForm":
				updateStreamForm(request, response);
				break;
				
			case "deleteStream":
				deleteStream(request, response);
				break;
				
			default:
				break;
			}
		}
	}

	private void listAllStream(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Stream> listStream = new ArrayList<>();
		listStream = new admin_stream_m().listStream(dataSource);
		request.setAttribute("listStream", listStream);
		request.setAttribute("title", "Streams");
		
		request.getRequestDispatcher("stream_list.jsp").forward(request, response);
	}

	private void addStream(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Stream stream = new Stream();
		stream.setName(request.getParameter("streamName"));
		stream.setStatus(Integer.parseInt(request.getParameter("status")));
		new admin_stream_m().addStream(stream, dataSource);
		String greet ="true";
		
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(greet);
	}

	private void updateStream(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int streamId = Integer.parseInt(request.getParameter("streamId"));
		Stream stream = null;
		stream = new admin_stream_m().getStreamById(streamId, dataSource);
        
		response.setContentType("application/json;charset=utf-8");
		Gson gson = new Gson();
		JSONObject json = new JSONObject();
		
		String streamdata = gson.toJson(stream);
		if(stream != null) {
			json.append("status", "true");
		}else {
			json.append("status", "false");
		}
		json.append("streamData", streamdata);
		response.getWriter().print(json);
	}
	
	private void updateStreamForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	private void deleteStream(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
