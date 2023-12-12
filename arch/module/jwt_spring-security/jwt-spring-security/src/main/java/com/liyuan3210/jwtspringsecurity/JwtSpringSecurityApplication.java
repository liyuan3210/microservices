package com.liyuan3210.jwtspringsecurity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import com.liyuan3210.jwtspringsecurity.config.JwtAuthenticationFilter;

@SpringBootApplication
@MapperScan("com.liyuan3210.jwtspringsecurity.springsecurity.mapper")
public class JwtSpringSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtSpringSecurityApplication.class, args);
	}
	
//    @Bean
//    public FilterRegistrationBean jwtFilter() {
//        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//        JwtAuthenticationFilter filter = new JwtAuthenticationFilter();
//        registrationBean.setFilter(filter);
//        return registrationBean;
//    }

}
