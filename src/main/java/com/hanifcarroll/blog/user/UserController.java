package com.hanifcarroll.blog.user;

import com.hanifcarroll.blog.post.Post;
import org.springframework.web.bind.annotation.*;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping({"", "/"})
    public List<User> listUsers() {
        return userRepository.findAll();
    }

    @PostMapping({"", "/"})
    public User createUser(@RequestParam("username") String username) {

        User newUser = new User();
        newUser.setUsername(username);
        userRepository.save(newUser);

        return newUser;
    }

    @GetMapping({"/{id}", "/{id}/"})
    public User getUser(@PathVariable("id") Long id) {

        return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @GetMapping({"/{id}/posts", "/{id}/posts/"})
    public Set<Post> getUSerPosts(@PathVariable("id") Long id) {

        User user = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return user.getPosts();
    }


    @PutMapping({"/{id}", "/{id}/"})
    public User editUser(@PathVariable("id") Long id, @RequestParam("username") String username) {
        User userToUpdate = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        userToUpdate.setUsername(username);
        userRepository.save(userToUpdate);

        return userToUpdate;
    }

    @DeleteMapping({"/{id}", "/{id}/"})
    public String deleteUser(@PathVariable("id") Long id) {

        User userToDelete = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        userRepository.delete(userToDelete);

        return "User deleted.";
    }
}
