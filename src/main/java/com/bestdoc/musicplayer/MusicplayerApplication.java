package com.bestdoc.musicplayer;

import com.bestdoc.musicplayer.model.Playlist;
import com.bestdoc.musicplayer.model.Song;
import com.bestdoc.musicplayer.service.PlaylistService;
import com.bestdoc.musicplayer.service.SongsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class MusicplayerApplication implements CommandLineRunner {

	@Autowired
	private SongsService songsService;
	@Autowired
	private PlaylistService playlistService;
	public static void main(String[] args) {
		SpringApplication.run(MusicplayerApplication.class, args);
	}

	/**
	 * default data insert
	 * @param args
	 * @throws Exception
	 */
	@Override
	public void run(String... args) throws Exception {
		playlistService.addPlaylist(new Playlist("rock"));
		playlistService.addPlaylist(new Playlist("pop"));
		playlistService.addPlaylist(new Playlist("classic"));

		songsService.addSong(new Song("How Long","Ace"), 1l);
		songsService.addSong(new Song("You Light Up My Life","Debby Boone"), 1l);

		songsService.addSong(new Song("Dancing Queen","ABBA"), 2l);
		songsService.addSong(new Song("Beautiful","Christina Aguilera"), 2l);

		songsService.addSong(new Song("Aaj Phir Jeene Ki Tamanna","Lata Mangeshkar"), 3l);
		songsService.addSong(new Song("Chaudhvin Ka Chand","Mohammad Rafi"), 3l);

	}
}
