package bookMaster.admin.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;
import org.hibernate.HibernateException;

import org.hibernate.Session;
import org.hibernate.Transaction;

import bookMaster.entity.Admin;
import bookMaster.hibernate.utility.HibernateUtil;
import jakarta.servlet.http.HttpServletRequest;

public class admin_m {

	public List<Admin> listLibrarian(DataSource dataSource) {
		
		List<Admin> listLibrarian = new ArrayList<>();
		Session session = null;
		Transaction transaction = null;
		
		session = HibernateUtil.getSession();
		transaction = session.beginTransaction();
		listLibrarian = session.createQuery("From Admin where role=2 and deleted_at is null").list();
		
		return listLibrarian;
	}

	public void addLibrarian(Admin admin, DataSource dataSource) {
	
		Session session = null;
		Transaction transaction = null;
		admin.setCreatedAt(new Date());
		try {
			session = HibernateUtil.getSession();
			transaction = session.beginTransaction();
			
			session.save(admin);
			transaction.commit();
		} catch (HibernateException e) {
			System.out.println("Hibernate exception "+e);
			e.printStackTrace();
		}finally {
			session.close();
		}
	}

	public Admin getLibrarianById(int adminId) {
		
		Session session = null;
		Transaction transaction = null;
		Admin admin = null;
		try {
			session = HibernateUtil.getSession();
			transaction = session.beginTransaction();
			
			admin = (Admin) session.createQuery("From Admin where admin_id="+adminId+" and role = 2 and deleted_at is null").uniqueResult();
			transaction.commit();
		} catch (HibernateException e) {
			System.out.println("Hibernate exception "+e);
			e.printStackTrace();
		}finally {
			session.close();
		}
		return admin;
	}

	public void updateLibrarian(HttpServletRequest request, DataSource dataSource) {
		
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = HibernateUtil.getSession();
			transaction = session.beginTransaction();
			
//			Query query = session.createSQLQuery("Update Admin set userName = :userName, status = :status, role = :role "+"where admin_id = :adminId");
//			session.update(admin);
//			int result = query.executeUpdate();
			
			int lib_id = Integer.parseInt(request.getParameter("admin_id"));
			Admin lib = session.get(Admin.class, lib_id);
			lib.setUserName(request.getParameter("userName"));
			lib.setPassword(request.getParameter("password"));
			lib.setRole(Integer.parseInt(request.getParameter("role")));
			lib.setStatus(Integer.parseInt(request.getParameter("status")));
			transaction.commit();
		} catch (HibernateException e) {
			System.out.println("Hibernate exception "+e);
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
}
