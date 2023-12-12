package com.liyuan3210.web.comm.service;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import com.liyuan3210.web.comm.dao.JdbcDao;

public class BaseService {
	@Resource(name = "jdbcDaoImpl")
	public JdbcDao jdbcDao;
	public Map<String,Object> queryPage(String sql, Map<String,Object> params) {
		Map<String,Object> map=new HashMap<String,Object>();
		Integer total=jdbcDao.getSqlCount(sql);
		map.put("total_rows",total);
		Integer page=0;
		Integer size=10;
		if(null!=params){
			page=(null==params.get("page")?0:Integer.parseInt(params.get("page").toString()));
			size=(null==params.get("size")?0:Integer.parseInt(params.get("size").toString()));
		}
		map.put("rows", jdbcDao.queryList(sql,page+1,size));
		return map;
	}
	
}
