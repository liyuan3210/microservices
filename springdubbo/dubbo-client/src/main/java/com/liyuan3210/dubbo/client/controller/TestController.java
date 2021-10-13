package com.liyuan3210.dubbo.client.controller;

import com.liyuan3210.dubbo.api.DemoService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/client")
public class TestController {

    @DubboReference(version = "${demo.service.version}")
    private DemoService demoService;

    @ResponseBody
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String pushYlgps(HttpServletRequest request, String data) {
        System.out.println("########:"+demoService.sayHello("lisi"));
        return "ok";
    }
}
