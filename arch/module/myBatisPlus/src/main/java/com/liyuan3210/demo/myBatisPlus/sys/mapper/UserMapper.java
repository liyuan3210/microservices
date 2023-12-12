package com.liyuan3210.demo.myBatisPlus.sys.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.liyuan3210.demo.myBatisPlus.sys.dto.UserDto;
import com.liyuan3210.demo.myBatisPlus.sys.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liyuan
 * @since 2022-04-23
 */
public interface UserMapper extends BaseMapper<User> {

    IPage<User> queryPage(IPage page, @Param("userDto")UserDto userDto);

}
