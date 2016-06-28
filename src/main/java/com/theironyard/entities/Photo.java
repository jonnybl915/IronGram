package com.theironyard.entities;

import javax.persistence.*;
import java.security.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by jonathandavidblack on 6/28/16.
 */
@Entity
@Table(name = "photos")
public class Photo {
    @Id
    @GeneratedValue
    int id;

    @ManyToOne
    User sender;

    @ManyToOne
    User recipient;

    @Column(nullable = false)
    String filename;

    @Column(nullable = true)
    LocalDateTime time;

    @Column
    int durationInSeconds;

    public Photo() {
    }

    public Photo(User sender, User recipient, String filename) {
        this.sender = sender;
        this.recipient = recipient;
        this.filename = filename;
    }

    public Photo(User sender, User recipient, String filename, LocalDateTime time) {
        this.sender = sender;
        this.recipient = recipient;
        this.filename = filename;
        this.time = time;
    }

    public Photo(User sender, User recipient, String filename, LocalDateTime time, int durationInSeconds) {
        this.sender = sender;
        this.recipient = recipient;
        this.filename = filename;
        this.time = time;
        this.durationInSeconds = durationInSeconds;
    }

    public Photo(int id, User sender, User recipient, String filename, LocalDateTime time) {
        this.id = id;
        this.sender = sender;
        this.recipient = recipient;
        this.filename = filename;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public int getDurationInSeconds() {
        return durationInSeconds;
    }

    public void setDurationInSeconds(int durationInSeconds) {
        this.durationInSeconds = durationInSeconds;
    }
}
