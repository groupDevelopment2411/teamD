package com.example.demo;

public class Entity {
	private int id;
	private String name;
	private int age;
	private String password;
	private String start;
	private String end;
	
	public Entity(int id, String name, int age, String password, String start, String end) {
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

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}
	

	
}