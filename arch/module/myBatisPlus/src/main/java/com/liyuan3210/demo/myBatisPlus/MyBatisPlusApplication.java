package com.liyuan3210.demo.myBatisPlus;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan("com.liyuan3210.demo.myBatisPlus.sys.mapper")
public class MyBatisPlusApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyBatisPlusApplication.class, args);
	}

	@Bean
	public MybatisPlusInterceptor MybatisPlusInterceptor(){
		MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
		interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
		return interceptor;
	}

}
