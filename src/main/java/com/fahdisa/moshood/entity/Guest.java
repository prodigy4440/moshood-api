package com.fahdisa.moshood.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "guest")
public class Guest implements Serializable {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "url")
    private String url;

    @JsonBackReference
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="event_id")
    private Event event;

    public Guest() {
    }

    public Guest(Long id, String name, String description, String url) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Guest)) return false;
        Guest guest = (Guest) o;
        return Objects.equals(getId(), guest.getId()) &&
                Objects.equals(getName(), guest.getName()) &&
                Objects.equals(getDescription(), guest.getDescription()) &&
                Objects.equals(getUrl(), guest.getUrl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(), getUrl());
    }

    @Override
    public String toString() {
        return "Guest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
