package com.liyuan3210.dubboDemo.client.service;

import com.liyuan3210.dubboDemo.client.dto.UserDTO;
import com.liyuan3210.dubboDemo.client.vo.UserVO;

public interface UserService {
	String helloSay(UserDTO userDTO);
}
