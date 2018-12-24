package com.fahdisa.moshood.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "review")
public class Review implements Serializable {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "comment", columnDefinition = "TEXT")
    private String comment;

    @Column(name = "book_id")
    private Long book;

    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public Review() {
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getBook() {
        return book;
    }

    public void setBook(Long book) {
        this.book = book;
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
        if (!(o instanceof Review)) return false;
        Review review = (Review) o;
        return Objects.equals(getId(), review.getId()) &&
                Objects.equals(getEmail(), review.getEmail()) &&
                Objects.equals(getComment(), review.getComment()) &&
                Objects.equals(getBook(), review.getBook()) &&
                Objects.equals(getDate(), review.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmail(), getComment(), getBook(), getDate());
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", comment='" + comment + '\'' +
                ", book=" + book +
                ", date=" + date +
                '}';
    }
}
