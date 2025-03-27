package com.example.demo.User;

//エンティティクラス
public class User {
	//変数
	private int id;
	private String name;
	private int age;
	private String password;
	
	//コンストラクタ
	public User() {};
	public User(int id, String name, int age, String password) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.password = password;
	}
	
	//ゲッターとセッター
	public int getId() {
		return this.id;
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
}
