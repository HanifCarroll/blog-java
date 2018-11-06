package com.hanifcarroll.blog.user;

import com.hanifcarroll.blog.BaseEntity;
import com.hanifcarroll.blog.comment.Comment;
import com.hanifcarroll.blog.post.Post;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "user")
public class User extends BaseEntity {

    @Column(name = "username")
    @NotBlank
    @Size(min = 4, max = 20, message = "Username must be between 4 and 20 characters.")
    private String username;

    private String password;

    @Column(name = "created_at")
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "author")
    @OrderBy("createdAt DESC")
    private Set<Post> posts = new HashSet<>();

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "author")
    @OrderBy("createdAt DESC")
    private Set<Comment> comments = new HashSet<>();

    User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        username = username.trim().replaceAll(" ", "");

        this.username = username;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{" +
                ", username='" + username + '\'' +
                ", posts=" + posts +
                '}';
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
