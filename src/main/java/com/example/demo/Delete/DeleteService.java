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
	
	public void delete(List<Integer> ids) {
        mapper.delete(ids); 
    }
}
