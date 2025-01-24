package com.example.demo;


@controller
public class Mycontroller01 {
	
		@RequestMapping("/first")
		public String first() {
			return "index";
		}

	}

