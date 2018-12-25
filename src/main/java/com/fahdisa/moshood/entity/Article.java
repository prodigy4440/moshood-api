package com.fahdisa.moshood.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "article")
public class Article implements Serializable {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @JsonProperty("short_description")
    @Column(name = "short_description")
    private String shortDescription;

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "article_id")
    private List<Section> sections;

    @JsonProperty("created_date")
    @Column(name = "created_date")
    private Date createdDate;

    public Article() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article)) return false;
        Article article = (Article) o;
        return getId().equals(article.getId()) &&
                getTitle().equals(article.getTitle()) &&
                getShortDescription().equals(article.getShortDescription()) &&
                getSections().equals(article.getSections()) &&
                getCreatedDate().equals(article.getCreatedDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getShortDescription(), getSections(), getCreatedDate());
    }
}
