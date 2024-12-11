package com.qianlin.schoolfront.Service.Impl;

import com.qianlin.schoolfront.Pojo.User;
import com.qianlin.schoolfront.Service.forgotPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qianlin.schoolfront.Mapper.forgotPasswordMapper;

@Service
public class forgotPasswordServiceImpl implements forgotPasswordService {
    @Autowired
    private forgotPasswordMapper forgotPasswordMapper;

    @Override
    public void forgotPassword(User user) {
        forgotPasswordMapper.forgotPassword(user);
    }
}
