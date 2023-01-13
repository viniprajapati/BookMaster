package bookMaster.admin.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mysql.cj.result.RowList;

import bookMaster.entity.Stream;
import bookMaster.hibernate.utility.HibernateUtil;

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

}
