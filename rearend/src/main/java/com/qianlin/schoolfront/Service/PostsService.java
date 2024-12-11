package com.qianlin.schoolfront.Service;

import com.qianlin.schoolfront.Pojo.PU;
import com.qianlin.schoolfront.Pojo.Post;

import java.util.List;

public interface PostsService {

    // 根据用户ID获取所有帖子
    List<Post> getPostsByUserId(Integer userId);

    // 删除指定的帖子
    boolean deletePost(Integer postId);

    // 更新指定的帖子
    boolean updatePost(Integer postId, Post post);

    List<Post> getAllPosts();

    PU getPUByPostId(Integer postId);
}
