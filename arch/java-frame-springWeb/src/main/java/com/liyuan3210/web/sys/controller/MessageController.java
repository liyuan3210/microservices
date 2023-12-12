package com.liyuan3210.web.sys.controller;

import java.io.IOException;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.liyuan3210.web.comm.controller.BaseController;
import com.liyuan3210.web.comm.vo.Result;
import com.liyuan3210.web.sys.service.LogService;
import com.liyuan3210.web.sys.service.MessageService;

@Controller
@RequestMapping("/sys/message")
public class MessageController extends BaseController{
	@Resource(name = "messageServiceImpl")
	private MessageService messageServiceImpl;
	
	@Resource(name = "logServiceImpl")
	private LogService logServiceImpl;
	
	@RequestMapping(method=RequestMethod.GET)
	public String forwardToPage(HttpServletRequest request) throws IOException{
		logServiceImpl.insert(request.getRequestURI(),"0",request.getSession().getId()+":留言建议");
		return "sys/message_list";
	}
	
	@RequestMapping(params="method=forwardAddJsp")
	public String forwardAddJsp(HttpServletRequest request, HttpServletResponse response){
		return "sys/message_add";
	}

	@RequestMapping(params="method=insert")
	public void insert(HttpServletRequest request, HttpServletResponse response){
		Result result=null;
		try{
			System.out.println("###保存成功2");
			Map<String, Object> params=getRequestParams(request);
			messageServiceImpl.insert(params);
			this.sendJsonDataToClient(new Result(true,"保存成功！"), response);
		}catch(Exception e){
			this.sendJsonDataToClient(new Result(false,"保存失败！"), response);
		}
	}
	
	@RequestMapping(params="method=transTest")
	public void transTest(HttpServletRequest request, HttpServletResponse response){
		Result result=null;
		try{
			System.out.println("##########transTest");
			messageServiceImpl.transTest();
			this.sendJsonDataToClient(new Result(true,"保存成功！"), response);
		}catch(Exception e){
			this.sendJsonDataToClient(new Result(false,"保存失败！"), response);
		}
	}

	@RequestMapping(params="method=queryList")
	@ResponseBody
	public Map<String, Object> queryList(HttpServletRequest request, HttpServletResponse response){
			Map<String, Object> params=getRequestParams(request);
			return messageServiceImpl.queryList(params);
	}
	
}
