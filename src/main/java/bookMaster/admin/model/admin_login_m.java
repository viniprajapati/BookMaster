package bookMaster.admin.model;

import java.net.http.HttpRequest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.fileupload.RequestContext;

import bookMaster.entity.Admin;

public class admin_login_m {

	public ResultSet login(Admin adminDetail, DataSource dataSource) {
		
		List<Admin> admin = new ArrayList<>();
		String url = "jdbc:mysql://127.0.0.1:3306/book_master";
		String usrename = "vini";
		String password = "123456";
		
		try {
			Connection conn = DriverManager.getConnection(url, usrename, password);
			String query = "Select * from admin where userName = ? and password = ? and status = 1";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, adminDetail.getUserName());
			stmt.setString(2, adminDetail.getPassword());
			
			return stmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
		
	}

}
