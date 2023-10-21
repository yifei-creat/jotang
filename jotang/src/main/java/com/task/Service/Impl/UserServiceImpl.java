package com.task.Service.Impl;

import com.task.Service.UserService;
import com.task.mapper.UserMapper;
import com.task.pojo.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;//注入Mapper对象
    @Override
    public User login(User user) {
        return userMapper.login(user);
    }
}
