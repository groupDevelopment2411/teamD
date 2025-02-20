package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Mycontroller2 {
	@RequestMapping("/Select")
	public String Select() {
		 return"index2";
	}

}
