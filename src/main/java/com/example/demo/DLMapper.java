package com.example.demo;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
@Mapper
public interface DLMapper {
	@Update("UPDATE teamd SET name = #{name}, age = #{age}, password = #{password}, start = #{start}, end = {end} WHERE id = #{id}")void update (Entity entity);
}
