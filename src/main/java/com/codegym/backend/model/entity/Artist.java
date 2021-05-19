package com.codegym.backend.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.Date;

@Entity
@JsonIgnoreProperties(value = {"albums", "songs", "avatarBlobString", "avatarUrl"}, allowGetters = true, ignoreUnknown = true)
public class Artist {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String name;

    @JsonIgnore
    private String unaccentName;

    @DateTimeFormat(pattern = "MM-dd-yyyy")
    private Date birthDate;

    private String avatarUrl;

    private String avatarBlobString;

    @Column(columnDefinition = "TEXT")
    private String biography;

    @ManyToMany(mappedBy = "artists", fetch = FetchType.LAZY)
    @Fetch(value = FetchMode.SUBSELECT)
    private Collection<Song> songs;

    @ManyToMany(mappedBy = "artists", fetch = FetchType.LAZY)
    @Fetch(value = FetchMode.SUBSELECT)
    private Collection<Album> albums;

    public Artist(@NotBlank String name) {
        this.name = name;
    }

    public Artist(@NotBlank String name, Date birthDate, String avatarUrl, String biography) {
        this.name = name;
        this.birthDate = birthDate;
        this.avatarUrl = avatarUrl;
        this.biography = biography;
    }

    public Artist() {
    }

    public Artist(Long id, @NotBlank String name, String unaccentName, Date birthDate, String avatarUrl, String avatarBlobString, String biography, Collection<Song> songs, Collection<Album> albums) {
        this.id = id;
        this.name = name;
        this.unaccentName = unaccentName;
        this.birthDate = birthDate;
        this.avatarUrl = avatarUrl;
        this.avatarBlobString = avatarBlobString;
        this.biography = biography;
        this.songs = songs;
        this.albums = albums;
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

    public String getUnaccentName() {
        return unaccentName;
    }

    public void setUnaccentName(String unaccentName) {
        this.unaccentName = unaccentName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getAvatarBlobString() {
        return avatarBlobString;
    }

    public void setAvatarBlobString(String avatarBlobString) {
        this.avatarBlobString = avatarBlobString;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public Collection<Song> getSongs() {
        return songs;
    }

    public void setSongs(Collection<Song> songs) {
        this.songs = songs;
    }

    public Collection<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Collection<Album> albums) {
        this.albums = albums;
    }
}
