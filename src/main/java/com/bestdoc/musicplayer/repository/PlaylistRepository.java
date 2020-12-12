package com.bestdoc.musicplayer.repository;

import com.bestdoc.musicplayer.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author nandishamm
 */
@Repository("playlistRepository")
public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
}
