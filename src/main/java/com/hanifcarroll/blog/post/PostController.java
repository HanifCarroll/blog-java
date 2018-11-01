package com.hanifcarroll.blog.post;

import com.hanifcarroll.blog.user.User;
import com.hanifcarroll.blog.user.UserRepository;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostController(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @PostMapping({"", "/"})
    public Post createPost(
            @RequestParam("title") String title,
            @RequestParam("body") String body,
            @RequestParam("authorId") Long authorId
            ) {

        User author = userRepository.findById(authorId).orElseThrow(EntityNotFoundException::new);
        Post newPost = new Post(title, body, author);
        postRepository.save(newPost);

        return newPost;
    }

    @GetMapping({"", "/"})
    public List<Post> getPosts() {

        return postRepository.findAllByOrderByCreatedAtDesc();
    }

    @GetMapping({"/{id}", "/{id}/"})
    public Post getPost(@PathVariable("id") Long id) {

        return postRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @PutMapping({"/{id}", "/{id}/"})
    public Post editPost(@PathVariable("id") Long id,
                         @RequestParam("title") String title,
                         @RequestParam("body") String body
    ) {
        Post postToUpdate = postRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        postToUpdate.setTitle(title);
        postToUpdate.setBody(body);
        postRepository.save(postToUpdate);

        return postToUpdate;
    }

    @DeleteMapping({"/{id}", "/{id}/"})
    public String deletePost(@PathVariable("id") Long id) {
        Post postToDelete = postRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        postRepository.delete(postToDelete);
        return "Post deleted.";
    }

}
