package com.codegym.backend.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_favorite_songs")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "song_id")
    private Long songId;

    @Column(name = "user_id")
    private Long userId;

    public Like() {
    }

    public Like(Long songId, Long userId) {
        this.songId = songId;
        this.userId = userId;
    }

    public Like(Long id, Long songId, Long userId) {
        this.id = id;
        this.songId = songId;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSongId() {
        return songId;
    }

    public void setSongId(Long songId) {
        this.songId = songId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
