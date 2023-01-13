package bookMaster.admin.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.Transaction;

import bookMaster.entity.Users;
import bookMaster.hibernate.utility.HibernateUtil;

public class admin_user_m {

	public List<Users> listUser(DataSource dataSource) {
		
		List<Users> listUsers = new ArrayList<>();
		Session session = null;
		Transaction transaction = null;
		
		session = HibernateUtil.getSession();
		transaction = session.beginTransaction();
		listUsers = session.createQuery("From Users where deleted_at is null").list();
		
		return listUsers;
	}

}
