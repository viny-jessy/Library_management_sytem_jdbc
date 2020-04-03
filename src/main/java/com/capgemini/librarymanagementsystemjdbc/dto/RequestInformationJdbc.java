package com.capgemini.librarymanagementsystemjdbc.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;


public class RequestInformationJdbc implements Serializable{

	private int requestId;
	private int bookId;
	private int userId;
	private int bookName;
	private BookInformationJdbc bookBean;
	private Admin_User_InformationJdbc userBean;
	public int getRequestId() {
		return requestId;
	}
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getBookName() {
		return bookName;
	}
	public void setBookName(int bookName) {
		this.bookName = bookName;
	}
	public BookInformationJdbc getBookBean() {
		return bookBean;
	}
	public void setBookBean(BookInformationJdbc bookBean) {
		this.bookBean = bookBean;
	}
	public Admin_User_InformationJdbc getUserBean() {
		return userBean;
	}
	public void setUserBean(Admin_User_InformationJdbc userBean) {
		this.userBean = userBean;
	}
	
	
	
}
