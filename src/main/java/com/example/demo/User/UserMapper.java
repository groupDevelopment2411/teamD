package com.example.demo.User;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
	
	@Select("<script>"
            + "SELECT * FROM users WHERE id IN "
            + "<foreach item='id' collection='ids' open='(' separator=',' close=')'>"
            + "#{id}"
            + "</foreach>"
            + "</script>")
	List<User> selectByIds(List<Integer> ids);
}
