package com.liyuan3210.jwtspringsecurity.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.DelegatingFilterProxy;

@RestController
@RequestMapping("/test")
public class TestController3 {
	@GetMapping("hello")
	public String hello() {
		return "hello security";
	}
	@GetMapping("index")
	public String index() {
		return "hello index";
	}
}
