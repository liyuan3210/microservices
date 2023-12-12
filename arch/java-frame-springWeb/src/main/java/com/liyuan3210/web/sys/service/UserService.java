package com.liyuan3210.web.sys.service;

import java.util.List;
import java.util.Map;

public interface UserService {

	public Map<String,Object> queryList(Map<String, Object> map);
	
	public int insert(Map<String,Object> map);
	
	public int update(Map<String,Object> map);
	
	public Map<String,Object> getObject(String id);
	public int delete(Map<String,Object> map);
}
