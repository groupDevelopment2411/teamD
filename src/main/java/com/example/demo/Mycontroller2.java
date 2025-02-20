package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;

public class Mycontroller2 {
	@RequestMapping("/Select")
	public String Select() {
		 return"index2";
	}

}
