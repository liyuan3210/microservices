package com.liyuan3210.demo.myBatisPlus.sys.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liyuan3210.demo.myBatisPlus.sys.dto.UserDto;
import com.liyuan3210.demo.myBatisPlus.sys.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    @Test
    void select() {
        System.out.println("mapper test:"+userMapper.selectById(1));
    }

    /**
     * xml自定义分页方式
     */
    @Test
    void xmlPage(){
        IPage<User> iPage = new Page<>(1,2);
        UserDto userDto = new UserDto();
        userDto.setName("z1");
        userDto.setAge(3);
        IPage<User> page = userMapper.queryPage(iPage,userDto);
        List<User> records = page.getRecords();
        System.out.println("records："+records);
        System.out.println("getPages："+page.getPages());
    }
}
