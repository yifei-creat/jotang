package com.task.Controller;


import com.task.Service.UserService;
import com.task.Utils.JwtUtils;
import com.task.pojo.Result;
import com.task.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody User user){
        User u=userService.login(user);

        //登录成功,生成JWT令牌
        if(u!=null){

            Map<String, Object> claims=new HashMap<>();
            claims.put("id",u.getId());
            claims.put("username",u.getUsername());
            claims.put("password",u.getPassword());
            String jwt = JwtUtils.generateJwt(claims);//令牌包含用户基本信息

            return Result.success(jwt);
        }
        //否则,返回错误信息
        return Result.error("用户名或密码错误");
    }
}
