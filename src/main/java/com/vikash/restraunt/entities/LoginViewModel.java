package com.vikash.restraunt.entities;

public class LoginViewModel {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    @Override
	public String toString() {
		return "LoginViewModel [username=" + username + ", password=" + password + "]";
	}

	public void setPassword(String password) {
        this.password = password;
    }
}
