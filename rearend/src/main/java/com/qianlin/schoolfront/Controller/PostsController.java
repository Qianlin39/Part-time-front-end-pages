package com.qianlin.schoolfront.Controller;

import com.qianlin.schoolfront.Pojo.PU;
import com.qianlin.schoolfront.Pojo.Post;
import com.qianlin.schoolfront.Pojo.Result;
import com.qianlin.schoolfront.Pojo.User;
import com.qianlin.schoolfront.Service.PostsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@Slf4j
public class PostsController {

    @Autowired
    private PostsService postsService;

    // 获取指定用户的所有帖子
    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/{userId}")
    public ResponseEntity<List<Post>> getPostsByUserId(@PathVariable Integer userId) {
        List<Post> posts = postsService.getPostsByUserId(userId);
        if (posts != null && !posts.isEmpty()) {
            return ResponseEntity.ok(posts);
        } else {
            return ResponseEntity.status(404).body(null);
        }
    }

    // 删除指定的帖子
    @CrossOrigin(origins = "http://localhost:5173")
    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Integer postId) {
        boolean isDeleted = postsService.deletePost(postId);
        if (isDeleted) {
            return ResponseEntity.ok("删除成功");
        } else {
            return ResponseEntity.status(400).body("删除失败，帖子不存在");
        }
    }

    // 更新指定的帖子
    @CrossOrigin(origins = "http://localhost:5173")
    @PutMapping("/{postId}")
    public ResponseEntity<String> updatePost(@PathVariable Integer postId, @RequestBody Post post) {
        boolean isUpdated = postsService.updatePost(postId, post);
        if (isUpdated) {
            return ResponseEntity.ok("更新成功");
        } else {
            return ResponseEntity.status(400).body("更新失败，帖子不存在");
        }
    }
    //获取所有帖子
    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/all")
    public List<Post> getAllPosts() {
        return postsService.getAllPosts();
    }

    //获取指定帖子
    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/post/{postId}")
    public Result getPostById(@PathVariable Integer postId) {
        PU p=postsService.getPUByPostId(postId);
        if (p != null) {
            return Result.success(p);
        } else {
            return Result.error("帖子不存在");
        }
    }

}
