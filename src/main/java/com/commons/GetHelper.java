package com.commons;

import com.model.Book;
import com.model.User;

public class GetHelper {
	int index;
	User userObj;
	Book bookObj;

	public GetHelper(int index, User userObj, Book bookObj) {
		super();
		this.index = index;
		this.userObj = userObj;
		this.bookObj=bookObj;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public User getUserObj() {
		return userObj;
	}

	public void setUserObj(User userObj) {
		this.userObj = userObj;
	}

	public Book getBookObj() {
		return bookObj;
	}

	public void setBookObj(Book bookObj) {
		this.bookObj = bookObj;
	}

}