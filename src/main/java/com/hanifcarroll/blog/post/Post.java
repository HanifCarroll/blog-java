package com.hanifcarroll.blog.post;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hanifcarroll.blog.BaseEntity;
import com.hanifcarroll.blog.comment.Comment;
import com.hanifcarroll.blog.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "post")
public class Post extends BaseEntity {

    @Column(name = "title")
    private String title;

    @Column(name = "body")
    private String body;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @NotNull
    @JsonIgnoreProperties({"posts", "comments", "createdAt", "updatedAt"})
    private User author;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "post")
    private Set<Comment> comments = new HashSet<>();

    public Post() {}

    public Post(String title, String body, User author) {
        this.title = title;
        this.body = body;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Post{" +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
