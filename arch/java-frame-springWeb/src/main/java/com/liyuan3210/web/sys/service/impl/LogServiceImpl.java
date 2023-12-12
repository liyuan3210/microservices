package com.liyuan3210.web.sys.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.liyuan3210.web.comm.dao.JdbcDao;
import com.liyuan3210.web.comm.dao.impl.JdbcDaoImpl;
import com.liyuan3210.web.comm.service.BaseService;
import com.liyuan3210.web.sys.service.LogService;
import com.liyuan3210.web.sys.service.UserService;

@Service
//@Service(value = "userServiceImpl")
public class LogServiceImpl extends JdbcDaoImpl implements LogService {
	@Resource(name = "jdbcDaoImpl")
	private JdbcDao jdbcDao;

	@Override
	public int insert(String url,String tp,String cont) {
		int isNo=0;
		String sql="SELECT count(1) id FROM s_log where l_desc='"+cont+"'";
		Map<String,Object> m=jdbcTemplate.queryForMap(sql);
		Integer count=Integer.parseInt(m.get("id").toString());
		if(count==0){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("id", UUID.randomUUID().toString());
			map.put("l_url", url);
			map.put("l_tp", tp);
			map.put("l_desc", cont);
			map.put("input_dt", sdf.format(new Date()));
			return jdbcDao.insert(map, "s_log");
		}
		return isNo;
		
	}
 
}
