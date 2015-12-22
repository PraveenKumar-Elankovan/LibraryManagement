package com.service;

import java.util.ArrayList;
import java.util.List;
import com.model.*;

public class LibraryService {

	private List<User> users = new ArrayList<User>();
	private List<Book> books = new ArrayList<Book>();
	
	public List<User> getAllUsers() {
		return users;
	}

	public void addUser(User user) {
		user.save();
		users.add(user);
	}

	public void updateUser(int userIndex, User updatedUser) {
		users.set(userIndex, updatedUser);
	}

	public int getUserIndex(String userId) {
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getId().equals(userId))
				return i;
		}
		return -1;
	}

	public User getUser(String userId) {
		for (User user : users) {
			if (user.getId().equals(userId))
				return user;
		}
		return null;
	}

	public List<Book> getAllBooks() {
		return books;
	}

	public void addBook(Book book) {
		book.save();
		books.add(book);
	}

	public List<Book> getBookByName(String name) {
		List<Book> bookList = new ArrayList<Book>();
		for (int i = 0; i < books.size(); i++) {
			Book book = books.get(i);
			if (book.getName().equalsIgnoreCase(name))
				bookList.add(book);
		}
		return bookList;
	}

	public int getBookIndex(String bookId) {
		for (int i = 0; i < books.size(); i++) {
			if (books.get(i).getId().equals(bookId))
				return i;
		}
		return -1;
	}

	public Book getBook(String bookId) {
		for (Book book : books) {
			if (book.getId().equals(bookId))
				return book;
		}
		return null;
	}
	
	public Book checkOutBook(String userId, String bookId) throws Exception {
		User user = this.getUser(userId);
		if (user == null)
			throw new Exception("User " + userId + " not found");
		Book book = this.getBook(bookId);
		if (book.getCheckedOutBy() == null) {
			book.checkOut(userId);
			return book;
		}
		throw new Exception("Book " + bookId + " already checked out");
	}

}
