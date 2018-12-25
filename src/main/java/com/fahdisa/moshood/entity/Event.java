package com.fahdisa.moshood.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "event")
public class Event implements Serializable {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @JsonProperty("start_time")
    @Column(name = "start_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;

    @JsonProperty("end_time")
    @Column(name = "end_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;

    @Column(name = "venue", columnDefinition = "TEXT")
    private String venue;

    @Column(name = "url")
    private String url;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name="event_guest", joinColumns = {
            @JoinColumn(name="event_id", referencedColumnName="id")
    }, inverseJoinColumns = {
            @JoinColumn(name="guest_id", referencedColumnName="id", unique=true) }
            )
    private List<Guest> guests;

    public Event() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Guest> getGuests() {
        return guests;
    }

    public void setGuests(List<Guest> guests) {
        this.guests = guests;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event)) return false;
        Event event = (Event) o;
        return getId().equals(event.getId()) &&
                Objects.equals(getTitle(), event.getTitle()) &&
                Objects.equals(getDescription(), event.getDescription()) &&
                Objects.equals(getStartTime(), event.getStartTime()) &&
                Objects.equals(getEndTime(), event.getEndTime()) &&
                Objects.equals(getVenue(), event.getVenue()) &&
                Objects.equals(getUrl(), event.getUrl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getDescription(),
                getStartTime(), getEndTime(), getVenue(), getUrl());
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", venue='" + venue + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
