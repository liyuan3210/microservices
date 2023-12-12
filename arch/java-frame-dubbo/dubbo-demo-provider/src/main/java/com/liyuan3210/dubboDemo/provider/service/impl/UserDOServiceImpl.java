package com.liyuan3210.dubboDemo.provider.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;
import com.liyuan3210.dubboDemo.provider.entity.UserDO;
import com.liyuan3210.dubboDemo.provider.service.UserDOService;
@Service
public class UserDOServiceImpl implements UserDOService {

	@Override
	public String helloSay(UserDO userDO) {
		return ("date:"+new Date()+"--->:"+userDO.getName()+","+userDO.getSay());
	}

	@Override
	public List<UserDO> queryList(UserDO param) {
		return null;
	}

}
