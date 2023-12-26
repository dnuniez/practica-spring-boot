package com.inditex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AlbumService {

	@Autowired
    private AlbumRepository albumRepository;
	@Autowired
	private RestTemplate restTemplate;
    private static final String ENDPOINT_ALBUMS = "https://jsonplaceholder.typicode.com/albums";
    private static final String ENDPOINT_PHOTOS = "https://jsonplaceholder.typicode.com/photos";
    
  
    /**
     * enrichs albums with photos and saves the resulting list in H2 database
     */
    @Transactional
    public void enrichAndSaveAlbums() {      
    	 try {
    	        List<Album> albums = enrichAlbums();
    	        if (null!=albums && !albums.isEmpty()) 
    	        	albumRepository.saveAll(albums);
    	    } catch (Exception e) {
    	    	throw new AlbumServiceException("Error al enriquecer y guardar álbumes: " + e.getMessage(), e);
    	    }
    }

    /**
     * enrichs albums with photos and returns the resulting list
     */
    public List<Album> enrichAndGetAlbums() {
        return enrichAlbums();
    }

    /**
     * returns the enriched album list from H2 database
     */
    public List<Album> getAlbums() {
        return albumRepository.findAll();
    }
    
    /**
     * returns an enriched list of albums with their photos previously read from two endpoints
     */
    private List<Album> enrichAlbums() {
    	ResponseEntity<List<Album>> albumsResponse = restTemplate.exchange(
    	            ENDPOINT_ALBUMS,HttpMethod.GET,null,new ParameterizedTypeReference<List<Album>>() {}
    	    );
    	List<Album> albums = albumsResponse.getBody();
    	ResponseEntity<List<Photo>> responseEntity = restTemplate.exchange(
    	        ENDPOINT_PHOTOS,HttpMethod.GET,null,new ParameterizedTypeReference<List<Photo>>() {}
    	);
    	List<Photo> photos = responseEntity.getBody();  
    	for (Album album : albums) {
    		album.setPhotos(getPhotosForAlbum(album,photos));
    	}
    	return albums;
    }

    /**
     * returns a list of photos for a given album
     */
    private List<Photo> getPhotosForAlbum(Album album,List<Photo> photos) {
    	List<Photo> albumPhotos=new ArrayList<>();	  	
    	for (Photo photo : photos) {
    		if (album.getId().equals(photo.getAlbumId())) {
    			albumPhotos.add(photo);
    		}
    	}
    	return albumPhotos;
    }
    
}
