package com.liyuan3210.thymeleaf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;

/**
 * @author liyuan
 *
 */
@Controller
@EnableAutoConfiguration
@ComponentScan(basePackages={"com.liyuan3210.thymeleaf"})
public class App {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(App.class, args);
    }
}
