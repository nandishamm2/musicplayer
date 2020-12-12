package com.bestdoc.musicplayer.service;

import com.bestdoc.musicplayer.model.Playlist;
import com.bestdoc.musicplayer.model.Song;
import com.bestdoc.musicplayer.repository.PlaylistRepository;
import com.bestdoc.musicplayer.repository.SongsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author nandishamm
 */
@Service
public class SongsServiceImpl implements SongsService {
    @Autowired
    private SongsRepository songsRepository;
    @Autowired
    private PlaylistService playlistService;

    Logger logger= LoggerFactory.getLogger(SongsServiceImpl.class);

    /**
     * add new song to playlist id
     * @param song
     * @param playlistId
     * @return
     * @throws Exception
     */
    @Override
    public Song addSong(Song song, Long playlistId) throws Exception {
        Playlist playlist = playlistService.getById(playlistId);
        song.setPlaylist(playlist);
        song =  songsRepository.save(song);
        logger.info("New song added to playlist");
        return song;
    }

    /**
     * get all songs
     * @return
     */

    @Override
    public List<Song> getAll() {
        return songsRepository.findAll();
    }

    /**
     * get song by song id
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Song getById(Long id) throws Exception {
        Optional<Song> playlistOptional = songsRepository.findById(id);
        if (playlistOptional.isPresent()) {
            return playlistOptional.get();
        }
        logger.warn("song not found");
        throw new Exception("song not found");
    }

    /**
     * delete song by song id
     * @param id
     * @return
     * @throws Exception
     */

    @Override
    public boolean delete(Long id) throws Exception {
        getById(id);
        songsRepository.deleteById(id);
        logger.info("song deleted of id :"+ id);
        return true;
    }
}
