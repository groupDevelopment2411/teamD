package com.example.demo;

import java.time.LocalDateTime;

public class Loginentity {
	private int id;
	private String password;
	private String name;
	private LocalDateTime loginTime;

	public Loginentity() {};
	public Loginentity(int id, String password ,String name ,LocalDateTime loginTime) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.loginTime = loginTime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDateTime getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(LocalDateTime loginTime) {
		this.loginTime = loginTime;
	}

}
