package com.antazri.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Author {

    private int id;
    private String uuid;
    private String firstname;
    private String lastname;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Author() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author)) return false;
        Author author = (Author) o;
        return getId() == author.getId() &&
                getUuid().equals(author.getUuid()) &&
                getFirstname().equals(author.getFirstname()) &&
                getLastname().equals(author.getLastname());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUuid(), getFirstname(), getLastname());
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
