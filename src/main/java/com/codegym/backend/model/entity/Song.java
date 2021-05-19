package com.codegym.backend.model.entity;

import com.codegym.backend.model.util.CustomUserJsonSerializer;
import com.codegym.backend.model.util.MediaObject;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.Date;

@Entity
@JsonIgnoreProperties(value = {"comments", "liked", "albums", "tags", "genres", "users", "playlists", "country", "theme", "uploader", "blobString"}, allowGetters = true, ignoreUnknown = true)
public class Song extends MediaObject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String title;

    @JsonIgnore
    private String unaccentTitle;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date releaseDate;

    @Length(max = 866)
    private String url;

    @OneToMany(mappedBy = "song", fetch = FetchType.LAZY)
    private Collection<Comment> comments;

    @ColumnDefault("0")
    private Long displayRating = 0L;

    @ColumnDefault("0")
    private Long listeningFrequency = 0l;

    private Boolean liked;

    @Column(columnDefinition = "TEXT")
    private String lyric;

    private String blobString;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "song_artist",
            joinColumns = @JoinColumn(
                    name = "song_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "artist_id", referencedColumnName = "id"))
    @Fetch(value = FetchMode.SUBSELECT)
    private Collection<Artist> artists;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "songs", cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    private Collection<Album> albums;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "song_tag",
            joinColumns = @JoinColumn(
                    name = "song_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "tag_id", referencedColumnName = "id"))
    @Fetch(value = FetchMode.SUBSELECT)
    private Collection<Tag> tags;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "song_genre",
            joinColumns = @JoinColumn(
                    name = "song_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "genre_id", referencedColumnName = "id"))
    @Fetch(value = FetchMode.SUBSELECT)
    private Collection<Genre> genres;

    @JsonBackReference(value = "user-favoriteSongs")
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "favoriteSongs")
    private Collection<User> users;

    @JsonSerialize(using = CustomUserJsonSerializer.class)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User uploader;

    @JsonBackReference(value = "playlist-song")
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "songs")
    @Fetch(value = FetchMode.SUBSELECT)
    private Collection<Playlist> playlists;

    @JsonBackReference("song-country")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "country_id")
    private Country country;

    @JsonBackReference("song-theme")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "theme_id")
    private Theme theme;

    public Song(@NotBlank String title, Date releaseDate) {
        this.title = title;
        this.releaseDate = releaseDate;
    }

    public Song() {
    }

    public Song(Long id, @NotBlank String title, String unaccentTitle, Date releaseDate, @Length(max = 866) String url, Collection<Comment> comments, Long displayRating, Long listeningFrequency, Boolean liked, String lyric, String blobString, Collection<Artist> artists, Collection<Album> albums, Collection<Tag> tags, Collection<Genre> genres, Collection<User> users, User uploader, Collection<Playlist> playlists, Country country, Theme theme) {
        this.id = id;
        this.title = title;
        this.unaccentTitle = unaccentTitle;
        this.releaseDate = releaseDate;
        this.url = url;
        this.comments = comments;
        this.displayRating = displayRating;
        this.listeningFrequency = listeningFrequency;
        this.liked = liked;
        this.lyric = lyric;
        this.blobString = blobString;
        this.artists = artists;
        this.albums = albums;
        this.tags = tags;
        this.genres = genres;
        this.users = users;
        this.uploader = uploader;
        this.playlists = playlists;
        this.country = country;
        this.theme = theme;
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

    public String getUnaccentTitle() {
        return unaccentTitle;
    }

    public void setUnaccentTitle(String unaccentTitle) {
        this.unaccentTitle = unaccentTitle;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Collection<Comment> getComments() {
        return comments;
    }

    public void setComments(Collection<Comment> comments) {
        this.comments = comments;
    }

    public Long getDisplayRating() {
        return displayRating;
    }

    public void setDisplayRating(Long displayRating) {
        this.displayRating = displayRating;
    }

    public Long getListeningFrequency() {
        return listeningFrequency;
    }

    public void setListeningFrequency(Long listeningFrequency) {
        this.listeningFrequency = listeningFrequency;
    }

    public Boolean getLiked() {
        return liked;
    }

    public void setLiked(Boolean liked) {
        this.liked = liked;
    }

    public String getLyric() {
        return lyric;
    }

    public void setLyric(String lyric) {
        this.lyric = lyric;
    }

    public String getBlobString() {
        return blobString;
    }

    public void setBlobString(String blobString) {
        this.blobString = blobString;
    }

    public Collection<Artist> getArtists() {
        return artists;
    }

    public void setArtists(Collection<Artist> artists) {
        this.artists = artists;
    }

    public Collection<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Collection<Album> albums) {
        this.albums = albums;
    }

    public Collection<Tag> getTags() {
        return tags;
    }

    public void setTags(Collection<Tag> tags) {
        this.tags = tags;
    }

    public Collection<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Collection<Genre> genres) {
        this.genres = genres;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    public User getUploader() {
        return uploader;
    }

    public void setUploader(User uploader) {
        this.uploader = uploader;
    }

    public Collection<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(Collection<Playlist> playlists) {
        this.playlists = playlists;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }
}
