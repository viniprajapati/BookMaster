package bookMaster.admin.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import bookMaster.entity.Users;
import bookMaster.hibernate.utility.HibernateUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

public class admin_user_m {

	public List<Users> listUser(DataSource dataSource) {
		
		List<Users> listUsers = new ArrayList<>();
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = HibernateUtil.getSession();
			transaction = session.beginTransaction();
			listUsers = session.createQuery("From Users where deleted_at is null").list();

			transaction.commit();
		} catch (HibernateException e) {
			System.out.println("Hibernate exception "+e);
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return listUsers;
	}

	public void addUser(Users user, DataSource dataSource) {
		
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = HibernateUtil.getSession();
			transaction = session.beginTransaction();
			
			session.save(user);
			transaction.commit();
		} catch (HibernateException e) {
			System.out.println("Hibernate exception "+e);
			e.printStackTrace();
		}finally {
			session.close();
		}
	}

	public Users getUserById(int userId) {
		
		Users user = null;
		Session session = null;
		Transaction transaction = null;
		
		try {
			
			session = HibernateUtil.getSession();
			transaction = session.beginTransaction();
			
			user = (Users) session.createQuery("From Users where user_id = "+userId+" and deleted_at is null").uniqueResult();
			transaction.commit();
		} catch (HibernateException e) {
			System.out.println("Hibernate exception "+e);
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return user;
	}

	public void updateUser(HttpServletRequest request) throws IOException, ServletException {
		
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = HibernateUtil.getSession();
			transaction = session.beginTransaction();
			String userImgPath = "G:\\JAVA\\upload_img\\user_profile_img";
			int userId = Integer.parseInt(request.getParameter("userId"));
			
			Users user = session.get(Users.class, userId);
			File uploadDir = new File(userImgPath);
			
			if(!uploadDir.exists()) uploadDir.mkdir();
			
			Part file = request.getPart("profile_img");
			String fileName = file.getSubmittedFileName();
			System.out.println("filename "+fileName.isEmpty());
			if(!fileName.isEmpty()) {
				System.out.println("in if");
				file.write(userImgPath+ File.separator+ fileName);
				user.setProfilePhoto(fileName);
			}else {
				System.out.println("in else");
			}
			
			user.setUserName(request.getParameter("userName"));
			user.setEmail(request.getParameter("email"));
			user.setPassword(request.getParameter("password"));
			user.setStatus(Integer.parseInt(request.getParameter("status")));
			user.setStreamId(Integer.parseInt(request.getParameter("streamId")));
			
			transaction.commit();
		} catch (HibernateException e) {
			System.out.println("Hibernate exception "+e);
			e.printStackTrace();
		}finally {
			session.close();
		}
		
	}

	public void deleteUser(int userId) {
		
		Session session = null;
		Transaction transaction = null;
		
		try {
			
			session = HibernateUtil.getSession();
			transaction = session.beginTransaction();
			
			Users user = session.get(Users.class, userId);
			user.setDeletedAt(new Date());
			
			transaction.commit();
			
		} catch (HibernateException e) {
			System.out.println("Hibernate exception "+e);
			e.printStackTrace();
		}finally {
			session.close();
		}
	}

}
