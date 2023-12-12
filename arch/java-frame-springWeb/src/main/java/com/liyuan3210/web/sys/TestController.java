package com.liyuan3210.web.sys;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/sys")
public class TestController {
    private JdbcTemplate jdbcTemplate;
	
    @RequestMapping(value="/getView")
    public String getTest(HttpServletRequest request){
    	List<Map<String,Object>> list=jdbcTemplate.queryForList("select * from s_log");
    	for(Map<String,Object> m:list){
    		System.out.println("###id4:"+m.get("id"));
    	}
        return "test-jsp";
    }
    
	@Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
}
