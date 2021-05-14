package com.example.demo.services.files;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    void save(MultipartFile file) throws FileUploadException;

    Resource load(String filename);
}
