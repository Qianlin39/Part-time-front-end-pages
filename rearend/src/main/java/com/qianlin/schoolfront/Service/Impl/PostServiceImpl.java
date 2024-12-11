package com.qianlin.schoolfront.Service.Impl;

import com.qianlin.schoolfront.Mapper.PostMapper;
import com.qianlin.schoolfront.Pojo.Post;
import com.qianlin.schoolfront.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostMapper postMapper;

    @Override
    public Post addPost(Post post) {
         postMapper.addPost(post);
         return post;

    }
}
