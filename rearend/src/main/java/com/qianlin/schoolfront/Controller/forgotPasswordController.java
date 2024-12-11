package com.qianlin.schoolfront.Controller;

import com.qianlin.schoolfront.Pojo.Result;
import com.qianlin.schoolfront.Pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.qianlin.schoolfront.Service.forgotPasswordService;
@RestController
public class forgotPasswordController {
    @Autowired
    private forgotPasswordService forgotPasswordService;

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/forgotPassword")
    public Result forgotPassword(@RequestBody User user){
        try{
            //修改密码
            forgotPasswordService.forgotPassword(user);
            return Result.success("密码修改成功");
        }catch(Exception e){
            return Result.error("找回密码失败，请稍后再试");
        }
    }

}
