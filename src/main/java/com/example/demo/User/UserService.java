package com.example.demo.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserMapper {
	@Autowired
	private UserMapper mapper;
	
	public List<User> selectByIds(List<Integer> ids){
		return mapper.selectByIds(ids);
	}
}
