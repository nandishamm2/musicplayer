package com.bestdoc.musicplayer.service;

import com.bestdoc.musicplayer.model.Song;

import java.util.List;

/**
 * @author nandishamm
 */
public interface SongsService {
    Song addSong(Song song, Long playlistId) throws Exception;

    List<Song> getAll();

    Song getById(Long id) throws Exception;

    boolean delete(Long id) throws Exception;
}
