package com.example.demo.Login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//サービスクラス
@Service
public class LoginService implements LoginMapper{
	//インスタンス化
	@Autowired
	private LoginMapper mapper;
	
	//社員情報認証
    public Login findByNameAndPassword(String name, String password) {
    	//社員名とパスワード取得
        Login user = mapper.findByNameAndPassword(name, password);
        //社員情報認証成功の場合
        if (user != null) {
            return new Login(user.getId(), user.getName());
        }
        //社員情報認証失敗の場合
        return null;
    }
}
