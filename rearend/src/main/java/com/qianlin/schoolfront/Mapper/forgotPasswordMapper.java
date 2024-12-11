package com.qianlin.schoolfront.Mapper;

import com.qianlin.schoolfront.Pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface forgotPasswordMapper {
    //修改密码
    @Update("update user set userpassword = #{userpassword} where useremail = #{useremail}")
    void forgotPassword(User user);
}
