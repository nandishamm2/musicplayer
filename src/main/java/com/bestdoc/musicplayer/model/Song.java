package com.bestdoc.musicplayer.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * @author nandishamm
 */
@Entity
@Table(name = "song")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotBlank(message = "Singer is mandatory")
    private String singer;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "playlistId", nullable = false)
    private Playlist playlist;

    public Song() {
    }


    public long getId() {
        return id;
    }

    public Song(String name, String singer) {
        this.name = name;
        this.singer = singer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public Playlist getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }

    @Override
    public String toString() {
        return "Songs{" +
                "name='" + name + '\'' +
                ", singer='" + singer + '\'' +
                '}';
    }
}
