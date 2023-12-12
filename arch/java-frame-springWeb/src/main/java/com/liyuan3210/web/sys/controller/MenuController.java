package com.liyuan3210.web.sys.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liyuan3210.web.comm.controller.BaseController;
import com.liyuan3210.web.sys.service.LogService;
import com.liyuan3210.web.sys.service.MenuService;

@Controller
@RequestMapping("/sys/menu")
public class MenuController extends BaseController{
	@Resource(name = "menuServiceImpl")
	private MenuService menuServiceImpl;
	
	@Resource(name = "logServiceImpl")
	private LogService logServiceImpl;

	@RequestMapping(params="method=queryList")
	@ResponseBody
	public List<Map<String,Object>> queryList(HttpServletRequest request, HttpServletResponse response) throws IOException{
//		logServiceImpl.insert(NetworkUtil.getIpAddress(request)+":"+request.getRequestURI(),"0","学习笔记");
		return menuServiceImpl.queryList();
	}
	
	
}
