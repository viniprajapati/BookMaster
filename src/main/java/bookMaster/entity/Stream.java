package bookMaster.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stream")
public class Stream {
	
	@Id
	@Column(name = "stream_id")
	int streamId;
	
	@Column(name = "name")
	String name;
	
	@Column(name = "status")
	int status;
	
	@Column(name = "created_at")
	Date createdAt;

	public Stream() {
		
	}

	public int getStreamId() {
		return streamId;
	}

	public void setStreamId(int streamId) {
		this.streamId = streamId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
