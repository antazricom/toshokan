package com.antazri.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Vinyl {

    private int id;
    private String uuid;
    private String title;
    private Artist artist;
    private int publicationYear;
    private Category category;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Vinyl() {
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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
        if (!(o instanceof Vinyl)) return false;
        Vinyl vinyl = (Vinyl) o;
        return id == vinyl.id && publicationYear == vinyl.publicationYear
                && Objects.equals(uuid, vinyl.uuid) && Objects.equals(title, vinyl.title)
                && Objects.equals(artist, vinyl.artist) && Objects.equals(category, vinyl.category)
                && Objects.equals(createdAt, vinyl.createdAt) && Objects.equals(updatedAt, vinyl.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uuid, title, artist, publicationYear, category, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "Vinyl{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", title='" + title + '\'' +
                ", artist=" + artist +
                ", publicationYear=" + publicationYear +
                ", category=" + category +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
