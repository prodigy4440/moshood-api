package com.fahdisa.moshood.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "book")
public class Book implements Serializable {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @JsonProperty("short_description")
    @Column(name = "short_description")
    private String shortDescription;

    @JsonProperty("long_description")
    @Column(name = "long_description", columnDefinition = "TEXT")
    private String longDescription;

    @Column(name = "authors")
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> authors;

    @Column(name = "language")
    private String language;

    @Column(name = "pages")
    private Integer pages;

    @Column(name = "chapters")
    private Integer chapters;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "category")
    private String category;

    @Column(name = "price")
    private Integer price;

    @Column(name = "discount")
    private Integer discount;

    @Column(name = "url")
    private String url;

    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    public Book() {
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

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Integer getChapters() {
        return chapters;
    }

    public void setChapters(Integer chapters) {
        this.chapters = chapters;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return Objects.equals(getId(), book.getId()) &&
                Objects.equals(getTitle(), book.getTitle()) &&
                Objects.equals(getShortDescription(), book.getShortDescription()) &&
                Objects.equals(getLongDescription(), book.getLongDescription()) &&
                Objects.equals(getAuthors(), book.getAuthors()) &&
                Objects.equals(getLanguage(), book.getLanguage()) &&
                Objects.equals(getPages(), book.getPages()) &&
                Objects.equals(getChapters(), book.getChapters()) &&
                Objects.equals(getIsbn(), book.getIsbn()) &&
                Objects.equals(getCategory(), book.getCategory()) &&
                Objects.equals(getPrice(), book.getPrice()) &&
                Objects.equals(getDiscount(), book.getDiscount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getShortDescription(),
                getLongDescription(), getAuthors(), getLanguage(), getPages(),
                getChapters(), getIsbn(), getCategory(), getPrice(), getDiscount());
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", longDescription='" + longDescription + '\'' +
                ", authors=" + authors +
                ", language='" + language + '\'' +
                ", pages=" + pages +
                ", chapters=" + chapters +
                ", isbn='" + isbn + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                '}';
    }
}
