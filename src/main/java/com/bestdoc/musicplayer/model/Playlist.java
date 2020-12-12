package com.bestdoc.musicplayer.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author nandishamm
 */

@Entity
@Table(name = "playlist")
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long playlistId;
    @NotBlank(message = "Name is mandatory")
    private String name;

    public Playlist() {
    }

    public Playlist(String name) {
        this.name = name;
    }

    @JsonManagedReference
    @OneToMany(fetch=FetchType.LAZY,  mappedBy="playlist", cascade = CascadeType.ALL)
    private List<Song> songList;

    public long getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(long playlistId) {
        this.playlistId = playlistId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Song> getSongList() {
        return songList;
    }

    public void setSongList(List<Song> songList) {
        this.songList = songList;
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "playlistId=" + playlistId +
                ", name='" + name + '\'' +
                '}';
    }
}
