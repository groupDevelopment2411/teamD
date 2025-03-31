package com.example.demo.Delete;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

//マッパークラス
@Mapper
public interface DeleteMapper {
	
	//複数の社員IDをusersテーブルから削除
	@Delete("<script>DELETE FROM users WHERE id IN "
            + "<foreach item='id' collection='list' open='(' separator=',' close=')'>#{id}</foreach>"
            + "</script>")
	void delete(List<Integer> ids);
}
