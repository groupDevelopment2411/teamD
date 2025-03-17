package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DLService {
	@Autowired
	private DLMapper mapper;
	public Entity getById(int id) {
		return mapper.getById(id);
	}
	
	public void update(Entity entity) {
		mapper.update(entity);
	}
}


