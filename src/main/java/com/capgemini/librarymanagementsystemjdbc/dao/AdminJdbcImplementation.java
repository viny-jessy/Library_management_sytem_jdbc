package com.capgemini.librarymanagementsystemjdbc.dao;

//import java.io.FileInputStream;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Properties;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import com.capgemini.librarymanagementsystemjdbc.dto.Admin_User_InformationJdbc;
import com.capgemini.librarymanagementsystemjdbc.dto.BookInformationJdbc;
import com.capgemini.librarymanagementsystemjdbc.dto.RequestInformationJdbc;
import com.capgemini.librarymanagementsystemjdbc.exception.LibraryManagementJdbcExceptions;

public class AdminJdbcImplementation implements AdminJdbcDao {

	@Override
	public boolean addUser(Admin_User_InformationJdbc userBean) {
		try(FileInputStream fis=new FileInputStream("LibraryManagementSystemDataBase.properties")){
			Properties properties=new Properties();
			properties.load(fis);
			Class.forName(properties.getProperty("path")).newInstance();
			String dburl=properties.getProperty("dburl");
			try(Connection connection=DriverManager.getConnection(dburl)){
				String query=properties.getProperty("addUser");
				try(PreparedStatement pstmt=connection.prepareStatement(query)){
					pstmt.setInt(1,userBean.getId());
					pstmt.setString(2, userBean.getUserName());
					pstmt.setString(3, userBean.getFirstName());
					pstmt.setString(4, userBean.getLastName());
					pstmt.setString(5, userBean.getEmailId());
					pstmt.setString(6,userBean.getPassword());
					pstmt.setString(7,userBean.getRole());
					
					int isRegistered=pstmt.executeUpdate();
					if(isRegistered!=0) {
						return true;
					}else {
						throw new LibraryManagementJdbcExceptions("User not registered");
					}
					
				}
			}catch(LibraryManagementJdbcExceptions e) {
				System.err.println("Book is already existing");
			}	
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Admin_User_InformationJdbc login(String emailId, String password) {
		
		Admin_User_InformationJdbc adminBean = new Admin_User_InformationJdbc();
		try(FileInputStream fis = new FileInputStream("LibraryManagementSystemDataBase.properties")){
			Properties properties = new Properties();
			properties.load(fis);
			Class.forName(properties.getProperty("path")).newInstance();
			
			String dburl=properties.getProperty("dburl");
			
			try(Connection conn = DriverManager.getConnection(dburl)){
				String query =properties.getProperty("login") ;
				try(PreparedStatement pstmt = conn.prepareStatement(query)){
					pstmt.setString(1, emailId);
					pstmt.setString(2, password); 
					ResultSet rs = pstmt.executeQuery();
					if(rs.next()) {
					adminBean.setEmailId(rs.getString("emailid"));
					adminBean.setPassword(rs.getString("password"));
							return adminBean;
						}else {
							System.out.println();
							return null;
					}
					}	
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		 
	return null;
	}

	@Override
	public boolean addBook(BookInformationJdbc info) {
		try(FileInputStream fis=new FileInputStream("LibraryManagementSystemDataBase.properties")){
			Properties properties=new Properties();
			properties.load(fis);
			Class.forName(properties.getProperty("path")).newInstance();
			String dburl=properties.getProperty("dburl");
			try(Connection connection=DriverManager.getConnection(dburl)){
				String query=properties.getProperty("addBook");
				try(PreparedStatement pstmt=connection.prepareStatement(query)){
					pstmt.setInt(1,info.getBookId());
					pstmt.setString(2, info.getBookName());
					pstmt.setString(3, info.getAuthorName());
					pstmt.setString(4, info.getPublisher());
					pstmt.setString(5, info.getCategory());
					
					int isAdded=pstmt.executeUpdate();
					if(isAdded!=0) {
						return true;
					}else {
						throw new LibraryManagementJdbcExceptions("Book is not added");
					}
					
				}
			}catch(LibraryManagementJdbcExceptions e) {
				System.err.println("Book is already existing");
			}	
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean removeBook(int bookId) {
		try(FileInputStream fis=new FileInputStream("LibraryManagementSystemDataBase.properties")){
			Properties properties=new Properties();
			properties.load(fis);
			Class.forName(properties.getProperty("path")).newInstance();
			String dburl=properties.getProperty("dburl");
			try(Connection connection=DriverManager.getConnection(dburl)){
				String query=properties.getProperty("removeBook");
				try(PreparedStatement pstmt=connection.prepareStatement(query)){
					pstmt.setInt(1,bookId );
					int res=pstmt.executeUpdate();
					if(res!=0) {
						return true;
					}
				}catch(LibraryManagementJdbcExceptions lmse) {
					System.err.println("book cannot be removed");
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateBook(BookInformationJdbc bookBean) {
		try(FileInputStream fis=new FileInputStream("LibraryManagementSystemDataBase.properties")){
			Properties properties=new Properties();
			properties.load(fis);
			Class.forName(properties.getProperty("path")).newInstance();
			String dburl=properties.getProperty("dburl");
			try(Connection connection=DriverManager.getConnection(dburl)){
				String query=properties.getProperty("updateBook");
				try(PreparedStatement pstmt=connection.prepareStatement(query)){
					pstmt.setInt(1, bookBean.getBookId());
					pstmt.setString(2, bookBean.getBookName());
					int res=pstmt.executeUpdate();
					if(res!=0) {
						return true;
					}
				}catch(LibraryManagementJdbcExceptions lmse) {
					System.err.println("book is not able to update");
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;	
	}
	@Override
	public BookInformationJdbc searchBook(int bookId) {
		BookInformationJdbc bookBean=new BookInformationJdbc();
		try(FileInputStream fis=new FileInputStream("LibraryManagementSystemDataBase.properties")){
			Properties properties=new Properties();
			properties.load(fis);
			Class.forName(properties.getProperty("path")).newInstance();
			String dburl=properties.getProperty("dburl");
			try(Connection connection=DriverManager.getConnection(dburl)){
				String query=properties.getProperty("searchBook");
				try(PreparedStatement pstmt=connection.prepareStatement(query)){
					pstmt.setInt(1, bookId);
					ResultSet rs=pstmt.executeQuery();
					if(rs.next()) {
						bookBean.setBookId(rs.getInt("bookId"));
						bookBean.setBookName(rs.getString("bookName"));
						bookBean.setAuthorName(rs.getString("authorName"));
						return bookBean;
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Admin_User_InformationJdbc> showUsers() {
		try(FileInputStream fis = new FileInputStream("LibraryManagementSystemDataBase.properties")){
			Properties properties = new Properties();
			properties.load(fis);
			Class.forName(properties.getProperty("path")).newInstance();
			
			String dburl=properties.getProperty("dburl");
			
			try(Connection conn = DriverManager.getConnection(dburl)){
				String query =properties.getProperty("getAllUsers") ;
				try(Statement pstmt = conn.createStatement()){
					
					ResultSet rs = pstmt.executeQuery(query);
					List<Admin_User_InformationJdbc> beans=new LinkedList<Admin_User_InformationJdbc>();
					while(rs.next()) {
						Admin_User_InformationJdbc userBean=new Admin_User_InformationJdbc();
						
						userBean.setId(rs.getInt("id"));
						userBean.setUserName(rs.getString("username"));
						userBean.setFirstName(rs.getString("firstname"));
						userBean.setLastName(rs.getString("lastname"));
						userBean.setEmailId(rs.getString("emailid"));
						userBean.setPassword(rs.getString("password"));
						userBean.setRole(rs.getString("role"));
						beans.add(userBean);
						
					
						}
					return beans;
					}	
				}
			}catch (Exception e) {
				e.printStackTrace();
			}

		return null;
	}

	@Override
	public List<BookInformationJdbc> showBooks() {
		try(FileInputStream fis = new FileInputStream("LibraryManagementSystemDataBase.properties")){
			Properties properties = new Properties();
			properties.load(fis);
			Class.forName(properties.getProperty("path")).newInstance();
			
			String dburl=properties.getProperty("dburl");
			
			try(Connection conn = DriverManager.getConnection(dburl)){
				String query =properties.getProperty("getAllBookInfo") ;
				try(Statement pstmt = conn.createStatement()){
					
					ResultSet rs = pstmt.executeQuery(query);
					List<BookInformationJdbc> beans=new ArrayList<BookInformationJdbc>();
					while(rs.next()) {
						BookInformationJdbc bookBean=new BookInformationJdbc();
						bookBean.setBookId(rs.getInt("bookId"));
						bookBean.setBookName(rs.getString("bookName"));
						bookBean.setAuthorName(rs.getString("authorName"));
						bookBean.setPublisher(rs.getString("publisher"));
						bookBean.setCategory(rs.getString("category"));
						beans.add(bookBean);
						
					
						}
					return beans;
					}
						
				}
			}catch (Exception e) {
				e.printStackTrace();
			}

		return null;
	}

	@Override
	public List<RequestInformationJdbc> showRequests() {
		try(FileInputStream fis = new FileInputStream("LibraryManagementSystemDataBase.properties")){
			Properties properties = new Properties();
			properties.load(fis);
			Class.forName(properties.getProperty("path")).newInstance();
			
			String dburl=properties.getProperty("dburl");
			
			try(Connection conn = DriverManager.getConnection(dburl)){
				String query =properties.getProperty("showRequest") ;
				try(Statement pstmt = conn.createStatement()){
					
					ResultSet rs = pstmt.executeQuery(query);
					List<RequestInformationJdbc> beans=new ArrayList<RequestInformationJdbc>();
					while(rs.next()) {
						RequestInformationJdbc requestInfo=new RequestInformationJdbc();
						requestInfo.setRequestId(rs.getInt("requestid"));
						requestInfo.setUserId(rs.getInt("userid"));
						requestInfo.setBookId(rs.getInt("bookid"));
						beans.add(requestInfo);
						}
					return beans;
					}	
				}
			}catch (Exception e) {
				e.printStackTrace();
			}

		return null;
	}

//	@Override
//	public boolean bookIssue(Admin_User_InformationJdbc user, BookInformationJdbc book) {
//		try(FileInputStream fis=new FileInputStream("LibraryManagementSystemDataBase.properties")){
//			Properties properties=new Properties();
//			properties.load(fis);
//			Class.forName(properties.getProperty("path")).newInstance();
//			String dburl=properties.getProperty("dburl");
//			try(Connection connection=DriverManager.getConnection(dburl)){
//				String query=properties.getProperty("issueBook");
//				try(PreparedStatement pstmt=connection.prepareStatement(query)){
//					pstmt.setInt(1, book.getSlno());
//					pstmt.setInt(2, user.getId());
//					pstmt.setInt(3, book.getBookId());
//					pstmt.setString(4, book.getBookName());
//					pstmt.setDate(5, book.getIssueDate());
//					pstmt.setDate(6, book.getReturnDate());
//					pstmt.setInt(7, book.getFine());
//					int isIssued=pstmt.executeUpdate();
//					if(isIssued!=0) {
//						
//						return true;
//					}
//					
//				
//				}
//			}
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//
//		return false;
//	}
	
	@Override
	public boolean bookIssue(int requestId) {
		try(FileInputStream fis = new FileInputStream("LibraryManagementSystemDataBase.properties")){
			Properties properties=new Properties();
			properties.load(fis);
			Class.forName(properties.getProperty("path")).newInstance();
			String dburl=properties.getProperty("dburl");
			try(Connection connection=DriverManager.getConnection(dburl)){
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return false;
	}

	
	@Override
	public boolean isBookReceived(Admin_User_InformationJdbc user, BookInformationJdbc book) {
	
		return false;
	}

	
	

}
