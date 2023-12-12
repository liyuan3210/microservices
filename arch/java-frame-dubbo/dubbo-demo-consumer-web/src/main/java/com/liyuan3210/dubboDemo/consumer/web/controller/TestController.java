package com.liyuan3210.dubboDemo.consumer.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;
import com.liyuan3210.dubboDemo.client.dto.UserDTO;
import com.liyuan3210.dubboDemo.client.service.UserService;
import com.liyuan3210.dubboDemo.client.vo.UserVO;

/**
 * 
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/sys")
public class TestController {
	
	@Reference
	private UserService userService;
	
	@ResponseBody
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String pushYlgps(HttpServletRequest request,String data) {
		UserDTO userDTO = new UserDTO();
		userDTO.setName("张三");
		String result=userService.helloSay(userDTO);
		System.out.println("call dubbo interface:"+result);
    	System.out.println("ok");
        return "ok";
    }
}
