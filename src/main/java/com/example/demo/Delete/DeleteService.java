package com.example.demo.Delete;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//サービスクラス
@Service
public class DeleteService implements DeleteMapper{
	//インスタンス化
	@Autowired
	private DeleteMapper mapper;
	
	//社員情報削除メソッド
	public void delete(List<Integer> ids) {
        mapper.delete(ids); 
    }
}
