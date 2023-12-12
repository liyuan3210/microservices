package com.liyuan3210.web.comm.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.liyuan3210.web.util.JsonParseUtil;

public class BaseController {
	public static Map<String, Object> getRequestParams(HttpServletRequest request) {
		Map<String, Object> params = new HashMap<String, Object>();
		Enumeration<?>  e = request.getParameterNames();
		Object ele = null;
		while (e.hasMoreElements()) {
			ele = e.nextElement();
			if(null != ele) {
				params.put(ele.toString(), request.getParameter(ele.toString()));
			}
        }
		params.remove("method");
		return params;
	}

	public static void sendJsonDataToClient(Object data, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		try {
			response.getWriter().write(JsonParseUtil.objctToJson(data));
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
