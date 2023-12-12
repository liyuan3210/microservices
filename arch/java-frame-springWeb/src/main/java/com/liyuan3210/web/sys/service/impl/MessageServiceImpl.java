package com.liyuan3210.web.sys.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.liyuan3210.web.comm.dao.JdbcDao;
import com.liyuan3210.web.comm.service.BaseService;
import com.liyuan3210.web.sys.service.MessageService;
import com.liyuan3210.web.util.JsonParseUtil;

@Service
//@Service(value = "userServiceImpl")
public class MessageServiceImpl extends BaseService implements MessageService {
	@Resource(name = "jdbcDaoImpl")
	private JdbcDao jdbcDao;
	
	@Override
	public Map<String, Object> queryList(Map<String, Object> params) {
		
		Map<String,Object> resutMap=new HashMap<String,Object>();
		Map<String,Object> queryPara=JsonParseUtil.jsonToMap(params.get("queryPara"));
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT t.id,t.l_url,t.l_desc,t.l_tp,date_format(t.input_dt,'%Y-%c-%d %h:%i:%s') input_dt FROM s_log t where t.l_tp=1 ");
		//拼接条件sql
		if(null!=queryPara.get("l_desc")&&!queryPara.get("l_desc").toString().equals("")){
			sql.append(" and t.l_desc like '%"+queryPara.get("l_desc").toString()+"%'");
		}
		resutMap=queryPage(sql.toString(),params);

		return resutMap;
		
	}
	
	

	@Override
	public int insert(Map<String, Object> map) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		map.put("l_tp", 1);
		map.put("id", UUID.randomUUID().toString());
		map.put("input_dt", sdf.format(new Date()));
		return jdbcDao.insert(map, "s_log");
	}



	@Override
	public int transTest() {
		Map<String, Object> map=new HashMap<String,Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		map.put("id", UUID.randomUUID().toString());
		map.put("u_name", "lisi");
		map.put("u_sex", 1);
		map.put("u_weight", 170);
		map.put("birth_dt", sdf.format(new Date()));
		jdbcDao.insert(map, "t_user");
		
		Map<String, Object> map2=new HashMap<String,Object>();
		map2.put("id", UUID.randomUUID().toString());
		map2.put("u_name", "zhangsan");
		map2.put("u_sex", "test");
		map2.put("u_weight", 175);
		map2.put("birth_dt", sdf.format(new Date()));
		jdbcDao.insert(map2, "t_user");
		return 0;
	}


}
