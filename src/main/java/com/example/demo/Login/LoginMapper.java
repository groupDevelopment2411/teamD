package com.example.demo.Login;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginMapper {
	
	@Select("SELECT * FROM users WHERE name = #{name} AND password = #{password}")
    Login findByNameAndPassword(String name, String password);
}
