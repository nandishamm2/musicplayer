package com.bestdoc.musicplayer.service;

import com.bestdoc.musicplayer.model.Playlist;

import java.util.List;

/**
 * @author nandishamm
 */
public interface PlaylistService {
    Playlist addPlaylist(Playlist playlist);

    List<Playlist> getAll();

    Playlist getById(Long id) throws Exception;

    boolean deletePlayList(Long id) throws Exception;
}
