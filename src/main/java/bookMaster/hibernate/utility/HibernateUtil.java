package bookMaster.hibernate.utility;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static SessionFactory sessionFactory = null;
	
	static {
		try {
			loadSessionFactory();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception while initializing hibernate util..");
			e.printStackTrace();
		}
	}
	
	public static void loadSessionFactory() {
		
		Configuration configuration = null;
		configuration = new Configuration();
		configuration.configure();
		
		StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().loadProperties("hibernate.cfg.xml").build();
		
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	}
	
	public static Session getSession() throws HibernateException {
		
		Session retSession = null;
		
		try {
			retSession = sessionFactory.openSession();
		} catch (Throwable th) {
			System.out.println("Exception while getting session..");
			th.printStackTrace();
		}
		if (retSession == null) {
			System.out.println("Session is discovered null");
		}
		
		return retSession;
	}
}
