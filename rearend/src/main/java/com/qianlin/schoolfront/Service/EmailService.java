package com.qianlin.schoolfront.Service;

import com.qianlin.schoolfront.Pojo.User;

public interface EmailService {
    User sendEmail(String email);

    String sEmail(String useremail);
}
