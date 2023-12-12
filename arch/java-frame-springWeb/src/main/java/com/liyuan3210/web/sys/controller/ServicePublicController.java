package com.liyuan3210.web.sys.controller;

import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.liyuan3210.web.comm.controller.BaseController;
import com.liyuan3210.web.sys.service.LogService;

@Controller
@RequestMapping("/sys/servicePublic")
public class ServicePublicController extends BaseController{
	@Resource(name = "logServiceImpl")
	private LogService logServiceImpl;
	@RequestMapping(method=RequestMethod.GET)
	public String forwardToPage(HttpServletRequest request) throws IOException{
		logServiceImpl.insert(request.getRequestURI(),"0",request.getSession().getId()+":服务开放");
		return "sys/servicePublic";
	}
	
	
}
