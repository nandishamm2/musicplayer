package com.bestdoc.musicplayer.service;

import com.bestdoc.musicplayer.model.Playlist;
import com.bestdoc.musicplayer.repository.PlaylistRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author nandishamm
 */
@Service
public class PlaylistServiceImpl implements PlaylistService {

    Logger logger = LoggerFactory.getLogger(PlaylistServiceImpl.class);

    @Autowired
    private PlaylistRepository playlistRepository;

    /**
     * add new playlist
     * @param playlist
     * @return
     */
    @Override
    public Playlist addPlaylist(Playlist playlist) {
        playlist= playlistRepository.save(playlist);
        logger.info("New playlist added "+ playlist.toString());
        return playlist;
    }

    /**
     * get all playlist
     * @return
     */

    @Override
    public List<Playlist> getAll() {
        return playlistRepository.findAll();
    }

    /**
     * get playlist by id
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Playlist getById(Long id) throws Exception {
        Optional<Playlist> playlistOptional = playlistRepository.findById(id);
        if (playlistOptional.isPresent()) {
            return playlistOptional.get();
        }
        logger.warn("playlist not found  : " +id);
        throw new Exception("playlist not found");
    }

    /**
     * delete playlist by playlist id
     * @param id
     * @return
     * @throws Exception
     */

    @Override
    public boolean deletePlayList(Long id) throws Exception {
        getById(id);
        playlistRepository.deleteById(id);
        logger.info("deleted playlist of id : " +id);
        return true;
    }
}
