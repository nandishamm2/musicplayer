package com.bestdoc.musicplayer.repository;

import com.bestdoc.musicplayer.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author nandishamm
 */
@Repository("songsRepository")
public interface SongsRepository extends JpaRepository<Song, Long> {
}
