package com.qianlin.schoolfront.Controller;

import com.qianlin.schoolfront.Pojo.Result;
import com.qianlin.schoolfront.Pojo.User;
import com.qianlin.schoolfront.Service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class LoginController {
    @Autowired
    private LoginService loginService;

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/login")
    public Result login(@RequestBody User user){
        try{
            log.info("用户登录");
            User u= loginService.login(user);
            u.setUserpassword("null");
            return u!=null?Result.success(u):Result.error("用户名或密码错误");
        }catch(Exception e){
            log.error("登录过程中发生错误",e);
            return Result.error("登录失败，请稍后再试");
        }
    }
}
