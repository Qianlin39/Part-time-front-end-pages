package com.qianlin.schoolfront.Mapper;

;
import com.qianlin.schoolfront.Pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface RegisterMapper {
    @Select("select * from user where useremail = #{useremail}")
    User findByEmail(String useremail);
    @Insert("insert into user(username,userpassword,useremail) values(#{username},#{userpassword},#{useremail})")
    void insert(User user);
}
