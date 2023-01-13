package bookMaster.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "admin")
public class Admin {

	@Id
	@Column(name = "admin_id")
	private int adminId;
	
	@Column(name = "userName")
	private String userName;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "role")
	private int role;
	
	@Column(name = "status")
	private int status;
	
	@Column(name = "created_at")
	private Date createdAt;
	
	public Admin() {
		
	}

	public Admin(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	
	public Admin(int adminId, String userName, int role) {
		super();
		this.adminId = adminId;
		this.userName = userName;
		this.role = role;
	}
	
	public Admin(int adminId, String userName, int role, int status, Date created_at) {
		super();
		this.adminId = adminId;
		this.userName = userName;
		this.role = role;
		this.status = status;
		this.createdAt = created_at;
	}

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date created_at) {
		this.createdAt = created_at;
	}

//	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", userName=" + userName + ", password=" + password + ", role=" + role
				+ ", status=" + status + ", createdAt=" + createdAt + "]";
	}

	
	
}
