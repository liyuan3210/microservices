package com.liyuan3210.jwtspringsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liyuan3210.jwtspringsecurity.config.JwtUtil;

@RestController
@RequestMapping("/jwt/wx")
public class TestController2 {
	@GetMapping("login")
	public String hello() {
		//验证密码逻辑,返回token
		String userId = "123";
		return JwtUtil.generateToken(userId);
	}
}
