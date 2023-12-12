package com.liyuan3210.dubboDemo.provider.service;

import java.util.List;

import com.liyuan3210.dubboDemo.client.dto.UserDTO;
import com.liyuan3210.dubboDemo.client.vo.UserVO;
import com.liyuan3210.dubboDemo.provider.entity.UserDO;

public interface UserDOService {
	List<UserDO> queryList(UserDO param);
	String helloSay(UserDO UserDO);
}
