package com.example.demo.Login;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

//マッパークラス
@Mapper
public interface LoginMapper {
	
	//社員名とパスワードの取得
	@Select("SELECT * FROM users WHERE name = #{name} AND password = #{password}")
    Login findByNameAndPassword(String name, String password);
}
