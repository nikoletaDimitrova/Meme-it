package com.vmware.talentboost.demo.controller;

import com.vmware.talentboost.demo.entity.Domain;
import com.vmware.talentboost.demo.entity.Meme;
import com.vmware.talentboost.demo.service.DomainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class DomainController {
    Logger log = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private DomainService domainService;

    @CrossOrigin
    @GetMapping("/allDomains")
    public Domain[] getDomains(){
        return domainService.getDomains();
    }

    @CrossOrigin
    @GetMapping("/otherMemes")
    public ResponseEntity<Meme[]> getMemes(@RequestParam(value = "domain") String domain){
        Meme[] memeArray = domainService.getMemes(domain);
        return new ResponseEntity<Meme[]>(memeArray, HttpStatus.OK);
    }
    @CrossOrigin
    @GetMapping("/search/{searchMeme}")
    public List<Meme> getFilteredMemes(@PathVariable String searchMeme){
        return domainService.getFilteredMemes(searchMeme);
    }


}
