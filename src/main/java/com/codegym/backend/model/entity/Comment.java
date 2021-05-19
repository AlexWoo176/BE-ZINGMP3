package com.codegym.backend.model.entity;

import com.codegym.backend.model.util.CustomUserJsonSerializer;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@JsonIgnoreProperties(value = {"localDateTime", "song", "user"}, allowGetters = true, ignoreUnknown = true)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Length(max = 500)
    @NotBlank
    @Column(columnDefinition = "TEXT")
    private String content;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime localDateTime;

    @JsonBackReference(value = "song-comment")
    @ManyToOne(fetch = FetchType.LAZY)
    private Song song;

    @JsonSerialize(using = CustomUserJsonSerializer.class)
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    public Comment() {
    }

    public Comment(Long id, @Length(max = 500) @NotBlank String content, LocalDateTime localDateTime, Song song, User user) {
        this.id = id;
        this.content = content;
        this.localDateTime = localDateTime;
        this.song = song;
        this.user = user;
    }

    public Comment(@Length(max = 500) @NotBlank String content, LocalDateTime localDateTime, Song song, User user) {
        this.content = content;
        this.localDateTime = localDateTime;
        this.song = song;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
