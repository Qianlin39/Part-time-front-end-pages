package com.qianlin.schoolfront.Controller;

import com.qianlin.schoolfront.Pojo.Result;
import com.qianlin.schoolfront.Pojo.User;
import com.qianlin.schoolfront.Service.RegisterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class RegisterController {
    @Autowired
    private RegisterService registerService;
    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/register")
    public Result register(@RequestBody User user){
        try {
            log.info("用户注册");
            User u = registerService.register(user);
            return u != null ? Result.success() : Result.error("该邮箱已被注册");
        } catch (Exception e) {
            log.error("注册过程中发生错误", e);
            return Result.error("注册失败，请稍后再试");
        }
    }
}
