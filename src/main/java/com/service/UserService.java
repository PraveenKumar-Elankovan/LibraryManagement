package com.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.model.*;
import com.commons.*;

public class UserService {

	private List<User> users = new ArrayList<User>();
	private List<Book> books = new ArrayList<Book>();

	public List<User> getAllUsers() {
		return users;
	}

	public GetHelper getObjAndIndex(String checker, String predicate) {
		if (checker.equals("user")) {
			for (int i = 0; i < users.size(); i++) {
				if (users.get(i).getId().equals(predicate))
					return new GetHelper(i, users.get(i), null);
			}
			throw new IllegalArgumentException("No user with id '" + predicate
					+ "' found");
		} else if (checker.equals("book")) {
			for (int i = 0; i < books.size(); i++) {
				if (books.get(i).getName().equals(predicate))
					return new GetHelper(i, null, books.get(i));
			}
			throw new IllegalArgumentException("No Book with name " + predicate
					+ " found");
		} else {
			for (int i = 0; i < books.size(); i++) {
				if (books.get(i).getId().equals(predicate))
					return new GetHelper(i, null, books.get(i));
			}
			throw new IllegalArgumentException("No Book with ID " + predicate
					+ " found");

		}
	}

	public User createUser(String fName, String mName, String lName, int age,
			char gender, String phone, String zip) {
		User user = new User(String.valueOf(users.size()), fName, mName, lName,
				age, gender, phone, zip);
		users.add(user);
		return user;
	}

	public User updateUser(String id, String fName, String mName, String lName,
			int age, char gender, String phone, String zip) {
		GetHelper userHelper = getObjAndIndex("user", id);
		User user = userHelper.getUserObj();
		int userIndex = userHelper.getIndex();
		user.setFirstName(fName);
		user.setMiddleName(mName);
		user.setLastName(lName);
		user.setAge(age);
		user.setGender(gender);
		user.setPhone(phone);
		user.setZip(zip);
		users.set(userIndex, user);
		return user;
	}

	public List<Book> getAllBooks() {
		return books;
	}

	public Book createBook(String name, String[] authors, String checkedByUserID) {
		Book book = null;
		if (checkedByUserID == null)
			book = new Book(name, Arrays.asList(authors), null);
		else {
			GetHelper userHelper = getObjAndIndex("user", checkedByUserID);
			User user = userHelper.getUserObj();
			book = new Book(name, Arrays.asList(authors), user);
		}
		books.add(book);
		return book;
	}

	public Book checkOutBook(String userID, String bookID) {
		User user = getObjAndIndex("user", userID).getUserObj();
		GetHelper bookHelper = getObjAndIndex("bookID", bookID);
		Book book = bookHelper.getBookObj();
		int index = bookHelper.getIndex();
		if (book.getCheckedOutBy() == null) {
			book.setCheckedOutBy(user);
			books.set(index, book);
			return book;
		}
		return null;
	}

}
