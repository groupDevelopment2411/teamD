package com.example.demo.Delete;

//エンティティクラス
public class Delete {
	//変数
	//社員ID
	private int id;
	
	//コンストラクタ
	public Delete() {};
	public Delete(int id) {
		this.id = id;
	}
	
	//ゲッターとセッター
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
