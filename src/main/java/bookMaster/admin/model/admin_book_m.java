package bookMaster.admin.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import bookMaster.entity.Books;
import bookMaster.hibernate.utility.HibernateUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

public class admin_book_m {

	public List<Books> listAllBooks() {

		List<Books> bookList = new ArrayList<>();
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = HibernateUtil.getSession();
			transaction = session.beginTransaction();
			
			bookList = session.createQuery("From Books where deleted_at is null").list();
			transaction.commit();
		} catch (HibernateException e) {
			System.out.println("Hibenate exception "+e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return bookList;
	}

	public Books getBookById(int bookId) {
		
		Books book = null;
		Session session = null;
		Transaction transaction = null;
		
		try {
			
			session = HibernateUtil.getSession();
			transaction = session.beginTransaction();
			
			book = (Books) session.createQuery("From Books where book_id = "+bookId+" and deleted_at is null").uniqueResult();
			transaction.commit();
		} catch (HibernateException e) {
			System.out.println("Hibernate exception "+e);
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return book;
	}
	
	public void addBook(Books book) {
		
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = HibernateUtil.getSession();
			transaction = session.beginTransaction();
			
			session.save(book);
			transaction.commit();
		} catch (HibernateException e) {
			System.out.println("Hibernate exception "+e);
			e.printStackTrace();
		}finally {
			session.clear();
			session.close();
		}
		
	}

	public void updateBook(HttpServletRequest request) throws IOException, ServletException {
		
		Session session = null;
		Transaction transaction = null;
		
		try {
			
			session = HibernateUtil.getSession();
			transaction = session.beginTransaction();
			String bookImgPath = "G:\\JAVA\\upload_img\\book_img";
			int bookId = Integer.parseInt(request.getParameter("book_id"));
			
			Books book = session.get(Books.class, bookId);
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
			book.setLeftBook((Integer.parseInt(request.getParameter("total_book")) - book.getIssuedBook()));
			book.setStatus(Integer.parseInt(request.getParameter("status")));
			
			transaction.commit();
			
		} catch (HibernateException e) {
			System.out.println("Hibernate exception "+e);
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
	
	public void deleteBook(int bookId) {
		
		Session session = null;
		Transaction transaction = null;
		
		try {
			
			session = HibernateUtil.getSession();
			transaction = session.beginTransaction();
			
			Books book = session.get(Books.class, bookId);
			book.setDeletedAt(new Date());
			
			transaction.commit();
			
		} catch (HibernateException e) {
			System.out.println("Hibernate exception "+e);
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

}
