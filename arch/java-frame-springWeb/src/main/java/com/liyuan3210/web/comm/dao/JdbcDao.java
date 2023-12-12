package com.liyuan3210.web.comm.dao;

import java.util.List;
import java.util.Map;

public interface JdbcDao {
	public List<Map<String,Object>> queryList(Map<String, Object> map,String tableNames,int pageNow,int pageSize);
	
	public List<Map<String,Object>> queryList(String sql,int pageNow,int pageSize);
	
	public List<Map<String,Object>> queryList(String sql);
	
	public int insert(Map<String,Object> map,String tableNames);
	
	public int insert2(Map<String, Object> map,String tableNames);
	
	public int update(Map<String,Object> map,String tableNames);
	
	public Map<String,Object> getObject(String id,String tableNames);
	
	public Map<String, Object> getObject(String sql);
	
	public Integer getCount(String tableNames,Map<String,Object> map);
	
	public Integer getSqlCount(String sql);
	
	public int delete(String id,String tableNames);
}
