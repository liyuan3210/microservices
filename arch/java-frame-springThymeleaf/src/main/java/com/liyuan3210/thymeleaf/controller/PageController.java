package com.liyuan3210.thymeleaf.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(Model model){
		return new ModelAndView("index");
	}
	@RequestMapping(value = "/tables", method = RequestMethod.GET)
	public ModelAndView tables(Model model){
		return new ModelAndView("tables");
	}
	@RequestMapping(value = "/tablesData", method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> tablesData(Model model){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		for(int i=1;i<100;i++){
			Map<String,Object>  map1 = new HashMap<String,Object>();
			map1.put("id",i);
			map1.put("name","item"+i);
			map1.put("price","$"+i);
			list.add(map1);
		}
		Map<String,Object>  map = new HashMap<String,Object>();
		map.put("total",99);
		map.put("totalNotFiltered",99);
		map.put("rows", list);
		return map;
	}
}
