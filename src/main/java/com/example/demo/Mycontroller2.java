package com.example.demo;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Select")
public class Mycontroller2 {

    private final AtomicReference<Loginentity> userLoginData = new AtomicReference<>();

    @PostMapping("/Select")
    public String Select(@RequestBody Loginentity user) {
        user.setLoginTime(LocalDateTime.now()); // 
        userLoginData.set(user);
        return "User " + user.getName() + " logged in at " + user.getLoginTime();
        
    }

    @GetMapping("/Select")
    public Loginentity LoginentityInfo() {
        return userLoginData.get();
    }


    @GetMapping("/Sample1")
    public String Sample1(){
	return "Sample1";
}
    
    @GetMapping("/Sample2")
    public String Sample2(){
    	return "Sample2";
    }
    
    @GetMapping("/Sample3")
    public String Sample3() {
    	return "Sample3";
    }
    
}