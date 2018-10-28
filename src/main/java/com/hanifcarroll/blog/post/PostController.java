package com.hanifcarroll.blog.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/")
    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity getPost(@PathVariable("id") Long id) {
        Optional<Post> post = postRepository.findById(id);
        return post
                .map(foundPost -> ResponseEntity.ok().body(foundPost))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity editPost(@PathVariable("id") Long id,
                         @RequestParam("title") String title,
                         @RequestParam("body") String body
    ) {
        Optional<Post> post = postRepository.findById(id);

        if (!post.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Post foundPost = post.get();
        foundPost.setTitle(title);
        foundPost.setBody(body);
        postRepository.save(foundPost);

        return ResponseEntity.ok(foundPost);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePost(@PathVariable("id") Long id) {
        Optional<Post> post = postRepository.findById(id);

        if (!post.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        postRepository.deleteById(id);
        return ResponseEntity.ok("Post deleted");
    }

}
