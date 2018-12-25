package com.fahdisa.moshood.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "comment")
public class Comment implements Serializable {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "message", columnDefinition = "TEXT")
    private String message;

    @Column(name = "article_id")
    private Long article;

    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public Comment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getArticle() {
        return article;
    }

    public void setArticle(Long article) {
        this.article = article;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;
        Comment comment = (Comment) o;
        return Objects.equals(getId(), comment.getId()) &&
                Objects.equals(getEmail(), comment.getEmail()) &&
                Objects.equals(getMessage(), comment.getMessage()) &&
                Objects.equals(getArticle(), comment.getArticle()) &&
                Objects.equals(getDate(), comment.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmail(), getMessage(), getArticle(), getDate());
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", message='" + message + '\'' +
                ", article=" + article +
                ", date=" + date +
                '}';
    }
}
