package com.example.demo.Login;

//エンティティクラス
public class Login {
	//変数
	//社員ID
	private int id;
	//社員名
    private String name;

    //コンストラクタ
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
