package com.fahdisa.moshood.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "section")
public class Section implements Serializable {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @ElementCollection
    @Column(name = "urls")
    private List<String> urls;

    public Section() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Section)) return false;
        Section section = (Section) o;
        return getId().equals(section.getId()) &&
                getContent().equals(section.getContent()) &&
                getUrls().equals(section.getUrls());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getContent(), getUrls());
    }

    @Override
    public String toString() {
        return "Section{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", urls=" + urls +
                '}';
    }
}
