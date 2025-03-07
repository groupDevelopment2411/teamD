package com.example.demo.Entry;

//エンティティクラス
public class Entry {
	//変数
	//社員名
	private String name;
	//年齢
	private int age;
	//パスワード
	private String password;
	//パスワード確認用の変数
	private String passwordConfirm;
	
	//コンストラクタ
	public Entry() {};
	public Entry(String name, int age, String password) {
		this.name = name;
		this.age = age;
		this.password = password;
	}
	//ゲッターとセッター作成
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getAge() {
		return this.age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getPassword() {
		return this.password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPasswordConfirm() {
		return this.passwordConfirm;
	}
	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
}
