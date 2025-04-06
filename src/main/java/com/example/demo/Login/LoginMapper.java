package com.example.demo.Login;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

//マッパークラス
@Mapper
public interface LoginMapper {
	
	//社員名とパスワードの取得
	@Select("SELECT * FROM users WHERE id = #{id} AND password = #{password}")
    Login findByNameAndPassword(int id, String password);
}
