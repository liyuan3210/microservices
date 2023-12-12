package com.liyuan3210.web.sys.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String, Object> m=new HashMap<String, Object>();
		m.put("ID", "1111111");
		m.put("NAME", "andy li");
		m.put("AGE", "99");
//		System.out.println("###########:"+getInsertSql(m,"USER"));
		StringBuffer keySql=new StringBuffer();
		System.out.println("###########:"+getQueryListSql(m,"USER",1,10));
	}
	
	private static String getUpdateSql(Map<String,Object> map,String tableNames){
		//UPDATE USER SET NAME='andy123' WHERE id='123456';
		String id=(null==map.get("id")?(null==map.get("ID")?"":map.get("ID").toString()):map.get("id").toString());
		StringBuffer sql = new StringBuffer();
		sql.append("update ");
		sql.append(tableNames+" set ");
		boolean isNo=true;
		for (Entry<String, Object> entry : map.entrySet()) {
		   System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
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
	
	private static String getInsertSql(Map<String,Object> map,String tableNames){
//		//INSERT INTO USER(id,NAME,age) VALUES ( '34','34','34')
		StringBuffer keySql=new StringBuffer();
		StringBuffer valSql=new StringBuffer();
		boolean isNo=true;
		
		for (Entry<String, Object> entry : map.entrySet()) {
		   System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
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
	private static String getQueryListSql(Map<String,Object> map,String tableNames,int pageNow,int pageSize){
//		//SELECT * FROM USER WHERE id LIMIT 0,6
		StringBuffer sql=new StringBuffer();
		StringBuffer whereSql=new StringBuffer();
		sql.append("SELECT * FROM "+tableNames+" WHERE 1=1 ");
		boolean isNo=true;
		for (Entry<String, Object> entry : map.entrySet()) {
			   System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
			   String key=entry.getKey();
			   String val=(null==entry.getValue()?"":entry.getValue().toString());
			   if(!val.equals("")){
				   whereSql.append("and "+key+"='"+val+"' ");
				   isNo=false;
			   }
		 }
		sql.append(whereSql);
		int start=(pageNow-1)*pageSize;
		sql.append("and id LIMIT "+start+","+pageSize);
		return sql.toString();
	}
	
	private String getQueryListSql(String sql,int pageNow,int pageSize){
//		//SELECT * FROM USER WHERE id LIMIT 0,6
		StringBuffer sql2=new StringBuffer();
		sql2.append(sql);
		int start=(pageNow-1)*pageSize;
		sql2.append(" and id LIMIT "+start+","+pageSize);
		return sql.toString();
	}

}
