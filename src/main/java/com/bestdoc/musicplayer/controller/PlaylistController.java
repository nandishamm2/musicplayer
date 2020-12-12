package com.bestdoc.musicplayer.controller;

import com.bestdoc.musicplayer.model.Playlist;
import com.bestdoc.musicplayer.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author nandishamm
 */
@RestController
@RequestMapping("/playlist")
public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;

    @PostMapping("/add")
    public Map<String, Playlist> addPlayList( @Valid @RequestBody Playlist playlist) {
        Map<String, Playlist> response = new HashMap<>();
        response.put("response", playlistService.addPlaylist(playlist));
        return response;
    }

    @GetMapping("/")
    public Map<String, List<Playlist>> getAll(){
        Map<String, List<Playlist>> response = new HashMap<>();
        response.put("response", playlistService.getAll());
        return response;
    }

    @GetMapping("/{id}")
    public Map<String, Playlist> getById(@RequestParam Long id) throws Exception {
        Map<String, Playlist> response = new HashMap<>();
        response.put("response", playlistService.getById(id));
        return response;
    }

    @DeleteMapping("/delete/{id}")
    public Map<String, Boolean> deleteById(@PathVariable Long id) throws Exception {
        Map<String, Boolean> response = new HashMap<>();
        response.put("response", playlistService.deletePlayList(id));
        return response;
    }
}
