package com.assignment;

public class Employee {
	private int id;
	private String firstName;
	private String password;
	private String email;
	private String dob;
	private String country;
	private String gender;

	public Employee(String firstName, String password, String email,String dob,String country,String gender) {
		this.firstName = firstName;
		this.password = password;
		this.email = email;
		this.dob = dob;
		this.country = country;
		this.gender = gender;
	}

	public Employee(int id, String firstName, String password, String email,String dob,String country,String gender) {
		this.firstName = firstName;
		this.password = password;
		this.email = email;
		this.dob = dob;
		this.country = country;
		this.gender = gender;
		this.id = id;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDOB() {
		return dob;
	}

	public void setDOB(String dob) {
		this.dob = dob;
	}
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", password=" + password + ", email=" + email + "]";
	}	
}
