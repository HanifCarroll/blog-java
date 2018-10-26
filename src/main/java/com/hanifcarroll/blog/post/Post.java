package com.hanifcarroll.blog.post;

import javax.persistence.*;


@Entity
@Table(name="POST")
public class Post {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="ID")
    private long id;

    @Column(name="Title")
    private String title;

    @Column(name="Content")
    private String body;

    public Post() {}

    public Post(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
