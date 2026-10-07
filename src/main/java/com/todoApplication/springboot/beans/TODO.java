package com.todoApplication.springboot.beans;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

@Entity
public class TODO {

    @Id
    @GeneratedValue
    private int id;
    private String username;

    @Size(min = 5, message = "Description is too short. Enter more than 5 characters")
    private String description;

//    @DateTimeFormat(pattern = "yyyy-MM-dd")  already declared the format in application.properties
    private LocalDate localDate;

    private Boolean done;

    public TODO(int id, String username, String description, LocalDate localDate, Boolean done) {
        this.id = id;
        this.username = username;
        this.description = description;
        this.localDate = localDate;
        this.done = done;
    }

    public TODO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return "TODO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", description='" + description + '\'' +
                ", localDate=" + localDate +
                ", done=" + done +
                '}';
    }
}
