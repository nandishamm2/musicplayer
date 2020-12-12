package com.bestdoc.musicplayer.controller;

import com.bestdoc.musicplayer.model.Song;
import com.bestdoc.musicplayer.service.SongsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author nandishamm
 */
@RestController
@RequestMapping("/songs")
public class SongsController {

    @Autowired
    private SongsService songsService;

    @PostMapping("/add/{pid}")
    public Map<String, Song> addPlayList(@Valid @RequestBody Song song, @PathVariable Long pid ) throws Exception {
        Map<String, Song> response = new HashMap<>();
        if(Objects.isNull(pid)){
            throw new Exception("playlist Id is required attribute");
        }
        response.put("response", songsService.addSong(song, pid));
        return response;
    }

    @GetMapping("/")
    public Map<String, List<Song>> getAll(){
        Map<String, List<Song>> response = new HashMap<>();
        response.put("response", songsService.getAll());
        return response;
    }

    @GetMapping("/{id}")
    public Map<String, Song> getById(@RequestParam Long id) throws Exception {
        Map<String, Song> response = new HashMap<>();
        response.put("response", songsService.getById(id));
        return response;
    }

    @DeleteMapping("/delete/{id}")
    public Map<String, Boolean> deleteById(@PathVariable Long id) throws Exception {
        Map<String, Boolean> response = new HashMap<>();
        response.put("response", songsService.delete(id));
        return response;
    }

}
