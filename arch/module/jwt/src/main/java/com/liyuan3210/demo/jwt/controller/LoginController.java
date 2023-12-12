package com.liyuan3210.demo.jwt.controller;

import com.liyuan3210.demo.jwt.util.JwtUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jwt")
public class LoginController {
	@GetMapping("login")
	public String hello() {
		//验证密码逻辑,返回token
		String userId = "zhangshan";
		return JwtUtil.generateToken(userId);
	}
}
