package com.example.demo;

import java.sql.Date;
import java.time.LocalDateTime;


public class Loginentity {
	private String id;
	private String  password;
	private String name;
	private String age;
	private Date start;
	private Date end;
	private LocalDateTime loginTime;

	public Loginentity() {};
	public Loginentity(String id, String password ,String name ,LocalDateTime loginTime) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.loginTime = loginTime;
		
		
		
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	public LocalDateTime getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(LocalDateTime loginTime) {
		this.loginTime = loginTime;
	}

	}
