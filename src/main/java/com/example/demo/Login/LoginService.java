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
        Login user = mapper.findByNameAndPassword(name, password);
        if (user != null) {
            return new Login(user.getId(), user.getName());
        }
        return null;
    }
}
