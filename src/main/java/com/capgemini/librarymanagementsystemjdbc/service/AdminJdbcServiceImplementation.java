package com.capgemini.librarymanagementsystemjdbc.service;

import java.util.List;
import com.capgemini.librarymanagementsystemjdbc.dao.AdminJdbcDao;
import com.capgemini.librarymanagementsystemjdbc.dto.Admin_User_InformationJdbc;
import com.capgemini.librarymanagementsystemjdbc.dto.BookInformationJdbc;
import com.capgemini.librarymanagementsystemjdbc.dto.RequestInformationJdbc;
import com.capgemini.librarymanagementsystemjdbc.factory.LMSJdbcFactory;

public class AdminJdbcServiceImplementation implements AdminJdbcService{
	
	private AdminJdbcDao admindao = LMSJdbcFactory.getAdminJdbcDao();

	@Override
	public boolean addUser(Admin_User_InformationJdbc user1) {
		
		return admindao.addUser(user1);
	}

	@Override
	public Admin_User_InformationJdbc login(String emailId, String password) {
		
		return admindao.login(emailId, password);
	}

	@Override
	public boolean addBook(BookInformationJdbc info) {
		
		return admindao.addBook(info);
	}

	@Override
	public boolean removeBook(int bookId) {
		
		return admindao.removeBook(bookId);
	}

	@Override
	public boolean updateBook(BookInformationJdbc bookBean) {
		
		return admindao.updateBook(bookBean);
	}

	@Override
	public BookInformationJdbc searchBook(int bookId) {
	
		return admindao.searchBook(bookId);
	}

	@Override
	public List<Admin_User_InformationJdbc> showUsers() {
		
		return admindao.showUsers();
	}

	@Override
	public List<BookInformationJdbc> showBooks() {
		
		return admindao.showBooks();
	}

	@Override
	public List<RequestInformationJdbc> showRequests() {
		
		return admindao.showRequests();
	}

	@Override
	public boolean bookIssue(Admin_User_InformationJdbc user, BookInformationJdbc book) {
		
		return admindao.bookIssue(user, book);
	}

	@Override
	public boolean isBookReceived(Admin_User_InformationJdbc user, BookInformationJdbc book) {
	
		return admindao.isBookReceived(user, book);
	}

	
}
