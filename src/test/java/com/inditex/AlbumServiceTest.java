package com.inditex;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.junit.Assert.assertEquals;

@SpringBootTest
@AutoConfigureWebClient
class AlbumServiceTest {

    @Autowired
    private AlbumService albumService;

    @MockBean
    private AlbumRepository albumRepository;

    @MockBean
    private RestTemplate restTemplate;

    @Test
    void testEnrichAndSaveAlbums() {
        List<Album> mockAlbums = getMockAlbums();
        List<Photo> mockPhotos = getMockPhotos();
        when(restTemplate.exchange(
                eq("https://jsonplaceholder.typicode.com/albums"),
                eq(HttpMethod.GET),isNull(),any(ParameterizedTypeReference.class))
        ).thenReturn(new ResponseEntity<>(mockAlbums, HttpStatus.OK));
        when(restTemplate.exchange(
                eq("https://jsonplaceholder.typicode.com/photos"),
                eq(HttpMethod.GET),isNull(),any(ParameterizedTypeReference.class))
        ).thenReturn(new ResponseEntity<>(mockPhotos, HttpStatus.OK));

        albumService.enrichAndSaveAlbums();

        verify(albumRepository, times(1)).saveAll(anyList());
    }
    
    @Test
    void testEnrichAndGetAlbums() {
        List<Album> mockAlbums = getMockAlbums();
        List<Photo> mockPhotos = getMockPhotos();
        when(restTemplate.exchange(
                eq("https://jsonplaceholder.typicode.com/albums"),
                eq(HttpMethod.GET),isNull(),any(ParameterizedTypeReference.class))
        ).thenReturn(new ResponseEntity<>(mockAlbums, HttpStatus.OK));
        when(restTemplate.exchange(
                eq("https://jsonplaceholder.typicode.com/photos"),
                eq(HttpMethod.GET),isNull(),any(ParameterizedTypeReference.class))
        ).thenReturn(new ResponseEntity<>(mockPhotos, HttpStatus.OK));

        List<Album> resultAlbums=albumService.enrichAndGetAlbums();
        List<Album> expectedAlbums = getExpectedAlbums();
  
        for (int i=0; i<resultAlbums.size(); i++) {
        	assertEquals(resultAlbums.get(i).getPhotos().size(), expectedAlbums.get(i).getPhotos().size());
        }
    }
    
    @Test
    void testGetAlbums() {
        albumService.getAlbums();
    }
    
    private List<Album> getMockAlbums() {
    	List<Album> mockAlbums = new ArrayList<>();
        mockAlbums.add(new Album(1L, 1L, "album1User1"));
        mockAlbums.add(new Album(2L, 1L, "album2User1"));
        mockAlbums.add(new Album(3L, 2L, "album3User2"));
        
        return mockAlbums;
    }
    
    private List<Photo> getMockPhotos() {
    	List<Photo> mockPhotos = new ArrayList<>();
    	mockPhotos.add(new Photo(1L, 1L, "foto1"));
    	mockPhotos.add(new Photo(2L, 1L, "foto2"));
    	mockPhotos.add(new Photo(3L, 2L, "foto3"));
    	mockPhotos.add(new Photo(4L, 3L, "foto4"));
    	mockPhotos.add(new Photo(5L, 3L, "foto5"));
    	      
        return mockPhotos;
    }
    
    private List<Album> getExpectedAlbums() {
		List<Album> expectedAlbums = new ArrayList<>();
        Album album1=new Album(1L, 1L, "album1User1");
        List<Photo> photosAlbum1=new ArrayList<>();
        photosAlbum1.add(new Photo(1L, 1L, "foto1"));
        photosAlbum1.add(new Photo(2L, 1L, "foto2"));
        album1.setPhotos(photosAlbum1);
        Album album2=new Album(2L, 1L, "album2User1");
        List<Photo> photosAlbum2=new ArrayList<>();
        photosAlbum2.add(new Photo(3L, 2L, "foto3"));
        album2.setPhotos(photosAlbum2);
        Album album3=new Album(3L, 2L, "album3User2");
        List<Photo> photosAlbum3=new ArrayList<>();
        photosAlbum3.add(new Photo(4L, 3L, "foto4"));
        photosAlbum3.add(new Photo(5L, 3L, "foto5"));
        album3.setPhotos(photosAlbum3);
        expectedAlbums.add(album1);
        expectedAlbums.add(album2);
        expectedAlbums.add(album3);
		return expectedAlbums;
	}
}