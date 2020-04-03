package com.capgemini.librarymanagementsystemjdbc.dao;

import java.util.List;

import com.capgemini.librarymanagementsystemjdbc.dto.Admin_User_InformationJdbc;
import com.capgemini.librarymanagementsystemjdbc.dto.BookInformationJdbc;
import com.capgemini.librarymanagementsystemjdbc.dto.RequestInformationJdbc;

public interface AdminJdbcDao {
	
	boolean addUser(Admin_User_InformationJdbc userBean);
	Admin_User_InformationJdbc login(String emailId, String password);
	boolean addBook(BookInformationJdbc info);
//	boolean returnedBook(int bookId);
	boolean removeBook(int bookId);
	boolean updateBook(BookInformationJdbc bookBean);
	BookInformationJdbc searchBook(int bookId);
	List<Admin_User_InformationJdbc> showUsers();
	List<BookInformationJdbc> showBooks();
	List<RequestInformationJdbc> showRequests();
	//boolean bookIssue(Admin_User_InformationJdbc user,BookInformationJdbc book);
	boolean bookIssue(int requestId);
	boolean isBookReceived(Admin_User_InformationJdbc user,BookInformationJdbc book);
	
}
