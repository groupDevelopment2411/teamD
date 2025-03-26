package com.example.demo.Login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements LoginMapper{
	@Autowired
	private LoginMapper mapper;

    public Login findByNameAndPassword(String name, String password) {
        Login user = mapper.findByNameAndPassword(name, password);
        if (user != null) {
            return new Login(user.getId(), user.getName());
        }
        return null;
    }
}
