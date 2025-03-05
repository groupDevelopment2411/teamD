package com.example.demo;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
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
}
