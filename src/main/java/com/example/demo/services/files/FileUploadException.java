package com.example.demo.services.files;

public class FileUploadException extends RuntimeException {
    public FileUploadException() {

    }

    public FileUploadException(String message) {
        super(message);
    }
}
