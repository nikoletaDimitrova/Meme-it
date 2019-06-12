package com.vmware.talentboost.demo.service;

import com.vmware.talentboost.demo.entity.Meme;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface DataService {

    List<Meme> findAll();
    Meme findById(int id);
    Meme save (Meme meme);
    void deleteById(int id);
    List<Meme> findByUrl(String url);
    List<Meme> findByDatePosted(Date datePosted);
    List<Meme> findByDescriptionIgnoreCaseContaining(String description);
}
