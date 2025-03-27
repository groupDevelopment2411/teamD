package com.example.demo.Login;

//エンティティクラス
public class Login {
	//変数
	private int id;
    private String name;

    //コメントアウト
    public Login() {}
    public Login(int id, String name) {
        this.id = id;
        this.name = name;
    }

    //ゲッターとセッター
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
}
