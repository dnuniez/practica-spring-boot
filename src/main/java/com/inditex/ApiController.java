package com.inditex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {
	
	@Autowired
    private AlbumService albumService;

    @GetMapping("/enrichAndSaveAlbums")
    public void enrichAndSaveAlbums() {
        albumService.enrichAndSaveAlbums();
    }

    @GetMapping("/enrichAndGetAlbums")
    public List<Album> enrichAndGetAlbums() {
        return albumService.enrichAndGetAlbums();
    }

    @GetMapping("/getAlbums")
    public List<Album> getAlbums() {
        return albumService.getAlbums();
    }
}