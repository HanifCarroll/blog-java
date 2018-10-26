package com.hanifcarroll.blog.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostRepository postRepository;

    @Autowired
    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @PostMapping("/")
    public Post createPost(@RequestParam("title") String title, @RequestParam("body") String body) {
        Post post = new Post(title, body);
        postRepository.save(post);
        return post;
    }
}
