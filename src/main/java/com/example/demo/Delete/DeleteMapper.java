package com.example.demo.Delete;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

//マッパークラス
@Mapper
public interface DeleteMapper {
	
	@Delete("delete from users where id = #{id}")
	void delete(int id);
}
