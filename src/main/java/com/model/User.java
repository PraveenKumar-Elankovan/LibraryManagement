package com.model;

import java.util.UUID;

public class User {

	private String id;
	private String firstName;
	private String middleName;
	private String lastName;
	private int age;
	private String gender;
	private String phone;
	private String zip;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public User() {
	}

	public void save() {
		this.id = UUID.randomUUID().toString();
	}

	/*
	 * public static User createUser(String fName) {
	 * if(isValid(fName,mName,lName,age,gender,phone,zip)) { User user=new
	 * User(fName,mName,lName,age,gender,phone,zip); return user; } return null;
	 * }
	 */

	public boolean isValid() {
		if (this.getFirstName() == null
				|| this.getFirstName().isEmpty()
				|| !this.getFirstName().chars()
						.allMatch(x -> Character.isLetter(x)))
			return false;

		if (!this.getMiddleName().isEmpty()
				&& !this.getMiddleName().chars()
						.allMatch(x -> Character.isLetter(x)))
			return false;

		if (this.getLastName() == null
				|| this.getLastName().isEmpty()
				|| !this.getLastName().chars()
						.allMatch(x -> Character.isLetter(x)))
			return false;

		if (this.getAge() <= 0)
			return false;

		if (this.getGender().equals( "M") && this.getGender().equals( "F"))
			return false;

		if (this.getPhone() == null || this.getPhone().isEmpty()
				|| !this.getPhone().chars().allMatch(x -> Character.isDigit(x))
				|| this.getPhone().length() != 10)
			return false;

		return true;
	}

	public void update(String userId) {
		this.id = userId;
	}

	public String getId() {
		// TODO Auto-generated method stub
		return this.id;
	}
}
