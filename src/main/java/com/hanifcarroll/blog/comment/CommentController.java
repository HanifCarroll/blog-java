package com.hanifcarroll.blog.comment;

import com.hanifcarroll.blog.post.Post;
import com.hanifcarroll.blog.post.PostRepository;
import com.hanifcarroll.blog.user.User;
import com.hanifcarroll.blog.user.UserRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping({"/api/comments", "/api/comments/"})
public class CommentController {

    private CommentRepository commentRepository;
    private UserRepository userRepository;
    private PostRepository postRepository;

    public CommentController(CommentRepository commentRepository, UserRepository userRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @PostMapping({"", "/"})
    public Comment createComment(
            @RequestParam("text") String text,
            @RequestParam("postId") Long postId,
            @RequestParam("authorId") Long authorId
    ) {
        User author = userRepository.findById(authorId).orElseThrow(EntityNotFoundException::new);
        Post post = postRepository.findById(postId).orElseThrow(EntityNotFoundException::new);

        Comment newComment = new Comment(text, author, post);

        return commentRepository.save(newComment);

    }

}
