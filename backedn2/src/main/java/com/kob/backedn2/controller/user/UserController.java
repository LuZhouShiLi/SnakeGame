package com.kob.backedn2.controller.user;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kob.backedn2.mapper.UserMapper;
import com.kob.backedn2.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserMapper userMapper;
//     注解开发  获取所有的用户
    @GetMapping("/user/all/")
    public List<User> getAll(){
        return userMapper.selectList(null);
    }
//    查询指定id的信息  userid 使用{} 进行包围
    @GetMapping("/user/{userId}/")
    public User getuser(@PathVariable int userId){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",userId);
        return userMapper.selectOne(queryWrapper);
    }

    // 插入指定用户数据
    @GetMapping("/user/add/{userId}/{username}/{password}/")
    public String addUser(@PathVariable int userId,@PathVariable String username,@PathVariable String password){
        User user = new User(userId,username,password);
        userMapper.insert(user);
        return "Add User Successfully";
    }

    // 删除用户   判断urL格式  然后调用该函数
    @GetMapping("/user/delete/{userId}/")
    public String deleteUser(@PathVariable int userId){
        userMapper.deleteById(userId);// 根据Id删除用户
        return "Delete User Successfully";
    }
}
