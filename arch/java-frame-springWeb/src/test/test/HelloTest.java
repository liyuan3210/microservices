package test;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 
 * @author liyuan
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath*:spring-mvc.xml"})
@ContextConfiguration({"/applicationContext_base.xml"})
public class HelloTest{
	private JdbcTemplate jdbcTemplate;
	@Test
	public void list() throws Exception {
		List<Map<String,Object>> list=jdbcTemplate.queryForList("select * from s_log");
    	for(Map<String,Object> m:list){
    		System.out.println("###id:"+m.get("id"));
    	}
		
		System.out.println("ok");
	}

	@Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
}