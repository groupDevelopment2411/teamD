package com.example.demo;

import java.sql.Date;



public class Entity {
	private int id;
	private String name;
	private int age;
	private String password;
	private Date start;
	private Date end;
	
	public Entity(int id, String name, int age, String password, Date start, Date end) {
		this.id = id;
		this.name =name;
		this.age=age;
		this.password =password;
		this.start =start;
		this.end =end;
		
		
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

}