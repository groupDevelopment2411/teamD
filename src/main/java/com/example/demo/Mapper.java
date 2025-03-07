package com.example.demo;

import org.apache.ibatis.annotations.Update;

public class Mapper {
	@Update("UPDATE member SET name = #{name}, age = #{age},password = #{password},start = #{start},end = {end} WHERE id = #{id}")void update(Member menber);
}
