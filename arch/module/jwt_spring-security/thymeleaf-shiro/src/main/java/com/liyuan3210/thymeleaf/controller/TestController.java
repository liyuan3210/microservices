package com.liyuan3210.thymeleaf.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestController {
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public String getTest(){
		 //jdbcTemplate数据库操作
//    	List<Map<String,Object>> list=jdbcTemplate.queryForList("select * from s_log");
//    	for(Map<String,Object> m:list){
//    		System.out.println("###TestControl id:"+m.get("id"));
//    	}
    	return "SUCCESS";
	}
}
