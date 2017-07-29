package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	@RequestMapping("/hello")
	public String helloWorld(){
		System.out.println("helloWorld function");
		return "HelloWorld1";
	}

}
