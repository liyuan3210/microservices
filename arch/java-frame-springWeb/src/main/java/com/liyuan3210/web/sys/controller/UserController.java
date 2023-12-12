package com.liyuan3210.web.sys.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.liyuan3210.web.comm.controller.BaseController;
import com.liyuan3210.web.comm.vo.Result;
import com.liyuan3210.web.sys.service.UserService;

@Controller
@RequestMapping("/sys/user")
public class UserController extends BaseController{
	@Resource(name = "userServiceImpl")
	private UserService userServiceImpl;
	
	@RequestMapping(method=RequestMethod.GET)
	public String forwardToPage(){
		return "sys/user_list";
	}
	
	@RequestMapping(params="method=forwardAddJsp")
	public String forwardAddJsp(HttpServletRequest request, HttpServletResponse response){
		return "sys/user_add";
	}
	@RequestMapping(params="method=forwardEditJsp")
	public String forwardEditJsp(HttpServletRequest request, HttpServletResponse response){
		return "sys/user_edit";
	}
	@RequestMapping(params="method=insert")
	public void insert(HttpServletRequest request, HttpServletResponse response){
		Result result=null;
		try{
			Map<String, Object> params=getRequestParams(request);
			userServiceImpl.insert(params);
			this.sendJsonDataToClient(new Result(true,"保存成功！"), response);
		}catch(Exception e){
			this.sendJsonDataToClient(new Result(false,"保存失败！"), response);
		}
	}
	@RequestMapping(params="method=update")
	public void update(HttpServletRequest request, HttpServletResponse response){
		Result result=null;
		try{
			Map<String, Object> params=getRequestParams(request);
			userServiceImpl.update(params);
			this.sendJsonDataToClient(new Result(true,"保存成功！"), response);
		}catch(Exception e){
			this.sendJsonDataToClient(new Result(false,"保存失败！"), response);
		}
	}
	@RequestMapping(params="method=delete")
	public void delete(HttpServletRequest request, HttpServletResponse response,String id){
		Result result=null;
		try{
			Map<String, Object> params=getRequestParams(request);
			userServiceImpl.delete(params);
			this.sendJsonDataToClient(new Result(true,"保存成功！"), response);
		}catch(Exception e){
			this.sendJsonDataToClient(new Result(false,"保存失败！"), response);
		}
	}
	@RequestMapping(params="method=get")
	public void get(HttpServletRequest request, HttpServletResponse response,String id){
			Map<String,Object> resultMap=new HashMap<String,Object>();
			try{
				Map<String, Object> params=getRequestParams(request);
				resultMap=userServiceImpl.getObject(id);
				this.sendJsonDataToClient(resultMap, response);
			}catch(Exception e){
				this.sendJsonDataToClient(resultMap, response);
			}
	}
	@RequestMapping(params="method=queryList")
//	@ResponseBody
	public void queryList(HttpServletRequest request, HttpServletResponse response) throws IOException{
		Map<String,Object> resultMap=new HashMap<String,Object>();
		try{
			Map<String, Object> params=getRequestParams(request);
			resultMap=userServiceImpl.queryList(params);
			this.sendJsonDataToClient(resultMap, response);
		}catch(Exception e){
			this.sendJsonDataToClient(resultMap, response);
		}
//		Map<String, Object> params=getRequestParams(request);
//		System.out.println("#####################queryPageList:"+params+"####ip:"+NetworkUtil.getIpAddress(request));
//		return userServiceImpl.queryList(params);
	}
	
}
