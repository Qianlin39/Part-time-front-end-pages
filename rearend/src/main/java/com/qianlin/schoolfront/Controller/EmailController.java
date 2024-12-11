package com.qianlin.schoolfront.Controller;

import com.qianlin.schoolfront.Pojo.Result;
import com.qianlin.schoolfront.Pojo.User;
import com.qianlin.schoolfront.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
    @Autowired
    private EmailService emailService;

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/email")
    public Result email(@RequestBody User useremail){
        try{
           User u=emailService.sendEmail(useremail.getUseremail());
            if(u!=null){
                String code=emailService.sEmail(useremail.getUseremail());
                System.out.println(code);
                return Result.success(code);
            }else{
                return Result.error("该邮箱未注册");
            }

        }catch(Exception e){
            e.printStackTrace();
            return Result.error("发送失败，请稍后再试");

        }
    }
}
