package com.bestdoc.musicplayer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author nandishamm
 */
@Controller
public class MusicPlayerController {
    @GetMapping({"/", "/index"})
    public String index() {
        return "index";
    }
}

