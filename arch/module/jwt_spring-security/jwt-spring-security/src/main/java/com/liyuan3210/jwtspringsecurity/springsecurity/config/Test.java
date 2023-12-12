package com.liyuan3210.jwtspringsecurity.springsecurity.config;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;

public class Test {

	public static void main(String[] args) {
		/*
		 * new FilterSecurityInterceptor(); //方法级过滤器(过滤哪些方法可以放行) new
		 * ExceptionTranslationFilter(); //异常过滤器 new
		 * UsernamePasswordAuthenticationFilter(); //用户认证过滤器
		 */		
		UserDetailsService userDetailsService=null;
		PasswordEncoder passwordEncoder=null;
		JdbcTokenRepositoryImpl jdbcTokenRepositoryImpl=null;
		System.out.println("ok");
	}

}
