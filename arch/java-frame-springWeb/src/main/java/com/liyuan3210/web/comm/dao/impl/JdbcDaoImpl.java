package com.liyuan3210.web.comm.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.liyuan3210.web.comm.dao.JdbcDao;

@Repository
//@Repository(value="jdbcDaoImpl")
public class JdbcDaoImpl  implements JdbcDao{
	protected String tableNames;
	protected JdbcTemplate jdbcTemplate;
	protected SimpleJdbcInsert insertActor;
	protected NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.insertActor = new SimpleJdbcInsert(dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
	@Override
	public Integer getCount(String tableNames,Map<String,Object> map) {
		String sql="select count(1) id from "+tableNames;
		Map<String,Object> m=jdbcTemplate.queryForMap(sql);
		Integer count=Integer.parseInt(m.get("id").toString());
		return count;
	}
	@Override
	public Integer getSqlCount(String sql) {
		String s="SELECT count(1) id FROM ("+sql+") t";
		Map<String,Object> m=jdbcTemplate.queryForMap(s);
		Integer count=Integer.parseInt(m.get("id").toString());
		return count;
	}
	@Override
	public List<Map<String, Object>> queryList(String sql) {
		return jdbcTemplate.queryForList(sql);
	}
	@Override
	public List<Map<String, Object>> queryList(Map<String, Object> map,String tableNames,int pageNow,int pageSize) {
		//SELECT * FROM USER WHERE id LIMIT 0,6
		
		return jdbcTemplate.queryForList(getQueryListSql(map,tableNames,pageNow,pageSize));
	}
	
	@Override
	public List<Map<String, Object>> queryList(String sql,int pageNow,int pageSize) {
		//SELECT * FROM USER WHERE id LIMIT 0,6
		
		return jdbcTemplate.queryForList(getQueryListSql(sql,pageNow,pageSize));
	}
	
	@Override
	public int insert(Map<String, Object> map,String tableNames) {
		return jdbcTemplate.update(getInsertSql(map,tableNames));
	}

	@Override
	public int insert2(Map<String, Object> map,String tableNames) {
		insertActor.setTableName(tableNames);
		return insertActor.execute(map);
	}

	@Override
	
	public int update(Map<String, Object> map,String tableNames) {
		return jdbcTemplate.update(getUpdateSql(map,tableNames));
	}
	@Override
	public Map<String, Object> getObject(String sql) {
		return jdbcTemplate.queryForMap(sql);
	}
	@Override
	public Map<String, Object> getObject(String id,String tableNames) {
//		Map<String, Object> paramMap=new HashMap<String, Object>();
//		paramMap.put("ID","1");
//		namedParameterJdbcTemplate.queryForMap("SELECT ID,NAME,AGE FROM USER WHERE ID=:ID", paramMap);
		return jdbcTemplate.queryForMap("SELECT * FROM "+tableNames+" WHERE ID=?",id);
	}
	@Override
	public int delete(String id, String tableNames) {
		return jdbcTemplate.update("delete from "+tableNames+" where id='"+id+"'");
	}
	private String getUpdateSql(Map<String,Object> map,String tableNames){
		//UPDATE USER SET NAME='andy123' WHERE id='123456';
		String id=(null==map.get("id")?(null==map.get("ID")?"":map.get("ID").toString()):map.get("id").toString());
		StringBuffer sql = new StringBuffer();
		sql.append("update ");
		sql.append(tableNames+" set ");
		boolean isNo=true;
		for (Entry<String, Object> entry : map.entrySet()) {
		   String key=entry.getKey();
		   String val=(null==entry.getValue()?"":entry.getValue().toString());
		   if(!(key.toLowerCase()).equals("id")){
			   if(isNo){
				   if(!val.equals("")){
					   sql.append(key+"='"+val+"'");
					   isNo=false;
				   }
			   }else{
				   if(!val.equals("")){
					   sql.append(","+key+"='"+val+"'");
				   }
			   }
		   }
		 }
		sql.append(" where id='"+id+"'");
		if(isNo){
			return "";
		}
		return sql.toString();
	}
	private String getInsertSql(Map<String,Object> map,String tableNames){
//		//INSERT INTO USER(id,NAME,age) VALUES ( '34','34','34')
		StringBuffer keySql=new StringBuffer();
		StringBuffer valSql=new StringBuffer();
		boolean isNo=true;
		
		for (Entry<String, Object> entry : map.entrySet()) {
		   String key=entry.getKey();
		   String val=(null==entry.getValue()?"":entry.getValue().toString());
		   if(isNo){
			   if(!val.equals("")){
				   keySql.append("("+key);
				   valSql.append("('"+val+"'");
				   isNo=false;
			   }
		   }else{
			   if(!val.equals("")){
				   keySql.append(","+key);
				   valSql.append(",'"+val+"'");
			   }
		   }
		 }
		keySql.append(") ");
		valSql.append(")");
		if(isNo){
			return "";
		}
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO "+tableNames);
		sql.append(keySql);
		sql.append("VALUES");
		sql.append(valSql);
		if(isNo){
			return "";
		}
		return sql.toString();
	}
	private String getQueryListSql(Map<String,Object> map,String tableNames,int pageNow,int pageSize){
//		//SELECT * FROM USER WHERE id LIMIT 0,6
		StringBuffer sql=new StringBuffer();
		StringBuffer whereSql=new StringBuffer();
		sql.append("SELECT * FROM "+tableNames+" WHERE 1=1 ");
		if(null!=map){
			for (Entry<String, Object> entry : map.entrySet()) {
				   String key=entry.getKey();
				   String val=(null==entry.getValue()?"":entry.getValue().toString());
				   if(!val.equals("")){
					   whereSql.append("and "+key+"='"+val+"' ");
				   }
			 }
			sql.append(whereSql);
		}
		int start=(pageNow-1)*pageSize;
		sql.append(" LIMIT "+start+","+pageSize);
		return sql.toString();
	}
	private String getQueryListSql(String sql,int pageNow,int pageSize){
//		//SELECT * FROM USER WHERE id LIMIT 0,6
		StringBuffer sql2=new StringBuffer();
		sql2.append(sql);
		int start=(pageNow-1)*pageSize;
		sql2.append(" LIMIT "+start+","+pageSize);
		return sql2.toString();
	}

}
