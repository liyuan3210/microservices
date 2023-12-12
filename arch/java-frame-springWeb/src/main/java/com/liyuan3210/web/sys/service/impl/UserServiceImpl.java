package com.liyuan3210.web.sys.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.springframework.stereotype.Service;
import com.liyuan3210.web.comm.service.BaseService;
import com.liyuan3210.web.sys.service.UserService;
import com.liyuan3210.web.util.JsonParseUtil;

@Service
//@Service(value = "userServiceImpl")
public class UserServiceImpl extends BaseService implements UserService{

	
	@Override
	public Map<String, Object> queryList(Map<String, Object> params) {
		Map<String,Object> resutMap=new HashMap<String,Object>();
		Map<String,Object> queryPara=JsonParseUtil.jsonToMap(params.get("queryPara"));
		StringBuffer sql = new StringBuffer();
//		sql.append("SELECT t.id,t.addr name,t.cont des FROM s_log t left join s_menu t2 on(t.id=t2.pId) ");
		sql.append("SELECT t.id,t.u_name,t.u_sex,t.u_weight,DATE_FORMAT(t.birth_dt,'%Y-%m-%d') birth_dt FROM t_user t ");
		sql.append("where 1=1 ");
		//拼接条件sql
		if(null!=queryPara.get("u_name")&&!queryPara.get("u_name").toString().equals("")){
			sql.append(" and t.u_name='"+queryPara.get("u_name").toString()+"'");
		}
		resutMap=queryPage(sql.toString(),params);

		return resutMap;
		
	}

	@Override
	public int insert(Map<String, Object> map) {
		map.put("id", UUID.randomUUID().toString());
		return jdbcDao.insert(map, "t_user");
	}
 
	@Override
	public int update(Map<String, Object> map) {
		return jdbcDao.update(map, "t_user");
	}

	@Override
	public Map<String, Object> getObject(String id) {
		String sql="SELECT t.id,t.u_name,t.u_sex,t.u_weight,DATE_FORMAT(t.birth_dt,'%Y-%m-%d') birth_dt FROM t_user t where t.id='"+id+"'";
		return jdbcDao.getObject(sql);
	}

	@Override
	public int delete(Map<String, Object> map) {
		String id=map.get("id").toString();
		return jdbcDao.delete(id, "t_user");
	}

}
