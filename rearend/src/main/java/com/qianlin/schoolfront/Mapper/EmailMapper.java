package com.qianlin.schoolfront.Mapper;

import com.qianlin.schoolfront.Pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmailMapper {
    @Select("select * from user where useremail = #{email}")
    User findByEmail(String email);
}
