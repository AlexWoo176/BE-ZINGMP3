package com.codegym.backend.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;

@Entity
@JsonIgnoreProperties(value = {"songs", "albums"}, allowGetters = true, ignoreUnknown = true)
public class Genre {
    @Id
    @GeneratedValue
    private Integer id;

    @NotBlank
    @Column(unique = true, nullable = false)
    private String name;

    @JsonBackReference("song-genre")
    @ManyToMany(mappedBy = "genres", fetch = FetchType.LAZY)
    private Collection<Song> songs;

    @JsonBackReference("album-genre")
    @ManyToMany(mappedBy = "genres", fetch = FetchType.LAZY)
    private Collection<Album> albums;

    public Genre(@NotBlank String name) {
        this.name = name;
    }

    public Genre(Integer id, @NotBlank String name, Collection<Song> songs, Collection<Album> albums) {
        this.id = id;
        this.name = name;
        this.songs = songs;
        this.albums = albums;
    }

    public Genre() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
