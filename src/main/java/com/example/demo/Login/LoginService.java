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
        //社員情報が存在する場合
        if (user != null) {
        	//社員IDと社員名を持つLoginを作成
            return new Login(user.getId(), user.getName());
        }
        //社員情報が存在しない場合
        return null;
    }
}
