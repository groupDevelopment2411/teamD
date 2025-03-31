package com.example.demo.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//サービスクラス
@Service
public class UserService implements UserMapper {
	//インスタンス化
	@Autowired
	private UserMapper mapper;
	
	//複数の社員IDを検索するメソッド
	public List<User> selectByIds(List<Integer> ids){
		return mapper.selectByIds(ids);
	}
}
