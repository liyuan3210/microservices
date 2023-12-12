package com.liyuan3210.web.sys.service;

import java.util.List;
import java.util.Map;

public interface MessageService {

	public Map<String,Object> queryList(Map<String, Object> map);
	
	public int insert(Map<String,Object> map);
	
	public int transTest();
}
