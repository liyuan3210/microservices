package com.liyuan3210.web.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.liyuan3210.web.comm.dao.JdbcDao;
import com.liyuan3210.web.sys.service.MenuService;

@Service
//@Service(value = "userServiceImpl")
public class MenuServiceImpl implements MenuService {
	@Resource(name = "jdbcDaoImpl")
	private JdbcDao jdbcDao;

	@Override
	public List<Map<String,Object>> queryList() {
		return jdbcDao.queryList("SELECT t.id,t.p_id,t.m_name name,t.m_url addr,t.m_tp,t.create_dt,t.m_desc,'true' is_open FROM s_menu t where t.m_tp=0");
	}

}
