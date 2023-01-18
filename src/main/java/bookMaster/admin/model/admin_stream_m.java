package bookMaster.admin.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;
import javax.sql.DataSource;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mysql.cj.result.RowList;

import bookMaster.entity.Stream;
import bookMaster.hibernate.utility.HibernateUtil;
import jakarta.servlet.http.HttpServletRequest;

public class admin_stream_m {

	public List<Stream> listStream(DataSource dataSource) {
		
		Session session = null;
		Transaction transaction = null;
		List<Stream> list = new ArrayList<>();
		
		session = HibernateUtil.getSession();
		transaction = session.beginTransaction();
		list = session.createQuery("From Stream where deleted_at is null").list();
		return list;
	}
	
	public List<Stream> listOfStream() {
		
		Session session = null;
		Transaction transaction = null;
		List<Stream> list = null;
		
		session = HibernateUtil.getSession();
		transaction = session.beginTransaction();
		list = session.createQuery("From Stream where status = 1 and deleted_at is null").list();
		System.out.println(list);
		return list;
	}

	public void addStream(Stream stream, DataSource dataSource) {

		Session session = null;
		Transaction transaction = null;
		
		try {
			session = HibernateUtil.getSession();
			transaction = session.beginTransaction();
			session.save(stream);
			transaction.commit();
		} catch (HibernateException e) {
			System.out.println("Hibernate exceptio jhn "+e);
			e.printStackTrace();
		}finally {
			session.close();
		}
	}

	public Stream getStreamById(int streamId, DataSource dataSource) {
		
		Stream streamData = null;
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = HibernateUtil.getSession();
			transaction = session.beginTransaction();
			streamData =  (Stream) session.createQuery("From Stream where stream_id = "+streamId+" and deleted_at is null").uniqueResult();
//			streamData = session.get(Stream.class, streamId);
			transaction.commit();
		} catch (HibernateException e) {
			System.out.println("Hibernate exceptio jhn "+e);
			e.printStackTrace();
		}finally {
			session.close();
		}
		return streamData;
	}

	public void updateStream(HttpServletRequest request, DataSource dataSource) {
		
		Session session = null;
		Transaction transaction = null;

		try {
			session = HibernateUtil.getSession();
			transaction = session.beginTransaction();
			int streamId = Integer.parseInt(request.getParameter("streamId"));
			
			Stream stream = session.get(Stream.class, streamId);
			stream.setName(request.getParameter("streamName"));
			stream.setStatus(Integer.parseInt(request.getParameter("status")));
			
			transaction.commit();
			
		} catch (HibernateException e) {
			System.out.println("Hibernate exception "+e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		
	}

	public int deleteStream(int streamId) {
		
		int result = 0;
		Session session = null;
		Transaction transaction = null;
		
		try {
			session = HibernateUtil.getSession();
			transaction = session.beginTransaction();
			
			Stream stream = session.get(Stream.class, streamId);
			stream.setDeletedAt(new Date());
			transaction.commit();
		} catch (Exception e) {
			System.out.println("Hibernate exception "+e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return 0;
	}

}
