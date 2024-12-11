package com.qianlin.schoolfront.Service.Impl;

import com.qianlin.schoolfront.Mapper.PostsMapper;
import com.qianlin.schoolfront.Pojo.PU;
import com.qianlin.schoolfront.Pojo.Post;
import com.qianlin.schoolfront.Service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostsServiceImpl implements PostsService {

    @Autowired
    private PostsMapper postsMapper;

    @Override
    public List<Post> getPostsByUserId(Integer userid) {
        return postsMapper.findPostsByUserId(userid); // 这里调用Mapper方法
    }

    @Override
    public boolean deletePost(Integer postId) {
        int result = postsMapper.deletePostById(postId); // 调用Mapper删除帖子
        return result > 0;
    }

    @Override
    public boolean updatePost(Integer postId, Post post) {
        int result = postsMapper.updatePost(postId, post); // 调用Mapper更新帖子
        return result > 0;
    }

    @Override
    public List<Post> getAllPosts() {
        return postsMapper.findAllPosts(); // 调用Mapper获取所有帖子

    }

    @Override
    public PU getPUByPostId(Integer postId) {
        Post post = postsMapper.getPostById(postId); // 调用Mapper获取帖子
        PU pu =postsMapper.getPUByPostId(postId,post.getPuid());
        return pu;
    }
}
