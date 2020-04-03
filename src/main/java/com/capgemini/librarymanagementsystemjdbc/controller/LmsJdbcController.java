package com.capgemini.librarymanagementsystemjdbc.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import com.capgemini.librarymanagementsystemjdbc.dto.Admin_User_InformationJdbc;
import com.capgemini.librarymanagementsystemjdbc.dto.BookInformationJdbc;
import com.capgemini.librarymanagementsystemjdbc.dto.RequestInformationJdbc;
import com.capgemini.librarymanagementsystemjdbc.factory.LMSJdbcFactory;
import com.capgemini.librarymanagementsystemjdbc.service.AdminJdbcService;
import com.capgemini.librarymanagementsystemjdbc.service.UserJdbcService;
import com.capgemini.librarymanagementsystemjdbc.validation.LibraryManagementJdbcValidations;

public class LmsJdbcController {
	public static void main(String[] args) {

	Admin_User_InformationJdbc libraryUserBean=LMSJdbcFactory.getAdmin_User_InformationJdbc();
	AdminJdbcService adminService=LMSJdbcFactory.getAdminJdbcService();
	UserJdbcService userService=LMSJdbcFactory.getUserJdbcService();
	BookInformationJdbc bookBean=LMSJdbcFactory.getBookInformationJdbc();
	RequestInformationJdbc requestInfo=LMSJdbcFactory.getRequestInformationJdbc();
	LibraryManagementJdbcValidations lmsValidation=LMSJdbcFactory.getLibraryManagementJdbcValidations();
	Scanner scanner=new Scanner(System.in);
	int choice;
	int check;
	int userChoice;
	
	do {
		System.out.println("1.Amin Login");
		System.out.println("2. User Login");
		System.out.println("Enter your choice");
		choice = scanner.nextInt();
		switch(choice) {
		case 1:
			System.out.println("-----------------");
			System.out.println("Enter Admin Email id");
			String emailId = scanner.next();
			System.out.println("Enter Admin password");
			String password = scanner.next();
			try {
				Admin_User_InformationJdbc bean=adminService.login(emailId, password);
				if(bean!=null) {
				System.out.println("Admin Login Successfull");
				}else {
					System.out.println("Admin cannot login");
				}
				do {
					System.out.println("1. Register");
					System.out.println("2. Search");
					System.out.println("3. Add Book");
					System.out.println("4. Show All Books");
					System.out.println("5. Show Users");
					System.out.println("6. Remove Book");
					System.out.println("7. Show Requests");
					System.out.println("8. Book Issue");
					System.out.println("9. Receive Returned Book");
					System.out.println("0. Exit");

					System.out.println("Enter your choice");
					check = scanner.nextInt();
					switch(check) {
					case 1:
						System.out.println("Enter user id");
						int userId=scanner.nextInt();
						System.out.println("Enter username");
						String userName=scanner.next();
						System.out.println("Enter Firstname");
						String firstName=scanner.next();
						System.out.println("Enter Lastname");
						String lastName=scanner.next();
						System.out.println("Enter email id");
						String email=scanner.next();
						System.out.println("Enter password");
						String userPassword=scanner.next();
						System.out.println("Enter role");
						String role=scanner.next();
						Admin_User_InformationJdbc user1=new Admin_User_InformationJdbc();
						user1.setId(userId);
						user1.setUserName(userName);
						user1.setFirstName(firstName);
						user1.setLastName(lastName);
						user1.setEmailId(email);
						user1.setPassword(userPassword);
						user1.setRole(role);
						
						boolean res=adminService.addUser(user1);
						
						if(res) {
							System.out.println("user registered succesfully");
						}else {
							System.out.println("not registered");
						}
						break;
					case 2:
						System.out.println("Search a Book");
						System.out.println("Enter book Id");
						int searchBookId = scanner.nextInt();
						try {
						BookInformationJdbc bookInfo=adminService.searchBook(searchBookId);
						if(bookInfo!=null) {
						System.out.println("Book is found");
						System.out.println(bookInfo.getBookId());
						System.out.println(bookInfo.getBookName());
						System.out.println(bookInfo.getAuthorName());
						
						}
						}catch(Exception e) {
							System.out.println("book not avaliable in library");
						}
						
						break;
					case 3:
						System.out.println("Enter book id");
						int bookid=scanner.nextInt();
						System.out.println("Enter Book name");
						String bookname=scanner.next();
						System.out.println("Enter author name");
						String authorname=scanner.next();
						System.out.println("Enter publishers name");
						String bookPublisher=scanner.next();
						System.out.println("Enter category");
						String category=scanner.next();
						
						
						
						
						BookInformationJdbc bookBean1 = new BookInformationJdbc();

						bookBean1.setBookId(bookid);
						bookBean1.setAuthorName(authorname);
						bookBean1.setBookName(bookname);
						bookBean1.setPublisher(bookPublisher);
						bookBean1.setCategory(category);
					
						boolean bookAdded = adminService.addBook(bookBean1);
						System.out.println(bookAdded);

						if (bookAdded) {
							System.out.println("book is added");
						} else {
							System.out.println("book is not added");
						}

						break;
					case 4:
						
						try {
							System.out.println("Books present in library are :");
							System.out.println("-------------------------------");

							List<BookInformationJdbc> allBooks = adminService.showBooks();
							Iterator<BookInformationJdbc> iterator=allBooks.iterator();
							while(iterator.hasNext()) {
								
								BookInformationJdbc book=(BookInformationJdbc)iterator.next();
							

								System.out.println("Book id ---------->" + book.getBookId());
								System.out.println("Book Name --------> " + book.getBookName());
								System.out.println("Book Authour------> " + book.getAuthorName());
								System.out.println("Book publisher------->"+book.getPublisher());
								System.out.println("Book Category-------->"+book.getCategory());
								System.out.println("-----------------------------------------------------");
							}
							
						} catch (Exception e) {
							System.out.println("no books present in library");
						}
						break;
					case 5:
						try {
							System.out.println("users in library are :");
							System.out.println("-------------------------------");

							List<Admin_User_InformationJdbc> allBooks = adminService.showUsers();
							Iterator<Admin_User_InformationJdbc> iterator=allBooks.iterator();
							while(iterator.hasNext()) {
								
								Admin_User_InformationJdbc user=(Admin_User_InformationJdbc)iterator.next();
							

								System.out.println("user id ---------->" + user.getId());
								System.out.println("Username --------> " + user.getUserName());
								System.out.println("Firstname------> " + user.getFirstName());
								System.out.println("Lastname------->"+user.getLastName());
								System.out.println("Email Id-------->"+user.getEmailId());
								System.out.println("Role---------->"+user.getRole());
								System.out.println("-----------------------------------------------------");
							}
							
						} catch (Exception e) {
							System.out.println("no books present in library");
						}
						break;
					case 6:
						System.out.println("Enter the bookId to be removed:");
						int bookId=scanner.nextInt();
						boolean isDeleted=adminService.removeBook(bookId);
						if(isDeleted) {
							System.out.println("Book removed from library");
						}else {
							System.out.println("Book cannot be removed");
						}
						break;
					case 7:
						
						try {
							System.out.println("Requests for Books are :");
							System.out.println("-------------------------------");

							List<RequestInformationJdbc> requestInfos = adminService.showRequests();
							for (RequestInformationJdbc info : requestInfos) {
								
								
								

								System.out.println("Request Id------------>"+info.getRequestId());
								System.out.println("User id----------- " + info.getUserId());
								System.out.println("Book id ---------- " + info.getBookId());

//								System.out.println("Book Issued ------" + info.isIssued());
//								System.out.println("Book Returned------" + info.isReturned());
//								System.out.println("Book IssueDate------------" + info.getIssuedDate());
								System.out.println("-------------------------------");
							}
						} catch (Exception e) {
							System.out.println("No requests");
						}
						break;

	
						
					}

					
				}while(check!=0);
			}catch(Exception e) {
				System.out.println("Invalid Credentials");
			}
			break;
		case 2:
			System.out.println("-----------------");
			System.out.println("Enter User Email id");
			String userEmailId = scanner.next();
			System.out.println("Enter User password");
			String userPassword = scanner.next();

			try {
				Admin_User_InformationJdbc userInfo = userService.login(userEmailId, userPassword);
				if(userInfo!=null) {
				System.out.println("User logged in");
				}else {
					System.out.println("Invalid Credentials");
				}
				do {
					System.out.println("1. Search a Book");
					System.out.println("2. Request a Book");
					System.out.println("3. Return Book");
					System.out.println("0. Exit");
					System.out.println("Enter your choice");
					userChoice = scanner.nextInt();
					switch (userChoice) {
					case 1:
						System.out.println("Search a Book");
						System.out.println("Enter book Id");
						int searchBookId = scanner.nextInt();
						try {
						BookInformationJdbc bookInfo=userService.searchById(searchBookId);
						if(bookInfo!=null) {
						System.out.println("Book is found");
						System.out.println("Book Id---------->"+bookInfo.getBookId());
						System.out.println("Book name is---------->"+bookInfo.getBookName());
						System.out.println("Author name is---------->"+bookInfo.getAuthorName());
//						System.out.println("Book is found");
						}else {
							System.out.println("Book is not avaliable in library");
						}
						}catch(Exception e) {
							e.printStackTrace();
						}
						
						break;
					case 2:
						System.out.println("Enter user Id");
						int userId=scanner.nextInt();
						libraryUserBean.setId(userId);
						System.out.println("Enter book Id");
						int bookId=scanner.nextInt();
						bookBean.setBookId(bookId);
						try {
							
							RequestInformationJdbc request=userService.bookRequest(libraryUserBean, bookBean);
//							 request = userService.bookRequest(userBean, bookBean);
							System.out.println("Enter Details to place a Request placed to admin");
							System.out.println("User Id-----" + request.getUserBean().getId());
							System.out.println("Book Id-----" + request.getBookBean().getBookId());
							

						} catch (Exception e) {

							System.out.println("Enter valid data or Invalid Request");
						}
						break;		
					

						
					
					}
		
			
		}while(userChoice!=0);
			}catch(Exception e) {
				
			}
		}
	}while(true);
}
}