package com.liyuan3210.web.comm.controller;


import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.liyuan3210.web.sys.service.LogService;

@Controller
@RequestMapping("/login")
public class loginController extends BaseController{
	@Resource(name = "logServiceImpl")
	private LogService logServiceImpl;
	
	@RequestMapping(method=RequestMethod.GET)
	public String forwardToPage(HttpServletRequest request) throws IOException{
//		System.out.println("###NetworkUtil:"+NetworkUtil.getIpAddress(request));
//		System.out.println("1###session id:"+request.getSession().getId());
//		System.out.println("###session url:"+request.getRequestURI());
//		System.out.println("###session url tostring:"+request.getRequestURI().toString());
//		System.out.println("###session getRemoteAddr:"+request.getRemoteAddr());
//		System.out.println("###session getRemoteHost:"+request.getRemoteHost());
//		System.out.println("###session getRemotePort:"+request.getRemotePort());
//		logServiceImpl.insert(request.getRequestURI(),"0",request.getSession().getId()+":login登录");
		return "comm/index";
	}
	@RequestMapping(params="method=forwardTopJsp")
	public String forwardTopJsp(){
		return "comm/top";
	}
	@RequestMapping(params="method=forwardZtreeLeftJsp")
	public String forwardZtreeLeftJsp(HttpServletRequest request){
		logServiceImpl.insert(request.getRequestURI(),"0",request.getSession().getId()+":学习笔记");
		return "comm/ztreeLeft";
	}
	@RequestMapping(params="method=forwardImgListJsp")
	public String forwardImgListJsp(HttpServletRequest request){
		logServiceImpl.insert(request.getRequestURI(),"0",request.getSession().getId()+":生活相册");
		return "comm/imgList";
	}
	@RequestMapping(params="method=forwardLicJsp")
	public String forwardLicJsp(HttpServletRequest request){
		return "comm/content";
	}
	@RequestMapping(params="method=forwardBottomJsp")
	public String forwardBottomJsp(){
		return "comm/bottom";
	}
	@RequestMapping(params="method=forwardAccordionLeftJsp")
	public String forwardAccordionLeftJsp(){
		return "comm/accordionLeft";
	}
	@RequestMapping(params="method=forwardLeftJsp")
	public String forwardLeftJsp(){
		return "comm/left";
	}
	
}
