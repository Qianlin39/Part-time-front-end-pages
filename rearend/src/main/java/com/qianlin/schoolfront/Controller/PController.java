package com.qianlin.schoolfront.Controller;

import com.qianlin.schoolfront.Pojo.Post;
import com.qianlin.schoolfront.Service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class PController {
    @Autowired
    private PostService postService;

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/addPost")
    public void addPost(@RequestBody Post post) {
//        打印数据
        String Pname = post.getPname();
        String Pcontent = post.getPcontent();
        String Ptype = post.getPtype();
        Integer Puid = post.getPuid();
        Integer Psalary = post.getPsalary();

        log.info( Pname + " " + Pcontent + " " + Ptype + " " + Puid + " " + Psalary);
        try {
            log.info("用户发帖");
            Post p=new Post();
            p.setPname(Pname);
            p.setPcontent(Pcontent);
            p.setPtype(Ptype);
            p.setPuid(Puid);
            p.setPsalary(Psalary);
            p.setPstatuse("1");
           postService.addPost(p);
        } catch (Exception e) {
            log.error("发帖过程中发生错误", e);
        }
    }
}
