package com.hanifcarroll.blog.user;

import com.hanifcarroll.blog.comment.Comment;
import com.hanifcarroll.blog.post.Post;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    public Long id;

    @Column(name = "username")
    public String username;

    @Column(name = "created_at")
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "author")
    @OrderBy("createdAt DESC")
    public Set<Post> posts = new HashSet<>();

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "author")
    @OrderBy("createdAt DESC")
    public Set<Comment> comments = new HashSet<>();

    public User() {
    }

    public User(String username) {
        this.username = username;
    }

    public User(String username, Set<Post> posts) {
        this.username = username;
        this.posts = posts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
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
                "id=" + id +
                ", username='" + username + '\'' +
                ", posts=" + posts +
                '}';
    }
}
