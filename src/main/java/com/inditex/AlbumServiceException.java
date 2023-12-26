package com.inditex;

public class AlbumServiceException extends RuntimeException {

	private static final long serialVersionUID = 8049387348865658700L;

	public AlbumServiceException(String message) {
        super(message);
    }

    public AlbumServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}