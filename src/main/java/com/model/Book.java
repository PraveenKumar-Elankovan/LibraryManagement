package com.model;

import java.util.Arrays; 
import java.util.List;
import java.util.UUID;


public class Book {

	private String id;
	private String name;
	private List<String> authors;
	private String checkedOutBy;

	private Book() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getAuthors() {
		return authors;
	}

	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}

	public String getCheckedOutBy() {
		return checkedOutBy;
	}

	public void setCheckedOutBy(String checkedOutBy) {
		this.checkedOutBy = checkedOutBy;
	}
	
	public void save() {
		this.id = UUID.randomUUID().toString();
	}

	public boolean isValid() {
		if( this.getName()!= null && !this.getName().isEmpty())
			return true;
		return false;
	}

	public String getId() {
		return this.id;
	}

	public void checkOut(String userId) {
		this.setCheckedOutBy(userId);
		
	}
	
	
}
