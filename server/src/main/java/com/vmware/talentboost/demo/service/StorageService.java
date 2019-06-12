package com.vmware.talentboost.demo.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class StorageService {

    Logger log = LoggerFactory.getLogger(this.getClass().getName());
    private String resource = "resources/images";

    public void storeImage(MultipartFile file) throws Exception {
        byte[] bytes = file.getBytes();
        String fileLocation = new File(resource).getAbsolutePath() + "\\" + file.getOriginalFilename();
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileLocation)){
           fileOutputStream.write(bytes);
       }catch (IOException e){
           e.printStackTrace();
       }
    }

    public void deleteMeme(String filename) throws IOException {
        String filepath = "resources"+filename;
        Path currentPath = Paths.get(filepath);
            Files.delete(currentPath);
    }
}

