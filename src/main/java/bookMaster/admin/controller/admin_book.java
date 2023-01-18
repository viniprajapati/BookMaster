package bookMaster.admin.controller;

import jakarta.servlet.http.HttpServlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bookMaster.admin.model.admin_book_m;
import bookMaster.admin.model.admin_stream_m;
import bookMaster.entity.Books;
import bookMaster.entity.Stream;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

/**
 * Servlet implementation class admin_book
 */
@WebServlet("/admin/book")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, 
					maxFileSize = 1024 * 1024 * 5, 
					maxRequestSize = 1024 * 1024 * 5 * 5)
public class admin_book extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	String bookImgPath = "G:\\JAVA\\upload_img\\book_img";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String encode = response.encodeURL(request.getContextPath());
		if(request.getSession().getAttribute("username") == null) {
			
			response.sendRedirect(encode + "/admin/login");
		} else {
			String action = request.getParameter("action");
			switch (action) {
			case "allBooks":
				listAllBooks(request, response);
				break;
				
			case "addBook":
				addBookList(request, response);
				break;

			case "updateBook":
				updateBookList(request, response);
				break;
				
			case "deleteBook":
				deleteBook(request, response);
				response.sendRedirect(encode+"/admin/book?action=allBooks");
				break;
				
			default:
				response.sendRedirect(encode+"/admin/book?action=allBooks");
				break;
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String encode = response.encodeURL(request.getContextPath());
		if(request.getSession().getAttribute("username") == null) {
			
			response.sendRedirect(encode + "/admin/login");
		} else {
			String action = request.getParameter("action");
			switch (action) {
			case "addBookForm":
				addBookForm(request, response);
				response.sendRedirect(encode+"/admin/book?action=allBooks");
				break;
			
			case "updateBookForm":
				updateBookForm(request,response);
				response.sendRedirect(encode+"/admin/book?action=allBooks");
				break;
				
			default:
				break;
			}
		}
		
	}

	private void listAllBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Books> listBooks = new ArrayList<>();
		listBooks = new admin_book_m().listAllBooks();
		
		request.setAttribute("bookList", listBooks);
		request.setAttribute("title", "Book List");
		request.getRequestDispatcher("all_book_list.jsp").forward(request, response);
	}
	
	protected void addBookList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		List<Stream> streamList = new ArrayList<>();
		streamList = new admin_stream_m().listOfStream();
		request.setAttribute("streams", streamList);
		request.setAttribute("title", "Add Book");
		request.getRequestDispatcher("book_add.jsp").forward(request, response);
	}
	
	protected void addBookForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Books book = new Books();
		File uploadDir = new File(bookImgPath);
		
		if(!uploadDir.exists()) uploadDir.mkdir();
		
		Part file = request.getPart("book_img");
		String fileName = file.getSubmittedFileName();
		if(!fileName.isEmpty()) {
			file.write(bookImgPath + File.separator + fileName);
			book.setBookPhoto(fileName);
		}
		
		book.setBookName(request.getParameter("name"));
		book.setAuthor(request.getParameter("author"));
		book.setPublisher(request.getParameter("publisher"));
		book.setStreamId(Integer.parseInt(request.getParameter("stream_id")));
		book.setTotalBook(Integer.parseInt(request.getParameter("total_book")));
		book.setIssuedBook(0);
		book.setLeftBook(Integer.parseInt(request.getParameter("total_book")));
		book.setLostBook(0);
		book.setStatus(Integer.parseInt(request.getParameter("status")));
		book.setCreatedAt(new Date());
		
		System.out.println(book);
//		new admin_book_m().addBook(book);
		return;
	}
	
	protected void updateBookList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Stream> streamList = new ArrayList<>();
		streamList = new admin_stream_m().listOfStream();
		
		int bookId = Integer.parseInt(request.getParameter("book_id"));
		Books book = new admin_book_m().getBookById(bookId);
		
		request.setAttribute("streams", streamList);
		request.setAttribute("book", book);
		request.setAttribute("title", "Update Book");
		request.getRequestDispatcher("book_edit.jsp").forward(request, response);
	}
	
	protected void updateBookForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		new admin_book_m().updateBook(request);
		return;
	}
	
	protected void deleteBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int bookId = Integer.parseInt(request.getParameter("book_id"));
		new admin_book_m().deleteBook(bookId);
		return;
	}

}
