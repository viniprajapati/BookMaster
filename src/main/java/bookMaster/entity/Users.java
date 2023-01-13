package bookMaster.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class Users {

	@Id
	@Column(name = "user_id")
	int userId;
	
	@Column(name = "user_name")
	String userName;

	@Column(name = "email")
	String email;
	
	@Column(name = "password")
	String password;
	
	@Column(name = "profile_photo")
	String profilePhoto;
	
	@Column(name = "book_limit")
	int bookLimit;
	
	@Column(name = "stream_id")
	int streamId;
	
	@Column(name = "status")
	int status;
	
	@Column(name = "created_at")
	Date createdAt;
	
	public Users() {
		super();
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getProfilePhoto() {
		return profilePhoto;
	}

	public void setProfilePhoto(String profilePhoto) {
		this.profilePhoto = profilePhoto;
	}

	public int getBookLimit() {
		return bookLimit;
	}

	public void setBookLimit(int bookLimit) {
		this.bookLimit = bookLimit;
	}

	public int getStreamId() {
		return streamId;
	}

	public void setStreamId(int streamId) {
		this.streamId = streamId;
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

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	
}
