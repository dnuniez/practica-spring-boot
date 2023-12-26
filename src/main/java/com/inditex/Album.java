package com.inditex;

import java.util.List;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Album {
    @Id
    private Long id;
    private Long userId;
    private String title;
    @JdbcTypeCode(SqlTypes.JSON)
    private List<Photo> photos;
    
	public Long getId() {
		return id;
	}
	public Long getUserId() {
		return userId;
	}
	public String getTitle() {
		return title;
	}
	public List<Photo> getPhotos() {
		return photos;
	}
	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}
	public Album(long id, long userId, String title) {
		this.id=id;
		this.userId=userId;
		this.title=title;
	}
	public Album() {
    }
	  
}