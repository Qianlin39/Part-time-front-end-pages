package com.qianlin.schoolfront.Mapper;

import com.qianlin.schoolfront.Pojo.PU;
import com.qianlin.schoolfront.Pojo.Post;
import com.qianlin.schoolfront.Pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PostsMapper {

    // 根据用户ID查询所有帖子
    @Select("SELECT * FROM post ,user WHERE Puid = #{userId} And userid=#{userId} AND Pstatuse = '1'") // 只查询状态为 '1' 的帖子
    List<Post> findPostsByUserId(Integer userId);

    // 根据帖子ID查询帖子详细信息
    @Select("SELECT * FROM post WHERE Pid = #{postId} AND Pstatuse = '1'") // 只查询状态为 '1' 的帖子
    Post findPostById(Integer postId);

    // 删除帖子
    @Delete("DELETE FROM post WHERE Pid = #{postId}")
    int deletePostById(Integer postId);

    // 更新帖子
    @Update("UPDATE post SET Pname = #{Pname}, Pcontent = #{Pcontent}, Ptype = #{Ptype}, " +
            "Pstatuse = #{Pstatuse}, Pupdatetime = CURRENT_TIMESTAMP, Psalary = #{Psalary} WHERE Pid = #{Pid}")
    int updatePost(@Param("Pid") Integer postId,
                  Post post);

    // 新增帖子（插入数据）
    @Insert("INSERT INTO post (Pname, Pcontent, Ptype, Puid, Pstatuse, Psalary) " +
            "VALUES (#{Pname}, #{Pcontent}, #{Ptype}, #{Puid}, '1', #{Psalary})")
    @Options(useGeneratedKeys = true, keyProperty = "Pid") // 自动获取生成的主键
    int insertPost(Post post);


    @Select("SELECT * FROM post WHERE Pstatuse = '1'") // 只查询状态为 '1' 的帖子
    List<Post> findAllPosts();

    @Select("SELECT * FROM post WHERE Pid = #{postId} AND Pstatuse = '1'") // 只查询状态为 '1' 的帖子
    Post getPostById(Integer postId);

    @Select("SELECT * FROM user,post WHERE userid = #{puid} AND Pid = #{postId} AND Pstatuse = '1'") // 只查询状态为 '1' 的帖子
    PU getPUByPostId(Integer postId, Integer puid);
}
