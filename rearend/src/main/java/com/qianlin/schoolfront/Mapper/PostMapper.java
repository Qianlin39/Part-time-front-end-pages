package com.qianlin.schoolfront.Mapper;

import com.qianlin.schoolfront.Pojo.Post;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostMapper {
    @Insert("insert into post(Pname, Pcontent, Ptype, Puid, Pstatuse, Pupdatetime, Psalary) values(#{Pname},#{Pcontent},#{Ptype},#{Puid},#{Pstatuse},#{Pupdatetime},#{Psalary})")
    void addPost(Post post);
}
