package com.example.demo.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//サービスクラス
@Service
public class EntryService {
	//インスタンス化
	@Autowired
	private EntryMapper mapper;

	public void insert(Entry entry) {
		mapper.insert(entry);
	}
}
