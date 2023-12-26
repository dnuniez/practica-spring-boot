package com.inditex;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Photo {
    @Id
    @Column(columnDefinition = "BIGINT")
    private Long id;
    @Column(columnDefinition = "BIGINT")
    private Long albumId;
    @Column(columnDefinition = "VARCHAR(255)")
    private String title;
    @Column(columnDefinition = "VARCHAR(255)")
    private String url;
    @Column(columnDefinition = "VARCHAR(255)")
    private String thumbnailUrl;    
    
	public void setId(Long id) {
		this.id = id;
	}
	public Long getAlbumId() {
		return albumId;
	}
	public void setAlbumId(Long albumId) {
		this.albumId = albumId;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}
	public Long getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public String getUrl() {
		return url;
	}
	public String getThumbnailUrl() {
		return thumbnailUrl;
	}
	public Photo(long id, long albumId, String title) {
		setId(id);
		setAlbumId(albumId);
		setTitle(title);
	}
}