package com.liyuan3210.demo.jwt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jwt")
public class TestController {
	@GetMapping("hello")
	public String hello() {
//		org.springframework.security.web.access
		return "hello world";
	}
}
