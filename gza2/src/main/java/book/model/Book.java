package book.model;

import java.util.Date;

public class Book {
	private Integer bookCode;
	private Customer custId;
	private Seller sellerId;
	private String title;
	private String location;
	private Date startDate;
	private Date endDate;

	public Book(Integer bookCode, Customer custId, Seller sellerId, String title, String location, Date startDate, Date endDate) {
		this.bookCode = bookCode;
		this.custId = custId;
		this.sellerId = sellerId;
		this.title = title;
		this.location = location;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Integer getBookCode() {
		return bookCode;
	}

	public Customer getCustId() {
		return custId;
	}

	public Seller getSellerId() {
		return sellerId;
	}

	public String getTitle() {
		return title;
	}

	public String getLocation() {
		return location;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

}
