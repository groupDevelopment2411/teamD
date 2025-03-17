package com.example.demo;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface DLMapper {
	@Select("SELECT id, name, age, password, start ,end FROM teamd WHERE id =#{id}")
	Entity getById(int id);
	
	@Update("UPDATE teamd SET name = #{name}, age = #{age}, password = #{password}, start = #{start}, end = #{end}  WHERE id = #{id}")
	void update (Entity entity);
	
}
