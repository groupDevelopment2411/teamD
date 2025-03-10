package com.example.demo;

import org.apache.ibatis.annotations.Update;

public interface Mapper {
	@Update("UPDATE teamd SET name = #{name}, age = #{age}, password = #{password}, start = #{start}, end = {end} WHERE id = #{id}")void update (Entity entity);
}
