package com.liyuan3210.demo.myBatisPlus.sys.service.impl;

import com.liyuan3210.demo.myBatisPlus.sys.entity.User;
import com.liyuan3210.demo.myBatisPlus.sys.mapper.UserMapper;
import com.liyuan3210.demo.myBatisPlus.sys.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liyuan
 * @since 2022-04-23
 */
@Service
public class UserImplService extends ServiceImpl<UserMapper, User> implements UserService {

}
