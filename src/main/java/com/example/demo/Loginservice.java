package com.example.demo;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Loginservice {
	@Autowired
	private Loginmapper mapper;
		public List<Loginentity> selectAll(){
		return mapper.selectAll();
	}
		
	 public List<Loginentity> selectById(int id) {
		return mapper.selectById(id);
	}	
	
public Loginentity getById(int id) {
    List<Loginentity> list = mapper.selectById(id);
    if (list != null && !list.isEmpty()) {
        return list.get(0); 
    }
    return null;
}
}


