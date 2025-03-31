package com.example.demo.User;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

//マッパークラス
@Mapper
public interface UserMapper {
	
	//複数の社員ID検索
	@Select("<script>"
            + "SELECT * FROM users WHERE id IN "
            + "<foreach item='id' collection='ids' open='(' separator=',' close=')'>"
            + "#{id}"
            + "</foreach>"
            + "</script>")
	List<User> selectByIds(List<Integer> ids);
}
