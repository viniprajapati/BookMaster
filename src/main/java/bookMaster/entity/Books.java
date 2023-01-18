package bookMaster.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "books")
public class Books {

	@Id
	@Column(name = "book_id")
	int bookId;
	
	@Column(name = "book_photo")
	String bookPhoto;
	
	@Column(name = "book_name")
	String bookName;
	
	@Column(name = "author")
	String author;
	
	@Column(name = "publisher")
	String publisher;
	
	@Column(name = "stream_id")
	int streamId;
	
	@Column(name = "total_book")
	int totalBook;
	
	@Column(name = "issued_book")
	int issuedBook;
	
	@Column(name = "left_book")
	int leftBook;
	
	@Column(name = "lost_book")
	int lostBook;
	
	@Column(name = "status")
	int status;
	
	@Column(name = "created_at")
	Date createdAt;
	
	@Column(name = "deleted_at")
	Date deletedAt;

	public Books() {
		super();
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getBookPhoto() {
		return bookPhoto;
	}

	public void setBookPhoto(String bookPhoto) {
		this.bookPhoto = bookPhoto;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getStreamId() {
		return streamId;
	}

	public void setStreamId(int streamId) {
		this.streamId = streamId;
	}

	public int getTotalBook() {
		return totalBook;
	}

	public void setTotalBook(int totalBook) {
		this.totalBook = totalBook;
	}

	public int getIssuedBook() {
		return issuedBook;
	}

	public void setIssuedBook(int issuedBook) {
		this.issuedBook = issuedBook;
	}

	public int getLeftBook() {
		return leftBook;
	}

	public void setLeftBook(int leftBook) {
		this.leftBook = leftBook;
	}

	public int getLostBook() {
		return lostBook;
	}

	public void setLostBook(int lostBook) {
		this.lostBook = lostBook;
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

	public Date getDeletedAt() {
		return deletedAt;
	}

	public void setDeletedAt(Date deletedAt) {
		this.deletedAt = deletedAt;
	}

	@Override
	public String toString() {
		return "Books [bookId=" + bookId + ", bookPhoto=" + bookPhoto + ", bookName=" + bookName + ", author=" + author
				+ ", publisher=" + publisher + ", streamId=" + streamId + ", totalBook=" + totalBook + ", issuedBook="
				+ issuedBook + ", leftBook=" + leftBook + ", lostBook=" + lostBook + ", status=" + status
				+ ", createdAt=" + createdAt + "]";
	}
	
	
	
}
