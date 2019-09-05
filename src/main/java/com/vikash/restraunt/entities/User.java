package com.vikash.restraunt.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Users")
public class User {
	
	@Id
	private int id;
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	private String mobile;
	private String roles;
	private int active;
	private String permissions;

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

	public String getLastName() {

		return lastName;
	}

	public void setLastName(String lastName) {

		this.lastName = lastName;
	}

	public String getPassword() {

		return password;
	}

	public void setPassword(String password) {

		this.password = password;
	}

	public String getEmail() {

		return email;
	}

	public void setEmail(String email) {

		this.email = email;
	}

	public String getMobile() {

		return mobile;
	}

	public void setMobile(String mobile) {

		this.mobile = mobile;
	}


	public void setRoles(String roles) {
		this.roles = roles;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String getPermissions() {
		return permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}

	public List<String> getRolesList() {

		if (this.roles.length() > 0) {
			return Arrays.asList(this.roles.split(","));
		}
		else
			return new ArrayList<>();

	}

	public List<String> getPermissionList() {

		if (this.roles.length() > 0) {
			return Arrays.asList(this.permissions.split(","));
		}
		else
			return new ArrayList<>();

	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", password=" + password
				+ ", email=" + email + ", mobile=" + mobile + ", roles=" + roles + ", active=" + active
				+ ", permissions=" + permissions + "]";
	}

}
