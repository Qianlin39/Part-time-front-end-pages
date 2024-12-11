package com.qianlin.schoolfront.Mapper;

import com.qianlin.schoolfront.Pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginMapper {
    @Select("select userid,useremail,username,userpassword from user where useremail=#{useremail} ")
    User findEmail(String user);


}
