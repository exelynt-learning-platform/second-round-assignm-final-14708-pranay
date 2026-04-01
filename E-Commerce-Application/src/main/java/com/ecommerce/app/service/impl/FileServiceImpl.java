package com.ecommerce.app.service.impl;

import com.ecommerce.app.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {

        // File path of current file original file
        String originalFilename = file.getOriginalFilename();

        // Generate Random string
        String randomId = UUID.randomUUID().toString();

        // create new file path
        String fileName = randomId.concat(originalFilename.substring(originalFilename.lastIndexOf(".")));
        String filePath = path + File.separator + fileName;

        // check if path exist and create
        File folder = new File(path);

        if (!folder.exists()) {
            folder.mkdir();
        }

        // Upload to server
        Files.copy(file.getInputStream(), Paths.get(filePath));

        // return filename
        return fileName;
    }

}
