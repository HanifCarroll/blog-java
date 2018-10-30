package com.hanifcarroll.blog.user;

import org.springframework.web.bind.annotation.*;
import javax.persistence.EntityNotFoundException;
import java.util.List;

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
