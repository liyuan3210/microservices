package com.liyuan3210.dubboDemo.provider.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.apache.dubbo.config.annotation.Service;
import com.liyuan3210.dubboDemo.client.dto.UserDTO;
import com.liyuan3210.dubboDemo.client.service.UserService;
import com.liyuan3210.dubboDemo.client.util.convert.BeanConvert;
import com.liyuan3210.dubboDemo.provider.entity.UserDO;
import com.liyuan3210.dubboDemo.provider.service.UserDOService;

@Service(timeout = 3000, cluster = "failfast", interfaceClass = UserService.class)
//@Service(version = "1.0.0")
public class UserBizServiceImpl implements UserService {
	
	@Autowired
    private UserDOService userDOService;

	@Override
	public String helloSay(UserDTO userDTO) {
		UserDO userDO = BeanConvert.bean2Object(userDTO, UserDO.class);
		String result = userDOService.helloSay(userDO);
		return result;
	}

}
