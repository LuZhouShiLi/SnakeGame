package com.kob.backedn2.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kob.backedn2.pojo.User;
import org.apache.ibatis.annotations.Mapper;

// 将增删改查的操作映射成sql语句
@Mapper
public interface UserMapper extends BaseMapper<User> {


}
