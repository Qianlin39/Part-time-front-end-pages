package com.qianlin.schoolfront.Service.Impl;

import com.qianlin.schoolfront.Mapper.RegisterMapper;
import com.qianlin.schoolfront.Pojo.User;
import com.qianlin.schoolfront.Service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private RegisterMapper registerMapper;

    @Override
    public User register(User user) {
        if (registerMapper.findByEmail(user.getUseremail()) == null) {
            registerMapper.insert(user);
            return user;
        }
        return null;
    }
}
