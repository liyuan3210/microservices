package com.liyuan3210.demo.myBatisPlus.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liyuan3210.demo.myBatisPlus.sys.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService UserService;

    @Test
    void save(){
        System.out.println("service test:"+UserService.getById(1));
    }

    /**
     * 一般分页方式
     */
    @Test
    void page(){
        IPage<User> iPage = new Page<>(1,2);
        IPage<User> page = UserService.page(iPage);
        List<User> records = page.getRecords();
        System.out.println("records："+records);
        System.out.println("getPages："+page.getPages());
    }
}
