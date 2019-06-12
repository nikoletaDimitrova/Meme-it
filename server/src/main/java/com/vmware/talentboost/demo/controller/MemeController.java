package com.vmware.talentboost.demo.controller;

import com.vmware.talentboost.demo.entity.Meme;
import com.vmware.talentboost.demo.service.DataService;
import com.vmware.talentboost.demo.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@CrossOrigin()
@RestController
public class MemeController {

    @Autowired
    StorageService storageService;

    @Autowired
    DataService dataService;

    @PostMapping(value = "/createMeme")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("description") String description) {
        try {
            storageService.storeImage(file);
            String url = "/images/" + file.getOriginalFilename();
            Meme newMeme = new Meme(url, description);
            dataService.save(newMeme);

            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }

    @GetMapping("/meme")
    public ResponseEntity<List<Meme>> getListFiles() throws InterruptedException {
        List<Meme> allMemes = dataService.findAll();
        return ResponseEntity.ok().body(allMemes);
    }

    @DeleteMapping("/deleteFile/{id}")
    public ResponseEntity<Void> deleteMeme(@PathVariable int id) throws IOException {
        Meme searchedMeme = dataService.findById(id);
        String imageUrl = searchedMeme.getPictureUrl();
        if (dataService.findByUrl(imageUrl).size() == 1) {  //checking if another meme doesnt use this image
            storageService.deleteMeme(searchedMeme.getPictureUrl());
        }
        dataService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @CrossOrigin
    @PutMapping("/editMeme/{id}")
    public ResponseEntity<Meme> editMeme(@PathVariable int id, @RequestParam(value = "file", required = false) MultipartFile file, @RequestParam("description") String description) {
        try {
            Meme currentMeme = dataService.findById(id);
            if (currentMeme == null) {
                //greshka
            }
            if (file != null) {
                String currentImageUrl = currentMeme.getPictureUrl();
                if (dataService.findByUrl((currentImageUrl)).size() == 1) {
                    storageService.deleteMeme(currentImageUrl);
                }
                storageService.storeImage(file);
                String url = "/images/" + file.getOriginalFilename();
                currentMeme.setPictureUrl(url);
            }
            currentMeme.setDescription(description);
            Meme editedMeme = dataService.save(currentMeme);

            return new ResponseEntity<Meme>(editedMeme, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }

    @GetMapping("/find/{id}")
    public Meme findById(@PathVariable int id) {
        return dataService.findById(id);
    }

    @GetMapping("/findByDesc")
    public ResponseEntity<List<Meme>> findByDescription(@RequestParam("searchDescription") String searchDescription) {
        List<Meme> searchedMemes = dataService.findByDescriptionIgnoreCaseContaining(searchDescription);
        return ResponseEntity.ok().body(searchedMemes);
    }

}