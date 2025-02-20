package com.example.demo;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface Loginmapper {
	@Select("SELECT * FROM teamd")
	List<Loginentity> selectAll();
	
	@Select("SELECT id ,password  FROM teamd WHERE id = #{id}")
	
	List<Loginentity> selectById(int id);
	
	
	
}
