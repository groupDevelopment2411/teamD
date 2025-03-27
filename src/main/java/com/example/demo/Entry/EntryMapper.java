package com.example.demo.Entry;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

//マッパークラス
@Mapper
public interface EntryMapper {

	@Insert("insert into users(name, age, password) values(#{name}, #{age}, #{password})")
	void insert(Entry entry);
}
