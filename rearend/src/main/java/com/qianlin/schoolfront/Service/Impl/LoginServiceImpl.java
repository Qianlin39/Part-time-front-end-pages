package com.qianlin.schoolfront.Service.Impl;

import com.qianlin.schoolfront.Mapper.LoginMapper;
import com.qianlin.schoolfront.Pojo.User;
import com.qianlin.schoolfront.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginMapper loginMapper;

    @Override
    public User login(User user) {
        User foundUser = loginMapper.findEmail(user.getUseremail());
        if (foundUser != null) {
            if (foundUser.getUserpassword().equals(user.getUserpassword())) {
                return foundUser;
            }
        }
        return null;
    }


}
