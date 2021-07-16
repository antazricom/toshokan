package com.antazri.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Artist {

    private int id;
    private String uuid;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Artist() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        if (!(o instanceof Artist)) return false;
        Artist artist = (Artist) o;
        return id == artist.id && Objects.equals(uuid, artist.uuid) && Objects.equals(name, artist.name) && Objects.equals(createdAt, artist.createdAt) && Objects.equals(updatedAt, artist.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uuid, name, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
